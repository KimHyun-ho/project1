package kr.human.anihospital.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.human.anihospital.service.LoginService;
import kr.human.anihospital.service.ProtectorPageService;
import kr.human.anihospital.vo.AnimalHospitalVO;
import kr.human.anihospital.vo.KakaoTokenVO;
import kr.human.anihospital.vo.KakaoUserInfoVO;
import kr.human.anihospital.vo.LoginAPIVO;
import kr.human.anihospital.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginConrtoller {

	@Autowired
	LoginService loginService;

	@Autowired
	ProtectorPageService protectorPageService;

	LoginAPIVO loginAPIVO = new LoginAPIVO();

	// ##########################
	// ###로그인 페이지 (공통)###
	// ##########################
	@GetMapping("/signin")
	public String signin(Model model) throws UnsupportedEncodingException {

		// 네이버 로그인 API URL
		String redirectURI = URLEncoder.encode(loginAPIVO.getNaverRedirectURI(), "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String naverApiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code" + "&client_id="
				+ loginAPIVO.getNaverClientId() + "&redirect_uri=" + redirectURI + "&state=" + state;
		model.addAttribute("naverApiURL", naverApiURL);
		log.info(naverApiURL);
		// session.setAttribute("state", state);

		// 카카오 로그인 API URL
		String kakaoApiURL = "https://kauth.kakao.com/oauth/authorize?client_id=" + loginAPIVO.getKakaoClientId()
				+ "&redirect_uri=" + loginAPIVO.getKakaoRedirectUri() + "&response_type=code";
		model.addAttribute("kakaoApiURL", kakaoApiURL);
		log.info(kakaoApiURL);

		return "signin";
	}

	// ######################################################################
	// ############################## NAVER #################################
	// ######################################################################
	// 네이버 로그인 후 정보를 받아 아이디 존재 여부를 판단해 넘겨줄 컨트롤러
	@GetMapping("/naverCallback")
	public String callback(HttpServletRequest request, Model model, HttpSession session)
			throws UnsupportedEncodingException {
		String code = request.getParameter("code");
		log.info("code : {}", code);
		String state = request.getParameter("state");
		log.info("state : {}", state);
		session.setAttribute("state", state);
		log.info("sessionTest : {}", session.getAttribute("state"));
		String redirectURI = URLEncoder.encode(loginAPIVO.getNaverRedirectURI(), "UTF-8");
		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code" + "&client_id="
				+ loginAPIVO.getNaverClientId() + "&client_secret=" + loginAPIVO.getNaverClientSecret()
				+ "&redirect_uri=" + redirectURI + "&code=" + code + "&state=" + state;
		String accessToken = "";
		String refreshToken = "";
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuilder res = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;

				accessToken = (String) jsonObj.get("access_token");
				session.setAttribute("naverAccessToken", accessToken);
				refreshToken = (String) jsonObj.get("refresh_token");

			}

		} catch (Exception e) {
			// Exception 로깅

		}
		if (accessToken != null) { // access_token을 잘 받아왔다면
			try {
				String apiurl = "https://openapi.naver.com/v1/nid/me";
				String header = "Bearer " + accessToken;
				URL url = new URL(apiurl);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Authorization", header);
				int responseCode = con.getResponseCode();
				BufferedReader br;

				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // 에러 발생
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String inputLine;
				StringBuffer res = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					res.append(inputLine);
				}
				br.close();
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject resObj = (JSONObject) jsonObj.get("response");

				MemberVO memberVO = new MemberVO();
				String memberEmailId = (String) resObj.get("email");
				session.setAttribute("memberEmailId", memberEmailId);
				memberVO.setMemberEmailId(memberEmailId);
				memberVO.setMemberName((String) resObj.get("name"));
				String birthday = (String) resObj.get("birthday");
				String birthYear = (String) resObj.get("birthyear");
				String birthDate = birthYear + '-' + birthday;
				memberVO.setMemberBirthdate(birthDate);
				memberVO.setMemberGender((String) resObj.get("gender") == "M" ? false : true);
				memberVO.setMemberPhoneNo((String) resObj.get("mobile"));
				log.info("memberVO  :{}", memberVO);
				String naverCode = (String) resObj.get("id");

				int count = 0;
				count = loginService.selectNaverLoginId(memberEmailId);
				MemberVO sessionMemberVO = new MemberVO();
				sessionMemberVO = loginService.selectNaverFindSession(memberEmailId);
				String page = null;
				if (count == 0) {
					model.addAttribute("memberVO", memberVO);
					page = "roleCheck";
				} else {
					// 아이디가 DB에 존재하면 홈으로, session에 seq넣는 작업 필요
					session.setAttribute("seqMember", sessionMemberVO.getSeqMember());
					session.setAttribute("memberRole", sessionMemberVO.isMemberRole());
					log.info("session에 저장된 memberRole : {}", session.getAttribute("memberRole"));
					page = "index";
					if (((Boolean) session.getAttribute("memberRole")).booleanValue() == false) {
						log.info("memberRole : {}", session.getAttribute("memberRole"));
						Map<String, Object> seqDoctorMap = null;
						seqDoctorMap = loginService.selectFindDoctorSeq((int) session.getAttribute("seqMember"));
						// count 가 0 이면 의사role로 가입만 하고 의사 정보를 입력하지 않았다는 뜻이므로
						if (Integer.parseInt(seqDoctorMap.get("count").toString()) == 0) {
							// memberEmailId를 가지고 다시 doctorMemberJoin로 보낸다.
							return "redirect:doctorMemberJoin";
						} else {
							session.setAttribute("seqDoctor", seqDoctorMap.get("seqDoctor"));
							page = "index";
						}
						log.info("seqDoctor : {}", session.getAttribute("seqDoctor"));
					}
				}
				return page;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/";
	}

	// 최초 로그인일때 roleCheck에서 넘어온 정보가 insert될 메서드
	@PostMapping("/naverLoginInsert")
	@ResponseBody
	public String naverLoginInsert(@ModelAttribute MemberVO memberVO, HttpSession session) throws Exception {
		log.info("roleCheck에서 넘어온 insert 정보 : {}", memberVO);
		loginService.insertNaverLogin(memberVO);
		session.setAttribute("seqMember", memberVO.getSeqMember());
		session.setAttribute("memberRole", memberVO.isMemberRole());
		return "성공";
	}

	// 의사 전용 회원가입
	// 로그인시 아이디를 받아와서 아이디로 seq값을 찾아
	// doctor테이블에 seqMember를 insert하기 위함
	// ##############나중에 Post로 다시 돌리기!!!!!!!!!!!!!!!################
	@GetMapping("/doctorMemberJoin")
	public String doctorMemberJoin(HttpSession session, Model model) throws Exception {
		String memberEmailId = session.getAttribute("memberEmailId").toString();
		log.info("session에서 받아온 memberEmailId : {}", memberEmailId);
		model.addAttribute("memberEmailId", memberEmailId);
		// 이메일을 받아서 seqMember를 찾은 후 doctorMemberJoin 페이지로 넘겨주기
		int seqMember = 0;
		seqMember = loginService.selectNaverFindSeq(memberEmailId);
		log.info("seqMember : {}", seqMember);
		model.addAttribute("seqMember", seqMember);

		List<AnimalHospitalVO> animalHospitalList = null;
		animalHospitalList = protectorPageService.selectAllAnimalHospitalVO();
		model.addAttribute("animalHospitalList", animalHospitalList);

		return "doctorMemberJoin";
	}

	// 의사가 네이버 아이디로 최초 로그인시 insert할 의사 정보
	@PostMapping("/doctorMemberJoinOk")
	public String doctorMemberJoinOk(@RequestParam Map<String, Object> naverDoctorJoinMap, MultipartFile file,
			HttpSession session) throws Exception {
		// doctorMemberJoin 에서 넘어온 의사 정보 확인
		log.info("doctorMemberJoin에서 넘어온 의사 정보 :{} {}", naverDoctorJoinMap, file);
		// 의사 정보 insert sql문 실행
		loginService.insertNaverDoctorInfo(naverDoctorJoinMap, file);
		Map<String, Object> seqDoctorMap = null;
		seqDoctorMap = loginService.selectFindDoctorSeq((int) session.getAttribute("seqMember"));
		log.info("selectFindDoctorSeq에서 넘어온 seqDoctor : {}", seqDoctorMap.get("seqDoctor"));
		session.setAttribute("seqDoctor", seqDoctorMap.get("seqDoctor"));
		log.info("session에 저장된 seqDoctor : {}", session.getAttribute("seqDoctor"));
		return "redirect:/";
	}

	// 네이버 로그아웃
	@GetMapping("/naverLogout")
	public String naverLogout(HttpSession session) {
		String accessToken = session.getAttribute("naverAccessToken").toString();
		String apiUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id="
				+ loginAPIVO.getNaverClientId() + "&client_secret=" + loginAPIVO.getNaverClientSecret()
				+ "&access_token=" + accessToken + "&service_provider=NAVER";
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuilder res = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		session.invalidate();

		return "redirect:/";
	}

	// ######################################################################
	// ############################## KAKAO #################################
	// ######################################################################
	// 카카오 로그인 api 주소에 로그인을 요청하고 토큰까지 발급받아 사용자 정보를 불러오는 메소드
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code, Model model, HttpSession session) {

		// POST 방식으로 key : value 형식의 HttpBody를 넘겨 kauth.kakao.com/auth/token에 토큰 요청
		// 요청에는 여러 방식이 있는데 RestTemplate를 사용하자.
		// HttpsURLConnection
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		// 내가 전송할 httpBody 데이터가 다음과 같은 타입이라고 헤더에 전송
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// Map은 Map인데 중복된 key를 허용하여 put이 가능한 Map이다. 왜 이걸로 하는지는 몰?루
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		// 생성한 MultiValueMap에 kauth.kakao.com/auth/token에 요청하며 넘길 httpBody를 저장한다.
		params.add("grant_type", loginAPIVO.getKakaoGrantType());
		params.add("client_id", loginAPIVO.getKakaoClientId());
		params.add("redirect_uri", loginAPIVO.getKakaoRedirectUri());
		params.add("code", code);

		// HttpBody와 HttpHeader로 구성된 HttpEntity 객체를 선언하자.
		// HttpEntity는 RequestEntity or ResponseEntity를 나타낸다.
		// HttpBody는 요청값 응답값 모두 될 수 있다.
		// 하지만 여기서는 요청값을 넘길것이다. 즉 RequestBody를 넘길것이다.
		HttpEntity<MultiValueMap<String, String>> requestForKakaoToken = new HttpEntity<>(params, headers);

		// ResponseEntity 객체는 다음과 같이 선언한다.
		// (요청하는 주소, 요청방식, HttpEntity(HttpBody + HttpHeader), 리턴 타입)
		ResponseEntity<String> responseEntity = restTemplate.exchange(loginAPIVO.getKakaoRequestTokenURI(),
				HttpMethod.POST, requestForKakaoToken, String.class);

		// Json 형식의 데이터를 vo에 담자.
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoTokenVO kakaoTokenVO = null;
		try {
			kakaoTokenVO = objectMapper.readValue(responseEntity.getBody(), KakaoTokenVO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// ##########################################################
		// ############# 카카오 사용자 정보를 받아오자 ##############
		// ##########################################################
		RestTemplate restTemplate2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + kakaoTokenVO.getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> requestForKakaoUserInfo = new HttpEntity<>(params, headers2);
		ResponseEntity<String> responseEntity2 = restTemplate2.exchange(loginAPIVO.getKakaoRequestUserInfoURI(),
				HttpMethod.POST, requestForKakaoUserInfo, String.class);

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoUserInfoVO kakaoUserInfoVO = new KakaoUserInfoVO();
		try {
			kakaoUserInfoVO = objectMapper2.readValue(responseEntity2.getBody(), KakaoUserInfoVO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		session.setAttribute("memberEmailId", kakaoUserInfoVO.getKakaoAccount().getEmail());
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(kakaoUserInfoVO.getKakaoAccount().getBirthday());
		stringBuilder.insert(2, "-");
		session.setAttribute("kakaoBirthday", stringBuilder);
		session.setAttribute("kakaoGender", kakaoUserInfoVO.getKakaoAccount().getGender());
		session.setAttribute("kakaoAccessToken", kakaoTokenVO.getAccessToken());
		return "redirect:/isUser";
	}

	// 회원여부를 판별하여 리다이렉트 하는 메소드
	@GetMapping("/isUser")
	public String isUser(HttpSession session) {
		int count = 0;
		String kakaoEmailId = session.getAttribute("memberEmailId").toString();
		try {
			count = loginService.selectNaverLoginId(kakaoEmailId);
			// 회원이 아니라면 회원 추가정보 입력 페이지로 리다이렉트
			if (count == 0) {
				return "redirect:/addKakaoUser";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 회원이라면 세션에 저장된 이메일을 파라미터로 사용하여
		// seqMember와 memberRole을 가져온다.
		MemberVO sessionMemberVO = new MemberVO();
		try {
			sessionMemberVO = loginService.selectNaverFindSession(kakaoEmailId);
			session.setAttribute("seqMember", sessionMemberVO.getSeqMember());
			session.setAttribute("memberRole", sessionMemberVO.isMemberRole());
			// memberRole이 false라면 -> 의사이므로 seqDoctor를 가져와 세션에 저장해주자.
			if (!sessionMemberVO.isMemberRole()) {
				Map<String, Object> doctorSeqMap = new HashMap<>();
				doctorSeqMap = loginService.selectFindDoctorSeq(sessionMemberVO.getSeqMember());
				session.setAttribute("seqDoctor", (int) doctorSeqMap.get("seqDoctor"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(session.getAttribute("seqMember").toString());
		log.info(session.getAttribute("memberRole").toString());
		return "redirect:/";
	}

	// 카카오 회원을 위한 추가정보 입력 페이지
	@GetMapping("/addKakaoUser")
	public String addKakaoUserInfo() {
		return "addKakaoUserInfo";
	}

	@PostMapping("/kakaoUserInfoAdd")
	public String kakaoUserInfoAdd(@ModelAttribute MemberVO memberVO, @RequestParam String memberBirthYear,
			@RequestParam String memberBirthDay) {
		memberVO.setMemberBirthdate(memberBirthYear + "-" + memberBirthDay);
		return "roleCheck";
	}
}

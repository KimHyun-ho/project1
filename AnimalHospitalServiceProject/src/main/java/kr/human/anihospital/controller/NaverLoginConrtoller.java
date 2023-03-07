package kr.human.anihospital.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kr.human.anihospital.service.LoginService;
import kr.human.anihospital.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NaverLoginConrtoller {
	
	@Autowired
	LoginService loginService;
	
	// 로그인 페이지
	@GetMapping("/signin")
	public String signin(Model model) throws UnsupportedEncodingException {
		String clientId = "jjZSe4Dzr_Kpcinyf4bH"; // 애플리케이션 클라이언트 아이디값;
		String redirectURI = URLEncoder.encode("http://localhost:8080/naverCallback", "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code" + "&client_id=" + clientId
				+ "&redirect_uri=" + redirectURI + "&state=" + state;
		model.addAttribute("apiURL", apiURL);
		log.info(apiURL);
		//session.setAttribute("state", state);
		return "signin";
	}
	
	// 네이버 로그인 후 정보를 받아 아이디 존재 여부를 판단해 넘겨줄 컨트롤러
	@GetMapping("/naverCallback")
	public String callback(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		String clientId = "jjZSe4Dzr_Kpcinyf4bH";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "TNFD4HY8OF";// 애플리케이션 클라이언트 시크릿값";
		String code = request.getParameter("code");
		log.info("code : {}", code);
		String state = request.getParameter("state");
		log.info("state : {}", state);
		// session.setAttribute("state", state);
		String redirectURI = URLEncoder.encode("http://localhost:8080/naverCallback", "UTF-8");
		String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code" + "&client_id=" + clientId
				+ "&client_secret=" + clientSecret + "&redirect_uri=" + redirectURI + "&code=" + code + "&state="
				+ state;
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
				//session.setAttribute("acces_token", accessToken);
				refreshToken = (String) jsonObj.get("refresh_token");
				
			}
			
		} catch (Exception e) {
			// Exception 로깅
			
		}
		if(accessToken != null) { // access_token을 잘 받아왔다면
			try {
				String apiurl = "https://openapi.naver.com/v1/nid/me";
				String header = "Bearer " + accessToken;
				URL url = new URL(apiurl);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Authorization", header);
				int responseCode = con.getResponseCode();
				BufferedReader br;

				if(responseCode==200) { // 정상 호출
				  br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {  // 에러 발생
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
				JSONObject jsonObj = (JSONObject)obj;
				JSONObject resObj = (JSONObject)jsonObj.get("response");
				
				MemberVO memberVO = new MemberVO();
				String memberEmailId = (String)resObj.get("email");
				memberVO.setMemberEmailId(memberEmailId);
				memberVO.setMemberName((String)resObj.get("name"));
				String birthday = (String)resObj.get("birthday");
				String birthYear = (String)resObj.get("birthyear");
				String birthDate = birthYear +'-'+birthday;
				memberVO.setMemberBirthdate(birthDate);
				memberVO.setMemberGender((String)resObj.get("gender")=="M" ? false : true);
				memberVO.setMemberPhoneNo((String)resObj.get("mobile"));
				log.info("memberVO  :{}",memberVO);
				String naverCode = (String)resObj.get("id");
				
				int count = 0;
				count = loginService.selectListLoginId(memberEmailId);
				String page = null;
				if(count == 0) {
					// 아이디가 DB에 존재하지 않으면 roleCheck로
					model.addAttribute("memberVO",memberVO);
					page = "roleCheck";
				}else {
					// 아이디가 DB에 존재하면 홈으로
					page = "index";
				}
				return page;
		    }catch (Exception e1) {
		    	e1.printStackTrace();
		    }
		}
		return "naverCallback";
	}
	
	// 최초 로그인일때 roleCheck에서 넘어온 정보가 insert될 메서드
	@PostMapping("/naverLoginInsert")
	@ResponseBody
	public String naverLoginInsert(@ModelAttribute MemberVO memberVO) throws Exception {
		log.info("roleCheck에서 넘어온 insert 정보 : {}", memberVO);
		loginService.insertNaverLogin(memberVO);
		return "성공";
	}
	
	// 의사 전용 회원가입
	// 로그인시 아이디를 받아와서 아이디로 seq값을 찾아 
	// doctor테이블에 seqMember를 insert하기 위함
	@PostMapping("/doctorMemberJoin")
	public String doctorMemberJoin(@RequestParam String memberEmailId, Model model) throws Exception {
		log.info("roleCheck에서 넘어온 memberEmailId : {}",memberEmailId);
		model.addAttribute("memberEmailId", memberEmailId);
		// 이메일을 받아서 seqMember를 찾은 후 doctorMemberJoin 페이지로 넘겨주기
		int seqMember = 0;
		seqMember = loginService.selectNaverFindSeq(memberEmailId);
		log.info("seqMember : {}",seqMember);
		model.addAttribute("seqMember", seqMember);
		
		return "doctorMemberJoin";
	}
}

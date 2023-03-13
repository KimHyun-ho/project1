/**
* Project Name : AnimalHospital 
* Page Name : pharmacyMap.js
* Author: Geon Woo Yun
*/

/* 네이버 지도 : 약국 */

$(function() {
	var kakaoToken = document.getElementById('hiddenKakaoAccessToken').value;
	var naverToken = document.getElementById('hiddenNaverAccessToken').value;
	var position = new naver.maps.LatLng(37.276983, 127.027534);

	var map = new naver.maps.Map('mapPharmacy', {
		center: position,
		zoom: 15,
		minZoom: 12
	});

	$.ajax({
		url: "/pharmacyMapRest",
		dataType: "json",
		type: "get",
		data: {
		},
		success: function(data) {
			var HOME_PATH = window.HOME_PATH || '.';
			var markers = [], infoWindows = [];
			for (var i = 0; i < data.length; i++) {
				var marker = new naver.maps.Marker({
					position: new naver.maps.LatLng(data[i].pharmacyLocationLatitude, data[i].pharmacyLocationLongitude),
					map: map,
					icon: {
						url: HOME_PATH + '/img/pill.png',
						size: new naver.maps.Size(48, 48),
						origin: new naver.maps.Point(0, 0),
						anchor: new naver.maps.Point(17, 10)
					},
					zIndex: 100
				});
				if (kakaoToken.trim().length == 0 && naverToken.trim().length == 0) {
					var contentString = [
						'<div class="iw_inner" id="iw_inner' + i + '">',
						'<h5><b>' + data[i].animalPharmacyName + '</b></h5>',
						'   <p style="font-size:11pt">' + data[i].animalPharmacyAddress + '<br />',
						'' + data[i].animalPharmacyPhoneNo + '<br />',
						'</p>',
						'</div>'
					].join('');
				} else {
					var contentString = [
						'<div class="iw_inner" id="iw_inner' + i + '">',
						'<h5><b>' + data[i].animalPharmacyName + '</b></h5>',
						'   <p style="font-size:11pt">' + data[i].animalPharmacyAddress + '<br />',
						'' + data[i].animalPharmacyPhoneNo + '<br />',
						'</p>',
						'</div>',
						'<form action="pharmacyInfoEdit" method="post">',
						'<input type="hidden" name="seq" value="' + data[i].seqPharmacyLocation + '" />',
						'<input type="submit" class="btn btn-primary" name="notice_add" id="notice_add" value="수정하기" ',
						'style="border-radius: 50px; width: 90px; text-align: center; float: right; color: white;">',
						'</form>'
					].join('');
				}

				var infoWindow = new naver.maps.InfoWindow({
					content: contentString,
					maxWidth: 300,
					backgroundColor: "#eee",
					borderColor: "#1977CC",
					borderWidth: 2,
					anchorSize: new naver.maps.Size(20, 10),
					anchorSkew: true,
					anchorColor: "#eee",
					pixelOffset: new naver.maps.Point(20, -20)
				});
				markers.push(marker);
				infoWindows.push(infoWindow);
			}
			naver.maps.Event.addListener(map, 'idle', function() {
				updateMarkers(map, markers);
			}); // for문

			function updateMarkers(map, markers) {
				var mapBounds = map.getBounds();
				var marker, position;

				for (var i = 0; i < markers.length; i++) {
					marker = markers[i]
					position = marker.getPosition();

					if (mapBounds.hasLatLng(position)) {
						showMarker(map, marker);
					} else {
						hideMarker(map, marker);
					}
				}
			}

			function showMarker(map, marker) {
				if (marker.setMap()) return;
				marker.setMap(map);
			}

			function hideMarker(map, marker) {
				if (!marker.setMap()) return;
				marker.setMap(null);
			}

			// 해당 마커의 인덱스를 seq라는 클로저 변수로 저장하는 이벤트 핸들러를 반환
			function getClickHandler(seq) {
				return function(e) {
					var marker = markers[seq],
						infoWindow = infoWindows[seq];
					if (infoWindow.getMap()) {
						infoWindow.close();
					} else {
						infoWindow.open(map, marker);
					}
				}
			}
			for (var i = 0; i < markers.length; i++) {
				naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
			}


		}, //성공시 
		error: function(data) {
			alert("약국 정보가 없습니다.");
		} // 에러시
	});
})


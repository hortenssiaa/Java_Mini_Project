<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
// 카테고리별 지도에 보여주는 코드들 모음...


$('#category').change(function() {
	
	checked = $('input:checked').val();
	console.log("change: "+checked);

if(checked == '야외운동기구'){
	//checked = $('input:checked').val();
	alert("야외운동기구 if문속 " + checked);
	console.log("야외운동기구 if문속 " + checked);
	
	$("#ajaxbtn").on("click", function() {
		
		
		var urlDao, dataDao,
			city= $("#city").children("option:selected").val(),
			town= $("#town").children("option:selected").val();
		
		if( town == "지역을 선택해주세요" ) {
			urlDao = '/healthproject/workout/workoutinfobc';
			dataDao = {'cityname': city}
		} else { 
			urlDao = '/healthproject/workout/workoutinfo';
			dataDao = {'townname': town};
		}
		
		$.ajax({
			url : urlDao,
			data : dataDao,
			type: 'post',
		
			dataType: 'json',
			success: function(details) {
				datas = details;
				
				var markers = [];
				
				var geocoder = new kakao.maps.services.Geocoder();
				
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    mapOption = {
				        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
				        level: 6 // 지도의 확대 레벨
				    };  
				
				var map = new kakao.maps.Map(mapContainer, mapOption); 
				
				var infowindow = new kakao.maps.InfoWindow();
				        
			    displayPlaces(datas);
				
			    displayPagination(pagination);
			    
				
				function displayPlaces(places) { // places == datas
				 			
				    var listEl = document.getElementById('placesList'), 
				    menuEl = document.getElementById('menu_wrap'),
				    fragment = document.createDocumentFragment(), 
				    bounds = new kakao.maps.LatLngBounds(), 
				    listStr = '';
					// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
				
				    
				    removeAllChildNods(listEl);
				
				    removeMarker();
	
				    var count = 0;
				    var addr = [];
				    for( var i=0; i<places.length; i++ ) {
				    	addr[i] = places[i].wo_addr;
				    }
				    
				    for ( var i=0; i<places.length; i++ ) {
	
	  			    	geocoder.addressSearch(addr[i], function(result, status) {  
	  			    		
						     if (status === kakao.maps.services.Status.OK) {
						    	 
						    	 lat[count] = result[0].y;
						    	 lng[count] = result[0].x;
						   		 
						        var placePosition = new kakao.maps.LatLng(lat[count], lng[count]),
						            marker = addMarker(placePosition, count), 
						            itemEl = getListItem(count, places[count]); // 검색 결과 항목 Element를 생성합니다
						            
						        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
						        bounds.extend(placePosition);
						            
						        var content = '<div class="customoverlay">' +
									    '  <a>' +
									    '    <span class="title">'+ places[count].wo_name+'</span>' +
									    '  </a>' +
									    '</div>';
						        
						        var customOverlay = new kakao.maps.CustomOverlay({
								    map: map,
								    position: placePosition,
								    content: content,
								    yAnchor: 0 
								}); 
						        
						    	//console.log(count);
						        count += 1;
						        
						        console.log(itemEl);
	
						        fragment.appendChild(itemEl);
							    listEl.appendChild(fragment);
						        
						        map.setCenter(placePosition);
								customOverlay.setMap(map);
						     }
				    	});
	 			    	
				    }
				    menuEl.scrollTop = 0;
				
				    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
				    function setBounds() {
				    	map.setBounds(bounds);
				    }
				    
				}
			 	
				function getListItem(index, places) {
	
					if(places.wo_admin == null) 
						places.wo_admin = "";
					
					var el = document.createElement('li'),
				    	itemStr = '<span class="markerbg marker_' + (index+1) + '"></span> ' +
				                '<div class="info"> ' +
				                '   <h5>' + places.wo_name + '</h5> '+
								'    <span>' + places.wo_addr + '</span>' +
				      			'  <span class="tel">' + places.wo_kind  + '</span> </div>';
				      			
				    el.innerHTML = itemStr;
				    el.className = 'item';
				    
				    console.log((index+1));
				    console.log(places.wo_name);
				    console.log(places.wo_addr);
				    console.log(places.wo_kind);
				    
				    return el;
				}
				
				function addMarker(position, idx, title) {
				    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
				        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기 // 24
				        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
				            marker = new kakao.maps.Marker({
				            position: position, // 마커의 위치
				            image: markerImage 
				        });
				    
				    marker.setMap(map); // 지도 위에 마커를 표출합니다
				    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
				    
				    return marker;
				}
				
				
				function removeMarker() {
				    for ( var i = 0; i < markers.length; i++ ) {
				        markers[i].setMap(null);
				    }   
				    markers = [];
				}
				
				// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
				function displayPagination(pagination) {
				    var paginationEl = document.getElementById('pagination'),
				        fragment = document.createDocumentFragment(),
				        i; 
				
				    // 기존에 추가된 페이지번호를 삭제합니다
				    while (paginationEl.hasChildNodes()) {
				        paginationEl.removeChild (paginationEl.lastChild);
				    }
				
				    for (i=1; i<=pagination.last; i++) {
				        var el = document.createElement('a');
				        el.href = "#";
				        el.innerHTML = i;
				
				        if (i===pagination.current) {
				            el.className = 'on';
				        } else {
				            el.onclick = (function(i) {
				                return function() {
				                    pagination.gotoPage(i);
				                }
				            })(i);
				        }
				
				        fragment.appendChild(el);
				    }
				    paginationEl.appendChild(fragment);
				}
				
				function displayInfowindow(marker, title) {
					
							var content = '<div class="customoverlay">' +
							    '  <a>' +
							    '    <span class="title">'+ title+'</span>' +
							    '  </a>' +
							    '</div>';
					
				    infowindow.setContent(content);
				    infowindow.open(map, marker);
				}
				
				function removeAllChildNods(el) {   
				    while (el.hasChildNodes()) {
				        el.removeChild (el.lastChild);
				    }
				}
				 
				var mapTypeControl = new kakao.maps.MapTypeControl();
	
				map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	
				var zoomControl = new kakao.maps.ZoomControl();
				map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
				
			}
		});
	});
}
});
    
    
/* $.ajax({
url : '/healthproject/workout/workoutinfobc',
data : {'cityname': city},
type: 'post',

dataType: 'json',
success: function(details) {
	alert('default workoutinfobc ajax working');
	
	datas = details;
	
	var markers = [];
	
	var geocoder = new kakao.maps.services.Geocoder();
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 7 // 지도의 확대 레벨
	    };  
	
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	var infowindow = new kakao.maps.InfoWindow();
	        
    displayPlaces(datas);
	
    displayPagination(pagination);
    
	
	function displayPlaces(places) { // places == datas
	 			
	    var listEl = document.getElementById('placesList'), 
	    menuEl = document.getElementById('menu_wrap'),
	    fragment = document.createDocumentFragment(), 
	    bounds = new kakao.maps.LatLngBounds(), 
	    listStr = '';
		// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
	
	    
	    removeAllChildNods(listEl);
	
	    removeMarker();

	    var count = 0;
	    var addr = [];
	    for( var i=0; i<places.length; i++ ) {
	    	addr[i] = places[i].wo_addr;
	    }
	    
	    for ( var i=0; i<places.length; i++ ) {

		    	geocoder.addressSearch(addr[i], function(result, status) {  
		    		
			     if (status === kakao.maps.services.Status.OK) {
			    	 
			    	 lat[count] = result[0].y;
			    	 lng[count] = result[0].x;
			   		 
			        var placePosition = new kakao.maps.LatLng(lat[count], lng[count]),
			            marker = addMarker(placePosition, count), 
			            itemEl = getListItem(count, places[count]); // 검색 결과 항목 Element를 생성합니다
			            
			        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
			        bounds.extend(placePosition);
			            
			        var content = '<div class="customoverlay">' +
						    '  <a>' +
						    '    <span class="title">'+ places[count].wo_name+'</span>' +
						    '  </a>' +
						    '</div>';
			        
			        var customOverlay = new kakao.maps.CustomOverlay({
					    map: map,
					    position: placePosition,
					    content: content,
					    yAnchor: 0 
					}); 
			        
			    	//console.log(count);
			        count += 1;
			        
			        console.log(itemEl);

			        fragment.appendChild(itemEl);
				    listEl.appendChild(fragment);
			        
			        map.setCenter(placePosition);
					customOverlay.setMap(map);
			     }
	    	});
		    	
	    }
	    menuEl.scrollTop = 0;
	
	    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	    function setBounds() {
	    	map.setBounds(bounds);
	    }
	    
	}
 	
	function getListItem(index, places) {
 		
			if(places.wo_admin == null) 
				places.wo_admin = "";
			
			var el = document.createElement('li'),
		    	itemStr = '<span class="markerbg marker_' + (index+1) + '"></span> ' +
		                '<div class="info"> ' +
		                '   <h5>' + places.wo_name + '</h5> '+
						'    <span>' + places.wo_addr + '</span>' +
		      			'  <span class="tel">' + places.wo_kind  + '</span> </div>';
		      			
		    el.innerHTML = itemStr;
		    el.className = 'item';
		    
		    console.log((index+1));
		    console.log(places.wo_name);
		    console.log(places.wo_addr);
		    console.log(places.wo_kind);
		    
		    return el;
	}
	
	function addMarker(position, idx, title) {
	    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
	        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기 // 24
	        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
	            marker = new kakao.maps.Marker({
	            position: position, // 마커의 위치
	            image: markerImage 
	        });
	    
	    marker.setMap(map); // 지도 위에 마커를 표출합니다
	    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
	    
	    return marker;
	}
	
	
	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	        markers[i].setMap(null);
	    }   
	    markers = [];
	}
	
	// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	function displayPagination(pagination) {
	    var paginationEl = document.getElementById('pagination'),
	        fragment = document.createDocumentFragment(),
	        i; 
	
	    // 기존에 추가된 페이지번호를 삭제합니다
	    while (paginationEl.hasChildNodes()) {
	        paginationEl.removeChild (paginationEl.lastChild);
	    }
	
	    for (i=1; i<=pagination.last; i++) {
	        var el = document.createElement('a');
	        el.href = "#";
	        el.innerHTML = i;
	
	        if (i===pagination.current) {
	            el.className = 'on';
	        } else {
	            el.onclick = (function(i) {
	                return function() {
	                    pagination.gotoPage(i);
	                }
	            })(i);
	        }
	
	        fragment.appendChild(el);
	    }
	    paginationEl.appendChild(fragment);
	}
	
	function displayInfowindow(marker, title) {
		
				var content = '<div class="customoverlay">' +
				    '  <a>' +
				    '    <span class="title">'+ title+'</span>' +
				    '  </a>' +
				    '</div>';
		
	    infowindow.setContent(content);
	    infowindow.open(map, marker);
	}
	
	function removeAllChildNods(el) {   
	    while (el.hasChildNodes()) {
	        el.removeChild (el.lastChild);
	    }
	}
	 
	var mapTypeControl = new kakao.maps.MapTypeControl();

	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	

	// 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
   // var title = places[i].pk_name;
    /* (function(marker, title) {
        kakao.maps.event.addListener(marker, 'mouseover', function() {
            displayInfowindow(marker, title);
        });

        kakao.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
        });

        itemEl.onmouseover =  function () {
            displayInfowindow(marker, title);
        };

        itemEl.onmouseout =  function () {
            infowindow.close();
        };
    })(marker, places[i].r_name); 
}
});  */




/* 밑에 방금 지움 
	$('#category').change(function() {
		
	// checked = $('input:checked').val();
	//	console.log("change: "+checked); 

	if(checked == '야외운동기구'){
		//checked = $('input:checked').val();
		
		$("#ajaxbtn").on("click", function() {
			
			
			var urlDao, dataDao,
				city= $("#city").children("option:selected").val(),
				town= $("#town").children("option:selected").val();
			
			if( town == "지역을 선택해주세요" ) {
				urlDao = '/healthproject/workout/workoutinfobc';
				dataDao = {'cityname': city}
			} else { 
				urlDao = '/healthproject/workout/workoutinfo';
				dataDao = {'townname': town};
			}
			
			$.ajax({
				url : urlDao,
				data : dataDao,
				type: 'post',
			
				dataType: 'json',
				success: function(details) {
					datas = details;
					
					var markers = [];
					
					var geocoder = new kakao.maps.services.Geocoder();
					
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					    mapOption = {
					        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
					        level: 6 // 지도의 확대 레벨
					    };  
					
					var map = new kakao.maps.Map(mapContainer, mapOption); 
					
					var infowindow = new kakao.maps.InfoWindow();
					        
				    displayPlaces(datas);
					
				    displayPagination(pagination);
				    
					
					function displayPlaces(places) { // places == datas
					 			
					    var listEl = document.getElementById('placesList'), 
					    menuEl = document.getElementById('menu_wrap'),
					    fragment = document.createDocumentFragment(), 
					    bounds = new kakao.maps.LatLngBounds(), 
					    listStr = '';
						// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
					
					    
					    removeAllChildNods(listEl);
					
					    removeMarker();
		
					    var count = 0;
					    var addr = [];
					    for( var i=0; i<places.length; i++ ) {
					    	addr[i] = places[i].wo_addr;
					    }
					    
					    for ( var i=0; i<places.length; i++ ) {
		
		  			    	geocoder.addressSearch(addr[i], function(result, status) {  
		  			    		
							     if (status === kakao.maps.services.Status.OK) {
							    	 
							    	 lat[count] = result[0].y;
							    	 lng[count] = result[0].x;
							   		 
							        var placePosition = new kakao.maps.LatLng(lat[count], lng[count]),
							            marker = addMarker(placePosition, count), 
							            itemEl = getListItem(count, places[count]); // 검색 결과 항목 Element를 생성합니다
							            
							        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
							        bounds.extend(placePosition);
							            
							        var content = '<div class="customoverlay">' +
										    '  <a>' +
										    '    <span class="title">'+ places[count].wo_name+'</span>' +
										    '  </a>' +
										    '</div>';
							        
							        var customOverlay = new kakao.maps.CustomOverlay({
									    map: map,
									    position: placePosition,
									    content: content,
									    yAnchor: 0 
									}); 
							        
							    	//console.log(count);
							        count += 1;
							        
							        console.log(itemEl);
		
							        fragment.appendChild(itemEl);
								    listEl.appendChild(fragment);
							        
							        map.setCenter(placePosition);
									customOverlay.setMap(map);
							     }
					    	});
		 			    	
					    }
					    menuEl.scrollTop = 0;
					
					    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
					    function setBounds() {
					    	map.setBounds(bounds);
					    }
					    
					}
				 	
					function getListItem(index, places) {
		
						if(places.wo_admin == null) 
							places.wo_admin = "";
						
						var el = document.createElement('li'),
					    	itemStr = '<span class="markerbg marker_' + (index+1) + '"></span> ' +
					                '<div class="info"> ' +
					                '   <h5>' + places.wo_name + '</h5> '+
									'    <span>' + places.wo_addr + '</span>' +
					      			'  <span class="tel">' + places.wo_kind  + '</span> </div>';
					      			
					    el.innerHTML = itemStr;
					    el.className = 'item';
					    
					    console.log((index+1));
					    console.log(places.wo_name);
					    console.log(places.wo_addr);
					    console.log(places.wo_kind);
					    
					    return el;
					}
					
					function addMarker(position, idx, title) {
					    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
					        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기 // 24
					        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
					            marker = new kakao.maps.Marker({
					            position: position, // 마커의 위치
					            image: markerImage 
					        });
					    
					    marker.setMap(map); // 지도 위에 마커를 표출합니다
					    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
					    
					    return marker;
					}
					
					
					function removeMarker() {
					    for ( var i = 0; i < markers.length; i++ ) {
					        markers[i].setMap(null);
					    }   
					    markers = [];
					}
					
					// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
					function displayPagination(pagination) {
					    var paginationEl = document.getElementById('pagination'),
					        fragment = document.createDocumentFragment(),
					        i; 
					
					    // 기존에 추가된 페이지번호를 삭제합니다
					    while (paginationEl.hasChildNodes()) {
					        paginationEl.removeChild (paginationEl.lastChild);
					    }
					
					    for (i=1; i<=pagination.last; i++) {
					        var el = document.createElement('a');
					        el.href = "#";
					        el.innerHTML = i;
					
					        if (i===pagination.current) {
					            el.className = 'on';
					        } else {
					            el.onclick = (function(i) {
					                return function() {
					                    pagination.gotoPage(i);
					                }
					            })(i);
					        }
					
					        fragment.appendChild(el);
					    }
					    paginationEl.appendChild(fragment);
					}
					
					function displayInfowindow(marker, title) {
						
								var content = '<div class="customoverlay">' +
								    '  <a>' +
								    '    <span class="title">'+ title+'</span>' +
								    '  </a>' +
								    '</div>';
						
					    infowindow.setContent(content);
					    infowindow.open(map, marker);
					}
					
					function removeAllChildNods(el) {   
					    while (el.hasChildNodes()) {
					        el.removeChild (el.lastChild);
					    }
					}
					 
					var mapTypeControl = new kakao.maps.MapTypeControl();
		
					map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		
					var zoomControl = new kakao.maps.ZoomControl();
					map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
					
				}
			});
		});
	}
	});
	*/
	
	
	
	
	/* 		
	var city= $("#city").children("option:selected").val();
	var town= $("#town").children("option:selected").val();
	var c = $('input:checked').val();
	console.log("findCityTown's c: "+c);
	
		if(town == "지역을 선택해주세요"){
			
			$.ajax({
				url : '/healthproject/river/city',
				data : {'cityname': city},
				type: 'post',
				
				dataType: 'json',
				success: function(town) {
					
					$("#town").empty();
					$("#town").append("<option value=\"지역을 선택해주세요\" selected=\"selected\">지역을 선택해주세요</option>");
					
					for(var i=0; i<town.length; i++){
						var option = "<option value=\""+town[i]+"\">"+town[i]+"</option>"
						$("#town").append(option);
					}
					
				},
				error: function(request,status,error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
			
		} else {
			$("#town").empty();
			$("#town").append("<option value=\"지역을 선택해주세요\" selected=\"selected\">지역을 선택해주세요</option>");
		}
	
	$("#city").change(function() {
		
		$("#town").empty();
		$("#town").append("<option value=\"지역을 선택해주세요\" selected=\"selected\">지역을 선택해주세요</option>");
		
		var city= $("#city").children("option:selected").val();
		
			$.ajax({
				url : '/healthproject/river/city',
				data : {'cityname': city},
				type: 'post',
				
				dataType: 'json',
				success: function(town) {
					
					$("#town").empty();
					$("#town").append("<option value=\"지역을 선택해주세요\" selected=\"selected\">지역을 선택해주세요</option>");
					
					for(var i=0; i<town.length; i++){
						var option = "<option value=\""+town[i]+"\">"+town[i]+"</option>"
						$("#town").append(option);
					}
					
				},
				error: function(request,status,error) {
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
	});//change
	 */
	 
	 
	 <%
		String loginid = "";
		if(session.getAttribute("loginCheck") != null) {
			loginid = (String) session.getAttribute("loginid");
			System.out.printf("loginid: %s",loginid);
		} else
			loginid = "notsignedinnull";
	%>
		
	<%
		if(session.getAttribute("loginCheck") != null) { // 로그인 돼 있을 때 
	%> 
	<c:set var="listsize" value="${fn:length(boardlist)}" /> <!--  역순 -->
	
		<c:forEach var="i" begin="1" end="${listsize}"> 
		 	<table align="center">
				<tr>
					<td>
						<div class="profile_box">
							<img id="profile_pic" src="" alt="${boardlist[listsize-i].id}'s profile">
						</div>
						<div class="id_style">
							<div>${boardlist[listsize-i].id }</div>
						</div>
					</td>
				</tr>
				<tr>
					<td> ${boardlist[listsize-i].location } </td>
				</tr>
				<tr>
					<td>
						<div class="seq_container">
							<div>${boardlist[listsize-i].seq }</div>
						</div>
					</td> 
				</tr>
				<tr>
					<td> 
						<div class="board_pic">
							<img class="board_photo" alt="board pic" src="/img/${boardlist[listsize-i].filepath }">  
						</div>
					</td>
				</tr>
				<tr>
					<td> 
						<div class="likes_container">
							<p>
								<img class="likes_pic" alt="board pic" src="./resources/likebtnblack.png">
							</p>
							<div class="likes_pic" id="likes_num">
								Likes:${boardlist[listsize-i].likes } 
							</div>
							<input type="hidden" class="likes_pic" id="likes_hidden" value="here">
							<input type="hidden" class="likes_pic" id="seq_hidden" value="${boardlist[listsize-i].seq }">
						</div>
						<div class="bookmark_container">
							<img id="bookmark_pic" alt="bookmark pic" src="./resources/bookmark.png">
							<input type="hidden" class="bookmark_container" id="bm_hidden" value="bmhere">
							<input type="hidden" class="bookmark_container" id="bm_seq_hidden" value="${boardlist[listsize-i].seq }">
						</div>
					</td>
				</tr>
				<tr>
					<td> ${boardlist[listsize-i].caption } </td>
				</tr>
			</table>
	
	<!-- [0]부터 보여주기 -->	
	<%-- <c:forEach items="${boardlist }" var="vo"> --%>
<%-- 	 	<table align="center">
			<tr>
				<td> ${vo.id } </td>
			</tr>
			<tr>
				<td> ${vo.location } </td>
			</tr>
			<tr>
				<td> 
					<p class="board_pic">
						<img class="board_photo" alt="board pic" src="/img/${vo.filepath }">  
					</p>
				</td>
			</tr>
			<tr>
				<td> 
					<div class="likes_container">
						<img class="likes_pic" alt="board pic" src="./resources/likebtnblack.png">  
						Likes : ${vo.likes } 
					</div>
				</td>
			</tr>
			<tr>
				<td> ${vo.caption } </td>
			</tr>
		</table> --%>
		
		
		<br> <br> <br>
	</c:forEach>
	<%
		} else { // 로그인 안돼 있을 때 
	%> 
	
	<c:set var="listsize" value="${fn:length(boardlist)}" /> <!--  역순 -->
		<c:forEach var="i" begin="1" end="${listsize}"> 
		 	<table align="center">
				<tr>
					<td> 
						<div class="profile_box">
							<img id="profile_pic" src="" alt="${boardlist[listsize-i].id}'s profile">
						</div>
						<div class="id_style">
							<div>${boardlist[listsize-i].id }</div>
						</div>
					</td>
				</tr>
				<tr>
					<td> ${boardlist[listsize-i].location } </td>
				</tr>
				<tr>
					<td>
						<div class="seq_container">
							<div>${boardlist[listsize-i].seq }</div>
						</div>
					</td>
				</tr>
				<tr>
					<td> 
						<p class="board_pic">
							<img class="board_photo" alt="board pic" src="/img/${boardlist[listsize-i].filepath }">  
						</p>
					</td>
				</tr>
				<tr>
					<td> 
						<div class="likes_container">
							<img class="likes_pic" id="like_img" alt="board pic" src="./resources/likebtnblack.png">  
							<div class="likes_pic" id="likes_num">
								Likes:${boardlist[listsize-i].likes } 
							</div>
							<input type="hidden" class="likes_pic" id="likes_hidden" value="here">
							<input type="hidden" class="likes_pic" id="seq_hidden" value="${boardlist[listsize-i].seq }">
						</div>
					</td>
				</tr>
				<tr>
					<td> ${boardlist[listsize-i].caption } </td>
				</tr>
			</table>
			<br> <br> <br>
	</c:forEach>
	<%
		}
	%>
	
<script src="/healthproject/resources/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		var likes_arr = $(".likes_pic").get();
		
		var blackbtn = "./resources/likebtnblack.png";
		var redbtn = "./resources/likebtnred.png";
		var bm_blackbtn = "./resources/bookmarkblack.png";
		var bm_btn = "./resources/bookmark.png";
		
		for(var i=0; i < likes_arr.length; i++) {
			//alert("likes_arr["+i+"] :"+$(likes_arr[i]).attr('src'))
			var l_array = [$(likes_arr[i]).attr('src')];
			if(i>0) {
				l_array.concat([$(likes_arr[i]).attr('src')]);
			}
		}
		/* for(var i=0; i < likes_arr.length; i++) {
			alert("l_array["+i+"]: "+[$(likes_arr[i]).attr('src')]);
		} // likes_arr 배열 확인하기 */ 
		
		/* $(".board_photo").on('click', function(e) { 
			alert($(e.target).attr('src'));
		}); // board_photo event 요소 갖고오기  */
		
		var loginid = '<%=loginid %>';
		var likes_status;
		var bm_status;
		var total_list = ${fn:length(boardlist)};
		
		//alert($(".seq_container").children().html());
		$.ajax({
			url : '/healthproject/getboardlistprofile', 
			type : 'POST',

			dataType : 'json',
			success : function (serverdata) {
				var num=0
		        $('.id_style').children().each(function() {
		          $(this).addClass('seq_c' + num);
		          num += 1;
		        }); 
				
				var u=0
				$('.profile_box>img').each(function() {
			       $(this).addClass('seq_img' + u);
			       u += 1;
			     }); 
				//alert(num);
				
				for(var i=0; i<num; i++) {
					for(var j=0; j<serverdata.length; j++) {
						if($('.seq_c'+i).html() == serverdata[j].id) {
							var profile_path = '/img'+serverdata[j].filepath+'?timestamp=' + new Date().getTime();
							$('.seq_img'+i).attr('src', profile_path);
						}
					}
				}
			}
		}); // get board list profile pictures _ $.ajax 완료 

		if( !(loginid == "notsignedinnull")) { // 로그인 되어 있으면, 
			
			// 좋아요 표시했던 게시물 갖고오기 
			$.ajax({ 
				url : '/healthproject/getlikeddata', 
				type : 'POST',

				dataType : 'json',
				success : function (serverdata) {
					var k=0
			        $('p').each(function() {
			          $(this).addClass('sseq' + k);
			          k += 1;
			        }); 
					
//					for(var i=0; i<total_list; i++) {
					for(var i=0; i<serverdata.length; i++) {
						var server_seq = new Array();
						
						// 아래, hboard seq 정리시; server_seq[i] = 11 - serverdata[i].seq; 사용할 것!
						if(serverdata[i].seq < 3)
							server_seq[i] = 8 - serverdata[i].seq;
						else	
							server_seq[i] = 11 - serverdata[i].seq;
						//alert(server_seq[i]);
						//alert($('.sseq' + server_seq[i]).next().next().next().val());
						$('.sseq' + server_seq[i]).children().attr('src', './resources/likebtnred.png');
					}
				}
			}); // get liked article_ $.ajax 완료  

			// 북마크 표시했던 게시물 갖고오기 
			$.ajax({  
				url : '/healthproject/getbookmarkdata', 
				type : 'POST',

				dataType : 'json',
				success : function (serverdata) {
					var k=0
			        $('.bookmark_container img').each(function() {
			          $(this).addClass('bmseq' + k);
			          k += 1;
			        }); 
					
//					for(var i=0; i<total_list; i++) {
					for(var i=0; i<serverdata.length; i++) {
						var server_bm_seq = new Array();
						
						// 아래, hboard seq 정리시; server_seq[i] = 11 - serverdata[i].seq; 사용할 것!
						if(serverdata[i].seq < 3)
							server_bm_seq[i] = 8 - serverdata[i].seq;
						else	
							server_bm_seq[i] = 11 - serverdata[i].seq;
						$('.bmseq' + server_bm_seq[i]).attr('src', './resources/bookmarkblack.png');
					}
				}
			}); // get bookmarked article_ $.ajax 완료  
		}
		
		// #1 img.likes_pic
		$("img.likes_pic").on('click', function(e) {
		
			if( !(loginid == "notsignedinnull")) { // 로그인 되어 있으면
		
				//alert($(e.target).attr('src'));
				if ($(e.target).attr('src') == blackbtn) {
					
					$(e.target).attr('src', './resources/likebtnred.png');
					$(e.target).parent().next().next().val("1"); // img 아래 hidden 
					//alert("seq: "+$(e.target).next().next().next().val()); // hidden seq
					//alert( "hidden value: " + $(e.target).next().val() ); // hidden, id:likes_hidden
					//alert($('.likes_pic').next().val());
					
					likes_status = $(e.target).parent().next().next().val();
				} else if ( $(e.target).attr('src') == redbtn ) {
					$(e.target).attr('src', './resources/likebtnblack.png');
					$(e.target).parent().next().next().val("0"); // img 아래 hidden 
					
					likes_status = $(e.target).parent().next().next().val();
				}	
					
				$.ajax({
					url : '/healthproject/likesprocess', 
					data : { 'likes':likes_status, 'seq': $(e.target).parent().next().next().next().val(), 'id':loginid },
					type : 'POST',

					dataType : 'json',
					success : function (serverdata) {
						//alert("likes container:"+$(e.target).next().html());
						$(e.target).parent().next().html
//						$("#likes_num").html
						(
						"Likes : " + serverdata.likes
						); 
					}
				}); // add likes _ $.ajax 완료  
				
			} else { // 로그인 안되어 있을 때 
				alert("로그인 먼저 해주세요.");
				location.href="/healthproject/signin";
			}

		}); // likes_pic event 요소 갖고오기  ($("img.likes_pic") on('click') 완료 )

		
		// #2 img#bookmark_pic
		$("img#bookmark_pic").on('click', function(e) {
		
			if( !(loginid == "notsignedinnull")) { // 로그인 되어 있으면
		
				//alert($(e.target).attr('src'));
				if ($(e.target).attr('src') == bm_btn) {
					
					$(e.target).attr('src', './resources/bookmarkblack.png');
					$(e.target).next().val("1"); // img 아래 hidden 
					//alert("seq: "+$(e.target).next().next().next().val()); // hidden seq
					//alert( "hidden value: " + $(e.target).next().val() ); // hidden, id:likes_hidden
					
					bm_status = $(e.target).next().val();
					//alert("bm_status:"+bm_status);
					//alert($('.likes_pic').next().val());
				} else if ( $(e.target).attr('src') == bm_blackbtn ) {
					$(e.target).attr('src', './resources/bookmark.png');
					$(e.target).next().val("0"); // img 아래 hidden 
					
					bm_status = $(e.target).next().val();
					//alert("bm_status:" + bm_status);
				}	
					
				$.ajax({
					url : '/healthproject/bookmarkprocess', 
					data : { 'bm_status':bm_status, 'seq': $(e.target).next().next().val(), 'id':loginid },
					type : 'POST',

					dataType : 'json',
					/* success : function (serverdata) {
						alert("bookmarkprocess ajax suceed!");
					} */
				}); // add likes _ $.ajax 완료  
				
			} else { // 로그인 안되어 있을 때 
				alert("로그인 먼저 해주세요.");
				location.href="/healthproject/signin";
			}

		}); // bookmark_pic event 요소 갖고오기  ($("img#bookmark_pic") on('click') 완료 )
		
	})
    </script>
</body>
</html>
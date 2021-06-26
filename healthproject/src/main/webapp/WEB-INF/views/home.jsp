<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
<style type="text/css">
@import url("https://fonts.googleapis.com/css?family=Fira+Sans");
  
html, body {
	background: #E1E8EE;
/* 	background: #267BE3; */
	font-family: "Fira Sans", Helvetica, Arial, sans-serif;
}

#signinform_container{
	align-content: right;
	margin-right: 30px;
	/* background-color: #87A4B3;	 */
}

#signinform {
	margin-right: 20px;
	display: block;
}

.profile_box {
		width: 150px; 
    	height: 150px; 
    	border-radius: 70%;
    	overflow: hidden;
    	/* position: absolute; */
}
	
.profile_pic {
		width: 100%;
   		height: 100%;
   		object-fit: cover;	
}

/* .kormap {
	display: inline;
} */ 
 
 .main_div {
 	width: 50px;
 	height: 20px;
 }
 
.submit-btn {
  display: block;
  background-color: rgba(0, 0, 0, 0.4);
  color: rgba(255, 255, 255, 0.7);
  border: 0;
  border-radius: 15px;
  margin: 15px auto;
  padding: 15px 45px;
  width: 100%;
  font-size: 13px;
  font-weight: bold;
  cursor: pointer;
  opacity: 1;
  visibility: visible;
  -webkit-transition: all .3s ease;
}
.submit-btn:hover {
  transition: all .3s ease;
  background-color: rgba(0, 0, 0, 0.8);
}
	
.kormap img {width: 81%;float: left; margin-left: 250px;}
#title_container {margin-top: 20px;text-align: center; display: inline; background-color: #ffffff;  }
h1 {margin-top: 20px;text-align: center; float: center;}
.peice {position: absolute;	width: 8%;}
.newpeice {position: absolute; z-index: 3;width: 50%;}
/* .newpeice {position: absolute;	width: 30%; z-index: 5;} */

#seoul {position: absolute;width: 35%;z-index: 2;}
#ggi {position: absolute; width: 35%;z-index: 2;}
#kangwon {position: absolute;width: 35%;z-index: 2;}
#incheon {position: absolute;width: 35%;z-index: 2;}
#jeju {position: absolute; width: 35%;z-index: 2;}
#jeonnam {position: absolute;width: 35%;z-index: 2;}
#gwangju {position: absolute;width: 35%;z-index: 2;}
#jeonbuk {position: absolute;width: 35%;z-index: 2;}
#busan {position: absolute;width: 35%;z-index: 2;}
#gyeongnam {position: absolute;width: 35%;z-index: 2;}
#ulsan {position: absolute;width: 35%;z-index: 2;}
#deagu {position: absolute;width: 35%;z-index: 2;}
#gyeongbuk {position: absolute;width: 35%;z-index: 2;}
#deajeon {position: absolute;width: 35%;z-index: 2;}
#chungbuk {position: absolute;width: 35%;z-index: 2;}
#chungnam {	position: absolute; width: 35%;x;z-index: 2;} 
/* #mainmap {position: absolute;pointer-events: none;width: 35%; z-index: -1;} */
/* #mainmap {position: absolute;pointer-events: none; z-index: -1;} */

#ggi_a {position: absolute; width: 35%;float: left;}
#seoul_a {position: absolute; width: 35%;float: left;}
#kangwon_a {position: absolute; width: 35%;float: left;}
#incheon_a {position: absolute; width: 35%;float: left;}
#jeju_a {position: absolute; width: 35%;float: left;}
#jeonnam_a {position: absolute; width: 35%;float: left;}
#gwangju_a {position: absolute; width: 35%;float: left;}
#jeonbuk_a {position: absolute; width: 35%;float: left;}
#busan_a {position: absolute; width: 35%;float: left;}
#gyeongnam_a {position: absolute; width: 35%;float: left;}
#ulsan_a {position: absolute; width: 35%;float: left;}
#deagu_a {position: absolute; width: 35%;float: left;}
#gyeongbuk_a {position: absolute; width: 35%;float: left;}
#deajeon_a {position: absolute; width: 35%;float: left;}
#chungbuk_a {position: absolute; width: 35%;float: left;}
#chungnam_a {	position: absolute; width: 35%;float: left;}

#mainmap_a {position: absolute; width: 30%;pointer-events: none;}
/* #ggi {position: absolute; top: 180px;left: 397px;width: 8%;z-index: 2;}
#seoul {position: absolute;top: 240px;left: 419px;width: 2.4%;z-index: 3;}
#kangwon {position: absolute;top: 164px;left: 455px;width: 13%;z-index: 2;}
#incheon {position: absolute;top: 216px;left: 371px;width: 3.8%;z-index: 2;}
#jeju {position: absolute; top: 569px;left: 360px;width: 6%;z-index: 2;}
#jeonnam {position: absolute;top: 439px;left: 343px;width: 12.5%;z-index: 2;}
#gwangju {position: absolute;top: 462px;left: 405px;width: 2.6%;z-index: 3;}
#jeonbuk {position: absolute;top: 384px;left: 393px;width: 8.5%;z-index: 2;}
#busan {position: absolute;top: 451px;left: 560px;width: 4.3%;z-index: 2;}
#gyeongnam {position: absolute;top: 405px;left: 483px;width: 9.8%;z-index: 2;}
#ulsan {position: absolute;top: 419px;left: 593px;width: 3.1%;z-index: 2;}
#deagu {position: absolute;top: 393px;left: 545px;width: 2.1%;z-index: 3;}
#gyeongbuk {position: absolute;top: 279px;left: 511px;width: 10%;z-index: 2;}
#deajeon {position: absolute;top: 347px;left: 456px;width: 1.7%;z-index: 3;}
#chungbuk {position: absolute;top: 279px;left: 462px;width: 7.1%;z-index: 2;}
#chungnam {	position: absolute; top: 293px;left: 360px;width: 9.6%;z-index: 2;}
#mainmap {position: absolute;pointer-events: none; z-index: -1;} */


#bordersample {
	float: right;
	height: 500px;
	width: 900px;
	margin-top: 20px;
	margin-right: 60px;
}

.today {
	margin-top: 50px;
	display: block;
}

.weather{
	height: 70px;
	width: 306px;
	margin-right: 40px;
	margin-bottom: 10px;
	border: 1px solid #D7D7D7;
	border-radius: 8px;
	background-color: #FFFFFF;
	text-align: center;
	color: #919595;
}

</style>	
<script src="/healthproject/resources/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#signupbtn").on("click", function() {
			location.href="/healthproject/signup/"
		});		
		
 		$("#signinbtn").on("click", function() {
			$.ajax({
				url : '/healthproject/memberinfo', 
				data : { 'id':$('#id').val(), 'password':$('#password').val()},
				type : 'POST',
				
				
				dataType : 'json',
				success : function (serverdata) {
					if(serverdata) {
						location.href="/healthproject/";
					}
				}
			}); // $.ajax 완료 
			
			//$("#signinform").hide();
			/* $.ajax({
				url : '/healthproject/memberinfo', 
				data : { 'id':$('#id').val(), 'password':$('#password').val()},
				type : 'POST',
				
				
				dataType : 'json',
				success : function (serverdata) {
					$("#signinform").html
//					$("#signinstatus").html
					(
					serverdata.name + "님("
					+ serverdata.id + ") 환영합니다. <br>"
					+ "<img alt='my profile' src='/img/" + serverdata.filepath + "'> <br>"
					+ "<input id='signoutbtn' type=button value='로그아웃'>"
					); // memberinf로 mapping된 getMemberInfo()에서 리턴되는 값 : vo == serverdata
					
					$("#signoutbtn").on("click", function() {
						location.href="/healthproject/signout"
					});
				}
			}); // $.ajax 완료 
			//$("#signinform").hide(); */
			
		}); // $("#signinbtn").on("click", function() end 
				
			  var imgSrc = './resources/kormap/a/';
			  //alert('srcName:'+srcName);
		 	  

				  /* 여기
			  $(function() {
       			$(".piece")
	            .mouseenter(function(e) {
	               //var src = $(this).attr("src");
	               var thisId = $(this).attr("id");
	              //alert("mouseenter thisId:"+thisId);
	               //var newSrc = src.substring(0, src.lastIndexOf('.')); 
	               
	            	var pieceLength = document.getElementsByClassName('piece').length;
	            	var imgLength = document.getElementsByTagName('img').length;
	            	var imgEl = document.getElementsByTagName('img');
	            	//alert(imgLength);
	            	
	            	var pieceEl = document.getElementsByClassName('piece');
	            	
 //	               for(var i = 0; i<imgLength; i++) { 
	               for(var i = 0; i<pieceLength; i++) {
//	            	   if(pieceEl[i].name != thisId) {
  	            		   $('#'+pieceEl[i].name).attr('hidden', 'hidden'); 
  	            	       $('#'+pieceEl[i].name).css({'display': 'none'}); 
  							//alert(imgEl[i].name);
	   //         		   $('#'+imgEl[i].name).attr('hidden', 'hidden'); 
  	 //           		   $('#'+imgEl[i].name).css({'display': 'none'}); 
 	            		   
	            	   //}
	               } 
 	            		   //$('#mainmap').attr('display', 'none');
 // 	            		   $('#'+thisId).attr('hidden', 'hidden'); 
 					
 					$(".newpiece").remove();
				   $(".kormap").append
					  (
						"<img class='newpiece' alt='newkormap' id='" + thisId + "_a' name='" + thisId + "_a' src='" + imgSrc + thisId + "_a.png'>"
					  ); 
	            })
	           // });
       			
//			  setTimeout(function() {
			//  $(function() {
	      // 			$(".piece")
//여기 	            .mouseout(function() {
	            	$(".newpiece").remove();	 
	                 var src = $(this).attr("src");
	               var thisId = $(this).attr("id");
	               //alert("mouseout thisId:"+thisId);
	               var newSrc = src.substring(0, src.lastIndexOf('.')); 
	               
	            	var pieceLength = document.getElementsByClassName('piece').length;
	            	var newpieceLength = document.getElementsByClassName('newpiece').length;
	            	var imgLength = document.getElementsByTagName('img').length;
	            	var imgEl = document.getElementsByTagName('img');
	            	
	            	var newpieceEl = document.getElementsByClassName('newpiece');
	            	var pieceEl = document.getElementsByClassName('piece');
	        //    	$('#' + thisId + '_a').attr('hidden', 'hidden'); 
	            	
	        //$(".newpiece").empty();
 	    //           for(var i = 0; i<imgLength; i++) { 
	               for(var i = 0; i<pieceLength; i++) {
//	            	   if(pieceEl[i].name != thisId) {
  	            		   $('#'+pieceEl[i].name).removeAttr('hidden'); 
	            		   $('#'+pieceEl[i].name).css({'display': 'inline-block'}); 
 	            		   //alert(imgEl[i].name);
//	            		   $('#'+imgEl[i].name).css({'display': 'inline-block'}); 
 //	            		   $('#'+imgEl[i].name).removeAttr('hidden'); 
/*  	            		   $('#'+imgEl[i].name).removeAttr('display');  */
 	            		   
 	            		   //$('#mainmap').attr('hidden', 'hidden');
	            	   //}
	/*여기               } 
	               
	            });
	  	  }); 
			
			
여기			*/
			
	  	$(".piece").on("click", function() {
	  		location.href="/healthproject/categorylist";
		});
			  
	}) // $(document).ready(function() end
	
</script>
</head>
<body>
	<div id='title_container'>
		<h1>
			당신 근처의 건강한 습관
		</h1> 
	</div>
	
	<div id='signinform_container' align="right">
		<div id="signinform" align="right">
			<jsp:include page="included_memberinfo.jsp"></jsp:include>
		</div>
	</div>
	<div class='kormap'>
		<!-- <img class='piece' id="mainmap" name="mainmap" alt="kormap" src="./resources/kormap/a/mainmap.png" > -->
		<img class="piece" id="seoul" alt="kormap" name="seoul" src="./resources/kormap/a/seoul.png">
		<img class="piece" id="ggi" alt="kormap" name="ggi" src="./resources/kormap/a/ggi.png">
		<img class="piece" id="kangwon" alt="kormap" name="kangwon" src="./resources/kormap/a/kangwon.png">
		<img class="piece" id="incheon" alt="kormap" name="incheon" src="./resources/kormap/a/incheon.png">
		<img class="piece" id="chungnam" alt="kormap" name="chungnam" src="./resources/kormap/a/chungnam.png">
		<img class="piece" id="chungbuk" alt="kormap" name="chungbuk" src="./resources/kormap/a/chungbuk.png">
		<img class="piece" id="deajeon" alt="kormap" name="deajeon" src="./resources/kormap/a/deajon.png">
		<img class="piece" id="gyeongbuk" alt="kormap" name="gyeongbuk" src="./resources/kormap/a/gyeongbuk.png">
		<img class="piece" id="deagu" alt="kormap" name="deagu" src="./resources/kormap/a/deagu.png">
		<img class="piece" id="ulsan" alt="kormap" name="ulsan" src="./resources/kormap/a/ulsan.png">
		<img class="piece" id="gyeongnam" alt="kormap" name="gyeongnam" src="./resources/kormap/a/gyeongnam.png">
		<img class="piece" id="busan" alt="kormap" name="busan" src="./resources/kormap/a/busan.png">
		<img class="piece" id="jeonbuk" alt="kormap" name="jeonbuk" src="./resources/kormap/a/jeonbuk.png">
		<img class="piece" id="gwangju" alt="kormap" name="gwangju" src="./resources/kormap/a/gwangju.png">
		<img class="piece" id="jeonnam" alt="kormap" name="jeonnam" src="./resources/kormap/a/jeonnam.png">
		<img class="piece" id="jeju" alt="kormap" name="jeju" src="./resources/kormap/a/jeju.png">
	</div> 
	
	<!-- <div class='today' align="right">
		<div class='weather'>
			(현재위치) 날씨
		</div>
		
		<div class='weather'>
			(현재위치) 미세먼지
		</div>
	</div> -->

	<div id="bordersample">
		<jsp:include page="board/homeBoardList.jsp"></jsp:include>
	</div>
</body>
</html>


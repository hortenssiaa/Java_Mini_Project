<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/healthproject/resources/jquery-3.2.1.min.js"></script>
<style type="text/css">
	.prof_box {
		width: 150px;
    	height: 150px; 
    	border-radius: 70%;
    	overflow: hidden;
    	display: inline-block;
    	/* position: absolute; */
	}
	
	.prof_pic {
		width: 100%;
   		height: 100%;
   		object-fit: cover;
	}
	
	#first_container{
		margin-bottom: 20px;
	}
	
	#gotowriteform {
		margin-bottom: 8px;
	}
	
	.btn_css {
		background-color : rgba(0, 0, 0, 0.4);
		color : rgba(255, 255, 255, 0.7);
		border: 0;
		padding :8px 25px;
		width: 8%;
		border-radius: 35px;
		font-size: 13px;
		font-weight: bold;
		opacity: 1;
		visibility: visible;
	}
	
	.btn_css_test {
		background-color : rgba(0, 0, 0, 0.4);
		color : rgba(255, 255, 255, 0.7);
		display: block;
		border: 0;
		padding :8px 25px;
		width: 20%;
		margin-top: 5px;
		border-radius: 35px;
		font-size: 13px;
		font-weight: bold;
		opacity: 1;
		visibility: visible;
	}
	
</style>
</head>
<body>
	<div id="signinform">
	<% 
		if(session.getAttribute("loginCheck") != null) { 
			 System.out.print("(insertmemberinfo.jsp) session connected:");
			 System.out.println(session.getAttribute("loginid"));
			 
			 String loginid = (String) session.getAttribute("loginid");
	%>
		
		<script>
		(function signinbtn() {
				$.ajax({
					url : '/healthproject/memberinfo', 
/* 					data : { 'id':'test'}, */
 					data : { 'id':'test', 'password':'test'}, 
					type : 'POST',
					error : function(){
		                alert('insertmemberinfo JSON 통신실패!!');
		            },

					dataType : 'json',
					success : function (serverdata) {
						$("#signinform").html
						(
					 	"<div class='prof_box' >"
						+ "<img class='prof_pic' alt='my profile' src='/img/" + serverdata.filepath + "'> <br>"
						+ "</div>" 
						+ serverdata.name + "("
						+ serverdata.id + ")"
/* 						+ serverdata.name + "님("
						+ serverdata.id + ") 로그인상태입니다." */
/* 						"<h3>" + serverdata.name + "님("
						+ serverdata.id + ") 로그인상태입니다. </h3><br>" */
						+ "<br> <div>"
						+ "<input id='signoutbtn' class='submit-btn' type=button value='로그아웃' text-align='center'> "
						+ "</div> <div>"
						+ "<input type='button' class='submit-btn' id='gotowriteform' value='글쓰기'>"
						+ "</div> <div>"
						+ '<input type="button" class="submit-btn" id="gotoMybookmark" value="my북마크">'
						+ "</div>"
						); // memberinf로 mapping된 getMemberInfo()에서 리턴되는 값 : vo == serverdata
						
						$("#signoutbtn").on("click", function() {
							location.href="/healthproject/signout"
						});
						
						$("#gotowriteform").on("click", function() {
							location.href="/healthproject/writeform/"
						});
						
						
						$('.submit-btn').css
						({
							'background-color': 'rgba(0, 0, 0, 0.4)',
							  'color': 'rgba(255, 255, 255, 0.7)',
							  'border': '0',
							 // 'display': 'block',
   							  'float' : 'right',
							  'text-align' : 'left',
							  'border-radius': '35px',
							  'margin-bottom': '30px',
							  'margin-right': '3px',
							  //'padding': '15px 45px',
							  //'margin': '15px auto',
							  'padding': '8px 25px',
							  'width': '8%',
							  'font-size': '13px',
							  'font-weight': 'bold',
							  //'cursor': 'pointer',
							  'opacity': '1',
							  'visibility': 'visible',
							  //'-webkit-transition': 'all .3s ease'
						})
						
						$('.submit-btn:hover').css
						({
							  'transition': 'all .3s ease',
							  'background-color': 'rgba(0, 0, 0, 0.8)'
						})
						
						$(".prof_box").css
						({
							'width':'40px', 
							'height': '40px', 
							'border-radius': '70%', 
					    	'display': 'inline-block',
							'overflow': 'hidden'
						});

						$(".prof_pic").css
						({
							'width':'100%', 
							'height': '100%', 
							'object-fit': 'cover'
						}); 
						
						$("#gotoMybookmark").on("click", function() {
				  			location.href="/healthproject/mybookmark";
						});
						
					}
				});
			})(); // function signinbtn() end
		</script>
<!-- 		<script>
		(function signinbtn() {
	/*			$.ajax({
					url : '/healthproject/memberinfo', 
 					data : { 'id':'test', 'password':'test'}, 
					type : 'POST',
					error : function(){
		                alert('insertmemberinfo JSON 통신실패!!');
		            },

					dataType : 'json',
					success : function (serverdata) {
						$("#signinform").html
						(
						"<h3>" + serverdata.name + "님("
						+ serverdata.id + ") 로그인상태입니다. </h3><br>"
						+ "<div class='profile_box' >"
						+ "<img class='profile_pic' alt='my profile' src='/img/" + serverdata.filepath + "'> <br>"
						+ "</div>"
						+ "<input id='signoutbtn' type=button value='로그아웃'> "
						+ "<input type='button' id='gotowriteform' value='글쓰기'>"
						); // memberinf로 mapping된 getMemberInfo()에서 리턴되는 값 : vo == serverdata
						
						$("#signoutbtn").on("click", function() {
							location.href="/healthproject/signout"
						});
						
						$("#gotowriteform").on("click", function() {
							location.href="/healthproject/writeform/"
						});
						
						$(".profile_box").css
						({
							'width':'150px', 
							'height': '150px', 
							'border-radius': '70%', 
							'overflow': 'hidden'
						});

						$(".profile_pic").css
						({
							'width':'100%', 
							'height': '100%', 
							'object-fit': 'cover'
						});
					}
				}); */
			})(); // function signinbtn() end
		</script> -->
		
	<% } else { %>
	<div id='first_container' align="right">
		ID : <input type="text" id="id"	name="id"> 
		PW : <input type="password" id="password" name="password">
	</div>	
		<input id="signinbtn" class='btn_css' type="button" value="로그인" >
		<input id="signupbtn" class='btn_css' type="button" value="회원가입" >
		<input type="button" class='btn_css' id="gotowriteform" value="글쓰기">

		<!-- <input id="signinbtn" class='btn_css_test' type="button" value="로그인" >
		<input id="signupbtn" class='btn_css_test' type="button" value="회원가입" >
		<input type="button" class='btn_css_test' id="gotowriteform" value="글쓰기"> -->
		<script>
			$("#gotowriteform").on("click", function() {
				alert("로그인 먼저 해주세요.");
			});
		</script>
	<% } %>
	</div>
</body>
</html>
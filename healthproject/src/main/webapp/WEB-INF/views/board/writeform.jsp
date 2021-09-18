<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewpoint" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Board Writeform</title>
<style type="text/css">
#submit {
	align: center;
}
@import url("https://fonts.googleapis.com/css?family=Fira+Sans");
html, body {
  position: relative;
  min-height: 100vh;
  background-color: #E1E8EE;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "Fira Sans", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.form-structor {
  background-color: #222;
  border-radius: 15px;
  height: 600px;
  width: 400px;
  position: relative;
  overflow: hidden;
}
.form-structor::after {
  content: '';
  opacity: .8;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-repeat: no-repeat;
  background-position: left bottom;
  background-size: 500px;
  background-color: #809EAD;
  /* background-image: url("https://images.unsplash.com/photo-1503602642458-232111445657?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=bf884ad570b50659c5fa2dc2cfb20ecf&auto=format&fit=crop&w=1000&q=100");
 */}
.form-structor .signup {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  width: 65%;
  z-index: 5;
  -webkit-transition: all .3s ease;
  margin-bottom: 20px;
}
.form-structor .signup.slide-up {
  top: 5%;
  -webkit-transform: translate(-50%, 0%);
  -webkit-transition: all .3s ease;
}
.form-structor .signup.slide-up .form-holder, .form-structor .signup.slide-up .submit-btn {
  opacity: 0;
  visibility: hidden;
  margin-top: 50px;
}
.form-structor .signup.slide-up .form-title {
  font-size: 1em;
  cursor: pointer;
}
.form-structor .signup.slide-up .form-title span {
  margin-right: 5px;
  opacity: 1;
  visibility: visible;
  -webkit-transition: all .3s ease;
}
.form-structor .signup .form-title {
  color: #fff;
  font-size: 1.7em;
  text-align: center;
}
.form-structor .signup .form-title span {
  color: rgba(0, 0, 0, 0.4);
  opacity: 0;
  visibility: hidden;
  -webkit-transition: all .3s ease;
}
.form-structor .signup .form-holder {
  border-radius: 15px;
  background-color: #fff;
  overflow: hidden;
  margin-top: 10px;
  opacity: 1;
  visibility: visible;
  -webkit-transition: all .3s ease;
}
.form-structor .signup .form-holder .input {
  border: 0;
  outline: none;
  box-shadow: none;
  display: block;
  height: 30px;
  line-height: 30px;
  padding: 8px 15px;
  border-bottom: 1px solid #eee;
  width: 100%;
  font-size: 12px;
}

#caption{
  height: 70px;
  width: 230px;
}

.form-structor .signup .form-holder .input:last-child {
  border-bottom: 0;
}
.form-structor .signup .form-holder .input::-webkit-input-placeholder {
  color: rgba(0, 0, 0, 0.4);
}
.form-structor .signup .submit-btn {
  background-color: rgba(0, 0, 0, 0.4);
  color: rgba(255, 255, 255, 0.7);
  border: 0;
  border-radius: 15px;
  display: block;
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
.form-structor .signup .submit-btn:hover {
  transition: all .3s ease;
  background-color: rgba(0, 0, 0, 0.8);
}
.form-structor .login {
  position: absolute;
  top: 20%;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #fff;
  z-index: 5;
  -webkit-transition: all .3s ease;
}
.form-structor .login::before {
  content: '';
  position: absolute;
  left: 50%;
  top: -20px;
  -webkit-transform: translate(-50%, 0);
  background-color: #fff;
  width: 200%;
  height: 250px;
  border-radius: 50%;
  z-index: 4;
  -webkit-transition: all .3s ease;
}
.form-structor .login .center {
  position: absolute;
  top: calc(50% - 10%);
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  width: 65%;
  z-index: 5;
  -webkit-transition: all .3s ease;
}
.form-structor .login .center .form-title {
  color: #000;
  font-size: 1.7em;
  text-align: center;
}
.form-structor .login .center .form-title span {
  color: rgba(0, 0, 0, 0.4);
  opacity: 0;
  visibility: hidden;
  -webkit-transition: all .3s ease;
}
.form-structor .login .center .form-holder {
  border-radius: 15px;
  background-color: #fff;
  border: 1px solid #eee;
  overflow: hidden;
  margin-top: 50px;
  opacity: 1;
  visibility: visible;
  -webkit-transition: all .3s ease;
}
.form-structor .login .center .form-holder .input {
  border: 0;
  outline: none;
  box-shadow: none;
  display: block;
  height: 30px;
  line-height: 30px;
  padding: 8px 15px;
  border-bottom: 1px solid #eee;
  width: 100%;
  font-size: 12px;
}
.form-structor .login .center .form-holder .input:last-child {
  border-bottom: 0;
}
.form-structor .login .center .form-holder .input::-webkit-input-placeholder {
  color: rgba(0, 0, 0, 0.4);
}
.form-structor .login .center .submit-btn {
  background-color: #6B92A4;
  color: rgba(255, 255, 255, 0.7);
  border: 0;
  border-radius: 15px;
  display: block;
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
.form-structor .login .center .submit-btn:hover {
  transition: all .3s ease;
  background-color: rgba(0, 0, 0, 0.8);
}
.form-structor .login.slide-up {
  top: 90%;
  -webkit-transition: all .3s ease;
}
.form-structor .login.slide-up .center {
  top: 10%;
  -webkit-transform: translate(-50%, 0%);
  -webkit-transition: all .3s ease;
}
.form-structor .login.slide-up .form-holder, .form-structor .login.slide-up .submit-btn {
  opacity: 0;
  visibility: hidden;
  -webkit-transition: all .3s ease;
}
.form-structor .login.slide-up .form-title {
  font-size: 1em;
  margin: 0;
  padding: 0;
  cursor: pointer;
  -webkit-transition: all .3s ease;
}
.form-structor .login.slide-up .form-title span {
  margin-right: 5px;
  opacity: 1;
  visibility: visible;
  -webkit-transition: all .3s ease;
}

.prof_span {
font-size: 12px;  
color: rgba(0, 0, 0, 0.4);
  border: 0;
  outline: none;
  box-shadow: none;
  display: block;
  padding-top: 8px;
  padding-left: 15px;
 }
 
.temp_img {
  padding-left: 15px;
}
 
</style>
<script src="/healthproject/resources/jquery-3.2.1.min.js"></script>
<script>

	var sel_files = [];

	$(document).ready(function() {
		$("#file").on("change", showfiles);
		
		var captions = $("#caption").val();
		captions = captions.replace(/(?:\r\n|\r|\n)/g, '<br />');
		$("#caption").html(captions);
		
		$('#cancle_btn').on("click", function() {
			location.href="/healthproject/";
		});
	});
	
	function showfiles(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}

			sel_files.push(f);

			var reader = new FileReader();
			reader.onload = function(e) {
				var img_html = "<img width='160px' height='120px' class='temp_img' src=\"" + e.target.result + "\" />";
				$("#fileplace").append(img_html);
			};

			reader.readAsDataURL(f);
			$("#filepath").val($("#file").get(0).files[0].name); 
		});
	}
	
</script>
</head>
<body>

	<%
		if (session.getAttribute("loginCheck") != null) {
			String loginid = (String) session.getAttribute("loginid");
	%>
	
	<!-- <h3 align='center'>게시물 작성하기</h3> -->
	<!-- <div id="boardform" align="center">
		
		<form action="<%-- <%=request.getContextPath() %> --%>/writeform" method="post" enctype="multipart/form-data">
			작성자 (id) :<%-- <%=loginid%> --%> <br> 
			위치선택하기 <input type="text" name="location"> <br> 
			올릴 사진 선택하기 
			<input id="file" type="file" name="file" multiple />
<!-- 			<input id="file" type="file" name="file" accept="image/*" multiple /> -->
			
			<!-- <input type="hidden" id="filepath" name="filepath" value="">
			
			<div id="fileplace"></div>
			
			
			<textarea id="caption" rows="5" cols="45" placeholder="내용을 입력해주세요..."
				value="" name="caption"></textarea>
			<input type="hidden" id="id" name="id" value="<%-- <%= loginid%> --%>">
				
			<div id="submit">
				<input type="submit" value="게시">
			</div>
		</form>
	</div>-->
	
	
			<div class="form-structor">
				<div class="signup">
					<h2 class="form-title" id="signup">오늘의 인증!</h2>
					<form action="<%=request.getContextPath() %>/writeform" method="post" enctype="multipart/form-data">
						<div class="form-holder">
							<input type="text" class="input" id="name" placeholder="위치" name="location" />
							<span class='prof_span'>업로드 사진</span> <input id="file" class="input" type="file" name="file" multiple />
							<input type="hidden" id="filepath" name="filepath" value="">

							<div id="fileplace" ></div>
							
							<textarea id="caption" class="input" rows="3" cols="25" placeholder="내용을 입력해주세요..."
								value="" name="caption"></textarea>
								
							<input type="hidden" id="id" name="id" value="<%= loginid%>">
						</div>
						<input id="submit" class="submit-btn" type="submit" value="인증하기">
					</form>
					<button id="cancle_btn" class="submit-btn">취소하기</button>
<!-- 					<input id="cancle" class="submit-btn" type="button" value="취소하기"> -->
					<!-- <button class="submit-btn">Sign up</button> -->
				</div>
				<!-- <div class="login slide-up">
					<div id="signinbtn" class="center">
						<h2 class="form-title"><span>or</span>로그인</h2>
					</div>
				</div> -->
			</div>
		

	<%
		} else {
	%>
	<script>
		alert("로그인을 해주세요.");
		(function() {
			location.href = "/healthproject/signin/"
		})();
	</script>
	<%
		}
	%>
</body>
</html>
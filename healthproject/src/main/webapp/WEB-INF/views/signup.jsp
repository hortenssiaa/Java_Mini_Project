<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up Page</title>
<script src="/healthproject/resources/jquery-3.2.1.min.js"></script>
<style type="text/css">
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
  height: 550px;
  width: 350px;
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
  background-image: url("https://images.unsplash.com/photo-1503602642458-232111445657?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=bf884ad570b50659c5fa2dc2cfb20ecf&auto=format&fit=crop&w=1000&q=100");
}
.form-structor .signup {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  width: 65%;
  z-index: 5;
  -webkit-transition: all .3s ease;
}
.form-structor .signup.slide-up {
  top: 5%;
  -webkit-transform: translate(-50%, 0%);
  -webkit-transition: all .3s ease;
}
.form-structor .signup.slide-up .form-holder, .form-structor .signup.slide-up .submit-btn {
  opacity: 0;
  visibility: hidden;
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
  margin-top: 20px;
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
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#signinbtn").on("click", function() {
			location.href="/healthproject/signin/"
		});

		/* $("#signupbtn").on("click", function() {
			alert("회원가입이 되었습니다. 로그인을 해주세요.")
			location.href="/healthproject/signin/"
		}); */
	})
</script>
</head>
<body>
<!-- 
	<form class='signupForm' action="<%-- <%=request.getContextPath() %> --%>/signup" method="post" enctype="multipart/form-data" >
		이름 : <input type="text" name="name"> <br>
		ID : <input type="text" name="id"> <br>
		PW : <input type="password" name="password"> <br> <!-- 반드시 db? vo? 랑 같은 이름으로 할것 !! 알아보기 password -->
<!-- 	email : <input type="email" name="email"> <br>
		프로필 사진 <input type="file" name="file"> <Br>
		<input type="submit" value="회원가입" >
		<button id="signinbtn" type="button" >로그인</button>
	</form>  -->
	
	 <form class='signupForm' action="<%=request.getContextPath() %>/signup" method="post" enctype="multipart/form-data" >
		<div class="form-structor">
			<div class="signup">
				<h2 class="form-title" id="signup">회원가입</h2>
				<div class="form-holder">
					<input type="text" class="input" id="name" placeholder="이름" name="name" />
					<input type="text" class="input" id="id" placeholder="아이디" name="id" />
					<input type="password" class="input" id="password" placeholder="비밀번호" name="password" />
					<input type="email" class="input" placeholder="이메일" />
					<span class='prof_span'>프로필 사진</span><input type="file" class="input" name="file"/>
				</div>
				<input id="signupbtn" class="submit-btn" type="submit" value="회원가입">
				<!-- <button class="submit-btn">Sign up</button> -->
			</div>
			<div class="login slide-up">
				<div id="signinbtn" class="center">
					<h2 class="form-title"><span>or</span>로그인</h2>
				</div>
			</div>
		</div>
	 </form>
</body>
</html>
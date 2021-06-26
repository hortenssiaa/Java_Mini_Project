<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Bookmark List</title>
<script src="/healthproject/resources/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#go_home').on("click", function() {
			location.href="/healthproject/";
		});

		$('#go_edit').on("click", function() {
			location.href="/healthproject/writeform";
		});
		
		$('#go_star').on("click", function() {
			location.href="/healthproject/mybookmark";
		});
		
		$('#go_maps').on("click", function() {
			location.href="/healthproject/categorylist";
		});
	});
</script>
<style type="text/css">
	html {
		/* background-color: #FAFAFA; */
		background-color: #E1E8EE;
	}
	
	h1 {
		margin-top: 60px;
		text-align: center;
	}
	
	hr {
		margin-bottom: 10px;
		max-width: 500px;
	}
	
	.board_photo {
		width: auto; height: auto;
    	max-width: 350px;
    	max-height: 350px;
    	align-content: center;
	}
	
	.board_pic {
		text-align: center;
	}
	
	table {
		width: 34%;
		border-collapse: collapse;
		border: 1px solid #D7D7D7;
		background-color: #FFFFFF;
	}
	
	th, td {
	  padding: 8px;
	  text-align: left;
	  border-bottom: 1px solid #ddd;
	}
	
	tr:hover {
		background-color: #f5f5f5;
	}
	
	#id_style {
		font-weight: bold;
	}
	
	#gohome{
		padding: 30px;
		margin-bottom: 10px;
	}

	.first_icons {
		width: 35px;
		height: 35px;
		margin-right: 50px;
		margin-bottom: 10px;
	}
</style>
</head>
<body>
	<%
		String loginid = "";
		if(session.getAttribute("loginCheck") != null) {
			loginid = (String) session.getAttribute("loginid");
			System.out.printf("loginid: %s",loginid);
		} else
			loginid = "notsignedinnull";
	%>
	
	<h1> <%=loginid%>님의 북마크</h1>
	<hr>
	
	
	<div id='upper_navi'>
		<div id="gohome" align='center'>
			<img id='go_home' class='first_icons' alt="home" src="./resources/home2.png">
			<img id='go_edit' class='first_icons' alt="write" src="./resources/edit.png">
			<img id='go_star' class='first_icons' alt="bookmark" src="./resources/star22.png">
			<img id='go_maps' class='first_icons' alt="map" src="./resources/location2.png">
		</div>
	</div>
	
	<div class="list_container">
		<c:set var="listsize" value="${fn:length(boardlist)}" /> <!--  역순 -->
			<c:forEach var="i" begin="1" end="${listsize}"> 
			 	<table align="center">
					<tr>
						<td> 
							<div id="id_style">
								${boardlist[listsize-i].id } 
							</div>
						</td>
					</tr>
					<tr>
						<td> ${boardlist[listsize-i].location } </td>
					</tr>
					<tr>
						<td> 
							<div class="board_pic">
								<img class="board_photo" alt="board pic" src="/img/${boardlist[listsize-i].filepath }">  
							</div>
						</td>
					</tr>
					<tr>
						<td> ${boardlist[listsize-i].caption } </td>
					</tr>
				</table>	
			<br> <br> <br>
		</c:forEach>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././resources/css/boardlist.css" type="text/css">
<title>Insert title here</title>
<style type="text/css">
#samplelists {
	border-radius: 10px;
}

.profile_box {
	display: inline-block;
}

.id_style {
	display: inline-block;
	margin-left: 5px;
	font-size: medium;
	color: #242424;
}

.loc {
	color: #919595;
	font-size: small;
}

table {
	width: 34%;
	border-collapse: collapse;
	border: 1px solid #D7D7D7;
	background-color: #FFFFFF;
	border-radius: 10px;
	align: right;
}


#caption {
	color: #242424;
	font-size: small;
}

#more{
	text-align: center;
	background-color: #ddd;
	font-size: small;
}
</style>
</head>
<body>
	<script>
//	var boardlistLen = 0,
	var count = 0,
		idList = [];
	
	$(document).ready(function() {
		$.ajax({
			url : '/healthproject/boardlistFH',
			type: 'post',
			
			dataType: 'json',
			async: false,
			success: function(boardlist) {
				boardlistLen = boardlist.length;
				
				$("#tables").empty();
				
				for(var i = boardlist.length-1; i > boardlist.length-4; i--) {
					var table;
					idList[count] = boardlist[i].id;

					table = '<tr> <td> <div class="profile_box"> ' 
					+ '<img id="profile_pic" name="boardprof" class="prof_' + count + '" src="" alt="${boardlist[listsize-i].id}\'s profile"> </div> '
					+ '<div class="id_style"> <div> ' + boardlist[i].id + '</div> <div class="loc">'
					+ boardlist[i].location + '</div> </div> </td> </tr> '
					+ '<tr> <td id="caption"> ' + boardlist[i].caption + '</td> </tr> ';
					          
					$("#tables").append(table);
					count += 1;
					
					if(i==boardlist.length-3) {
						var more = '<tr> <td id="more"> 전체보기 </td> </tr> ';
						$("#tables").append(more);
					}
				}
			}
		}); // boardlistFH ajax end
		
		$.ajax({
			url : '/healthproject/getboardlistprofile', 
			type : 'POST',

			dataType : 'json',
			async: false,
			success : function (serverdata) {
				
				for(var i = 0; i < idList.length; i++) {
					for(var j=0; j<serverdata.length; j++) {
						if(idList[i] == serverdata[j].id) {
							var profile_path = '/img'+serverdata[j].filepath+'?timestamp=' + new Date().getTime();
							$('.prof_'+i).attr('src', profile_path);
							//console.log('filepath: ' + serverdata[j].filepath);
						}
					}
				} 
			}
		}); // get board list profile pictures _ $.ajax end   */
		
		
		$("#more").on("click", function() {
			location.href="/healthproject/boardlist";
		});
	});
	
	</script>
	
	<div id='samplelists'>
		<table align="right" id="tables" style="border-radius: 8px"> </table>
	</div>
</body>
</html>
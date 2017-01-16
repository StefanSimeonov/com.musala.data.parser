<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("form").submit(function(event) {
					event.preventDefault();
					var id = $("input[name='id']").val();
					var name = $("input[name='name']").val();
					$.ajax({
						type : 'POST',
						url : 'AjaxController',
						data : {
							id:id,
							name:name,
							funcRequest:"third",
						},
						success : function(result) {
							console.log(result.status);
							if (result.status) {
								var res = result.username;
								$("p").text(result.message);
								setTimeout(function() {
									window.location.replace("index.jsp");
								}, 2000);
			
							} else {
								console.log(result.message);
								$("form").empty();
								$("#answear").text(result.message);
								setTimeout(function() {
									window.location.replace("index.jsp");
								}, 3000);
							}
						}
					});
					return false;
				});
			});
		</script>
	</head>
	<body>
		<form >
			<p>
				Enter the id you want:<br> <input type='text' name='id'>
			<p>Enter the name you want:</p>
			<input type='text' name='name'><br>
			<input type="submit" name='submit' value='Submit query'>
		</form>
		<p id="answear"></p>
	</body>
</html>
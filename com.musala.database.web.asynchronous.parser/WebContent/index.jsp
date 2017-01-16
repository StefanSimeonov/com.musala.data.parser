<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Test</title>
		<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("form").submit(function(event) {
					event.preventDefault();
					var serverName = $("input[name='ServerName']").val();
					var databaseName = $("input[name='DatabaseName']").val();
					var username = $("input[name='Username']").val();
					var password = $("input[name='Password']").val();
					console.log(serverName);
					$.ajax({
						type : 'POST',
						url : 'AjaxController',
						data : {
							serverName : serverName,
							databaseName : databaseName,
							username : username,
							password : password,
							funcRequest : "first",
						},
						success : function(result) {
							console.log(result.status);
							if (result.status) {
								var res = result.username;
								$("p").text(result.status + " " + "operation");
								setTimeout(function() {
									window.location.replace("second.jsp");
								}, 1000);
		
							} else {
								$("p").text(result.status + " " + "operation");
							}
						}
					});
					return false;
				});
			});
		</script>
	</head>
	<body>
		<form>
			Please enter the server name:<br> <input type='text' id="5"
				name='ServerName' value='localhost'><br> Please enter
			database name:<br> <input type='text' name='DatabaseName'
				value='schools'><br> Please enter username:<br> <input
				type='text' name='Username' value='root'><br> Please
			enter user's password:<br> <input type='text' name='Password'
				value=''><br> <br> <input type="submit"
				value="Connect">
		</form>
		<p style="color: red"></p>
	</body>
</html>
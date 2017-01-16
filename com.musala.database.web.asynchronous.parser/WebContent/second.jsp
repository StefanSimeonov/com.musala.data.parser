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
					var queriesType = $("select[name='queriesType']").val();
					var tableName = $("select[name='tableName']").val();
					var properties = $("input[name = 'properties']").val();
					$.ajax({
						type : 'POST',
						url : 'AjaxController',
						data : {
							queriesType : queriesType,
							tableName : tableName,
							properties : properties,
							funcRequest : "second",
						},
						success : function(result) {
							console.log(result.status);
							if (result.status) {
								$("form").empty();
								$("#answear").text(result.message);
								setTimeout(function() {
									window.location.replace("third.jsp");
								}, 2000);
		
							} else {
								$("form").empty();
								$("#answear").text(result.message);
								setTimeout(function() {
									window.location.replace("index.jsp");
								}, 2000);
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
			<p>Enter type of query your want:</p>
			<select name='queriesType'><option value='getAllRecords'>getAllRecords</option>
				<option value='getRecordById'>getRecordById</option>
				<option value='getRecordByName'>getRecordByName</option>
				<option value='closeTheQuery'>close</option></select>
			<p>Enter table you want:</p>
			<select name='tableName'><option value='schools'>schools</option>
				<option value='schoolclasses'>school classes</option>
				<option value='students'>students</option>
				<option value='teachers'>teachers</option></select>
			<p>Please enter the record's properties you want, splited by
				space:</p>
			<input type='text' name='properties'> <br> <br> <input
				type='submit'>
		</form>
		<p id="answear"></p>
	</body>
</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conference Room Booking</title>
</head>
<body>
	<h3 style="color: red;">Enter Booking List</h3>

	<div id="addBooking">
		<form:form action="/scheduleBooking" method="post"
			modelAttribute="bookingDetails">
			<p>
				<form:textarea rows="20" cols="100" path="" name="bookingList" />
			</p>
			
			<input type="SUBMIT" value="Submit" />
		</form:form>
	</div>
</body>
</html>

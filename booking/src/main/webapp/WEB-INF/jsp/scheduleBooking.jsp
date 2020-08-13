<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conference Room Booking</title>
</head>
<body>
	<h3 style="color: red;">Scheduled Booking List</h3>

	<div id="addBooking">
		<form:form action="/scheduleBooking" method="post"
			modelAttribute="map">
			<p>

				<c:forEach var="map" items="${map}">

					<p>${map.value}</p>
					<c:forEach var="booking" items="${map.key}">

						<p>${booking.talk}</p>
					</c:forEach>
				</c:forEach>
			</p>


		</form:form>
	</div>
</body>
</html>

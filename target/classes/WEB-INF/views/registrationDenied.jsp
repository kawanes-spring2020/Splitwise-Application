<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>AccessDenied page</title>
</head>
<body>
	<div class="generic-container">
		<div class="authbar">
			<span>Dear <strong>User</strong>, You are not authorized to sign up with ADMIN/DBA in Splitwise.</span> <span class="floatRight"><a href="<c:url value="/register" />">Go back to Registration Page</a></span>
		</div>
	</div>
</body>
</html>
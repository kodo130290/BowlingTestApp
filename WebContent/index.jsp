<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bowling APP</title>
    <link rel=stylesheet type='text/css' href='${pageContext.request.contextPath}/appstyle/style.css'/>
</head>

<body>
	<h1>Bowling test application</h1>
	<form action='main' method=get >
		<button>Initiate new game</button>
	</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/autoredirect.js"></script>
    <link rel=stylesheet type='text/css' href='${pageContext.request.contextPath}/appstyle/style.css'/>
    <title>Error</title>
</head>

<body>
<div class="box">
    <div class="inner">
        <div class="form">
            <h1>An error occurred</h1><br><br>
            <span style="color:green;">Please see logs for details!!! </span><br><br>
            <span style="color:green;">U will be redirected to the main page in ten seconds.</span><br><br>
            <br>
            <table>
		        <tr>
			        <td class="item"><a id="linkToMain" href="${pageContext.request.contextPath}" title="Back to main NOW!!!">Back to main NOW!!!</a></td>
		        </tr>
		    </table>
        </div>
    </div>
</div>
</body>
</html>
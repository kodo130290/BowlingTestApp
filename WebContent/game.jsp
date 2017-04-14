<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bowling Game page</title>
    <link rel=stylesheet type='text/css' href='${pageContext.request.contextPath}/appstyle/style.css'/>
    <script type="text/javascript" src="js/numberFilter.js"></script>
</head>

<body>
	<h1>Bowling test application</h1>
	<form action='game' method=get style="display:${isTextFieldHidden};" >
	    <div id=warning>
	    	<b>${warn}</b>
	    </div>
	    <b id=textField>Enter number of pins: </b>
		<input type = "text" name = pins onkeypress = "return OnlyNum(event)" />
		<button>Ok</button>
	</form>
	<div id=table align='center'>
        <h3>${resultMessage}</h3>
        <table>
        	<tr>
        		<% for(int i = 1; i <= 10; i++) { %>
        		<th>Frame #<%=i%> </th> 
        		<% } %>
        	</tr>
        	<tr>
	            <c:forEach items="${framesToDisplay}" var="pinsToDisplay">                
	                    <td>${pinsToDisplay.toString()}</td>               
	            </c:forEach>
            </tr>
        	<tr>
	            <c:forEach items="${resultsByFrames}" var="byFrame">                
	                    <td>${byFrame.toString()}</td>               
	            </c:forEach>
            </tr>
        </table>
    </div>
    <div id="menu">
        <jsp:include page="menu.jsp"/>
    </div>

</body>
</html>
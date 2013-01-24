<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Pubs</title>
        <meta name="decorator" content="admin" />
    </head>
    <body>
        <ul>
            <c:forEach var="race" items="${races}">
                <li>${race}</li>
            </c:forEach>
        </ul>
    </body>
</html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 01/10/12
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit ${race.name}</title>
        <meta name="decorator" content="admin" />
    </head>
    <body>
    <c:if test="${SUCCESS_MESSAGE != null}">
        <div class="alert alert-success">${SUCCESS_MESSAGE}</div>
    </c:if>
    <c:set var="actionUrl" value="/admin/races${race.id != null ? '/'+pub.id : ''}/save"/>
    <spring:form commandName="race" action="${actionUrl}">
        <spring:errors />
        <spring:hidden path="id"/>
        <ul>
            <li><span>Name<span><span><spring:input path="name"/><span></li>
            <li><span>Description<span><span><spring:textarea path="description" /><span></li>
        </ul>
        <input id="submit" type="submit" class="btn btn-primary" value="Save">
        <a href="/admin/races/${race.id}" class="btn">Cancel</a>
    </spring:form>
    <h1>${actionUrl}</h1>
</body>
</html>
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
            <li>
                <span>starting units</span>
                <ul>
                    <c:forEach items="${UNITS}" var="unit">
                        <c:set var="name" value="startingUnits['${unit}']" />
                        <li>
                            <label for="${name}">${unit}</label>
                            <input name="${name}" value="${race.startingUnits[unit]}"/>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li>
                <span>starting technologies</span>
                <ul style="clear:right; overflow:auto">
                    <c:forEach items="${technologies}" var="technology" varStatus="stat">
                        <li style="width: 200px; float:left">
                            <c:set var="id" value="chk${technology.name}" />
                            <label for="${id}">${technology.name}</label>
                            <input id="${id}" type="checkbox" name="startingTechnologies[${stat.index}]" value="${technology.id}"/>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li>
<!--                <span>Special Abilites</span>
                <ul>
                    <li>
                        <textarea name="specialAbilities"></textarea>
                    </li>
                    <li>
                        <textarea name="specialAbilities"></textarea>
                    </li>
                    <li>
                        <textarea name="specialAbilities"></textarea>
                    </li>
                    <li>
                        <textarea name="specialAbilities"></textarea>
                    </li>
                </ul>-->
            </li>
        </ul>
        <input id="submit" type="submit" class="btn btn-primary" value="Save">
        <a href="/admin/races/${race.id}" class="btn">Cancel</a>
    </spring:form>
    <h1>${actionUrl}</h1>
</body>
</html>
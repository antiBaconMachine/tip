<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title><decorator:title default="Twilight Imperium Race Picker" /></title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-2.1.1.min.css" />
        <link rel="stylesheet" type="text/css" href="/css/tip.less" />
        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script><![endif]-->

    </head>
    <body>
        <div class="container-fluid" id="container">
            <div class="span12 row-fluid" id="header">
                <div id="titleWrapper" class="span7">
                    <div class="span7" id="title">
<!--                        <h1><a href="/" id="homeLink" class="routeTrigger">Twilight Imperium Race Picker</a></h1>-->
                        <h1><a href="/" id="homeLink" class="routeTrigger">TIP</a></h1>
                    </div>
                </div>
                <div class="span2">&nbsp;</div>
                <div class="span3">

                    <%--c:url value="/j_spring_security_logout" var="logoutUrl"/>
                    <sec:authorize access="isAuthenticated()"> 
                        <div id="userDetails" class="row-fluid span3">
                            <div id="accounts" class="span9">
                                <c:forEach items="${principalUser.connections}" var="connection">
                                    <div class="account">
                                        <img class="socialIcon" src="/images/social/${connection.providerId}_16.png"/>
                                        <a class="socialProfile routeTrigger" href="#/user/${connection.userId}"><c:out value="${connection.displayName}"/></a>
                                        <a class="socialThumbnail" href="${connection.profileUrl}" target="_blank"><img src="${connection.imageUrl}" border="0"/></a>
                                    </div>
                                </c:forEach>
                            </div>
                            <ul class="span3">
                                <li><a href="${logoutUrl}">Log Out</a></li>
                                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                    <li><a href="/admin/">Admin</a></li>
                                </sec:authorize>
                            </ul>
                        </div>
                    </sec:authorize--%>
                </div>
            </div>

            <decorator:body/>

        </div>


<!--        <script type="text/javascript">

            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-31300940-3']);
            _gaq.push(['_trackPageview']);

            (function() {
                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
            })();

        </script>-->
    </body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title><decorator:title default="Brighton Pub Challenge" /></title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-2.1.1.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tip.less" />
        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script><![endif]-->

    </head>
    <body>
        <div class="container-fluid" id="container">
<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="#">TIP Admin</a>
    <ul class="nav">
      <li><a href="/">Home</a></li>
      <li><a href="/admin/races/create">Create</a></li>
<!--        <li><a href="/j_spring_security_logout">Log Out</a></li>-->
    </ul>
  </div>
</div>
            <div class="row-fluid">
                <div class="span12">
                    <decorator:body/>
                </div>
            </div>
                    <div id="push">&nbsp;</div>
        </div>
        <div id="footer" class="container-fluid">

        </div>
    </body>
</html>
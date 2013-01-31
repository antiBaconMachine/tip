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
        <title>Edit ${match.name}</title>
        <meta name="decorator" content="main" />
    </head>
    <body>
    <c:if test="${SUCCESS_MESSAGE != null}">
        <div class="alert alert-success">${SUCCESS_MESSAGE}</div>
    </c:if>
    <c:set var="actionUrl" value="/match/${match.id}/save"/>
    <div id="matchContent"></div>
    <script type="text/javascript">
            var require = {
                config: {
                    options : { 
                        jsDebugLevel : "${jsDebugLevel}",
                        matchJSON : '${matchJSON != null ? matchJSON : "null"}'
                    }
                }
            }
        </script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/require.js/2.1.1/require.${debugLevel != 'off' ? '' : 'min.'}js" 
        data-main="/js/${debugLevel != 'off' ? 'main' : 'build'}/main-player.js"></script>
</body>
</html>
<%-- 
    Document   : home.jsp
    Created on : 23-Jan-2013, 15:44:16
    Author     : Ollie Edwards <oliver.s.edwards@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="decorator" content="main" />
        <title>Twilight Imperium Race Picker</title>
    </head>
    <body>
        <div id="ajaxSpinner">
            <img src="/images/spinner.gif"/>
        </div>
        <div id="raceApp" class="row-fluid">
            <div class="span9">
                <div id="appView">

                </div>
            </div>
            <div class="span3 sidebar">

            </div> <!-- sidebar end-->
        </div>
        <script type="text/javascript">
            var require = {
                config: {
                    options : { 
                        jsDebugLevel : "${jsDebugLevel}",
                        bootstrappedModels : ${races != null ? races : "null"}
                    }
                }
            }
        </script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/require.js/2.1.1/require.${debugLevel != 'off' ? '' : 'min.'}js" 
        data-main="/js/${debugLevel != 'off' ? 'main' : 'build'}/main.js"></script>
    </body>
</html>

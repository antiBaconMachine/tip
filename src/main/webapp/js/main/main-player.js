requirejs.config({
    baseUrl : "/js/main/",
    paths : {
        jquery: "http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min",
        underscoreSource: "lib/underscore", 
        backboneSource: "lib/backbone",
        underscore : "lib/underscoreWrapper",
        backbone : "lib/backboneWrapper",
        text     :  "lib/text"
    },
    shim : {
        jquery : {
            exports : "$"
        },
        backboneSource: {
            deps: ['jquery','underscore'],
            exports: 'Backbone'
        },
        underscoreSource : {
            exports : '_'
        }
    },
    waitSeconds: 15
}); 
require(['jquery','lib/abm-log','router/MatchRouter','options', "underscore"], 
    function($, log, Router, options, _) {
        
        $(function() {
            log.init(options.jsDebugLevel);    
            new Router({
            });
        });
    });
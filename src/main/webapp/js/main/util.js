define(['underscore','backbone','options'], function(_, Backbone, options) {
    
    return {
        
        url : function() {
            return options.restPrefix;
        },
        
        dispatcher : _.clone(Backbone.Events)
    }
});



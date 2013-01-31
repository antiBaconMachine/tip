define(['jquery', 'underscore', 'backbone', 'util'],
function($, _, Backbone, util) {
    return Backbone.Model.extend({
        
        defaults : {
            name : "",
            players : []
        },
        
        initialize : function() {
           
        }
    });
});
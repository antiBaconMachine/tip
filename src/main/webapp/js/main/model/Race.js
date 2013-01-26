define(['jquery', 'underscore', 'backbone', 'util'],
function($, _, Backbone, util) {
    return Backbone.Model.extend({
        
        defaults : {
            name : "",
            description : "",
            startingUnits : [],
            startingTechnologies : [],
            specialAbilities : []
        },
        
        initialize : function() {
           
        }
    });
});
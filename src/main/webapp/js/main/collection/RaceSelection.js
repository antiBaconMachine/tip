define(['jquery', 'underscore', 'backbone', 'util', 'model/Race'], 
    function($, _, Backbone, util, Race) {
        return Backbone.Collection.extend({
          
            //reference to the type of model object stored in collection
            model : Race,
        
            initialize : function(models, match) {
                if (!match || !match.id) {
                    throw "No match supplied for raceSelection";
                }
                this.match = match;
            },
        
            url : function(){
                return util.url() + "match/" + this.match.get("handle") + "/raceSelection"
            }
          
        });
    });


define(['jquery', 'underscore', 'backbone', 'util', 'model/Race'], 
    function($, _, Backbone, util, Race) {
        return Backbone.Collection.extend({
          
            //reference to the type of model object stored in collection
            model : Race,
        
            initialize : function(match) {
                if (!match || !match.id) {
                    throw "No match supplied for raceSelection";
                }
                this.match = match;
            },
        
            url : function(){
                return util.url() + "match/" + this.match.id + "/raceSelection"
            },
            
            fetch : function() {
                return [
                    {id:1,name:"bar"},
                    {id:2,name:"spam"},
                    {id:3,name:"eggs"}
                ]
            }
          
        });
    });


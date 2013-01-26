define(['jquery', 'underscore', 'backbone', 'util', 'model/Race'], 
    function($, _, Backbone, util, Race) {
        return Backbone.Collection.extend({
          
            //reference to the type of model object stored in collection
            model : Race,
        
            initialize : function() {
                console.debug("initialized pubs collection",this);
            },
        
            url : function(){
                return util.url()+"races/"
            }
          
        });
    });


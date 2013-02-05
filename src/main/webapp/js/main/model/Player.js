define(['jquery', 'underscore', 'backbone', 'util'],
function($, _, Backbone, util) {
    return Backbone.Model.extend({
        
        defaults : {
            name    : "",
            race    : null
        },
        
        initialize : function(attributes, match) {
            _.extend(this.attributes, attributes || {});
            this.match = match;
            this.bind("sync", _.bind(function(){
                console.debug("Handling player sync event");
                match.fetch();
            },this), match);
        },
        
        url : function() {
            var url = util.url() + "match/" + this.match.get("id") + "/addPlayer";
            console.debug("Player model url %s", url);
            return url;
        }
    });
});
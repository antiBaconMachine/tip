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
            this.bind("sync", match.fetch, match);
        },
        
        url : function() {
            return util.url() + "match/" + this.match.get("id") + "/addPlayer"
        }
    });
});
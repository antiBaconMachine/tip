define(['jquery', 'underscore', 'backbone', 'util'],
function($, _, Backbone, util) {
    return Backbone.Model.extend({
        
        defaults : {
            matchView   : null,
            name    : "",
            race    : null
        },
        
        url : function() {
            return util.url() + "/match/" + this.get("match").id + "/addPlayer"
        }
    });
});
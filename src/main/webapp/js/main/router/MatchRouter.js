define(function(require) {
    
    var     $       = require('jquery'), 
    _               = require('underscore'), 
    Backbone        = require('backbone'),
    Races           = require('collection/Races'),
    options         = require('options'),
    PlayerView      = require('view/Player'),
    BaseRouter      = require('router/BaseRouter'),
    Match           = require('model/Match'),
    MatchView       = require('view/Match'),
    util            = require('util');
            
    /**
     * ATM the only way we load matches is as part of jsp so routing is quite unnescesary
     * 
     * It might not be in future and seems sensible to keep initialization code
     * for edit Match form here to ease future migration
     */
    return BaseRouter.extend({
        
        routes : {
            "match/:id" : "viewMatch" 
        },
        
        match : null,
        
        initialize : function() {
            var matchJSON = options.matchJSON;
            var currentRoute = this.getCurrentRoute();
            if (!matchJSON) {
                 throw "TODO: get match id from route and fetch a valid match object"
            }
            this.match = new Match(JSON.parse(matchJSON));
            //console.debug("initialising with route %s", currentRoute);
            Backbone.history.start({
                pushState : options.pushState
            });
            this.navigate("match/" + this.match.get("id"), this.NAV.REPLACE_AND_TRIGGER);
            _.bindAll(this);
        },
        
        viewMatch : function(id) {
            console.debug("view match route triggered %o", this.match);
            //var player = new PlayerView($("#matchproperties")).render();
            new MatchView(this.match).render();
        }
        
    });       
    
});
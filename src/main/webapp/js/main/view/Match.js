define(function(require) {
    var     $       = require('jquery'), 
    _               = require('underscore'), 
    Backbone        = require('backbone'),
    RaceSelection   = require('collection/RaceSelection'),
    options         = require('options'),
    Player          = require('model/Player'),
    PlayerView      = require('view/Player'),
    template        = require('text!template/match.html!strip'),
    util            = require('util'); 
            
    return Backbone.View.extend({
        
        insert : $("#matchContent"),
        
        template : _.template($(template).html()),
        
        initialize : function(match) {
            console.debug("init Match view %o", arguments);
            this.model = match;
            this.model.bind('sync', this.render, this);
//            this.model.bind('destroy', this.remove, this);
        },
        
        render : function() {
            var html = this.template({match : this.model});
            console.debug("rendering match element %o ", this.insert);
            this.insert.html(html);
            var player = new Player({}, this.model); 
            new PlayerView(this.insert.find("#raceSelection"), player, this.model).render();
            return this;
        }
    });
}); 
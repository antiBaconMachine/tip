define(function(require) {
    var     $               = require('jquery'), 
            _               = require('underscore'), 
            Backbone        = require('backbone'),
            Races           = require('collection/Races'),
            options         = require('options'),
            playerTemplate  = require('text!template/enterPlayer.html!strip'),
            Player          = require('model/Player'),
            RaceSelection   = require('collection/RaceSelection'),
            util            = require('util'); 
            
     return Backbone.View.extend({
         
        template : _.template($(playerTemplate).html()),
        
        initialize : function($listElement, model, match) {
            this.$listElement = $listElement;
            this.model =  model;
            this.match = match;
            this.bind("sync", match.fetch, match);
            _.bindAll(this);
        },
        
        render : function() {
            var races = new RaceSelection({},this.match);
            races.fetch();
            console.debug("rendering player with raceSelection %o, %o", races);
            console.debug("rendering player with raceSelection %o, %o", races, _.pluck(races, "name"));
            var html = this.template({
                races : races
            });
            console.debug("rendering player with races html {}", html);
            this.$listElement.append(html);
            $("a.raceSelect").on("click", this.createPlayer);
            //return this;
        },
        
        createPlayer : function() {
            console.debug("creating new player");
            var name = $("#newPlayer").val();
            var race = {
                id : $("#raceId")
            }
            this.model.save({
                name : name,
                race : race
            });
        }
        
     });
}); 
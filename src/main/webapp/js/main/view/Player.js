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
            _.bindAll(this);
        },
        
        render : function() {
            var races = new RaceSelection({},this.match);
            races.fetch({
                success : _.bind(function(resp, arrModels) {
                    console.debug("rendering player with raceSelection %o, %o", _.pluck(arrModels, "name"), arguments);
                    
                    var html = this.template({
                        races : arrModels
                    });
                    //console.debug("rendering player with races html {}", html);
                    this.$listElement.append(html);
                    $("a.raceSelect").on("click", this.createPlayer);
                }, this)
            });
            
        return this;
        },
        
        createPlayer : function() {
            console.debug("creating new player");
            var name = $("#newPlayerInput").val();
            var race = {
                id : $("#raceId").val()
            }
            this.model.save({
                name : name,
                race : race
            });
        }
        
    });
}); 
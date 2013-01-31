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
        
        initialize : function($listElement, model) {
            this.$listElement = $listElement;
            this.model =  model;
            //TODO this is made of nightmare, fix it
            this.bind("sync", model.get("matchView").model.fetch, model.get("matchView").model);
            _.bindAll(this);
        },
        
        render : function() {
            //var races = new RaceSelection().fetch();
            var html = this.template({
                races : [{id:1,name:"foo"},{id:2,name:"bar"}]
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
define(function(require) {
        
        var $           = require("jquery"),
            _           = require("underscore"),
            Backbone    = require("backbone"),
            RaceView    = require("view/Race"),
            BaseAppView = require("view/BaseApp"),
            template    = require("text!template/list.html!strip")
    
        return _.bindAll(BaseAppView.extend({
            
            el : $("#appView"),
        
            template : _.template($(template).html()),
        
            races : null,
        
            filter : null,
        
            initialize : function(params) {
                this.races = params.races;
            },
            
            render : function(fast,cb) {
                console.info("rendering list view", this.races);
                cb = cb || function(){};
                var func = _.bind(function() {
                    this.transition(_.bind(function(){
                        this.paint();
                        cb();
                    },this), fast);
                },this);
                if (!this.races.length) {
                    //this.races.fetch({success : func});
                } else {
                    func();
                }
            },
            
            paint : function() {
                this.addAll();
            },
        
            addOne : function(race) {
                var view = new RaceView({
                    model : race
                });
                $("#racesList").append(view.render().el);
            },
        
            addAll : function() {
                $("#racesList").html("");
                console.debug("adding all races");
                var self = this;
                this.races.each(function(pub, i) {
                    pub.set("index", i+1);
                    self.addOne.call(self,pub);
                });
            }
            
        }, this));
    });
define(function(require) {
    var     $           = require('jquery'), 
            _           = require('underscore'), 
            Backbone    = require('backbone'),
            Races       = require('collection/Races'),
            options     = require('options');
            ListView    = require('view/List'),
            util        = require('util');

        return Backbone.Router.extend({
            NAV_OPTIONS : {
                REPLACE_AND_TRIGGER : {
                    replace : true, 
                    trigger : true
                }
            },
            
            routes : {
                "list" : "list"
            },
            
            activeView : null,
            
            initialize : function(params) {
                this.options = options;
                console.debug("effective params %o", this.options);
                _.bindAll(this);
                this.activeView = params.activeView || "browse";
                var currentRoute = this.getCurrentRoute();
               
                
                $("body")
                //assign navigation handlers
                .on("click",".routeTrigger",this.handleRoute)
                //Catch clicks on body as global clear all event
                .on("click", function(event) {
                    util.dispatcher.trigger("body:click", event); 
                });
                
                //ajax spinner setup
                var $spinner = $("#ajaxSpinner");
                $(document)
                .on("ajaxStart", function(){
                    $spinner.show()
                })
                .on("ajaxStop", function(){
                    $spinner.hide()
                });
                
                this.races = new Races();                
                
                //if the page contains bootstrapped models add them to collection
                if (this.options.bootstrappedModels) {
                    this.races.reset(this.options.bootstrappedModels);
                    this.onModels();
                } else {
                    this.races.fetch({
                        success : this.onModels
                    });
                }
                this.options.bootstrappedModels={};
                return this;
            },
            
            /**
             * Called when the models become available for the first time
             */
            onModels : function() {
                Backbone.history.start({
                    pushState : options.pushState
                });
                if (!this.getCurrentRoute()) {
                    console.debug("navigating to init view ", this.activeView);
                    this.navigate("list", this.NAV_OPTIONS.REPLACE_AND_TRIGGER);
                }
            },
            
            /**
             *Global route handling, maps relative hrefs onto the current user.
             *This means we can declare routes as static hrefs in the front end
             *and map to the currently viewed user. Also provides us with a 
             *convenient migration to pushState nav
             */
            handleRoute : function(event) {
                event.preventDefault();
                var href= $(event.currentTarget).attr("href");
                var path = this.getFullPath(href || options.defaultView);
                console.debug("mapping href %s to path %s",href,path);
                this.navigate(path,{
                    trigger : true
                });
            },
            
            
            getCurrentRoute : function() {
                var hash = document.location.hash.substr(1);
                var path = document.location.pathname.substr(1);
                return hash || path;
            },
            
            list : function() {
                console.info("lisitng races " + this.races.models);
                new ListView({races : this.races}).render();
            },
            
            defaultRoute : function() {
                console.debug("default route triggered with activeView ",this.activeView)
                var viewName = this.activeView || "list";
                this.navigate(this.getFullPath(viewName, username), this.NAV_OPTIONS.REPLACE_AND_TRIGGER);  
            },
            
            setActiveNav : function(active) {
                $(".nav-pills>.active").removeClass("active");
                $(active).parent().addClass("active");
            }
        });
    });
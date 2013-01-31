define(function(require) {
    
    var     $               = require('jquery'), 
    _               = require('underscore'), 
    Backbone        = require('backbone'),
    Races           = require('collection/Races'),
    options         = require('options'),
    util            = require('util');
            
    /**
     * Contains convenience routing functionf for subclasses
     */
    return Backbone.Router.extend({
        
        NAV : {
            REPLACE_AND_TRIGGER : {
                replace : true, 
                trigger : true
            }
        },
            
        getCurrentRoute : function() {
            var hash = document.location.hash.substr(1);
            var path = document.location.pathname.substr(1);
            return hash || path;
        },
            
        /**
        *Renders an ad-hoc view, without routing. Useful for popups etc.
        *
        *Works by retrieving a view clas form the data-view attribute of a clicked element
        *and rendering an instance of said view
        */
        handleView : function(event) {
            event.preventDefault();
            var $target = $(event.currentTarget);
            var viewClass = $target.data("view");
            if (viewClass) {
                require([viewClass], _.bind(function(View) {
                    console.debug("loading ",View);
                    new View({
                        triggerElement : $target,
                        router : this
                    }).render();
                },this));
            } else {
                console.warn("no view class provided on ",$target);
            }
        }
            
    });
});
define(['jquery', 'underscore', 'backbone'], function($, _, Backbone) {
    return Backbone.View.extend({
        el : $("#appView"),
        
        /**
         *handles transition  between the master app views
         *callback is called after the html is updated but before 
         *the new view is shown
         */
        transition : function(callback, fast) {
            if (fast) {
                this.fastTransition(callback);
            } else {
                this.prettyTransition(callback);
            }
        },
        
        prettyTransition : function(callback) {
            //function that will be called once the app container is hidden
            var afterFade = _.bind(function() {
                this.$el.html(this.template({}));
            
                if (callback) {
                    callback.call(this);
                }
                this.$el.animate({
                    opacity : 1
                }, "fast");
            },this);
            
            if (1 * this.$el.css("opacity")) {
                this.$el.animate({
                    opacity : 0
                }, "fast", "swing", afterFade);
            } else {
                afterFade();
            }
        },
        
        fastTransition : function(callback) {
            this.$el.html(this.template({}));
            if (callback) {
                callback.call(this);
            }
        }
    });
});
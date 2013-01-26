define(['jquery', 'underscore', 'backbone', 'text!template/race.html!strip'],
    function($, _, Backbone, template) {
        return Backbone.View.extend({
            tagName : "li",
            
            //Cache a copy of the actual template
            template : _.template($(template).html()),
            
            events : {
                "click " : function(e){
                    console.debug("clicked race %o", this.model);
                    e.preventDefault();
                    e.stopPropagation();
                }
            },
            
            initialize : function(params) {
                _.extend(this, params);
                this.model.bind('sync', this.render, this);
                this.model.bind('destroy', this.remove, this);
                _.bindAll(this);
            },
           
            render : function() {
                var html = this.template(_.extend(this.model.toJSON(),{}));
                console.info("rendering race view %o with html %o", this.model, html);
                this.$el.html(html);
                return this;
            }
        });
    });
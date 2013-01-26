define(['jquery','backboneSource'], function($, Backbone) {
    //Hack to work around Backbone syncronous init
    Backbone.$ = Backbone.$ || $;
    return Backbone;
});
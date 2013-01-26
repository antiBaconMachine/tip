define(['staticOptions','module','underscore'], function(opt, module, _){
    return _.extend({}, opt, module.config() || {});
});
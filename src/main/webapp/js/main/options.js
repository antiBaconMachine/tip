define(['staticOptions','module','underscore'], function(opt, module, _){
    console.debug("moduel config options %o", module.config());
    return _.extend({}, opt, module.config() || {});
});
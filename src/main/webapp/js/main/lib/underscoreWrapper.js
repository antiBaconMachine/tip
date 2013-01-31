/**
 * Wrapper sets custom template delimiters before the app has a chance to compile
 * any templates with the defaults
 */
define(['underscoreSource'], function(_) {
    _.templateSettings = {
        interpolate: /{{\=(.+?)}}/gim,
        evaluate: /{{(.+?)}}/gim
    };
    return _;
})
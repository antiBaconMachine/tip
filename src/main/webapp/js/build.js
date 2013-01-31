({
    baseUrl: "main/",
    dir: "build/",
    paths : {
        jquery: "empty:",
        underscoreSource: "lib/underscore", 
        backboneSource: "lib/backbone",
        underscore : "lib/underscoreWrapper",
        backbone : "lib/backboneWrapper",
        text     :  "lib/text"
    },
    modules: [
        {name: "main"},
        {name: "main-player"}
    ],
    removeCombined : true,
    optimize : "closure",
    closure: { 
        CompilationLevel: 'SIMPLE_OPTIMIZATIONS' 
    },
    mainConfigFile: 'main/main.js'
})
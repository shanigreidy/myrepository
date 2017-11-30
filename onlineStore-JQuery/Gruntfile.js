module.exports = function(grunt){
    grunt.initConfig({
        jshint: {
                    client: {files: {src: ['public/**/*.js']}},
                    server: {files: {src: ['routes/**/*.js', 'models/**/*.js']}}
                }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');
};
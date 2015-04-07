module.exports = function (grunt) {

    // Project configuration.
    grunt.initConfig({
        //if we want to keep the ng application separately we need to configure it to use backend api on 8080 port 
        connect: {
            'static': {
                base: 'app',
                options: {
                    hostname: 'localhost',
                    port: 8001,
                    base: 'app'
                }
            }
            , server: {
                base: 'app',
                //directory: 'app',
                options: {
                    base: 'app',
                    port: 8000,
                    hostname: 'localhost',
                    open: true,
                    keepalive: true,
                    middleware: function (connect, options) {
                        var proxySnippet = require('grunt-connect-proxy/lib/utils').proxyRequest;
                        return [proxySnippet];
                    }
                }
                , proxies: [
                    {
                        context: '/rest',
                        host: 'localhost',
                        port: 8080
                    },
                    //makes sure that our static files are served from our "static" connect task.
                    {
                        context: '/',
                        host: 'localhost',
                        port: 8001
                    }
                ]
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-connect-proxy');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.registerTask('server', ['connect:static', 'configureProxies:server', 'connect:server']);
};
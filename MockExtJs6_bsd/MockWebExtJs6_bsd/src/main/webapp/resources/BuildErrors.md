# Build erors 
---------------------------------------------------------------------------------------------------------------------

## Build @ Feb 5
------------------------------------------------------------------------------------------------------------------------------------

Build time errors
 
	ERROR in C:\spring-tool-suite-3.8.3_Workspace\maven.1486311882298\MockExtJs6_bsd\MockWebExtJs6_bsd\src\main\webapp\resources\node_modules\@angular\router\src\version
	.d.ts
	(13,10): error TS2305: Module '"C:/spring-tool-suite-3.8.3_Workspace/maven.1486311882298/MockExtJs6_bsd/MockWebExtJs6_bsd/src/main/webapp/resources/node_modules/@ang
	ular/core/index"' has no exported member 'Version'.

	ERROR in C:\spring-tool-suite-3.8.3_Workspace\maven.1486311882298\MockExtJs6_bsd\MockWebExtJs6_bsd\src\main\webapp\resources\node_modules\@angular\router\src\router_
	module.d.ts
	(9,104): error TS2305: Module '"C:/spring-tool-suite-3.8.3_Workspace/maven.1486311882298/MockExtJs6_bsd/MockWebExtJs6_bsd/src/main/webapp/resources/node_modules/@ang
	ular/core/index"' has no exported member 'NgProbeToken'.

-----

Run time errors  

	version.js:12Uncaught TypeError: __WEBPACK_IMPORTED_MODULE_0__angular_core__.Version is not a constructor
	app.module.ts:29Uncaught TypeError: Cannot read property 'forRoot' of undefined

-------

### Update package.json from [github](https://github.com/Farata/angular2typescript)  

Invalid configuration object. Webpack has been initialised using a configuration object that does not match the API schema.
 - configuration has an unknown property 'debug'. These properties are valid:
   object { amd?, bail?, cache?, context?, dependencies?, devServer?, devtool?, entry, externals?, loader?, module?, name?, node?, output?, plugins?, profile?, recor
dsInputPath?, recordsOutputPath?, recordsPath?, resolve?, resolveLoader?, stats?, target?, watch?, watchOptions? }
   The 'debug' property was removed in webpack 2.
   Loaders should be updated to allow passing this option via loader options in module.rules.
   Until loaders are updated one can use the LoaderOptionsPlugin to switch loaders into debug mode:
   plugins: {
     new webpack.LoaderOptionsPlugin({
       debug: true
     })
   }
 - configuration.resolve.extensions[0] should not be empty.

- Remove "debug: true" from webpack.config.js from 

	module.exports = {
	  debug: true,
	  devServer: {

- Remove '' from

	resolve: {
	  extensions: ['', '.ts', '.js']
	}

All fixed
---


.
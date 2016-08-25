System.config({
  transpiler: 'typescript',
  typescriptOptions: {emitDecoratorMetadata: true},
  map: {
    'app'     : 'resources/app',
    'rxjs'    : 'resources/node_modules/rxjs',
    '@angular': 'resources/node_modules/@angular'
  },
  packages: {
    'app'                              : {main: 'main', defaultExtension: 'ts'},
    'rxjs'                             : {main: 'index'},
    '@angular/core'                    : {main: 'index'},
    '@angular/common'                  : {main: 'index'},
    '@angular/compiler'                : {main: 'index'},
    '@angular/platform-browser'        : {main: 'index'},
    '@angular/platform-browser-dynamic': {main: 'index'},
    '@angular/http'                    : {main: 'index'},
    '@angular/forms'                    : {main: 'index'},
    '@angular/router'                  : {main: 'index'}
  }
});
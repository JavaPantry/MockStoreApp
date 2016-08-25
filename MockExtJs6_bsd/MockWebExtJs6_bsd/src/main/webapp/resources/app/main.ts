import {bootstrap} from '@angular/platform-browser-dynamic';
import {disableDeprecatedForms, provideForms} from '@angular/forms';
import {provideRouter} from '@angular/router';
import {LocationStrategy, HashLocationStrategy} from '@angular/common';
import {HTTP_PROVIDERS} from '@angular/http';

import ApplicationComponent from './components/application/application';
import HomeComponent from './components/home/home';
import ProductDetailComponent from './components/product-detail/product-detail';
import OrderComponent from './components/order-detail/order-detail';
import {ProductService} from './services/product-service';
import {OrderService} from './services/order-service';
import {ONLINE_AUCTION_SERVICES} from './services/services';

bootstrap(ApplicationComponent, [
  provideRouter([
    {path: '',                    component: HomeComponent},
    {path: 'products/:productId', component: ProductDetailComponent},
    {path: 'orders',              component: OrderComponent}
  ]),
  {provide: LocationStrategy, useClass: HashLocationStrategy},
  disableDeprecatedForms(),
  provideForms(),
  HTTP_PROVIDERS,
  ProductService,
  OrderService,
  ONLINE_AUCTION_SERVICES
]);
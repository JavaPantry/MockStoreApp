import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from '@angular/router';
import HomeComponent from '../home/home';
import NavbarComponent from '../navbar/navbar';
import FooterComponent from '../footer/footer';
import SearchComponent from '../search/search';
import ProductDetailComponent from '../product-detail/product-detail';
import OrderComponent from '../order-detail/order-detail';

@Component({
  selector: 'auction-application',
  //templateUrl: 'resources/app/components/application/application.html',
  template: require('./application.html'),
  directives: [
    ROUTER_DIRECTIVES,
    NavbarComponent,
    FooterComponent,
    SearchComponent,
    HomeComponent
  ]
})
export default class ApplicationComponent {}

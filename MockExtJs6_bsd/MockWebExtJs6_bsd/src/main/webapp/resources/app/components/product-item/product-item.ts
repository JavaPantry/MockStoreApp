import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from '@angular/router';
import {Product} from '../../services/product-service';
import StarsComponent from '../stars/stars';

@Component({
  selector: 'auction-product-item',
  properties: ['product'],
  //templateUrl: 'resources/app/components/product-item/product-item.html',
  template: require('./product-item.html'),
  //styleUrls: ['resources/app/components/product-item/product-item.css'],
  styles: [require('./product-item.css')],
  directives: [ROUTER_DIRECTIVES, StarsComponent],
})
export default class ProductItemComponent {
  product: Product;
}

import {EventEmitter, Injectable} from '@angular/core';
import {Http, URLSearchParams } from '@angular/http';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';

export class Order {
  constructor(public id: number, public sku: string) {}
}

  /*
  * Removed: interface OrderSearchParams {
  * see app\services\product-service.ts
  */

@Injectable()
export class OrderService {
  constructor(private http: Http) {}
  getOrders(): Observable<Order[]> {
    return this.http.get('angular/products')
      .map(response => response.json());
  }
  /*getProductById(productId: string): Observable<Order> {
    return this.http.get(`angular/products/${productId}`)
      .map(response => response.json());
  }*/
}

/*
 * Removed function encodeParams(params: any)
 * Encodes the object into a valid query string.
 * see app\services\product-service.ts
 */

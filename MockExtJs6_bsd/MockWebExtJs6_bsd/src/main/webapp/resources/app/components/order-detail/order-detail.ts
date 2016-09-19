import { Component, OnDestroy } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable'
import { Subscription } from 'rxjs/Subscription';

import {Order, OrderService} from "../../services/order-service";

@Component({
  selector: 'auction-order-page',
  styles: [ 'auction-stars.large {font-size: 24px;}' ],
  templateUrl: 'resources/app/components/order-detail/order-detail.html'
})
export default class OrderComponent implements OnDestroy {


  private orders: Observable<Order[]>;
  private subscription: Subscription;

  constructor(
      //route: ActivatedRoute,
      private orderService: OrderService
      ) {

    this.orders = this.orderService.getOrders();
    // subscribe search orders like in product-detail
  }

  ngOnDestroy(): any {
    /*if (this.subscription) {
      this.subscription.unsubscribe();
    }*/
    return Promise.resolve(true);
  }


}

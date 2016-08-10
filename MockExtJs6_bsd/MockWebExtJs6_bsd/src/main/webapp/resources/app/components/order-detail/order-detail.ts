import {Component, OnDestroy} from '@angular/core';
import {NgClass} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {Observable} from "rxjs/Observable";

import {OrderService} from "../../services/order-service";
import {Order} from "../../services/order-service";



@Component({
  selector: 'auction-order-page',
  templateUrl: 'resources/app/components/order-detail/order-detail.html',
  directives: [NgClass]
})
export default class OrderComponent implements OnDestroy {

  private orders: Observable<Order[]>;
  private subscription: Subscription;

  constructor(
      route: ActivatedRoute,
      private orderService: OrderService
      ) {

    //const productId = parseInt(route.snapshot.params['productId']);
    //const productId = route.snapshot.params['productId'];

    this.orders = this.orderService.getOrders();

    // subscribe search orders like in product-detail

  }


/*  routerOnDeactivate(): any {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }*/

  ngOnDestroy(): any {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }



}

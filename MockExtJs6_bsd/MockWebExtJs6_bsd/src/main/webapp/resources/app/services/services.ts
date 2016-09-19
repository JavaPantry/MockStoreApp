import {BidService} from './bid-service';
import {ProductService} from './product-service';
import {WebSocketService} from './websocket-service';
import {OrderService} from './order-service';
export const ONLINE_AUCTION_SERVICES = [
  BidService,
  ProductService,
  OrderService,
  WebSocketService
];

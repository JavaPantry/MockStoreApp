import { Component } from '@angular/core';
import { Observable } from 'rxjs/Observable'
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Product, ProductService } from '../../services/product-service';

@Component({
  selector: 'auction-home-page',
  styleUrls: [ 'resources/app/components/home/home.css' ],
  templateUrl: 'resources/app/components/home/home.html'
})
export default class HomeComponent {
  products: Observable<Product[]>;

  formModel2: FormGroup;

  constructor(private productService: ProductService) {
    
    /*const fb = new FormBuilder();
    this.formModel2 = fb.group({
      //'title': [null, Validators.minLength(3)],
      //'price': [null, positiveNumberValidator],
      //'category': ['']
    })*/
    
    
    
    this.products = this.productService.getProducts();

    this.productService.searchEvent
      .subscribe(
        params => this.products = this.productService.search(params),
        console.error.bind(console),
        () => console.log('DONE')
      );
  }
  
  onReload() {
          this.products = this.productService.getProducts();
  }
}

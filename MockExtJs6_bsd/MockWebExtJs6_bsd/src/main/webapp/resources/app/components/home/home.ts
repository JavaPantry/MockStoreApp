import { Component } from '@angular/core';
import { Observable } from 'rxjs/Observable'
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Product, ProductService } from '../../services/product-service';

@Component({
  selector: 'auction-home-page',
  styleUrls: [ 'resources/app/components/home/home.css' ],
  template: `
    <div class="row carousel-holder">
      <div class="col-md-12">
        <auction-carousel></auction-carousel>
      </div>
    </div>

    <div class="row">
    <!-- <form [formGroup]="formModel2"  (ngSubmit)="onReload()" novalidate> -->
    <form (ngSubmit)="onReload()" novalidate>
     <div class="form-group">
     	<div class="col-sm-2 col-lg-2 col-md-2">
	    	<button type="submit" class="btn btn-primary btn-block">Reload</button>
	    </div>
	  </div>
	</form>
	</div>


    <div class="row">
      <div *ngFor="let product of products | async" class="col-sm-4 col-lg-4 col-md-4">
        <auction-product-item [product]="product"></auction-product-item>
      </div>
    </div>
  `
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

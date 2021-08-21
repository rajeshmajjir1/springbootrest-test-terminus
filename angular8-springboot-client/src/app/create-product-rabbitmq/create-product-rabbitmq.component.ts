import { Component, OnInit } from '@angular/core';

import { Product } from '../product';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-create-product-rabbitmq',
  templateUrl: './create-product-rabbitmq.component.html',
  styleUrls: ['./create-product-rabbitmq.component.css']
})
export class CreateProductRabbitmqComponent implements OnInit {

  product: Product = new Product();
  submitted = false;
  submittedSuccess = false;

  constructor(private productService: ProductService,
    private router: Router) { }

  ngOnInit() {
  }
  newProduct(): void {
    this.submitted = false;
    this.product = new Product();
  }

  save() {
    this.productService.createProductRabbitmq(this.product)
      .subscribe(data => console.log(data), error => console.log(error));
    this.product = new Product();
  }

  onSubmit() {
    this.submittedSuccess = true;
    this.save();    
  }

}

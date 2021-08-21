import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { CreateProductRabbitmqComponent } from './create-product-rabbitmq/create-product-rabbitmq.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';



const routes: Routes = [

  { path: '', redirectTo: 'product', pathMatch: 'full' },
  { path: 'products', component: ProductListComponent },
  { path: 'add', component: CreateProductComponent },
  { path: 'addmq', component: CreateProductRabbitmqComponent },
  { path: 'update/:id', component: UpdateProductComponent },
  { path: 'details/:id', component: ProductDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

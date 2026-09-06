import { Injector, NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { OktaAuthGuard, OktaCallbackComponent } from '@okta/okta-angular';
import { CheckoutComponent } from './checkout/checkout.component';
import { LoginComponent } from './login/login.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductsComponent } from './products/products.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './purchase-history/purchase-history.component';
import OktaAuth from '@okta/okta-auth-js';

function goToLogin(oktaAuth: OktaAuth, injector: Injector) {
  const router = injector.get(Router);
  router.navigate(['/login']);
}


const routes: Routes = [
    {path: 'history', component: PurchaseHistoryComponent, canActivate: [OktaAuthGuard],
                    data: {onAuthRequired: goToLogin} },
    {path:'login/callback',component: OktaCallbackComponent},
    {path:'login', component: LoginComponent},
    {path: 'checkout', component: CheckoutComponent},
    {path: 'cart-details', component: ShoppingCartComponent},
    {path: 'products/:id', component: ProductDetailsComponent},
    {path: 'search/:name', component: ProductsComponent},
    {path: 'category/:id', component: ProductsComponent},
    {path: 'subcategory/:id', component: ProductsComponent},
    {path: 'category', component: ProductsComponent},
    {path: 'products', component: ProductsComponent},
    {path: '', redirectTo: '/products', pathMatch: 'full'},
    {path: '**', redirectTo: '/products', pathMatch: 'full'}
  ]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

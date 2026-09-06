import { ProductCategory } from '../classes/product-category';
import { ProductSubcategory } from '../classes/product-subcategory';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';
import { Component, Inject, OnInit } from '@angular/core';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';
import { PurchaseHistoryService } from '../services/purchase-history.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  productCategories: ProductCategory[] = [];

  storage: Storage = sessionStorage;
  isAuthenticated: boolean = false;
  userFullName: string = '';

  constructor(private router: Router, private productService: ProductService,
    private oktaAuthService: OktaAuthStateService,
    @Inject(OKTA_AUTH) private oktaAuth: OktaAuth,
    private purchaseHistoryService: PurchaseHistoryService) { }

  ngOnInit() {
    this.oktaAuthService.authState$.subscribe(
      (result) => {
        this.isAuthenticated = result.isAuthenticated!;
        this.getUserDetails();
      }
    );
    this.listProductCategoriesAndSubc();
  }

  listProductCategoriesAndSubc() {
    this.productService.getProductCategories().subscribe(
      data => {
        this.productCategories = data;
        for(let singleProductCategory of this.productCategories){
          this.productService.getProductSubcategoriesByCatId(singleProductCategory.id).subscribe(
            data => {
              singleProductCategory.productsubcategories = data;
            }
          );
        }
      }
    );
  }

  doSearch(value: string) {
    this.router.navigateByUrl(`/search/${value}`);
  }
  
  getUserDetails() {
    if (this.isAuthenticated) {
      this.oktaAuth.getUser().then(
        (res) => {
          this.userFullName = res.name as string;
          
          this.purchaseHistoryService.setUserAccountEmail(res.email as string);
        }
      );
    }
  }

  logout() {
    this.oktaAuth.signOut();
  }
}

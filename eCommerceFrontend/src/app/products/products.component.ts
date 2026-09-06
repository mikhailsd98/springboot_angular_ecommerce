import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/classes/product';
import { ProductService } from 'src/app/services/product.service';
import { ShoppingCartItem } from '../classes/shopping-cart-item';
import { ShoppingCartService } from '../services/shopping-cart-service.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  currentRouteAndCategoryId!: string;
  products: Product[] = [];
  thePageNumber: number = 1;
  thePageSize: number = 15;
  theTotalElements: number = 0;
  previousRouteAndCategoryId!: string;
  previousName: string = "";


  sortByValue: string = '';
  sortByDir: string = '';
  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private cartService: ShoppingCartService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      this.sortByValue = "DateCreated"; 
      this.sortByDir = "Desc";
      this.listProducts();
    });
  }
  onSelectChange (val: any) {
    if(val.target.value==1) {
      this.sortByValue = "DateCreated"; 
      this.sortByDir = "Desc";
    }
    else if (val.target.value==2) {
      this.sortByValue = "Rating"; 
      this.sortByDir = "Asc";
    }
    else if (val.target.value==3) {
      this.sortByValue = "UnitPrice"; 
      this.sortByDir = "Desc";
    }
    else if (val.target.value==4) {
      this.sortByValue = "UnitPrice"; 
      this.sortByDir = "Asc";
    }
    this.listProducts();
  }

  listProducts() {
    console.log(this.sortByValue);
    if (this.route.snapshot.paramMap.has('name')) {
      const theName: string = this.route.snapshot.paramMap.get('name')!;
      if (this.previousName != theName) {
        this.thePageNumber = 1;
      }
      
      this.previousName = theName;

      this.productService.searchProductsPaginate(this.thePageNumber - 1,
                                                 this.thePageSize,
                                                 theName,
                                                 `${this.sortByValue}${this.sortByDir}`).subscribe((data: any) => {
                                                      this.products = data._embedded.products;
                                                      this.thePageNumber = data.page.number + 1;
                                                      this.thePageSize = data.page.size;
                                                      this.theTotalElements = data.page.totalElements;
                                                    })
    }
    else {
      if (this.route.snapshot.paramMap.has('id')) {

        if(this.route.routeConfig?.path=="subcategory/:id") {
          this.currentRouteAndCategoryId = `/search/findBySubcategoryId?id=${this.route.snapshot.paramMap.get('id')}`;
        }
        else {
          this.currentRouteAndCategoryId = `/search/findBySubcategoryCategoryId?id=${this.route.snapshot.paramMap.get('id')}`;
        }
      }
      else {
        this.currentRouteAndCategoryId = `?`;
      }

      if (this.previousRouteAndCategoryId != this.currentRouteAndCategoryId) {
        this.thePageNumber = 1;
      }

      this.previousRouteAndCategoryId = this.currentRouteAndCategoryId;

      this.productService.getProductListPaginate(this.thePageNumber - 1,
                                                this.thePageSize,
                                                this.currentRouteAndCategoryId,
                                                this.sortByValue,
                                                this.sortByDir)
                                                .subscribe((data: any) => {
                                                        this.products = data._embedded.products;
                                                        this.thePageNumber = data.page.number + 1;
                                                        this.thePageSize = data.page.size;
                                                        this.theTotalElements = data.page.totalElements;
                                                      })
    }
  }
  addToShoppingCart(theProduct: Product) {
    this.productService.getProductColorsById(theProduct.id!).subscribe(
      data => {
        let theCartItem = new ShoppingCartItem(); 
        theCartItem.setItem(theProduct);
        theCartItem.setColor(data[0].color!);
        this.cartService.addToShoppingCart(theCartItem);
      }
    )
  }
}

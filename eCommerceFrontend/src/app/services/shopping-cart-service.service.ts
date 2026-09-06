import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/internal/Subject';
import { ShoppingCartItem } from '../classes/shopping-cart-item';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { ColorAmountOfItem } from '../classes/color-amount-of-order-item';



@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  cartItems: ShoppingCartItem[] = [];
  storage: Storage = sessionStorage;
  cartPrice: Subject<number> = new BehaviorSubject<number>(0);
  cartQuantity: Subject<number> = new BehaviorSubject<number>(0);

  constructor() { 
    let data = JSON.parse(this.storage.getItem('cartItems')!);

    if (data != null) {
      this.cartItems = data;
      this.сartPriceAndQuantityCompute();
    }
  }
  addToShoppingCart(theCartItem: ShoppingCartItem) {
    let existingCartItem: ShoppingCartItem | undefined;
    if (this.cartItems.length > 0) {
      existingCartItem = this.cartItems.find( singleCartItem =>singleCartItem.id === theCartItem.id );
    }

    if (existingCartItem) {
      existingCartItem.quantity!+=theCartItem.quantity!;
      let colorExist = existingCartItem.colors.find( singleColor =>singleColor.color === theCartItem.colors[0].color);
      if(colorExist) {
        colorExist.quantity! += theCartItem.quantity!;
      }
      else {
        theCartItem.colors[0].quantity!+=theCartItem.quantity!;
        existingCartItem.colors.push(theCartItem.colors[0]);
      }
    }
    else {
      this.cartItems.push(theCartItem);
    }
    this.сartPriceAndQuantityCompute()
  }
  сartPriceAndQuantityCompute(){
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;
    for (let currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.quantity! * currentCartItem.itemPrice!;
      totalQuantityValue += currentCartItem.quantity!;
    }

    this.cartPrice.next(totalPriceValue);
    this.cartQuantity.next(totalQuantityValue);
    this.storage.setItem('cartItems', JSON.stringify(this.cartItems));
  }

  decrementItemQuantity(theCartItem: ShoppingCartItem) {
    theCartItem.quantity!--;

    if (theCartItem.quantity === 0) {
      this.removeItem(theCartItem);
    }
    else {
      this.сartPriceAndQuantityCompute();
    }
  }

  removeItem(theCartItem: ShoppingCartItem) {

    const itemIndex = this.cartItems.findIndex( tempCartItem => tempCartItem.id === theCartItem.id );

    if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);
      this.сartPriceAndQuantityCompute();
    }
  }
}

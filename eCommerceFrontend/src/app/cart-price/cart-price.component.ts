import { Component } from '@angular/core';
import { ShoppingCartService } from '../services/shopping-cart-service.service';

@Component({
  selector: 'app-cart-price',
  templateUrl: './cart-price.component.html',
  styleUrls: ['./cart-price.component.css']
})
export class CartPriceComponent {
  cartPrice: number = 0.00;

  constructor(private cartService: ShoppingCartService) { }

  ngOnInit(): void {
    this.updateCartStatus();
  }

  updateCartStatus() {
    
    this.cartService.cartPrice.subscribe(
      data => this.cartPrice = data
    );

  }

}
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { OrderFormService } from '../services/order-form.service';
import { Country } from '../classes/country';
import { Region } from '../classes/region';
import { MycustomWhitespaseValidators } from '../validators/mycustom-whitespase-validators';
import { ShoppingCartService } from '../services/shopping-cart-service.service';
import { ShoppingCartItem } from '../classes/shopping-cart-item';
import { CheckoutService } from '../services/checkout.service';
import { Router } from '@angular/router';
import { Order } from '../classes/order';
import { OrderItem } from '../classes/order-item';
import { Purchase } from '../classes/purchase';
import { PurchaseHistoryService } from '../services/purchase-history.service';

declare var paypal: any;

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cartPrice: number = 0;
  cartQuantity: number = 0;
  shoppingCartItems: ShoppingCartItem[] = [];
  
  countries: Country[] = [];
  regions: Region[] = [];

  checkoutFormGroup!: FormGroup;

  @ViewChild('paypal',{static: true})
  paypalHtmlElement: ElementRef | undefined;

  @ViewChild('checkoutForm') 
  checkoutForm: ElementRef | undefined;
  
  constructor(private formBuilder: FormBuilder,
              private orderFormService: OrderFormService,
              private shoppingCartService: ShoppingCartService,
              private checkoutService: CheckoutService,
              private router: Router,
              private purchaseHistoryService: PurchaseHistoryService) { }

  ngOnInit(): void {
    this.purchaseHistoryService.userAccEmail.subscribe(
      dataEmail => {
        this.checkoutFormGroup = this.formBuilder.group({
          customer: this.formBuilder.group({
            firstName: new FormControl('', [Validators.required, 
                                          Validators.minLength(2), 
                                          MycustomWhitespaseValidators.notOnlyWhitespace]),
            lastName: new FormControl('', [Validators.required, 
                                            Validators.minLength(2), 
                                            MycustomWhitespaseValidators.notOnlyWhitespace]),
            phoneNumber: new FormControl('', [Validators.required, Validators.pattern("^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$")]),
            email: new FormControl(dataEmail, [Validators.required, Validators.email])
          }),
          address: this.formBuilder.group({
            street: new FormControl('', [Validators.required, 
              Validators.minLength(2), 
              MycustomWhitespaseValidators.notOnlyWhitespace]),
            city: new FormControl('', [Validators.required, 
              Validators.minLength(2), 
              MycustomWhitespaseValidators.notOnlyWhitespace]),
            region: new FormControl('', [Validators.required]),
            country: new FormControl('', [Validators.required]),
            zipCode: new FormControl('', [Validators.required, 
              Validators.minLength(2), 
              MycustomWhitespaseValidators.notOnlyWhitespace]),
          }),
          someOrderInfo: this.formBuilder.group({
            orderNotes: new FormControl('', [Validators.minLength(15)]),
            noteByEmail: [true]
          })
        });    
      }
    );

    this.orderFormService.getCountries().subscribe(
      data => {
        this.countries = data;
      }
    );
    this.shoppingCartService.cartQuantity.subscribe(
      data => {this.cartQuantity = data}
    );

    this.shoppingCartService.cartPrice.subscribe(
      data => {this.cartPrice = data}
    );
    this.shoppingCartItems = this.shoppingCartService.cartItems;
    

    paypal.Buttons({
      createOrder: (data, actions) => {
        return actions.order.create({
          purchase_units: [{
              amount: {
                  value: this.cartPrice,
                  currency_code: 'USD' 
              },
              description: 'Your order description'
          }]
        });
      },
      onApprove: (data, actions) => {
        const order = actions.order.capture()
        .then((details) => {
        if (details["status"] == "COMPLETED") {
          this.checkoutForm?.nativeElement.submit();
          this.placeOrder();
        }
        })
      },
      onError: (err) => {
        alert(`Error: ${err.message}`);
      }
    })
    .render(this.paypalHtmlElement?.nativeElement)
  }

  getRegions() {
    const formGroup = this.checkoutFormGroup.get("address");
    const countryName = formGroup!.value.country.name;
    this.orderFormService.getRegions(countryName).subscribe(
      data => {
          this.regions = data;
        formGroup!.get('region')!.setValue(data[0]);
      }
    );
  }

  get firstName() { return this.checkoutFormGroup.get('customer.firstName'); }
  get lastName() { return this.checkoutFormGroup.get('customer.lastName'); }
  get email() { return this.checkoutFormGroup.get('customer.email'); }
  get phoneNumber() { return this.checkoutFormGroup.get('customer.phoneNumber'); }

  get addressStreet() { return this.checkoutFormGroup.get('address.street'); }
  get addressCity() { return this.checkoutFormGroup.get('address.city'); }
  get addressRegion() { return this.checkoutFormGroup.get('address.region'); }
  get addressZipCode() { return this.checkoutFormGroup.get('address.zipCode'); }
  get addressCountry() { return this.checkoutFormGroup.get('address.country'); }

  get orderNotes() { return this.checkoutFormGroup.get('someOrderInfo.orderNotes'); }
  
  placeOrder() {
    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }
    let purchase = new Purchase();
        
    purchase.customer = this.checkoutFormGroup.controls['customer'].value;

    purchase.address = this.checkoutFormGroup.controls['address'].value;
    const country: Country = JSON.parse(JSON.stringify(purchase.address?.country));
    const region: Region = JSON.parse(JSON.stringify(purchase.address?.region));
    purchase.address!.country = country.name;
    purchase.address!.region = region.name;
    let order = new Order();
    order.itemsPrice = this.cartPrice;
    order.itemsQuantity = this.cartQuantity;
    order.noteByEmail = this.checkoutFormGroup.get('someOrderInfo')?.value.noteByEmail;
    order.orderNotes = this.checkoutFormGroup.get('someOrderInfo')?.value.orderNotes;

    let orderItems: OrderItem[] = [];
    for (let i=0; i < this.shoppingCartService.cartItems.length; i++) {
      orderItems[i] = new OrderItem(this.shoppingCartService.cartItems[i]);
    }
  
    purchase.order = order;
    purchase.orderItems = orderItems;
    this.checkoutService.placeOrder(purchase).subscribe({
        next: data => {
          alert(`Order is received.\nTracking number of order: ${data.trackingNumber}`);

          this.shoppingCartService.cartItems = [];
          this.shoppingCartService.cartPrice.next(0);
          this.shoppingCartService.cartQuantity.next(0);
          this.shoppingCartService.storage.setItem('cartItems', JSON.stringify(this.shoppingCartService.cartItems));
          this.checkoutFormGroup.reset();

          this.router.navigateByUrl("/products");
        },
        error: error => {
          alert(`Error: ${error.message}`);
        }
      }
    );
  }
}

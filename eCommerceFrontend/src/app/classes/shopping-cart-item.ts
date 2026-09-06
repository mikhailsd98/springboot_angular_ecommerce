
import { ColorAmountOfItem } from './color-amount-of-order-item';
import { Product } from './product';
export class ShoppingCartItem {

    id?: number;
    name?: string;
    itemPrice?: number;
    quantity?: number;
    imageUrl?: string;
    colors: ColorAmountOfItem[] = [];

    setItem(product: Product) {
        this.id = product?.id;
        this.name = product?.name;
        this.itemPrice = product?.unitPrice;
        this.quantity = 1;
        this.imageUrl = product?.imageUrl;
        this.colors[0] = new ColorAmountOfItem();
    }
    public setColor(color: string):void {
        this.colors[0].color = color;
    }
}

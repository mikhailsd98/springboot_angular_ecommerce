
export class PurchaseHistory {
    constructor(public id: string,
        public trackingNumber: string,
        public itemsQuantity: number,
        public itemsPrice: number,
        public dateCreated: Date) { }
}

import { ProductSubcategory } from "./product-subcategory";
export class ProductCategory {
    constructor(public id: number,
        public categoryName: string,
        public productsubcategories: ProductSubcategory[]) {
    }
}

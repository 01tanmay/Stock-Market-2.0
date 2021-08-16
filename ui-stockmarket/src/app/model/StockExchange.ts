export class StockExchange {
    price: number
    companyCode: String
    stockCreatedDate: String

    constructor(price?: number, companyCode?: String, stockCreatedDate?: String) {
        this.price = price;
        this.companyCode = companyCode;
        this.stockCreatedDate = stockCreatedDate;
    }
    
   
}
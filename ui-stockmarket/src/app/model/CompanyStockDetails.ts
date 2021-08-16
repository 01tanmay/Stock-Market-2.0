import { StockExchange } from "./StockExchange";

export class CompanyStockDetails {
    companyCode: String;
    minStockPrice: number;
    maxStockPrice: number;
    averageStockPrice: number;
    stockExchanges: StockExchange[];
    fromDate: String;
    toDate: String;

}
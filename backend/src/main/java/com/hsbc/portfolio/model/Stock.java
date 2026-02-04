package com.hsbc.portfolio.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STOCK")
public class Stock extends PortfolioItem {
    public Stock() { super(); }
    public Stock(String name, Double buyPrice, Double currentPrice, Double quantity, String date) {
        super(name, buyPrice, currentPrice, quantity, date);
    }
    
    public static StockBuilder builder() { return new StockBuilder(); }
    
    public static class StockBuilder {
        private String name;
        private Double buyPrice;
        private Double currentPrice;
        private Double quantity;
        private String date;
        
        public StockBuilder name(String name) { this.name = name; return this; }
        public StockBuilder buyPrice(Double buyPrice) { this.buyPrice = buyPrice; return this; }
        public StockBuilder currentPrice(Double currentPrice) { this.currentPrice = currentPrice; return this; }
        public StockBuilder quantity(Double quantity) { this.quantity = quantity; return this; }
        public StockBuilder date(String date) { this.date = date; return this; }
        
        public Stock build() {
            Stock stock = new Stock();
            stock.setName(name);
            stock.setBuyPrice(buyPrice);
            stock.setCurrentPrice(currentPrice);
            stock.setQuantity(quantity);
            stock.setDate(date);
            return stock;
        }
    }
}

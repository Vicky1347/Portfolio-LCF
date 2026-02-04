package com.hsbc.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Base Entity Class for all portfolio items.
 */
@Entity
@Table(name = "portfolio_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public abstract class PortfolioItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", nullable = false, length = 255)
    @NotBlank(message = "Item name cannot be blank")
    @Size(min = 1, max = 255, message = "Item name must be between 1 and 255 characters")
    private String name;
    
    @Column(name = "buy_price", nullable = false)
    @NotNull(message = "Buy price cannot be null")
    @DecimalMin(value = "0.01", message = "Buy price must be greater than 0")
    private Double buyPrice;
    
    @Column(name = "current_price", nullable = false)
    @NotNull(message = "Current price cannot be null")
    @DecimalMin(value = "0.01", message = "Current price must be greater than 0")
    private Double currentPrice;
    
    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Quantity cannot be null")
    @DecimalMin(value = "0.01", message = "Quantity must be greater than 0")
    private Double quantity;
    
    @Column(name = "purchase_date", nullable = false, length = 10)
    @NotBlank(message = "Purchase date cannot be blank")
    private String date;
    
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    
    public PortfolioItem() {}
    
    public PortfolioItem(String name, Double buyPrice, Double currentPrice, Double quantity, String date) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
        this.date = date;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getBuyPrice() { return buyPrice; }
    public Double getCurrentPrice() { return currentPrice; }
    public Double getQuantity() { return quantity; }
    public String getDate() { return date; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBuyPrice(Double buyPrice) { this.buyPrice = buyPrice; }
    public void setCurrentPrice(Double currentPrice) { this.currentPrice = currentPrice; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
    public void setDate(String date) { this.date = date; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    // Calculated getters
    public Double getTotalInvestment() { return buyPrice * quantity; }
    public Double getCurrentValue() { return currentPrice * quantity; }
    public Double getReturnAmount() { return (currentPrice - buyPrice) * quantity; }
    public Double getReturnPercentage() {
        Double totalInvestment = getTotalInvestment();
        return totalInvestment == 0 ? 0.0 : (getReturnAmount() / totalInvestment) * 100;
    }
}

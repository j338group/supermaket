package com.cz.sm.pojo;

import java.util.Date;

public class Bills {
    private Long id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private Double productCount;
    private Double totalPrice;
    private Integer isPayment;
    private Date creationDate=new Date();
    private String providerName;
    private Integer providerId;

    public Bills() {
    }

    public Bills(Long id, String billCode, String productName, String productUnit, Double productCount, Double totalPrice, Integer isPayment, Integer providerId) {
        this.id = id;
        this.billCode = billCode;
        this.productName = productName;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.providerId = providerId;
    }

    public Bills(String billCode, String productName, String productUnit, Double productCount, Double totalPrice, Integer isPayment, Integer providerId) {
        this.billCode = billCode;
        this.productName = productName;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Double getProductCount() {
        return productCount;
    }

    public void setProductCount(Double productCount) {
        this.productCount = productCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}

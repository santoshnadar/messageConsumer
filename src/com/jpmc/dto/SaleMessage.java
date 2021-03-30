package com.jpmc.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SaleMessage {

	private String messageType;
	private String product;
	private BigDecimal price;
	private BigDecimal totalPrice;
	private int noOfSales;
	private String operationType;
	private BigDecimal adjustmentValue;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getNoOfSales() {
		return noOfSales;
	}

	public void setNoOfSales(int noOfSales) {
		this.noOfSales = noOfSales;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getAdjustmentValue() {
		return adjustmentValue;
	}

	public void setAdjustmentValue(BigDecimal adjustmentValue) {
		this.adjustmentValue = adjustmentValue;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "SaleMessage [messageType=" + messageType + ", product=" + product + ", price=" + price + ", totalPrice="
				+ totalPrice + ", noOfSales=" + noOfSales + ", operationType=" + operationType + ", adjustmentValue="
				+ adjustmentValue + "]";
	}

}

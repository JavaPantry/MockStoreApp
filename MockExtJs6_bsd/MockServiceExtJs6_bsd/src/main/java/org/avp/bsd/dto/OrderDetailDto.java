package org.avp.bsd.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrderDetailDto implements java.io.Serializable {

	private Long id;
	private Long orderId;
	
	private String sku;     //FK product id
	private Double priceValue;
	private Integer qty;
	private Date createDt;

	public OrderDetailDto() {
	}

	public OrderDetailDto(Long id, Long orderId, String sku,
			Double priceValue, Integer qty, Date createDt) {
		this.id = id;
		this.orderId = orderId;
		this.sku = sku;
		this.priceValue = priceValue;
		this.qty = qty;
		this.createDt = createDt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Double getPriceValue() {
		return this.priceValue;
	}

	public void setPriceValue(Double priceValue) {
		this.priceValue = priceValue;
	}

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
}

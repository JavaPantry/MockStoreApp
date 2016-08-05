package org.avp.bsd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//Entity
public class OrderDetail implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;
	private Long orderId;
	
	private String sku;     //FK product id
	private Double priceValue;
	private Integer qty;
	private Date createDt;

	public OrderDetail() {
	}

	public OrderDetail(Long id, Long orderId, String sku,
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderDetail))
			return false;
		OrderDetail castOther = (OrderDetail) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getOrderId() == castOther.getOrderId()) || (this
						.getOrderId() != null && castOther.getOrderId() != null && this
						.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getSku() == castOther.getSku()) || (this.getSku() != null
						&& castOther.getSku() != null && this.getSku().equals(
						castOther.getSku())))
				&& ((this.getPriceValue() == castOther.getPriceValue()) || (this
						.getPriceValue() != null
						&& castOther.getPriceValue() != null && this
						.getPriceValue().equals(castOther.getPriceValue())))
				&& ((this.getQty() == castOther.getQty()) || (this.getQty() != null
						&& castOther.getQty() != null && this.getQty().equals(
						castOther.getQty())))
				&& ((this.getCreateDt() == castOther.getCreateDt()) || (this
						.getCreateDt() != null
						&& castOther.getCreateDt() != null && this
						.getCreateDt().equals(castOther.getCreateDt())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result
				+ (getSku() == null ? 0 : this.getSku().hashCode());
		result = 37
				* result
				+ (getPriceValue() == null ? 0 : this.getPriceValue()
						.hashCode());
		result = 37 * result
				+ (getQty() == null ? 0 : this.getQty().hashCode());
		result = 37 * result
				+ (getCreateDt() == null ? 0 : this.getCreateDt().hashCode());
		return result;
	}

}

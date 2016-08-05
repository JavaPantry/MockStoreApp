package org.avp.bsd.model;

import java.util.Date;

import javax.persistence.Entity;


public class ProductPriceInStore implements java.io.Serializable {

	private Long storeId;
	private Long productId;
	private Double priceValue;
	private Double priceValueSched;
	private Date priceSchedule;
	private Date createDt;

	public ProductPriceInStore() {
	}

	public ProductPriceInStore(Long storeId, Long productId, Double priceValue,
			Double priceValueSched, Date priceSchedule, Date createDt) {
		this.storeId = storeId;
		this.productId = productId;
		this.priceValue = priceValue;
		this.priceValueSched = priceValueSched;
		this.priceSchedule = priceSchedule;
		this.createDt = createDt;
	}

	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getPriceValue() {
		return this.priceValue;
	}

	public void setPriceValue(Double priceValue) {
		this.priceValue = priceValue;
	}

	public Double getPriceValueSched() {
		return this.priceValueSched;
	}

	public void setPriceValueSched(Double priceValueSched) {
		this.priceValueSched = priceValueSched;
	}

	public Date getPriceSchedule() {
		return this.priceSchedule;
	}

	public void setPriceSchedule(Date priceSchedule) {
		this.priceSchedule = priceSchedule;
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
		if (!(other instanceof ProductPriceInStore))
			return false;
		ProductPriceInStore castOther = (ProductPriceInStore) other;

		return ((this.getStoreId() == castOther.getStoreId()) || (this
				.getStoreId() != null && castOther.getStoreId() != null && this
				.getStoreId().equals(castOther.getStoreId())))
				&& ((this.getProductId() == castOther.getProductId()) || (this
						.getProductId() != null
						&& castOther.getProductId() != null && this
						.getProductId().equals(castOther.getProductId())))
				&& ((this.getPriceValue() == castOther.getPriceValue()) || (this
						.getPriceValue() != null
						&& castOther.getPriceValue() != null && this
						.getPriceValue().equals(castOther.getPriceValue())))
				&& ((this.getPriceValueSched() == castOther
						.getPriceValueSched()) || (this.getPriceValueSched() != null
						&& castOther.getPriceValueSched() != null && this
						.getPriceValueSched().equals(
								castOther.getPriceValueSched())))
				&& ((this.getPriceSchedule() == castOther.getPriceSchedule()) || (this
						.getPriceSchedule() != null
						&& castOther.getPriceSchedule() != null && this
						.getPriceSchedule()
						.equals(castOther.getPriceSchedule())))
				&& ((this.getCreateDt() == castOther.getCreateDt()) || (this
						.getCreateDt() != null
						&& castOther.getCreateDt() != null && this
						.getCreateDt().equals(castOther.getCreateDt())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStoreId() == null ? 0 : this.getStoreId().hashCode());
		result = 37 * result
				+ (getProductId() == null ? 0 : this.getProductId().hashCode());
		result = 37
				* result
				+ (getPriceValue() == null ? 0 : this.getPriceValue()
						.hashCode());
		result = 37
				* result
				+ (getPriceValueSched() == null ? 0 : this.getPriceValueSched()
						.hashCode());
		result = 37
				* result
				+ (getPriceSchedule() == null ? 0 : this.getPriceSchedule()
						.hashCode());
		result = 37 * result
				+ (getCreateDt() == null ? 0 : this.getCreateDt().hashCode());
		return result;
	}

}

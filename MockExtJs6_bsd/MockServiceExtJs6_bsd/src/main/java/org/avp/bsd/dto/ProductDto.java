package org.avp.bsd.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class ProductDto{

	//@JsonProperty
	private String sku;  
	private String image;
	//@JsonProperty
	private String EProductName;
	//@JsonProperty
	private String FProductName;
	//@JsonProperty
	private String EProductDescription;
	//@JsonProperty
	private String FProductDescription;
	//@JsonProperty
	private String EPackaging;
	//@JsonProperty
	private String FPackaging;
	//@JsonProperty
	private String EPriceUnit;
	//@JsonProperty
	private String FPriceUnit;
	//@JsonProperty
	private Integer ordinal;
	//@JsonProperty
	private Boolean active;
	private Date createDt;

	// properties from ProductPriceInStore
	//@JsonProperty
	private Double	price;
	//@JsonProperty
	private Double	priceScheduled;
	//@JsonProperty
	private Date	priceSchedule;
	//@JsonProperty
	private Date	created;

	//private Set<ProductPriceInStore> productsInStore;
	
	public ProductDto() {
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getEProductName() {
		return this.EProductName;
	}

	public void setEProductName(String EProductName) {
		this.EProductName = EProductName;
	}

	public String getFProductName() {
		return this.FProductName;
	}

	public void setFProductName(String FProductName) {
		this.FProductName = FProductName;
	}

	public String getEProductDescription() {
		return this.EProductDescription;
	}

	public void setEProductDescription(String EProductDescription) {
		this.EProductDescription = EProductDescription;
	}

	public String getFProductDescription() {
		return this.FProductDescription;
	}

	public void setFProductDescription(String FProductDescription) {
		this.FProductDescription = FProductDescription;
	}

	public String getEPackaging() {
		return this.EPackaging;
	}

	public void setEPackaging(String EPackaging) {
		this.EPackaging = EPackaging;
	}

	public String getFPackaging() {
		return this.FPackaging;
	}

	public void setFPackaging(String FPackaging) {
		this.FPackaging = FPackaging;
	}

	public String getEPriceUnit() {
		return this.EPriceUnit;
	}

	public void setEPriceUnit(String EPriceUnit) {
		this.EPriceUnit = EPriceUnit;
	}

	public String getFPriceUnit() {
		return this.FPriceUnit;
	}

	public void setFPriceUnit(String FPriceUnit) {
		this.FPriceUnit = FPriceUnit;
	}

	public Integer getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPriceScheduled() {
		return priceScheduled;
	}

	public void setPriceScheduled(Double priceScheduled) {
		this.priceScheduled = priceScheduled;
	}

	public Date getPriceSchedule() {
		return priceSchedule;
	}

	public void setPriceSchedule(Date priceSchedule) {
		this.priceSchedule = priceSchedule;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "ProductDto [sku=" + sku + ", EProductName=" + EProductName
				+ ", EProductDescription=" + EProductDescription + ", price="
				+ price + ", priceScheduled=" + priceScheduled
				+ ", priceSchedule=" + priceSchedule + ", created=" + created
				+ "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

/*	public Set<ProductPriceInStore> getProductsInStore() {
		return productsInStore;
	}

	public void setProductsInStore(Set<ProductPriceInStore> productsInStore) {
		this.productsInStore = productsInStore;
	}
*/
}

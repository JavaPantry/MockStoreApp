package org.avp.bsd.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class ProductDto{

	private String sku;  
	private String EProductName;
	private String FProductName;
	private String EProductDescription;
	private String FProductDescription;
	private String EPackaging;
	private String FPackaging;
	private String EPriceUnit;
	private String FPriceUnit;
	private Integer ordinal;
	private Boolean active;
	private Date createDt;

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

/*	public Set<ProductPriceInStore> getProductsInStore() {
		return productsInStore;
	}

	public void setProductsInStore(Set<ProductPriceInStore> productsInStore) {
		this.productsInStore = productsInStore;
	}
*/
}

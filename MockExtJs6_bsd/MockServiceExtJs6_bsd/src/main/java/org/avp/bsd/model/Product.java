package org.avp.bsd.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product implements java.io.Serializable {

	@Id
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
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean active;
	private Date createDt;

	/*
	 * transient for json convertor to not call getter
	 */
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY , mappedBy="pk.product",cascade={CascadeType.ALL}, orphanRemoval=true)
	private Set<ProductPriceInStore> productsInStore;

	
	public Product() {
	}

	public Product(String sku, String EProductName, String FProductName) {
		this.sku = sku;
		this.EProductName = EProductName;
		this.FProductName = FProductName;
	}

	public Product(String sku, String EProductName,
			String FProductName, String EProductDescription,
			String FProductDescription, String EPackaging, String FPackaging,
			String EPriceUnit, String FPriceUnit, Integer ordinal,
			Boolean active, Date createDt) {
		this.sku = sku;
		this.EProductName = EProductName;
		this.FProductName = FProductName;
		this.EProductDescription = EProductDescription;
		this.FProductDescription = FProductDescription;
		this.EPackaging = EPackaging;
		this.FPackaging = FPackaging;
		this.EPriceUnit = EPriceUnit;
		this.FPriceUnit = FPriceUnit;
		this.ordinal = ordinal;
		this.active = active;
		this.createDt = createDt;
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

	public Set<ProductPriceInStore> getProductsInStore() {
		return productsInStore;
	}

	public void setProductsInStore(Set<ProductPriceInStore> productsInStore) {
		this.productsInStore = productsInStore;
	}

}

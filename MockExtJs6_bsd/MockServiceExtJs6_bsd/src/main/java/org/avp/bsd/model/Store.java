package org.avp.bsd.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "stores")
public class Store implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	
	private String storeName;
	private String clientName;
	private String storeDescription;
	@Column(columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private Boolean attSecurity;
	private String attLangPref;
	private String clientLogo;
	private Date createDt;

	@OneToMany(fetch=FetchType.EAGER , mappedBy="pk.store",cascade={CascadeType.ALL}, orphanRemoval=true)//CascadeType.PERSIST, CascadeType.MERGE, 
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<ProductPriceInStore> productsInStore;

	
	public Store() {
	}

	public Store(String storeName,
			String clientName, String storeDescription, Boolean attSecurity,
			String attLangPref, String clientLogo) {
		this.storeName = storeName;
		this.clientName = clientName;
		this.storeDescription = storeDescription;
		this.attSecurity = attSecurity;
		this.attLangPref = attLangPref;
		this.clientLogo = clientLogo;
		this.createDt = new Date();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getStoreDescription() {
		return this.storeDescription;
	}

	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}

	public Boolean getAttSecurity() {
		return this.attSecurity;
	}

	public void setAttSecurity(Boolean attSecurity) {
		this.attSecurity = attSecurity;
	}

	public String getAttLangPref() {
		return this.attLangPref;
	}

	public void setAttLangPref(String attLangPref) {
		this.attLangPref = attLangPref;
	}

	public String getClientLogo() {
		return this.clientLogo;
	}

	public void setClientLogo(String clientLogo) {
		this.clientLogo = clientLogo;
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

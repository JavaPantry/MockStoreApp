package org.avp.bsd.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class StoreDto implements java.io.Serializable {

	private Long id;
	
	private String storeName;
	private String clientName;
	private String storeDescription;
	
	private Boolean attSecurity;
	private String attLangPref;
	private String clientLogo;
	private Date createDt;

	//private Set<ProductPriceInStore> productsInStore;
	//private Set<BsdUser> users;
	
	public StoreDto() {
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

/*
	public Set<ProductPriceInStore> getProductsInStore() {
		return productsInStore;
	}

	public void setProductsInStore(Set<ProductPriceInStore> productsInStore) {
		this.productsInStore = productsInStore;
	}

	public Set<BsdUser> getUsers() {
		return users;
	}

	public void setUsers(Set<BsdUser> users) {
		this.users = users;
	}
*/
	
}

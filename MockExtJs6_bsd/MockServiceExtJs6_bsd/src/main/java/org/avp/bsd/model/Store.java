package org.avp.bsd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Store() {
	}

	public Store(Long id, String storeName,
			String clientName, String storeDescription, Boolean attSecurity,
			String attLangPref, String clientLogo, Date createDt) {
		this.id = id;
		this.storeName = storeName;
		this.clientName = clientName;
		this.storeDescription = storeDescription;
		this.attSecurity = attSecurity;
		this.attLangPref = attLangPref;
		this.clientLogo = clientLogo;
		this.createDt = createDt;
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


}

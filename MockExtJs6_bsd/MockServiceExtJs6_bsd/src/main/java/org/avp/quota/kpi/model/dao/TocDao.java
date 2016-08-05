package org.avp.quota.kpi.model.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * TOC - transactional organization code
 * 
 * Guessing:
 * in project context TOC is a record for department
 * and orgLayerNumber property - place in organization hierarchy
 * 
 * So the best name for this entity would be Department
 * 
 * SalesRepesentative belongs to Department (TOC)
 * 
 * 
 * imported from as400 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "ids_toc")
public final class TocDao  implements Serializable{

	public TocDao() {
		super();
	}

	
	
	public TocDao(String tocId, String tocName/*, String coa_brCode,
			String coa_chCode, String coa_prodCode, String orgCode,
			Integer orgLayerNumber*/) {
		super();
		this.tocId = tocId;
		this.tocName = tocName;
		/*this.coa_brCode = coa_brCode;
		this.coa_chCode = coa_chCode;
		this.coa_prodCode = coa_prodCode;
		this.orgCode = orgCode;
		this.orgLayerNumber = orgLayerNumber;*/
	}



	@Id
	@Column(name="toc_cd")//@ColumnTransformer(read="RTRIM(LTRIM(toc_cd))")
	private String tocId;

	@Column(name="toc_nm")//@ColumnTransformer(read="RTRIM(LTRIM(toc_nm))")
	private String tocName;

	@Column(name="coa_br_cd")//@ColumnTransformer(read="RTRIM(LTRIM(coa_br_cd))")
	private String coa_brCode;
	
	@Column(name="coa_ch_cd")//@ColumnTransformer(read="RTRIM(LTRIM(coa_ch_cd))")
	private String coa_chCode;

	@Column(name="coa_prod_cd")//@ColumnTransformer(read="RTRIM(LTRIM(coa_prod_cd))")
	private String coa_prodCode;

	@Column(name="org_cd")//@ColumnTransformer(read="RTRIM(LTRIM(org_cd))")
	private String orgCode;
	
	@Column(name="org_layer_num")
	private Integer orgLayerNumber;


	public String getTocId() {return tocId;}
	public void setTocId(String tocId) {this.tocId = tocId;}
	public String getTocName() {return tocName;}
	public void setTocName(String tocName) {this.tocName = tocName;}
	public String getCoa_brCode() {return coa_brCode;}
	public void setCoa_brCode(String coa_brCode) {this.coa_brCode = coa_brCode;}
	public String getCoa_chCode() {return coa_chCode;}
	public void setCoa_chCode(String coa_chCode) {this.coa_chCode = coa_chCode;}
	public String getCoa_prodCode() {return coa_prodCode;}
	public void setCoa_prodCode(String coa_prodCode) {this.coa_prodCode = coa_prodCode;}
	public String getOrgCode() {return orgCode;}
	public void setOrgCode(String orgCode) {this.orgCode = orgCode;}
	public Integer getOrgLayerNumber() {return orgLayerNumber;}
	public void setOrgLayerNumber(Integer orgLayerNumber) {this.orgLayerNumber = orgLayerNumber;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coa_brCode == null) ? 0 : coa_brCode.hashCode());
		result = prime * result
				+ ((coa_chCode == null) ? 0 : coa_chCode.hashCode());
		result = prime * result
				+ ((coa_prodCode == null) ? 0 : coa_prodCode.hashCode());
		result = prime * result + ((orgCode == null) ? 0 : orgCode.hashCode());
		result = prime * result
				+ ((orgLayerNumber == null) ? 0 : orgLayerNumber.hashCode());
		result = prime * result + ((tocId == null) ? 0 : tocId.hashCode());
		result = prime * result + ((tocName == null) ? 0 : tocName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TocDao other = (TocDao) obj;
		if (tocId == null) {
			if (other.tocId != null)
				return false;
		} else if (!tocId.equals(other.tocId))
			return false;
		/*if (tocName == null) {
			if (other.tocName != null)
				return false;
		} else if (!tocName.equals(other.tocName))
			return false;*/
		return true;
	}

	@Override
	public String toString() {
		return String
				.format("TocDao [tocId=%s, tocName=%s, coa_brCode=%s, coa_chCode=%s, coa_prodCode=%s, orgCode=%s, orgLayerNumber=%s]",
						tocId, tocName, coa_brCode, coa_chCode, coa_prodCode, orgCode, orgLayerNumber);
	}

}

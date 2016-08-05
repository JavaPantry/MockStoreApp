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
 * imported from as400 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ids_mdse_stru_lv3")
public final class CategoryDao  implements Serializable{

	public CategoryDao() {
		super();
	}

/*	public CategoryDao(String category, String categoryName) {
		super();
		this.category = category;
		this.categoryName = categoryName;
	}*/
	public CategoryDao(String categoryId, String categoryName, String zerothProdCtrlCode, String zerothProdCtrlName,String firstProdCtrlCode, String firstProdCtrlName
			) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.zerothProdCtrlCode = zerothProdCtrlCode;
		this.zerothProdCtrlName = zerothProdCtrlName;
		this.firstProdCtrlCode = firstProdCtrlCode;
		this.firstProdCtrlName = firstProdCtrlName;
	}

	@Column(name="zeroth_prod_ctrl_cd")//@ColumnTransformer(read="RTRIM(LTRIM(zeroth_prod_ctrl_cd))")
	private String zerothProdCtrlCode;
	
	@Column(name="zeroth_prod_ctrl_nm")//@ColumnTransformer(read="RTRIM(LTRIM(zeroth_prod_ctrl_nm))")
	private String zerothProdCtrlName;
	
	@Column(name="first_prod_ctrl_cd")//@ColumnTransformer(read="RTRIM(LTRIM(first_prod_ctrl_cd))")
	private String firstProdCtrlCode;
	
	@Column(name="first_prod_ctrl_nm")//@ColumnTransformer(read="RTRIM(LTRIM(first_prod_ctrl_nm))")
	private String firstProdCtrlName;
	
	@Column(name="scd_prod_ctrl_cd")//@ColumnTransformer(read="RTRIM(LTRIM(scd_prod_ctrl_cd))")
	private String secondProdCtrlCode;
	
	@Column(name="scd_prod_ctrl_nm")//@ColumnTransformer(read="RTRIM(LTRIM(scd_prod_ctrl_nm))")
	private String secondProdCtrlName;
	
	@Id
	@Column(name="third_prod_ctrl_cd")//@ColumnTransformer(read="RTRIM(LTRIM(third_prod_ctrl_cd))")
	private String categoryId;
	
	@Column(name="third_prod_ctrl_nm")//@ColumnTransformer(read="RTRIM(LTRIM(third_prod_ctrl_nm))")
	private String categoryName;

	public String getZerothProdCtrlCode() {	return zerothProdCtrlCode;}
	public void setZerothProdCtrlCode(String zerothProdCtrlCode) {	this.zerothProdCtrlCode = zerothProdCtrlCode;}
	public String getZerothProdCtrlName() {	return zerothProdCtrlName;}
	public void setZerothProdCtrlName(String zerothProdCtrlName) {	this.zerothProdCtrlName = zerothProdCtrlName;}
	public String getFirstProdCtrlCode() {	return firstProdCtrlCode;}
	public void setFirstProdCtrlCode(String firstProdCtrlCode) {	this.firstProdCtrlCode = firstProdCtrlCode;}
	public String getFirstProdCtrlName() {	return firstProdCtrlName;}
	public void setFirstProdCtrlName(String firstProdCtrlName) {	this.firstProdCtrlName = firstProdCtrlName;}
	public String getSecondProdCtrlCode() {	return secondProdCtrlCode;}
	public void setSecondProdCtrlCode(String secondProdCtrlCode) {	this.secondProdCtrlCode = secondProdCtrlCode;}
	public String getSecondProdCtrlName() {	return secondProdCtrlName;}
	public void setSecondProdCtrlName(String secondProdCtrlName) {	this.secondProdCtrlName = secondProdCtrlName;}
	public String getCategoryId() {	return categoryId;}
	public void setCategoryId(String categoryId) {	this.categoryId = categoryId;}
	public String getCategoryName() {	return categoryName;}
	public void setCategoryName(String categoryName) {	this.categoryName = categoryName;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime
				* result
				+ ((firstProdCtrlCode == null) ? 0 : firstProdCtrlCode
						.hashCode());
		result = prime
				* result
				+ ((firstProdCtrlName == null) ? 0 : firstProdCtrlName
						.hashCode());
		result = prime
				* result
				+ ((secondProdCtrlCode == null) ? 0 : secondProdCtrlCode
						.hashCode());
		result = prime
				* result
				+ ((secondProdCtrlName == null) ? 0 : secondProdCtrlName
						.hashCode());
		result = prime
				* result
				+ ((zerothProdCtrlCode == null) ? 0 : zerothProdCtrlCode
						.hashCode());
		result = prime
				* result
				+ ((zerothProdCtrlName == null) ? 0 : zerothProdCtrlName
						.hashCode());
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
		CategoryDao other = (CategoryDao) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (firstProdCtrlCode == null) {
			if (other.firstProdCtrlCode != null)
				return false;
		} else if (!firstProdCtrlCode.equals(other.firstProdCtrlCode))
			return false;
		if (firstProdCtrlName == null) {
			if (other.firstProdCtrlName != null)
				return false;
		} else if (!firstProdCtrlName.equals(other.firstProdCtrlName))
			return false;
		if (secondProdCtrlCode == null) {
			if (other.secondProdCtrlCode != null)
				return false;
		} else if (!secondProdCtrlCode.equals(other.secondProdCtrlCode))
			return false;
		if (secondProdCtrlName == null) {
			if (other.secondProdCtrlName != null)
				return false;
		} else if (!secondProdCtrlName.equals(other.secondProdCtrlName))
			return false;
		if (zerothProdCtrlCode == null) {
			if (other.zerothProdCtrlCode != null)
				return false;
		} else if (!zerothProdCtrlCode.equals(other.zerothProdCtrlCode))
			return false;
		if (zerothProdCtrlName == null) {
			if (other.zerothProdCtrlName != null)
				return false;
		} else if (!zerothProdCtrlName.equals(other.zerothProdCtrlName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String
				.format("CategoryDao [zerothProdCtrlCode=%s, zerothProdCtrlName=%s, firstProdCtrlCode=%s, firstProdCtrlName=%s, secondProdCtrlCode=%s, secondProdCtrlName=%s, categoryId=%s, categoryName=%s]",
						zerothProdCtrlCode, zerothProdCtrlName,
						firstProdCtrlCode, firstProdCtrlName,
						secondProdCtrlCode, secondProdCtrlName, categoryId,
						categoryName);
	}

}

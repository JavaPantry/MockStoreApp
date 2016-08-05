package org.avp.quota.kpi.model.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnTransformer;

/*
 *
 * select * from ids_quota
 * 
 * http://www.java2s.com/Tutorial/Java/0355__JPA/DiscriminatorColumnAndDiscriminatorValue.htm
 * @Entity
 * @Table(name = "PERSON_HIERARCHY")
 * @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 * @DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING)
 * @DiscriminatorValue("PERSON")
 * 
 * "nested exception is java.lang.ClassCastException: org.hibernate.mapping.SingleTableSubclass cannot be cast to org.hibernate.mapping.RootClass"
 * http://stackoverflow.com/questions/15516460/sessionfactory-bean-creation-failed-classcastexception-is-thrown
 * When you are using inheritance in POJO super class should have the uniqueId as the primary key and the sub classess can only use 
 * it as foreign key if you do not do it that way i feel u land in this exception. 
 * Try making the subclass primary key different and also provide a foreign key connection between the super class and the subclass. 
 * Check this is a good example that is solved:
 * http://stackoverflow.com/q/12087011/2006839
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ids_quota")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING)
public class BaseQuotaDao implements Serializable{

	public BaseQuotaDao() {
		super();
	}

	
	public BaseQuotaDao(/*String alt_salesrep_cd,*/CategoryDao category, int amountType, int year){
		super();
		//this.alt_salesrep_cd = alt_salesrep_cd;
		this.category = category;
		this.amountType = amountType;
		this.year = year;
	}


	@Id
	@GeneratedValue
	@Column(name="idsQuotaId")
	private Long id;

	
	//moved to QuotaDao 
	//@Column(name="alt_salesrep_cd")
	//private String alt_salesrep_cd;

	//@Column(name="third_prod_ctrl_cd")
	//private String category;
	@ManyToOne(optional=false)
    @JoinColumn(name="third_prod_ctrl_cd", referencedColumnName="third_prod_ctrl_cd")
	private CategoryDao category;

	@Column(name="AmtType")
	private int amountType;

	@Column(name="Year")
	private int year;

	@Column(name="A1")
	private int value1;
	
	@Column(name="A2")
	private int value2;
	
	@Column(name="A3")
	private int value3;
	
	@Column(name="A4")
	private int value4;
	
	@Column(name="A5")
	private int value5;
	
	@Column(name="A6")
	private int value6;
	
	@Column(name="A7")
	private int value7;
	
	@Column(name="A8")
	private int value8;
	
	@Column(name="A9")
	private int value9;
	
	@Column(name="A10")
	private int value10;
	
	@Column(name="A11")
	private int value11;
	
	@Column(name="A12")
	private int value12;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
//	public String getAlt_salesrep_cd() {return alt_salesrep_cd;}
//	public void setAlt_salesrep_cd(String alt_salesrep_cd) {this.alt_salesrep_cd = alt_salesrep_cd;}
	public CategoryDao getCategory() {return category;}
	public void setCategory(CategoryDao category) {this.category = category;}
	public int getAmountType() {return amountType;}
	public void setAmountType(int amountType) {this.amountType = amountType;}
	public int getYear() {return year;}
	public void setYear(int year) {this.year = year;}
	public int getValue1() {return value1;}
	public void setValue1(int value1) {this.value1 = value1;}
	public int getValue2() {return value2;}
	public void setValue2(int value2) {this.value2 = value2;}
	public int getValue3() {return value3;}
	public void setValue3(int value3) {this.value3 = value3;}
	public int getValue4() {return value4;}
	public void setValue4(int value4) {this.value4 = value4;}
	public int getValue5() {return value5;}
	public void setValue5(int value5) {this.value5 = value5;}
	public int getValue6() {return value6;}
	public void setValue6(int value6) {this.value6 = value6;}
	public int getValue7() {return value7;}
	public void setValue7(int value7) {this.value7 = value7;}
	public int getValue8() {return value8;}
	public void setValue8(int value8) {this.value8 = value8;}
	public int getValue9() {return value9;}
	public void setValue9(int value9) {this.value9 = value9;}
	public int getValue10() {return value10;}
	public void setValue10(int value10) {this.value10 = value10;}
	public int getValue11() {return value11;}
	public void setValue11(int value11) {this.value11 = value11;}
	public int getValue12() {return value12;}
	public void setValue12(int value12) {this.value12 = value12;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result
//				+ ((alt_salesrep_cd == null) ? 0 : alt_salesrep_cd.hashCode());
		result = prime * result + amountType;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + value1;
		result = prime * result + value10;
		result = prime * result + value11;
		result = prime * result + value12;
		result = prime * result + value2;
		result = prime * result + value3;
		result = prime * result + value4;
		result = prime * result + value5;
		result = prime * result + value6;
		result = prime * result + value7;
		result = prime * result + value8;
		result = prime * result + value9;
		result = prime * result + year;
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
		BaseQuotaDao other = (BaseQuotaDao) obj;
		/*if (alt_salesrep_cd == null) {
			if (other.alt_salesrep_cd != null)
				return false;
		} else if (!alt_salesrep_cd.equals(other.alt_salesrep_cd))
			return false;*/
		if (amountType != other.amountType)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (value1 != other.value1)
			return false;
		if (value10 != other.value10)
			return false;
		if (value11 != other.value11)
			return false;
		if (value12 != other.value12)
			return false;
		if (value2 != other.value2)
			return false;
		if (value3 != other.value3)
			return false;
		if (value4 != other.value4)
			return false;
		if (value5 != other.value5)
			return false;
		if (value6 != other.value6)
			return false;
		if (value7 != other.value7)
			return false;
		if (value8 != other.value8)
			return false;
		if (value9 != other.value9)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String
				.format("QuotaDao [id=%s, category=%s, amountType=%s, year=%s, value1=%s, value2=%s, value3=%s, value4=%s, value5=%s, value6=%s, value7=%s, value8=%s, value9=%s, value10=%s, value11=%s, value12=%s]",
						id, /*alt_salesrep_cd=%s <=> alt_salesrep_cd, */category, amountType, year,
					value1, value2, value3, value4, value5, value6, value7,
					value8, value9, value10, value11, value12);
	}

	
}

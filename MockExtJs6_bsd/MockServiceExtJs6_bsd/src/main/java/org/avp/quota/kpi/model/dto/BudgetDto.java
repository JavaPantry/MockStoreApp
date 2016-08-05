package org.avp.quota.kpi.model.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BudgetDto implements Serializable{

	private Long id;
	private String categoryId;
	private String categoryName;
	private int amountType;
	private int year;
	private int value1;
	private int value2;
	private int value3;
	private int value4;
	private int value5;
	private int value6;
	private int value7;
	private int value8;
	private int value9;
	private int value10;
	private int value11;
	private int value12;

	public BudgetDto() {
		super();
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getCategoryId() {return categoryId;}
	public void setCategoryId(String categoryId) {this.categoryId = categoryId;}
	public String getCategoryName() {return categoryName;}
	public void setCategoryName(String categoryName) {this.categoryName = categoryName;}
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

}
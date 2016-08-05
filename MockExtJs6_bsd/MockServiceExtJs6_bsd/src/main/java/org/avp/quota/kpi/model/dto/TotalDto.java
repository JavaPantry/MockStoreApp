package org.avp.quota.kpi.model.dto;

/**
 * Class to fill in Quota/Budjet aggregate hql
 * 
 *  org.avp.quota.kpi.model.dto.TotalDto(value1, value2, value3, value4,
										value5, value6, value7, value8, 
										value9, value10, value11, value12)
 * @author Q05459
 *
 */
public class TotalDto {
	
	private long value1;
	private long value2;
	private long value3;
	private long value4;
	private long value5;
	private long value6;
	private long value7;
	private long value8;
	private long value9;
	private long value10;
	private long value11;
	private long value12;
	private long total;
	
	public TotalDto(){
		
	}
	
	public TotalDto(long value1, long value2, long value3, long value4,
			long value5, long value6, long value7, long value8, long value9,
			long value10, long value11, long value12) {
		super();
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
		this.value5 = value5;
		this.value6 = value6;
		this.value7 = value7;
		this.value8 = value8;
		this.value9 = value9;
		this.value10 = value10;
		this.value11 = value11;
		this.value12 = value12;
		updateTotal();
	}

	public void updateTotal(){
		this.total =  value1 + value2 + value3 + value4 + value5 + value6
					+ value7 + value8 + value9 + value10 + value11 + value12; 
	}
	 
	public long getValue1() {return value1;}
	public void setValue1(long value1) {this.value1 = value1;}
	public long getValue2() {return value2;}
	public void setValue2(long value2) {this.value2 = value2;}
	public long getValue3() {return value3;}
	public void setValue3(long value3) {this.value3 = value3;}
	public long getValue4() {return value4;}
	public void setValue4(long value4) {this.value4 = value4;}
	public long getValue5() {return value5;}
	public void setValue5(long value5) {this.value5 = value5;}
	public long getValue6() {return value6;}
	public void setValue6(long value6) {this.value6 = value6;}
	public long getValue7() {return value7;}
	public void setValue7(long value7) {this.value7 = value7;}
	public long getValue8() {return value8;}
	public void setValue8(long value8) {this.value8 = value8;}
	public long getValue9() {return value9;}
	public void setValue9(long value9) {this.value9 = value9;}
	public long getValue10() {return value10;}
	public void setValue10(long value10) {this.value10 = value10;}
	public long getValue11() {return value11;}
	public void setValue11(long value11) {this.value11 = value11;}
	public long getValue12() {return value12;}
	public void setValue12(long value12) {this.value12 = value12;}

	public long getTotal() {return total;}
	public void setTotal(long total) {this.total = total;}

	
}

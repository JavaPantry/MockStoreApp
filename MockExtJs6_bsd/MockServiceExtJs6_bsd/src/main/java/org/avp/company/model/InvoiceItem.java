package org.avp.company.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InvoiceItem implements Serializable {

	private Long id;
	private String serialNo;
	
	private Double bwPrice;
	private Double clrPrice;
	
	private Integer bwMeter;
	private Integer clrMeter;
	
	public InvoiceItem(){
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public Double getBwPrice() {
		return bwPrice;
	}
	
	public void setBwPrice(Double bwPrice) {
		this.bwPrice = bwPrice;
	}
	
	public Double getClrPrice() {
		return clrPrice;
	}
	
	public void setClrPrice(Double clrPrice) {
		this.clrPrice = clrPrice;
	}
	
	public Integer getBwMeter() {
		return bwMeter;
	}
	
	public void setBwMeter(Integer bwMeter) {
		this.bwMeter = bwMeter;
	}
	
	public Integer getClrMeter() {
		return clrMeter;
	}
	
	public void setClrMeter(Integer clrMeter) {
		this.clrMeter = clrMeter;
	}

	public void merge(InvoiceItem newInvoiceItem) {
		serialNo = newInvoiceItem.getSerialNo();
		bwPrice = newInvoiceItem.getBwPrice();
		clrPrice = newInvoiceItem.getClrPrice();
		bwMeter = newInvoiceItem.getBwMeter();
		clrMeter = newInvoiceItem.getClrMeter();
	}
	
}

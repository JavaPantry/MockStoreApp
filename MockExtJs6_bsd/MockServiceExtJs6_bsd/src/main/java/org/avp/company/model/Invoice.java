package org.avp.company.model;

import java.util.Date;
import java.util.Set;

@SuppressWarnings("serial")
public class Invoice implements java.io.Serializable {

	private String invoiceNo;
	private Date date;
	
	private Set<InvoiceItem> items;
	
	public Invoice(){
		super();
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Set<InvoiceItem> getItems() {
		return items;
	}
	
	public void setItems(Set<InvoiceItem> items) {
		this.items = items;
	}
	
	
}

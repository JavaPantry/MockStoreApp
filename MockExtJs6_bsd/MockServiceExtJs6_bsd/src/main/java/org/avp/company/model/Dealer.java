package org.avp.company.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dealer")
public class Dealer  extends NamedWithAddress{

	private Contact tonerContact = new Contact();
	

	public Contact getServiceContact() {
		return getContact();
	}
	
	public void setServiceContact(Contact serviceContact) {
		this.setContact(serviceContact);
	}
	
	public Contact getTonerContact() {
		return tonerContact;
	}
	
	public void setTonerContact(Contact tonerContact) {
		this.tonerContact = tonerContact;
	}
	
}

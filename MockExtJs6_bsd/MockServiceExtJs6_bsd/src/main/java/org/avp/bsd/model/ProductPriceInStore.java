package org.avp.bsd.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="ProductPriceInStore")
@AssociationOverrides({
	@AssociationOverride(name ="pk.store", joinColumns = @JoinColumn(name ="store")),// this is real column name
	@AssociationOverride(name ="pk.product", joinColumns = @JoinColumn(name ="product"))// this is real column name
    })
public class ProductPriceInStore implements java.io.Serializable {

	@EmbeddedId
	private StoreProductPK pk;

	
	private Double price;
	private Double priceScheduled;
	private Date priceSchedule;
	private Date created;

	public ProductPriceInStore() {
	}

	public ProductPriceInStore( StoreProductPK pk, 
								Double price,
								Double priceScheduled, 
								Date priceSchedule) {
		this.pk = pk;
		this.price = price;
		this.priceScheduled = priceScheduled;
		this.priceSchedule = priceSchedule;
		this.created = new Date();
	}

	public StoreProductPK getPk() {
		return pk;
	}

	public void setPk(StoreProductPK pk) {
		this.pk = pk;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPriceScheduled() {
		return priceScheduled;
	}

	public void setPriceScheduled(Double priceScheduled) {
		this.priceScheduled = priceScheduled;
	}

	public Date getPriceSchedule() {
		return priceSchedule;
	}

	public void setPriceSchedule(Date priceSchedule) {
		this.priceSchedule = priceSchedule;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}


}

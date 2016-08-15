package org.avp.bsd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * For usage see SalesRepEmployeeJoin and for description see
 * 1) http://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Example_of_a_ManyToMany_relationship_annotation
 * 2) >https://giannigar.wordpress.com/2009/09/04/mapping-a-many-to-many-join-table-with-extra-column-using-jpa/
 */

@SuppressWarnings("serial")
@Embeddable
public class StoreProductPK implements Serializable {

	public StoreProductPK() {}

	public StoreProductPK(Store store, Product product) {
		super();
		this.store = store;
		this.product = product;
	}

	@ManyToOne
	private Store store;

	@ManyToOne
	private Product product; 					

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}

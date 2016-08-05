package org.avp.quota.kpi.model.dao;

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
public class SalesRepEmployeePK implements Serializable {

	public SalesRepEmployeePK() {}

	public SalesRepEmployeePK(SalesRepresentativeDao salesRepresentative, EmployeeDao manager, ProductLine productLine) {
		super();
		this.salesRepresentative = salesRepresentative;
		this.manager = manager;
		this.productLine = productLine;
	}

	@ManyToOne
	private SalesRepresentativeDao salesRepresentative;//Column(name="alt_salesrep_cd")//transient 

	@ManyToOne
	private EmployeeDao manager;//Column(name="manager_id") //transient 					

	@ManyToOne
	@JoinColumn(name="first_prod_ctrl_cd")//, referencedColumnName = "code"
	private ProductLine productLine;

	public ProductLine getProductLine() {return productLine;}
	public void setProductLine(ProductLine productLine) {this.productLine = productLine;}
	public SalesRepresentativeDao getSalesRepresentative() {return salesRepresentative;}
	public void setSalesRepresentative(SalesRepresentativeDao salesRepresentative) {this.salesRepresentative = salesRepresentative;}
	public EmployeeDao getManager() {return manager;}
	public void setManager(EmployeeDao manager) {this.manager = manager;}
	
}

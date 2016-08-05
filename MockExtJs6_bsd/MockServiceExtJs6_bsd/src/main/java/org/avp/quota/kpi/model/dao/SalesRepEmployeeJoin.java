package org.avp.quota.kpi.model.dao;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * SalesRepesentative connected with manager(employee table)
 * 
 * Record: |SalesRepesentative|manager|product Line|
 * 
 * See:
 * 1) http://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Example_of_a_ManyToMany_relationship_annotation
 * 2) >https://giannigar.wordpress.com/2009/09/04/mapping-a-many-to-many-join-table-with-extra-column-using-jpa/
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name="ids_manager")
@AssociationOverrides({
	@AssociationOverride(name ="pk.manager", joinColumns = @JoinColumn(name ="manager_id")),
	@AssociationOverride(name ="pk.salesRepresentative", joinColumns = @JoinColumn(name ="alt_salesrep_cd"))
    })
public class SalesRepEmployeeJoin implements Serializable {

	public SalesRepEmployeeJoin() {}

	public SalesRepEmployeeJoin(SalesRepEmployeePK pk) {
		super();
		this.pk = pk;
	}

	@EmbeddedId
	private SalesRepEmployeePK pk;

	public SalesRepEmployeePK getPk() {return pk;}
	public void setPk(SalesRepEmployeePK pk) {this.pk = pk;}
	
	@Transient
	public EmployeeDao getManager() {return getPk().getManager();}
	public void setManager(EmployeeDao manager) {getPk().setManager(manager);}
	@Transient
	public SalesRepresentativeDao getSalesRepresentative() {return getPk().getSalesRepresentative();}
	public void setSalesRepresentative(SalesRepresentativeDao salesRepresentative) {getPk().setSalesRepresentative(salesRepresentative);}
	@Transient
	public ProductLine getProductLine() {return getPk().getProductLine();}
	public void setProductLine(ProductLine productLine) {getPk().setProductLine(productLine);}

}

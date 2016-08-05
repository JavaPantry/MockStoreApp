package org.avp.company.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "billto")
public class BillTo extends NamedWithAddress {

	
	@ManyToOne
	@JoinColumn(name="companyId")
	private Company company;

	@OneToMany(fetch=FetchType.EAGER, mappedBy = "billTo")
	private Set<ShipTo> shipTos;

	public Set<ShipTo> getShipTos() {
		return shipTos;
	}

	public void setShipTos(Set<ShipTo> shipTos) {
		this.shipTos = shipTos;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}

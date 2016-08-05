package org.avp.company.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shipto")
public class ShipTo extends NamedWithAddress {

	@ManyToOne
	@JoinColumn(name="billtoId")
	private BillTo	billTo;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "shipTo")
	private Set<Machine> machines;
	
	private String businessHours;
	@Column(name="lunchOpen", columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private boolean lunchOpen;
	private String lunchStart;
	private String lunchEnd;

	public BillTo getBillTo() {
		return billTo;
	}

	public void setBillTo(BillTo billTo) {
		this.billTo = billTo;
	}

	public Set<Machine> getMachines() {
		return machines;
	}

	public void setMachines(Set<Machine> machines) {
		this.machines = machines;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public boolean isLunchOpen() {
		return lunchOpen;
	}

	public void setLunchOpen(boolean lunchOpen) {
		this.lunchOpen = lunchOpen;
	}

	public String getLunchStart() {
		return lunchStart;
	}

	public void setLunchStart(String lunchStart) {
		this.lunchStart = lunchStart;
	}

	public String getLunchEnd() {
		return lunchEnd;
	}

	public void setLunchEnd(String lunchEnd) {
		this.lunchEnd = lunchEnd;
	}
	
}

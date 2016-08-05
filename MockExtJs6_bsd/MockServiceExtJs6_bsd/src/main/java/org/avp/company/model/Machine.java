package org.avp.company.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import org.avp.company.util.CustomExtJsDateDeserializer;

//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "machine")
public class Machine extends Named{

	private String serialNo;
	private String model;
	private String manufacturer;
	private String machineType;
	private String modelType;
	private String segment;
	private double bwBillRate;
	private double colorBillRate;
	private String billingCycle;
	
	//@JsonDeserialize(using = CustomExtJsDateDeserializer.class)
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
	private Date contractStart;

	//@JsonDeserialize(using = CustomExtJsDateDeserializer.class)
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
	private Date contractEnd;
	
	private int escalators;
	private int bwMinimum;
	private int clrMinimum;
	private String valuePack;
	
	@Column(name="poRequired", columnDefinition = "BIT", length = 1)//, columnDefinition="boolean default false", nullable=false,
	private boolean poRequired;
	
	@ManyToOne
	@JoinColumn(name="shiptoId")
	private ShipTo shipTo;

	@ManyToOne
	@JoinColumn(name="dealerId")
	private Dealer dealer;
	
	public String getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getShipToCode() {
		return null == shipTo ? null : shipTo.getCode();
	}

	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String type) {
		this.modelType = type;
	}

	public String getSegment() {
		return segment;
	}
	
	public void setSegment(String segment) {
		this.segment = segment;
	}
	
	public double getBwBillRate() {
		return bwBillRate;
	}
	
	public void setBwBillRate(double bwBillRate) {
		this.bwBillRate = bwBillRate;
	}
	
	public double getColorBillRate() {
		return colorBillRate;
	}
	
	public void setColorBillRate(double colorBillRate) {
		this.colorBillRate = colorBillRate;
	}
	
	public String getBillingCycle() {
		return billingCycle;
	}
	
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
	
	public Date getContractStart() {
		return contractStart;
	}
	
	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}
	
	public Date getContractEnd() {
		return contractEnd;
	}
	
	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public int getEscalators() {
		return escalators;
	}

	public void setEscalators(int escalators) {
		this.escalators = escalators;
	}

	public int getBwMinimum() {
		return bwMinimum;
	}

	public void setBwMinimum(int bwMinimum) {
		this.bwMinimum = bwMinimum;
	}

	public int getClrMinimum() {
		return clrMinimum;
	}

	public void setClrMinimum(int clrMinimum) {
		this.clrMinimum = clrMinimum;
	}

	public String getValuePack() {
		return valuePack;
	}

	public void setValuePack(String valuePack) {
		this.valuePack = valuePack;
	}

	public boolean isPoRequired() {
		return poRequired;
	}

	public void setPoRequired(boolean poRequired) {
		this.poRequired = poRequired;
	}

	public ShipTo getShipTo() {
		return shipTo;
	}

	public void setShipTo(ShipTo shipTo) {
		this.shipTo = shipTo;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	
	public boolean isUnderValidContract() {
		Date now = new Date();
		return
			null != getContractStart() && (
				now.after(getContractStart()) && (null == getContractEnd() || now.before(getContractEnd()))
			);
	}
}

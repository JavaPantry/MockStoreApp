package org.avp.quota.kpi.model.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * A	CAMERA
 * B	CALCULATOR
 * C	MICRO
 * D	COPIER
 * E	BCD
 * F	SEMICOM
 * I	PC COPIER
 * J	PERIPHERALS
 * K	FAX
 * L	LBP
 * M	TYPE WRITER
 * N	MEDICAL
 * P	C-ETW
 * Q	LFP
 * T	PRINTER
 * U	FAX PHONE
 * V	VIDEO
 * X	VISUAL COMM SYSTEMS
 * Y	GRAPHICS
 * Z	ADMIN
 * 
 * @author Q05459
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "productline")
public class ProductLine implements Serializable {

	@NotNull
	@Size(max=2)
	@Id
	@Column(name="code")
	private String code;
	
	@NotNull
	@Size(max=50)
	@Column(name="description")
	private String description;

	public ProductLine(){
		super();
	}
	
	public ProductLine(String code, String description) {
		super();
		this.code = code; 
		this.description = description;
	}
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductLine other = (ProductLine) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("ProductLine [code=%s, description=%s]", code,
				description);
	}
}

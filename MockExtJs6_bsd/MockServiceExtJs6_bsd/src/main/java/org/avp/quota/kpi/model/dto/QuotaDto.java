package org.avp.quota.kpi.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.avp.quota.kpi.model.dao.BaseQuotaDao;
import org.avp.quota.kpi.model.dao.CategoryDao;

@SuppressWarnings("serial")
public class QuotaDto extends BudgetDto  implements Serializable{

	private String salesRepresentativeId;
	private String salesRepresentativeName;

	public QuotaDto() {
		super();
	}

	public String getSalesRepresentativeId() {return salesRepresentativeId;}
	public void setSalesRepresentativeId(String salesRepresentativeId) {this.salesRepresentativeId = salesRepresentativeId;}
	public String getSalesRepresentativeName() {return salesRepresentativeName;}
	public void setSalesRepresentativeName(String salesRepresentativeName) {this.salesRepresentativeName = salesRepresentativeName;}

}

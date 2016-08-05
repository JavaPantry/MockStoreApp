package org.avp.quota.kpi.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * for difference JPA JoinColumn vs mappedBy
 * see http://stackoverflow.com/questions/11938253/jpa-joincolumn-vs-mappedby
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Q")
public class QuotaDao extends BaseQuotaDao implements Serializable{
	public QuotaDao() {super();}
	//public QuotaDao(String alt_salesrep_cd, CategoryDao category, int amountType, int year){
	public QuotaDao(SalesRepresentativeDao salesRepresentative, CategoryDao category, int amountType, int year){
		super(/*alt_salesrep_cd, */category, amountType, year);
		this.salesRepresentative = salesRepresentative;
	}

	/*
	 * 'optional' set to true to make it nullable in BudgetDao
	 * without @OneToMany on SalesRepresentativeDao side this salesRepresentative will be null (but in database will have proper value)
	 * In SalesRepresentativeDao try:
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "salesRepresentative")
	 * private List<QuotaDao> quotas;
	 * 
	 * ERROR: Caused by: org.hibernate.AnnotationException: mappedBy reference an unknown target entity property:
	 * 						org.avp.quota.kpi.model.dao.QuotaDao.salesRepresentative in org.avp.quota.kpi.model.dao.SalesRepresentativeDao.quotas
	 * if declaration 'private SalesRepresentativeDao salesRepresentative;' use 'transient' modifier
	 * To fix use 'private transient List<QuotaDao> quotas;' on SalesRepresentativeDao side 
	 */
	@ManyToOne(optional=true, fetch = FetchType.LAZY)//QKPI-71: was default EAGER
    @JoinColumn(name="alt_salesrep_cd", referencedColumnName="alt_salesrep_cd")
	private SalesRepresentativeDao salesRepresentative;

	public SalesRepresentativeDao getSalesRepresentative() {return salesRepresentative;}
	public void setSalesRepresentative(SalesRepresentativeDao salesRepresentative) {this.salesRepresentative = salesRepresentative;} 

}

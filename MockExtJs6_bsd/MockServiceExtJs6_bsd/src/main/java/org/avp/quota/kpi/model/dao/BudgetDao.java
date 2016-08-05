package org.avp.quota.kpi.model.dao;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("B")
public class BudgetDao extends BaseQuotaDao implements Serializable{
	public BudgetDao() {super();}
	public BudgetDao(/*String alt_salesrep_cd, */CategoryDao category, int amountType, int year){
		super(/*alt_salesrep_cd, */category, amountType, year);
	}

}

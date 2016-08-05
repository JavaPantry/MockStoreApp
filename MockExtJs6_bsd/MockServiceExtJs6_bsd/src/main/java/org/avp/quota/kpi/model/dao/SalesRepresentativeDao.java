package org.avp.quota.kpi.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * Guess:
 * ids_sequrity in project context sounds like SalesRepesentative
 * SalesRepesentative represents/(not really belongs) to Department (TOC)
 * (see QKPI-108) TOC.id(dep-t) is unique in SalesRep<>TOC table
 * But SalesRepesentative may represent many TOC(dep)
 * 
 * 
 * 
 * SELECT s.alt_salesrep_cd,alt_salesrep_nm,ids_user_id,all_access,st.toc_cd
 *   FROM ids_security as s, ids_security_toc as st, ids_toc as t
 * where s.alt_salesrep_cd = st.alt_salesrep_cd
 * and st.toc_cd = t.toc_cd
 * alt_salesrep_nm = 'RUSSELL BROWN'
 * 
 * --SELECT alt_salesrep_cd,toc_cd FROM ids_security_toc
 * --SELECT toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num FROM ids_toc
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ids_security")
public final class SalesRepresentativeDao  implements Serializable{

	public SalesRepresentativeDao() {
		super();
	}
	
	public SalesRepresentativeDao(String salesRepresentativeId,
			String salesRepresentativeName, EmployeeDao user,
			boolean allAccess/*, List<TocDao> tocs
			,List<SalesRepEmployeeJoin> salesRepEmployeeJoins*/) {
		super();
		this.salesRepresentativeId = salesRepresentativeId;
		this.salesRepresentativeName = salesRepresentativeName;
		this.user = user;
		this.allAccess = allAccess;
		//this.tocs = tocs;
		//this.salesRepEmployeeJoins = salesRepEmployeeJoins;
	}

	@Id
	@Column(name="alt_salesrep_cd")//@ColumnTransformer(read="RTRIM(LTRIM(alt_salesrep_cd))")
	private String salesRepresentativeId;
	
	/*
	 * 	@NotNull(message="Other Id must not be blank")
	 * 	@Size(max=60)
	 */
	@Column(name="alt_salesrep_nm")//@ColumnTransformer(read="RTRIM(LTRIM(alt_salesrep_nm))")
	private String salesRepresentativeName;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ids_user_id")
	private EmployeeDao user;
	
	/*
	 * @ManyToOne(fetch=FetchType.EAGER)
	 * @JoinColumn(name="ids_manager_id")
	 * private EmployeeDao manager;
	 */
	
	@Column(name="all_access", columnDefinition = "BIT", length = 1)//@ColumnTransformer(read="RTRIM(LTRIM(all_access))")
	private boolean allAccess;

	/* https://github.com/kmb385/ToThoughtDataLayer/blob/master/src/main/java/org/tothought/entities/Post.java */
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, targetEntity=TocDao.class)
	@JoinTable(name="ids_security_toc", joinColumns={@JoinColumn(name="alt_salesrep_cd", referencedColumnName="alt_salesrep_cd")},
										inverseJoinColumns={@JoinColumn(name="toc_cd", referencedColumnName="toc_cd")})
	private List<TocDao> tocs = new ArrayList<TocDao>();

	public String getSalesRepresentativeName() {return salesRepresentativeName;}
	public void setSalesRepresentativeName(String salesRepresentativeName) {this.salesRepresentativeName = salesRepresentativeName;}

	public boolean getAllAccess() {return allAccess;}
	public void setAllAccess(boolean allAccess) {this.allAccess = allAccess;}

	/* https://giannigar.wordpress.com/2009/09/04/mapping-a-many-to-many-join-table-with-extra-column-using-jpa*/
	/*if LAZY throw exception on convert response to JSON*/
	@OneToMany(fetch=FetchType.EAGER , mappedBy="pk.salesRepresentative",cascade={CascadeType.ALL}, orphanRemoval=true)//CascadeType.PERSIST, CascadeType.MERGE, 
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<SalesRepEmployeeJoin> salesRepEmployeeJoins;
	
	/*
	Original from QuotaKPI_Oct28_RCv1.zip
	QKPI-71: 
	'at'OneToMany(fetch = FetchType.EAGER, mappedBy = "salesRepresentative",cascade={CascadeType.ALL}, orphanRemoval=true)
	'at'Fetch(value = FetchMode.SUBSELECT)
	
	by removing FetchMode.SUBSELECT and change to FetchType.LAZY we are delete extra select (backward reference from SalesRep->Quota) 
	
	select quotas0_.alt_salesrep_cd as alt_sal18_7_2_, quotas0_.idsQuotaId as idsQuota2_6_2_, quotas0_.idsQuotaId as idsQuota2_6_1_, quotas0_.AmtType as AmtType3_6_1_, quotas0_.third_prod_ctrl_cd as third_p17_6_1_, 
	quotas0_.A1 as A4_6_1_, quotas0_.A10 as A5_6_1_, quotas0_.A11 as A6_6_1_, quotas0_.A12 as A7_6_1_, quotas0_.A2 as A8_6_1_, 
	quotas0_.A3 as A9_6_1_, quotas0_.A4 as A10_6_1_, quotas0_.A5 as A11_6_1_, quotas0_.A6 as A12_6_1_, quotas0_.A7 as A13_6_1_, 
	quotas0_.A8 as A14_6_1_, quotas0_.A9 as A15_6_1_, quotas0_.Year as Year16_6_1_, quotas0_.alt_salesrep_cd as alt_sal18_6_1_, 
		categoryda1_.third_prod_ctrl_cd as third_pr1_5_0_, categoryda1_.third_prod_ctrl_nm as third_pr2_5_0_, 
		categoryda1_.first_prod_ctrl_cd as first_pr3_5_0_, categoryda1_.first_prod_ctrl_nm as first_pr4_5_0_, 
		categoryda1_.scd_prod_ctrl_cd as scd_prod5_5_0_, categoryda1_.scd_prod_ctrl_nm as scd_prod6_5_0_, 
		categoryda1_.zeroth_prod_ctrl_cd as zeroth_p7_5_0_, categoryda1_.zeroth_prod_ctrl_nm as zeroth_p8_5_0_ 
	from ids_quota quotas0_ inner join ids_mdse_stru_lv3 categoryda1_ on quotas0_.third_prod_ctrl_cd=categoryda1_.third_prod_ctrl_cd 
	where quotas0_.alt_salesrep_cd=?
 	*/
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salesRepresentative",cascade={CascadeType.ALL}, orphanRemoval=true)
	private Set<QuotaDao> quotas; 
	
	public Set<QuotaDao> getQuotas() {return quotas;}
	public void setQuotas(Set<QuotaDao> quotas) {this.quotas = quotas;}
	 
	
	public Set<SalesRepEmployeeJoin> getSalesRepEmployeeJoins() {return salesRepEmployeeJoins;}
	public void setSalesRepEmployeeJoins(Set<SalesRepEmployeeJoin> salesRepEmployeeJoins) {
		this.salesRepEmployeeJoins = salesRepEmployeeJoins;
	}
	public String getSalesRepresentativeId() {return salesRepresentativeId;}
	public void setSalesRepresentativeId(String salesRepresentativeId) {this.salesRepresentativeId = salesRepresentativeId;}
	public EmployeeDao getUser() {return user;}
	public void setUser(EmployeeDao user) {this.user = user;}
	
	public List<TocDao> getTocs() {return tocs;}
	public void setTocs(List<TocDao> tocs) {this.tocs = tocs;}

	
}

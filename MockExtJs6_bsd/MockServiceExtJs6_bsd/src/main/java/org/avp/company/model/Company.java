package org.avp.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company extends Named {

	
	/**
	 * with such short annotation OneToMany(fetch=FetchType.EAGER) only
	 * class Company{
	 *		@OneToMany(fetch=FetchType.EAGER , mappedBy = "company")
	 *		private Set<BillTo> billTos;
	 * }
	 *
	 * class BillTo{
	 *		@ManyToOne
	 *		@JoinColumn(name="companyId")
	 *		private Company company;
	 * }
	 *
	 * reference table company_billto will be created by hibernate
	 * 
	 * describe company;
	 *		id
	 *		code
	 *		modifiedAt
	 *		modifiedBy
	 *		name
	 * describe company_billto;
	 *		Company_id
	 *		billTos_id
	 * describe billto;
	 *		id
	 *		code
	 *		modifiedAt
	 *		modifiedBy
	 *		name
	 *		company_id
	 *
	 * Consider
	 *    class UserRDao{
	 *		@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	 *		private List<AuthoritiesDao> authorities = new ArrayList<AuthoritiesDao>();
	 *    }
	 *    class AuthoritiesDao{
	 *		@ManyToOne
	 *		@JoinColumn(name="userId")
	 *		private UserDao user;
	 *    }		
	 *
	 * OR Consider something more specific
	 *    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Role.class)
	 *    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	 *    private Set<Role> roles;
	 */
	@OneToMany(fetch=FetchType.EAGER , mappedBy = "company")
	private Set<BillTo> billTos;
	
	public Set<BillTo> getBillTos() {
		return billTos;
	}

	public void setBillTos(Set<BillTo> billTos) {
		this.billTos = billTos;
	}

}


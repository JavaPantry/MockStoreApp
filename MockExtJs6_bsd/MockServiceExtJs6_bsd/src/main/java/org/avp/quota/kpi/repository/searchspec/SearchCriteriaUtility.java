package org.avp.quota.kpi.repository.searchspec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.model.dao.BudgetDao;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.TocDao;
import org.avp.quota.kpi.model.dao.QuotaUser;
import org.avp.quota.kpi.model.dto.BudgetDto;
import org.avp.quota.kpi.model.dto.QuotaDto;
import org.avp.quota.kpi.util.GeneralUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public final class SearchCriteriaUtility {

	private static Logger logger = Logger.getLogger(SearchCriteriaUtility.class);
	
	private static final String FIELD_USER_ID 	= "userId";
	private static final String FIELD_OTHER_ID 	= "otherId";
	
	private SearchCriteriaUtility() {}
	
	/**
	 * 
	 * @param userId
	 * @return
	 
	public static Specification<CpaUserDao> findUsersById(final String userId) {
		return new Specification<CpaUserDao>() {
			@Override
			public Predicate toPredicate(Root<CpaUserDao> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
						// QUSRID=QOTHER
						Predicate otherPredicate = cb.equal(root.<String>get(FIELD_USER_ID), root.<String>get(FIELD_OTHER_ID));
						Predicate userIdPredicate = cb.equal(root.<String>get(FIELD_USER_ID), userId.trim());
				return cb.and(otherPredicate, userIdPredicate);
			}
			
		};
	}*/	

	public static Specification<QuotaUser> findUserById(final String userId) {
		return new Specification<QuotaUser>() {
			@Override
			public Predicate toPredicate(Root<QuotaUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate userIdPredicate = cb.equal(root.<String>get(FIELD_USER_ID), userId.trim());
				return cb.and(userIdPredicate);
			}
		};
	}//eof findUserById

	public static Specification<SalesRepEmployeeJoin> findJoinsBySalesRepAndManager( final String salesRepresentativeId, final String managerId) {
		return new Specification<SalesRepEmployeeJoin>() {
			@Override
			public Predicate toPredicate(Root<SalesRepEmployeeJoin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				//Predicate salesRepresentativeIdPredicate = cb.equal(root.get("salesRepresentative").get("salesRepresentativeId"), salesRepresentativeId);
				Predicate salesRepresentativeIdPredicate = cb.equal(root.get("pk").get("salesRepresentative").get("salesRepresentativeId"), salesRepresentativeId);
				//Predicate managerIdPredicate = cb.equal(root.get("manager").<String>get("employeeId"), managerId.trim());
				Predicate managerIdPredicate = cb.equal(root.get("pk").get("manager").<String>get("employeeId"), managerId.trim());
				predicates.add(salesRepresentativeIdPredicate);
				predicates.add(managerIdPredicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	/*
	 * suppose to return only one
	 */
	public static Specification<SalesRepEmployeeJoin> findJoinsBySalesRepManagerAndProdLine(final String salesRepresentativeId, final String managerId, final String productLineCode) {
		return new Specification<SalesRepEmployeeJoin>() {
			@Override
			public Predicate toPredicate(Root<SalesRepEmployeeJoin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate salesRepresentativeIdPredicate = cb.equal(root.get("pk").get("salesRepresentative").get("salesRepresentativeId"), salesRepresentativeId);
				Predicate managerIdPredicate = cb.equal(root.get("pk").get("manager").<String>get("employeeId"), managerId.trim());
				Predicate productLinePredicate = cb.equal(root.get("pk").get("productLine").get("code"), productLineCode.trim());
				
				predicates.add(salesRepresentativeIdPredicate);
				predicates.add(managerIdPredicate);
				predicates.add(productLinePredicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	//public static Sort findJoinsBySalesRep(String salesRepresentativeId) {
	public static Specification<SalesRepEmployeeJoin> findJoinsBySalesRep( final String salesRepresentativeId) {
		return new Specification<SalesRepEmployeeJoin>() {
			@Override
			public Predicate toPredicate(Root<SalesRepEmployeeJoin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate salesRepresentativeIdPredicate = cb.equal(root.get("pk").get("salesRepresentative").get("salesRepresentativeId"), salesRepresentativeId);
				predicates.add(salesRepresentativeIdPredicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
/**
 * http://stackoverflow.com/questions/5115422/not-in-constraint-using-jpa-criteria
 * > builder.not(root.get({field_name}).in(seqs));//seqs is collection.
 * @param managers
 * @return
 */
	public static Specification<EmployeeDao> findEmployeesNotInSalesRep(final List<String> managers, final String strQuery){
		return new Specification<EmployeeDao>() {
			@Override
			public Predicate toPredicate(Root<EmployeeDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate managerIdPredicate = cb.not(root.get("employeeId").in(managers));
				predicates.add(managerIdPredicate);
				
				if(!GeneralUtil.isEmpty(strQuery)){	
					Collection<Predicate> orPredicates = new ArrayList<Predicate>();
					Path<String> tocIdPath = root.get("employeeId");
					Predicate tocIdLike = cb.like(tocIdPath, "%"+strQuery+"%");
					orPredicates.add(tocIdLike);
					Path<String> tocNamePath = root.get("name");
					Predicate tocNameLike = cb.like(tocNamePath, "%"+strQuery+"%");
					orPredicates.add(tocNameLike);
					
					Predicate orPredicate = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
					predicates.add(orPredicate);
				}
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
	
	public static Specification<EmployeeDao> findEmployees(final String strQuery){
		return new Specification<EmployeeDao>() {
			@Override
			public Predicate toPredicate(Root<EmployeeDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				if(!GeneralUtil.isEmpty(strQuery)){	
					Collection<Predicate> orPredicates = new ArrayList<Predicate>();
					Path<String> tocIdPath = root.get("employeeId");
					Predicate tocIdLike = cb.like(tocIdPath, "%"+strQuery+"%");
					orPredicates.add(tocIdLike);
					Path<String> tocNamePath = root.get("name");
					Predicate tocNameLike = cb.like(tocNamePath, "%"+strQuery+"%");
					orPredicates.add(tocNameLike);
					
					Predicate orPredicate = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
					predicates.add(orPredicate);
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
	
/* QKPI-55:  replace by findTocNotInSalesRepAndTocLookup
	public static Specification<TocDao> findTocNotInSalesRep(final List<String> alreadyAssignedTocIds) {
		return new Specification<TocDao>() {
			@Override
			public Predicate toPredicate(Root<TocDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate tocIdPredicate = cb.not(root.get("tocId").in(alreadyAssignedTocIds));
				predicates.add(tocIdPredicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}*/
	public static Specification<TocDao> findTocNotInSalesRepAndTocLookup(final List<String> alreadyAssignedTocIds, final String strQuery) {
		return new Specification<TocDao>() {
			@Override
			public Predicate toPredicate(Root<TocDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate tocIdPredicate = cb.not(root.get("tocId").in(alreadyAssignedTocIds));
				predicates.add(tocIdPredicate);
				
				if(!GeneralUtil.isEmpty(strQuery)){	
					Collection<Predicate> orPredicates = new ArrayList<Predicate>();
					Path<String> tocIdPath = root.get("tocId");
					Predicate tocIdLike = cb.like(tocIdPath, "%"+strQuery+"%");
					orPredicates.add(tocIdLike);
					Path<String> tocNamePath = root.get("tocName");
					Predicate tocNameLike = cb.like(tocNamePath, "%"+strQuery+"%");
					orPredicates.add(tocNameLike);
					
					Predicate orPredicate = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
					predicates.add(orPredicate);
				}
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public static Specification<QuotaDao> findQuotaByHeader(final QuotaDto quota) {
		return new Specification<QuotaDao>() {
			@Override
			public Predicate toPredicate(Root<QuotaDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate salesRepIdPredicate = cb.equal(root.get("salesRepresentative").get("salesRepresentativeId"),quota.getSalesRepresentativeId());
				predicates.add(salesRepIdPredicate);
				Predicate categoryPredicate = cb.equal(root.get("category").get("categoryId"),quota.getCategoryId());
				predicates.add(categoryPredicate);
				Predicate typePredicate = cb.equal(root.get("amountType"),quota.getAmountType());
				predicates.add(typePredicate);
				Predicate yearPredicate = cb.equal(root.get("year"),quota.getYear());
				predicates.add(yearPredicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public static Specification<BudgetDao> findQuotaByHeader(final BudgetDto dto) {
		return new Specification<BudgetDao>() {
			@Override
			public Predicate toPredicate(Root<BudgetDao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate categoryPredicate = cb.equal(root.get("category").get("categoryId"),dto.getCategoryId());
				predicates.add(categoryPredicate);
				Predicate typePredicate = cb.equal(root.get("amountType"),dto.getAmountType());
				predicates.add(typePredicate);
				Predicate yearPredicate = cb.equal(root.get("year"),dto.getYear());
				predicates.add(yearPredicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};	}

}
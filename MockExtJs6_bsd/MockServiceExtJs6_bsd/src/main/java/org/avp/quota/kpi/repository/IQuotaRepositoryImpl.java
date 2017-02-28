package org.avp.quota.kpi.repository;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.model.dto.TotalDto;
import org.avp.quota.kpi.util.FilterOperator;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class IQuotaRepositoryImpl implements IQuotaRepositoryCustom {
	private static Logger logger = Logger.getLogger(IQuotaRepositoryImpl.class);	
	private final String SELECT_COUNT_FOR_TOTAL = "select count(*)";
	private final String SELECT_COUNT_FOR_TOTAL_QUOTA = SELECT_COUNT_FOR_TOTAL + " from QuotaDao q";
	private final String SELECT_COUNT_FOR_TOTAL_BUDGET = SELECT_COUNT_FOR_TOTAL + " from BudgetDao q";

	private final String SELECT_TOTAL = "select new org.avp.quota.kpi.model.dto.TotalDto(sum(q.value1), sum(q.value2), sum(q.value3), sum(q.value4), "
																						+ "sum(q.value5), sum(q.value6), sum(q.value7), sum(q.value8), "
																						+ "sum(q.value9), sum(q.value10), sum(q.value11), sum(q.value12))";

	private final String SELECT_TOTAL_QUOTA = SELECT_TOTAL + " from QuotaDao q";
	private final String SELECT_TOTAL_BUDGET = SELECT_TOTAL + " from BudgetDao q";
	
	@PersistenceContext(unitName="hibernate_bsd")
	// at.Qualifier(value="quotaEntityManagerFactory") //set custom bean name otherwise -> No bean named 'entityManagerFactory' is defined
	@Qualifier(value="entityManagerFactory")
	private EntityManager em;

	@Override
	public TotalDto sumQuotaWithFilters(FilterParameterExtJs6[] filters) {
		//QKPI-68
		String countHql = SELECT_COUNT_FOR_TOTAL_QUOTA + buildWhereClause(filters);
		Query countQuery = em.createQuery(countHql.toString());
		Long count = (Long)countQuery.getSingleResult();
		if(count.longValue() == 0L)
			return new TotalDto();
		//eof QKPI-68
		String hql = SELECT_TOTAL_QUOTA+ buildWhereClause(filters);
		Query query = em.createQuery(hql.toString());
		return (TotalDto) query.getSingleResult();
	}
	
	@Override
	public TotalDto sumBudgetWithFilters(FilterParameterExtJs6[] filters) {
		//QKPI-68
		String countHql = SELECT_COUNT_FOR_TOTAL_BUDGET + buildWhereClause(filters);
		Query countQuery = em.createQuery(countHql.toString());
		Long count = (Long)countQuery.getSingleResult();
		if(count.longValue() == 0L)
			return new TotalDto(); 
		//eof QKPI-68
		String hql = SELECT_TOTAL_BUDGET+ buildWhereClause(filters);
		Query query = em.createQuery(hql.toString());
		return (TotalDto) query.getSingleResult();
	}

	private String buildWhereClause(FilterParameterExtJs6[] filterParameters) {
		StringBuffer sb = new StringBuffer();
		if(filterParameters!=null && filterParameters.length>0){
			sb.append(" WHERE ");
			int i = 0;
			for (FilterParameterExtJs6 filter : filterParameters) {
				if(i > 0){
					sb.append("AND ");
				}
				i++;
				
				String propertyName = null;
				if(filter.getField() != null){
					propertyName = filter.getField();
				} else if(filter.getProperty() != null){
					propertyName = filter.getProperty();
				} else {
					logger.error("!!!!No 'field' or 'property' property defined in filter!!!!");
				}
				
				if (filter.getOperator().equals(FilterOperator.LIKE)){
					sb.append(propertyName);// " WHERE columnName " +filter.getField()
					sb.append( " like '%"+filter.getValue()+"%'"); // " WHERE columnName like %value%"
				}else if (filter.getOperator().equals(FilterOperator.EQ)||
						filter.getOperator().equals(FilterOperator.GT)||
						filter.getOperator().equals(FilterOperator.LT)){
					sb.append(propertyName);// " WHERE columnName "+filter.getField()
					sb.append( " = "+new Integer(filter.getValue()));	
				}else if (filter.getOperator().equals(FilterOperator.IN)){
					List<String> values = filter.getValues();
					Collection<Predicate> orPredicates = new ArrayList<Predicate>();
					if(values.size()>0){
						sb.append("(");
						int j = 0;
						for (String value : values) {
							if(j > 0){
								sb.append("OR ");
							}
							j++;
							sb.append(propertyName);// " columnName "filter.getField()
							sb.append( "="+value);
						}
						sb.append(")");
					}//eof list not empty
				}//eof LIST
			}//eof for all filters
		}
		return sb.toString();
	}

}

package org.avp.quota.kpi.util;

import java.util.ArrayList;
import java.util.List;

import org.avp.quota.kpi.model.dao.BudgetDao;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.ProductLine;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dto.BudgetDto;
import org.avp.quota.kpi.model.dto.CodeDto;
import org.avp.quota.kpi.model.dto.EmployeeDto;
import org.avp.quota.kpi.model.dto.ProductLineDTO;
import org.avp.quota.kpi.model.dto.QuotaDto;
import org.avp.quota.kpi.model.dto.SalesRepresentativeDto;

public class DtoFactory {

	static public QuotaDto createDtoFromDao(QuotaDao dao){
		QuotaDto dto = null;
		if(dao!=null){
			dto = new QuotaDto();
			BeanUtility.nullSafeMergeTo(dao, dto, new String[]{"+salesRepresentative","category"});
			if(dao.getSalesRepresentative() != null){
				dto.setSalesRepresentativeId(dao.getSalesRepresentative().getSalesRepresentativeId());
				dto.setSalesRepresentativeName(dao.getSalesRepresentative().getSalesRepresentativeName());
			}
			if(dao.getCategory() != null){
				dto.setCategoryId(dao.getCategory().getCategoryId());
				dto.setCategoryName(dao.getCategory().getCategoryName());
			}
		}
		return dto;
	}
	
	public static List<QuotaDto> createQuotaDtoList(List<QuotaDao> quotas) {
		List<QuotaDto> quotaDtos = new ArrayList<QuotaDto>();
		for (QuotaDao quota : quotas) {
			quotaDtos.add(DtoFactory.createDtoFromDao(quota));
		}
		return quotaDtos;
	}
	
	static public BudgetDto createDtoFromDao(BudgetDao dao){
		BudgetDto dto = null;
		if(dao!=null){
			dto = new BudgetDto();
			BeanUtility.nullSafeMergeTo(dao, dto, new String[]{"+category"});
			if(dao.getCategory() != null){
				dto.setCategoryId(dao.getCategory().getCategoryId());
				dto.setCategoryName(dao.getCategory().getCategoryName());
			}
		}
		return dto;
	}

	public static List<BudgetDto> createBudgetDtoList(List<BudgetDao> budgets) {
		List<BudgetDto> budgetDtos = new ArrayList<BudgetDto>();
		for (BudgetDao budget : budgets) {
			budgetDtos.add(DtoFactory.createDtoFromDao(budget));
		}
		return budgetDtos;
	}

	static public EmployeeDto createDtoFromDao(EmployeeDao dao){
		EmployeeDto dto = null;
		if(dao!=null){
			dto = new EmployeeDto();
			dto.setEmployeeId(dao.getEmployeeId());//.trim()
			dto.setName(dao.getName()); 
			dto.setStatus(dao.getStatus());					
			dto.setJobTitle(dao.getJobTitle());					
			dto.setCompany(dao.getCompany());					
			dto.setLocation(dao.getLocation());					
			dto.setLocationName(dao.getLocationName());					
			dto.setDeptId(dao.getDeptId());					
			dto.setDeptName(dao.getDeptName());					
			dto.setManagerId(dao.getManagerId());					
			dto.setManagerLevel(dao.getManagerLevel());					
			dto.setReportPath(dao.getReportPath());					
			dto.setReportPathName(dao.getReportPathName());
		}
		return dto;
	}
	
	static public SalesRepresentativeDto createDtoFromDao(SalesRepresentativeDao dao){
		SalesRepresentativeDto dto = null;
		if(dao!=null){
			dto = new SalesRepresentativeDto();
			dto.setSalesRepresentativeId(dao.getSalesRepresentativeId());
			dto.setSalesRepresentativeName(dao.getSalesRepresentativeName());
			dto.setUser(DtoFactory.createDtoFromDao(dao.getUser()));
			dto.setAllAccess(dao.getAllAccess());
			dto.setTocs(dao.getTocs());
			if( dao.getSalesRepEmployeeJoins() == null )
				return dto;
			for(SalesRepEmployeeJoin join: dao.getSalesRepEmployeeJoins()){
				EmployeeDto manager = DtoFactory.createDtoFromDao(join.getManager());
				manager.setProductLinesContentStr("a, b, c, d, e, f[, ...]");
				dto.getManagers().add(manager);
			}
		}
		return dto;
	}

	public static ProductLineDTO createDtoFromDao(ProductLine dao) {
		ProductLineDTO dto = null;
		if(dao!=null){
			dto = new ProductLineDTO();
			dto.setCode(dao.getCode());
			dto.setDescription(dao.getDescription());
		}
		return dto;
	}

	public static CodeDto createCodeDtoFromDao(SalesRepresentativeDao dao) {
		CodeDto dto  = null;
		if(dao!=null){
			dto = new CodeDto();
			dto.setCode(dao.getSalesRepresentativeId());
			dto.setName(dao.getSalesRepresentativeName());
		}
		return dto;
	}

	public static CodeDto createCodeDtoFromDao(CategoryDao dao) {
		CodeDto dto  = null;
		if(dao!=null){
			dto = new CodeDto();
			dto.setCode(dao.getCategoryId());
			dto.setName(dao.getCategoryName());
		}
		return dto;
	}

}

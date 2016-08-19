package org.avp.bsd.service;

import java.util.ArrayList;
import java.util.List;

import org.avp.bsd.dto.OrderHeaderDto;
import org.avp.bsd.model.OrderHeader;
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
import org.avp.quota.kpi.util.BeanUtility;

public class DtoFactory {

	static public OrderHeaderDto createDtoFromDao(OrderHeader dao){
		OrderHeaderDto dto = null;
		if(dao!=null){
			dto = new OrderHeaderDto();
			BeanUtility.nullSafeMergeTo(dao, dto, null);//new String[]{"+salesRepresentative","category"}
			/*if(dao.getSalesRepresentative() != null){
				dto.setSalesRepresentativeId(dao.getSalesRepresentative().getSalesRepresentativeId());
				dto.setSalesRepresentativeName(dao.getSalesRepresentative().getSalesRepresentativeName());
			}
			if(dao.getCategory() != null){
				dto.setCategoryId(dao.getCategory().getCategoryId());
				dto.setCategoryName(dao.getCategory().getCategoryName());
			}*/
		}
		return dto;
	}
	
	public static List<OrderHeaderDto> createDtoList(List<OrderHeader> entities) {
		List<OrderHeaderDto> dtos = new ArrayList<OrderHeaderDto>();
		for (OrderHeader entity : entities) {
			dtos.add(DtoFactory.createDtoFromDao(entity));
		}
		return dtos;
	}

}

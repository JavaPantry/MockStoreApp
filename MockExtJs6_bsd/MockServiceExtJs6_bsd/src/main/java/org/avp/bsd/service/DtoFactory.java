package org.avp.bsd.service;

import java.util.ArrayList;
import java.util.List;

import org.avp.bsd.dto.BsdUserDto;
import org.avp.bsd.dto.OrderHeaderDto;
import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.dto.StoreDto;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.quota.kpi.util.BeanUtility;

public class DtoFactory {

	static public BsdUserDto createDtoFrom(BsdUser entity){
		BsdUserDto dto = null;
		if(entity == null)
			return dto;
		dto = new BsdUserDto();
		BeanUtility.nullSafeMergeTo(entity, dto, new String[]{"+store", "orders"});//new String[]{"+salesRepresentative","category"}
		dto.setStore(createDtoFrom(entity.getStore()));
		return dto;
	}

	public static List<BsdUserDto> createBsdUserDtoList(List<BsdUser> entities) {
		List<BsdUserDto> dtos = new ArrayList<BsdUserDto>();
		for (BsdUser entity : entities) {
			dtos.add(DtoFactory.createDtoFrom(entity));
		}
		return dtos;
	}
	
	static public StoreDto createDtoFrom(Store entity){
		StoreDto dto = null;
		if(entity == null)
			return dto;
		dto = new StoreDto();
		BeanUtility.nullSafeMergeTo(entity, dto, null);//new String[]{"+salesRepresentative","category"}
		return dto;
	}

	public static List<StoreDto> createStoreDtoList(List<Store> entities) {
		List<StoreDto> dtos = new ArrayList<StoreDto>();
		for (Store entity : entities) {
			dtos.add(DtoFactory.createDtoFrom(entity));
		}
		return dtos;
	}

	static public ProductDto createDtoFrom(Product entity){
		ProductDto dto = null;
		if(entity == null)
			return dto;
		
		dto = new ProductDto();
		BeanUtility.nullSafeMergeTo(entity, dto, null);//new String[]{"+salesRepresentative","category"}
		
		return dto;
	}

	public static List<ProductDto> createProductDtoList(List<Product> entities) {
		List<ProductDto> dtos = new ArrayList<ProductDto>();
		for (Product entity : entities) {
			dtos.add(DtoFactory.createDtoFrom(entity));
		}
		return dtos;
	}
	public static List<ProductDto> createProductDtoListFromProductPriceInStore(List<ProductPriceInStore> productPricesInStore) {
		List<ProductDto> dtos = new ArrayList<ProductDto>();
		for (ProductPriceInStore productPriceInStore : productPricesInStore) {
			Product product = productPriceInStore.getPk().getProduct();
			ProductDto dto = DtoFactory.createDtoFrom(product);
			dto.setPrice(productPriceInStore.getPrice());
			dto.setPriceScheduled(productPriceInStore.getPriceScheduled());
			dto.setPriceSchedule(productPriceInStore.getPriceSchedule());
			dto.setCreated(productPriceInStore.getCreated());
			dtos.add(dto);
		}
		return dtos;
	}

	static public OrderHeaderDto createDtoFrom(OrderHeader entity){
		OrderHeaderDto dto = null;
		if(entity == null)
			return dto;

		dto = new OrderHeaderDto();
		BeanUtility.nullSafeMergeTo(entity, dto, null);//new String[]{"+salesRepresentative","category"}
		
		if(entity.getUser() == null)
			return dto;
		
		BsdUser user = entity.getUser();
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
		dto.setUserId(user.getUserId());
		
		return dto;
	}
	
	public static List<OrderHeaderDto> createDtoList(List<OrderHeader> entities) {
		List<OrderHeaderDto> dtos = new ArrayList<OrderHeaderDto>();
		for (OrderHeader entity : entities) {
			dtos.add(DtoFactory.createDtoFrom(entity));
		}
		return dtos;
	}


}

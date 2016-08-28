package org.avp.quota.kpi.service;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.avp.bsd.dto.OrderHeaderDto;
import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.model.StoreProductPK;
import org.avp.bsd.service.BsdService;
import org.avp.quota.kpi.configuration.HsqlJUnitDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.JbossTestDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfiguration;
import org.avp.quota.kpi.model.dao.BudgetDao;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.ProductLine;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepEmployeePK;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.avp.quota.kpi.model.dao.QuotaUser;
import org.avp.quota.kpi.model.dto.BudgetDto;
import org.avp.quota.kpi.model.dto.ProductLineDTO;
import org.avp.quota.kpi.model.dto.QuotaDto;
import org.avp.quota.kpi.service.interfaces.QuotaService;
import org.avp.quota.kpi.util.DtoFactory;
import org.avp.quota.kpi.util.GsonUtil;
import org.avp.security.model.Authority;
import org.avp.security.model.User;
import org.avp.security.service.CustomUserService;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
SELECT * FROM gitmockauth.stores;
SELECT * FROM gitmockauth.products;
SELECT * FROM gitmockauth.productpriceinstore;
SELECT * FROM gitmockauth.bsdusers;
 */

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  //LOL 8))) A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class
						,DirtiesContextTestExecutionListener.class
						,TransactionalTestExecutionListener.class
						,DbUnitTestExecutionListener.class 
						})

//@ActiveProfiles("JBossTest")
//@ContextConfiguration(classes = {JbossTestDataServiceModuleConfiguration.class})

@ActiveProfiles("Tomcat")
@ContextConfiguration(classes = {TomcatDataServiceModuleConfiguration.class})

//@ActiveProfiles("HSQL_JUNIT")
//@ContextConfiguration(classes = {HsqlJUnitDataServiceModuleConfiguration.class})

public class ValidateBsdTables {
	private static Logger logger = Logger.getLogger(ValidateBsdTables.class);

	@Autowired
	DataSource dataSource;
	
	@Autowired
	QuotaService quotaService;
	
	@Autowired
	BsdService bsdService;

	@Autowired
	CustomUserService userService;
	
	@Before
	public void setUp() throws Exception {
		logger.debug("Verifying BSD tables.");
	}

	@After
	public void tearDown() throws Exception {
		logger.debug("Verifying BSD tables completed.");
	}
	
	@Test
	public void averifyBsdSetup(){
		Gson gson = new GsonBuilder().create();//.registerTypeAdapter(java.util.Date.class, new UtilDateSerializer()).setDateFormat(DateFormat.LONG).create()
		
		String userName = "Tim Adams";
        BsdUser user = (BsdUser) userService.getDomainUser(userName);
        Store store = user.getStore();
        assertNotNull(store);
    	Set<ProductPriceInStore> productsPriceInStore = store.getProductsInStore();
    	assertNotNull(productsPriceInStore);
    	List<Product> products = new ArrayList<Product>();//bsdService.getProducts();
    	for (ProductPriceInStore productPriceInStore : productsPriceInStore) {
    		assertNotNull(productPriceInStore.getPk());
    		Product product = productPriceInStore.getPk().getProduct();
    		assertNotNull(product);
    		products.add(product);
		}    	
    	assertThat(products.size(), is(5));
		//String jsonProducts = gson.toJson(products);
		List<ProductDto> productsDtos = org.avp.bsd.service.DtoFactory.createProductDtoList(products);
		String jsonProducts = gson.toJson(productsDtos);
		logger.debug(" jsonProducts = \""+jsonProducts+"\"");
    	
		//--------------------------------------------------------------------------------------------------
		List<OrderHeader> orders = bsdService.getOrderHeaders();
		assertNotNull(orders);
		assertThat(orders.size(), is(3));
		
		logger.debug("orders size = "+orders.size());
		OrderHeader order = orders.get(0);
		
		OrderHeaderDto orderDto = org.avp.bsd.service.DtoFactory.createDtoFrom(order);
		String jsonOrderHeader = gson.toJson(orderDto);
		logger.debug("jsonOrderHeader = \""+jsonOrderHeader+"\"");

		List<OrderHeaderDto> orderDtos = org.avp.bsd.service.DtoFactory.createDtoList(orders);
		String jsonOrderHeaders = gson.toJson(orderDtos);
		logger.debug("jsonOrderHeaders = \""+jsonOrderHeaders+"\"");
		
		//------------------------------- TEST PRODUCT in STORE and AVAILABLE for STORE --------------------
		List<ProductDto> productsInStore = bsdService.getProductPriceInStore(store.getId());
		logger.debug("productsInStore = \""+productsInStore+"\"");
		List<ProductDto> productsAvailableForStore = bsdService.getProductNotInStore(store.getId());
		logger.debug("productsAvailableForStore = \""+productsAvailableForStore+"\"");
	}
	
}
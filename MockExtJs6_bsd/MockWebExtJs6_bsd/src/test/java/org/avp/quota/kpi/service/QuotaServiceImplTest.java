package org.avp.quota.kpi.service;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import org.apache.log4j.Logger;
import org.avp.quota.kpi.configuration.HsqlJUnitDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfiguration;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.ProductLine;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dto.ProductLineDTO;
import org.avp.quota.kpi.model.dto.SalesRepresentativeDto;
import org.avp.quota.kpi.service.interfaces.QuotaService;
import org.avp.quota.kpi.util.DtoFactory;
import org.avp.quota.kpi.util.GeneralUtil;
import org.avp.quota.kpi.util.GsonUtil;
import org.avp.quota.kpi.util.SortParameter;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sporcic.extjs.ExtData;
import org.sporcic.extjs.ExtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
/*
 * See: Spring Data JPA Tutorial: Integration Testing
 * http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-integration-testing/
 * 		article project-> https://github.com/pkainulainen/spring-data-jpa-examples/tree/master/integration-testing
 *			reference to 3rd party gist-> https://gist.github.com/PatrickGotthard/9558923
 *
 * 
 * java.lang.IllegalStateException: Failed to load ApplicationContext
 * 	at org.springframework.test.context.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:94)
 * 
 * see http://mojo.codehaus.org/dbunit-maven-plugin/examples/clean-insert.html to populate database from maven goal 
 * 
 * @see http://springtestdbunit.github.io/spring-test-dbunit/
 * for annotations: @ DatabaseTearDown @ ExpectedDatabase
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class
						,DirtiesContextTestExecutionListener.class
						,TransactionalTestExecutionListener.class
						,DbUnitTestExecutionListener.class 
						})
//@ActiveProfiles("JBossTest")
//@ContextConfiguration(classes = {JbossTestDataServiceModuleConfiguration.class})

//@ActiveProfiles("Tomcat")
//@ContextConfiguration(classes = {TomcatDataServiceModuleConfiguration.class})

@ActiveProfiles("HSQL_JUNIT")
@ContextConfiguration(classes = {HsqlJUnitDataServiceModuleConfiguration.class})

@DatabaseSetup(ExportData.DBUNIT_INTEGRATION_DATA)
public class QuotaServiceImplTest {
	private static Logger logger = Logger.getLogger(QuotaServiceImplTest.class);
	
	@Autowired
	private QuotaService quotaService;

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testCheckSalesRepId(){
		boolean salesRepIdExists = quotaService.checkSalesRepId("CIG001");
		assertThat(salesRepIdExists, is(true));
	}	
	@Test
	public void testQuotaDelete(){
		QuotaDao q = quotaService.getQuotaById(1L);
		assertNotNull(q);
		assertThat(q.getId(), is(1L));
		quotaService.deleteQuotaById(q.getId());
		QuotaDao q1 = quotaService.getQuotaById(1L);
		assertNull(q1);
	}

	@Test
	public void testSalesRepDelete(){
		SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById("CIG001");
		quotaService.deleteSalesRepresentative(salesRepresentative.getSalesRepresentativeId());
		salesRepresentative = quotaService.getSalesRepresentativeById("CIG001");
		logger.debug("salesRepresentative = "+salesRepresentative);
		assertNull(salesRepresentative);
		//should be deleted
		QuotaDao q = quotaService.getQuotaById(1L);
		assertNull(q);
	}
	/*
	 * see: QuotaController
	 * 	public ExtResponse findAjaxQuotas(
	 *		@RequestParam(value="limit", required=false) Integer limit, 
	 *		@RequestParam(value="page", required=false) Integer page, 
	 *		@RequestParam(value="start", required=false) Integer start,
	 *		@RequestParam(value="sort", required=false) String sort,
	 *		@RequestParam(value="filter", required=false) String filter
	 *		) throws AppSpecificException{
	 *			ExtData response = new ExtData();
	 *			logger.debug("(limit="+limit+", page="+page+", start="+start+", sort="+sort+", filter="+filter+")");
	 *			FilterParameter[] filterParameters = getFiltersFromJson(filter);
	 *			SortParameter[] sortParameters = getSortFromJson(sort);;
	 *			List<QuotaDao> quotas = quotaService.getQuotas();
	 *
	 * Request with sorting and filtering:
	 * filter [	{"type":"string","value":"cig","field":"salesRepresentativeId"}
	 * 			,{"type":"string","value":"tbd","field":"salesRepresentativeName"}
	 * 			,{"type":"string","value":"2014","field":"year"}
	 * 			,{"type":"string","value":"A9","field":"categoryId"}
	 * ]
	 * limit	35
	 * page	1
	 * sort	[{"property":"salesRepresentativeName","direction":"ASC"}]
	 * start	0 
	 * 
	 *
	 * !!!!! ********** ExtJs 6 filter looks like:  ********** !!!!!!
	 * (prefix 'http://localhost:8080/MockStubWebExtJs6/ajax/quotas?page=1&start=0&limit=20&' removed)
	 * 
	 * filter=[{"operator":"like","value":"aaa","property":"category.categoryId"}]
	 * 
	 * more than one:
	 * filter=[{"operator":"like","value":"aaa","property":"category.categoryId"}
	 * 			,{"operator":"like","value":"an","property":"category.categoryName"}]
	 * filter=[{"operator":"like","value":"aaa","property":"category.categoryId"}
	 * 			,{"operator":"like","value":"an","property":"category.categoryName"}
	 * 			,{"operator":"like","value":"cig","property":"salesRepresentative.salesRepresentativeId"}]
	 * 
	 * with operator IN (generated by column type 'list')
	 * filter=[{"operator":"like","value":"cig","property":"salesRepresentative.salesRepresentativeId"}
	 * 			,{"operator":"in","value":[0],"property":"amountType"}]
	 * filter=[{"operator":"like","value":"cig","property":"salesRepresentative.salesRepresentativeId"}
	 * 			,{"operator":"in","value":[0,1],"property":"amountType"}]
	 * 
	 * for column with filter type = 'numeric'
	 * filter=[{"operator":"eq","value":2013,"property":"year"}]
	 * 
	 * filter=[{"operator":"lt","value":2016,"property":"year"}
	 * 			,{"operator":"gt","value":2014,"property":"year"}]
	 * 
	 */
	
	/*@Test
	public void testSearchQuotasWithSalesRepresentativeCategoryYear(){
		String filterStr = "[{\"type\":\"string\",\"value\":\"CIG\",\"field\":\"salesRepresentativeId\"}"
				+ ",{\"type\":\"string\",\"value\":\"TBD\",\"field\":\"salesRepresentativeName\"}"
				+ ",{\"type\":\"string\",\"value\":\"2014\",\"field\":\"year\"}"
				+ ",{\"type\":\"string\",\"value\":\"A9\",\"field\":\"categoryId\"}"
				+ ",{\"type\":\"string\",\"value\":\"CE\",\"field\":\"categoryName\"}]";//
		FilterParameter[] filterParameters = getFiltersFromJson(filterStr);
		SortParameter[] sortParameters = null;
		int limit = 1; 
		int page = 1;
		int start = 0;
		
		Page<QuotaDao> quotas = quotaService.getPaginatedFilteredQuotas(limit, page, start, filterParameters, sortParameters);
		assertNotNull(quotas);
		
		Page<QuotaDao> quotas1 = quotaService.getPaginatedFilteredQuotas(limit, page, start, null, null);
		assertNotNull(quotas);

		//assertThat(actual, matcher);
	}*/

	@Test
	public void testCreateSalesRepresentative(){
		SalesRepresentativeDao dao = new SalesRepresentativeDao();
		dao.setSalesRepresentativeId("HSQL");
		dao.setSalesRepresentativeName("HSQL test");
		EmployeeDao user = quotaService.getEmployeeById("C05622");
		dao.setUser(user);
		dao.setAllAccess(true);
		quotaService.saveSalesRepresentativeHeader(dao);
		
		SalesRepresentativeDao salesRep = quotaService.getSalesRepresentativeById(dao.getSalesRepresentativeId());
		assertNotNull(salesRep);
		assertThat(salesRep.getSalesRepresentativeName(), equalToIgnoringCase("HSQL test"));
		assertThat(salesRep.getUser(),equalTo(user));
		assertThat(salesRep.getAllAccess(), is(true));
	}
	
	@Test
	public void testMangerProductLines() {
//		salesRepRecord.get('managerId'));salesRepRecord.get('salesRepresentativeId')
		String salesRepresentativeId = "CIG001";
		String managerId = "C05622";
		List<ProductLineDTO> dtos = new ArrayList<ProductLineDTO>();
		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = quotaService.getSalesRepEmployeeJoinsFor(salesRepresentativeId, managerId);
    	List<ProductLine> productLines = quotaService.getProductLines();
		for (ProductLine productLine:productLines) {
			ProductLineDTO dto = DtoFactory.createDtoFromDao(productLine);
			dto.setExists(false);
			dto.setManagerId(managerId);
			dto.setSalesRepresentativeId(salesRepresentativeId);
			if(doesExistsIn(salesRepEmployeeJoins, dto))
				dto.setExists(true);
			dtos.add(dto);
		}
		for (ProductLineDTO dto : dtos) {
			logger.debug(dto);
		}
		logger.debug("End of testMangerProductLines");
	}

	private boolean doesExistsIn(List<SalesRepEmployeeJoin> salesRepEmployeeJoins, ProductLineDTO dto){
		for (SalesRepEmployeeJoin salesRepEmployeeJoin : salesRepEmployeeJoins) {
			if(salesRepEmployeeJoin.getProductLine().getCode().equals(dto.getCode()))
				return true;
		}
		return false;
	}
	
	//@ Test
	public void testGetSalesRepresentatives() {
//	 	List<TocDao>  tocs = quotaService.getTocs();
		List<EmployeeDao> employees = quotaService.getEmployees();
		assertThat(employees.size(), greaterThan(0));

		List<SalesRepresentativeDao>  salesRepresentatives= quotaService.getSalesRepresentatives();
		assertThat(salesRepresentatives.size(), greaterThan(1));
		for (Iterator iterator = salesRepresentatives.iterator(); iterator.hasNext();) {
			SalesRepresentativeDao salesRepresentativeDao = (SalesRepresentativeDao) iterator.next();
			if(salesRepresentativeDao.getSalesRepEmployeeJoins().size()>0){
				logger.debug("salesRepresentativeDao has "+
						"("+salesRepresentativeDao.getSalesRepEmployeeJoins().size()+") SalesRepEmployeeJoins");
			}
				
		}
		SalesRepresentativeDao salesRepresentativeDao = salesRepresentatives.get(1);
		assertThat(salesRepresentativeDao.getSalesRepEmployeeJoins(),org.hamcrest.Matchers.notNullValue());
		assertThat(salesRepresentativeDao.getSalesRepEmployeeJoins().size(), greaterThan(0));
		logger.debug("salesRepresentativeDao.getSalesRepEmployeeJoins() = "+salesRepresentativeDao.getSalesRepEmployeeJoins());
		List<SalesRepresentativeDto> dtos = new ArrayList<SalesRepresentativeDto>();
		for (SalesRepresentativeDao salesRepresentative:salesRepresentatives) {
			SalesRepresentativeDto dto = DtoFactory.createDtoFromDao(salesRepresentative);
			dtos.add(dto);
		}
		String jsonString = GsonUtil.obj2json(dtos);
		assertNotNull(jsonString);
		assertThat(jsonString.length(), greaterThan(0));
		//[{"salesRepresentativeId":"CIG001","salesRepresentativeName":"TBD(WEST MANAGER)","allAccess":false,"tocs":[],"managers":[{"employeeId":"C05622","name":"Taizaburo Ted Egawa","status":"A","jobTitle":"* President","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTZ910","deptName":"General Administration","managerId":"C02924","managerLevel":"7"}]},{"salesRepresentativeId":"CIG002","salesRepresentativeName":"JOANNE HENRY","user":{"employeeId":"C08420","name":"Masaharu Choki","status":"A","jobTitle":"* Director, Plan \u0026 Ctrl-CIG","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTA111","deptName":"TR Camera Adm - Marketing","managerId":"T02899","managerLevel":"3","reportPath":"T02899,C05622","reportPathName":"Justin Lam;Taizaburo Ted Egawa"},"allAccess":false,"tocs":[{"tocId":"TA420","tocName":"JOANNE H PPG A","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"AO","orgCode":"CA20E","orgLayerNumber":5},{"tocId":"TI420","tocName":"JOANNE H ISPG I","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"II","orgCode":"CB25E","orgLayerNumber":5},{"tocId":"TQ420","tocName":"TR LFP ON PPG QD","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"SA","orgCode":"CB20E","orgLayerNumber":5},{"tocId":"TV419","tocName":"JOANNE H PPG V","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"VV","orgCode":"CA20E","orgLayerNumber":5}],"managers":[{"employeeId":"C05622","name":"Taizaburo Ted Egawa","status":"A","jobTitle":"* President","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTZ910","deptName":"General Administration","managerId":"C02924","managerLevel":"7"}]},{"salesRepresentativeId":"CIG004","salesRepresentativeName":"JERRY HOSIER","user":{"employeeId":"C10591","name":"Josh Saji","status":"T","jobTitle":"* Manager, Bdgts \u0026 Invntry ISG","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTD001","deptName":"Copier Administration","managerId":"U03202","managerLevel":"15","reportPath":"U03202,T02720,C05622","reportPathName":"Elaine Kwong;Satch Hattori;Taizaburo Ted Egawa"},"allAccess":false,"tocs":[{"tocId":"TB404","tocName":"JERRRY H-ISPG B-DAISYTEK","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"BO","orgCode":"CB30E","orgLayerNumber":5},{"tocId":"TI404","tocName":"JERRY H ISPG I-DAISYTEK","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"IC","orgCode":"CB30E","orgLayerNumber":5},{"tocId":"TT404","tocName":"JERRY H ISPG T-DAISYTEK","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"TB","orgCode":"CB30E","orgLayerNumber":5}],"managers":[{"employeeId":"C05622","name":"Taizaburo Ted Egawa","status":"A","jobTitle":"* President","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTZ910","deptName":"General Administration","managerId":"C02924","managerLevel":"7"}]},{"salesRepresentativeId":"CIG005","salesRepresentativeName":"ROB FARDEN","user":{"employeeId":"C10744","name":"Hiroshi Miyazato","status":"T","jobTitle":"Director Sr, IT","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTZ620","deptName":"IT","managerId":"T03197","managerLevel":"13","reportPath":"T03197","reportPathName":"Kazuto Ogawa"},"allAccess":false,"tocs":[{"tocId":"VA421","tocName":"ROB F - LONDON DRUGS PPG A","coa_brCode":"100","coa_chCode":"998","coa_prodCode":"AO","orgCode":"CA10W","orgLayerNumber":5},{"tocId":"VA424","tocName":"VA CAMERA REG-1 V.V-I.OKA-V RF","coa_brCode":"100","coa_chCode":"998","coa_prodCode":"AO","orgCode":"CA20W","orgLayerNumber":5},{"tocId":"VI424","tocName":"VA COPIER VA COPIER BC RF","coa_brCode":"100","coa_chCode":"998","coa_prodCode":"TB","orgCode":"CB25W","orgLayerNumber":5},{"tocId":"VQ424","tocName":"VA LFP BC PPG RF","coa_brCode":"100","coa_chCode":"998","coa_prodCode":"SA","orgCode":"CB20W","orgLayerNumber":5},{"tocId":"VT424","tocName":"VA PRINTR PRT BC PRT PPG RF","coa_brCode":"100","coa_chCode":"998","coa_prodCode":"TB","orgCode":"CB25W","orgLayerNumber":5},{"tocId":"VV423","tocName":"ROB F - LONDON DRUGS PPG V","coa_brCode":"100","coa_chCode":"998","coa_prodCode":"VV","orgCode":"CA10W","orgLayerNumber":5},{"tocId":"VV424","tocName":"VA VIDEO  REG   V.V-I.OKA-V RF","coa_brCode":"100","coa_chCode":"998","coa_prodCode":"VV","orgCode":"CA20W","orgLayerNumber":5}],"managers":[{"employeeId":"C11266","name":"Makoto Shibata","status":"T","jobTitle":"* Mgr, Budget \u0026 Product Ctr","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTA110","deptName":"TR Camera Administration","managerId":"C08420","managerLevel":"15","reportPath":"C08420,T02899,C05622","reportPathName":"Masaharu Choki;Justin Lam;Taizaburo Ted Egawa"}]},{"salesRepresentativeId":"CIG006","salesRepresentativeName":"RUSSELL BROWN","user":{"employeeId":"C11266","name":"Makoto Shibata","status":"T","jobTitle":"* Mgr, Budget \u0026 Product Ctr","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTA110","deptName":"TR Camera Administration","managerId":"C08420","managerLevel":"15","reportPath":"C08420,T02899,C05622","reportPathName":"Masaharu Choki;Justin Lam;Taizaburo Ted Egawa"},"allAccess":false,"tocs":[{"tocId":"EA416","tocName":"CL CAMERA REG-1 AB          TC","coa_brCode":"105","coa_chCode":"998","coa_prodCode":"AO","orgCode":"CA20W","orgLayerNumber":5},{"tocId":"EQ416","tocName":"CL LFP AB PPG VA","coa_brCode":"105","coa_chCode":"998","coa_prodCode":"SA","orgCode":"CB20W","orgLayerNumber":5},{"tocId":"ET416","tocName":"CL PRINTR PRT AB PRT PPG TC","coa_brCode":"105","coa_chCode":"998","coa_prodCode":"TB","orgCode":"CB25W","orgLayerNumber":5},{"tocId":"EI416","tocName":"CL PC COPIER CL VA","coa_brCode":"105","coa_chCode":"998","coa_prodCode":"II","orgCode":"CB80W","orgLayerNumber":5},{"tocId":"EA418","tocName":"CL CAMERA REG-1 N/A -VISIONS","coa_brCode":"105","coa_chCode":"998","coa_prodCode":"AO","orgCode":"CA10W","orgLayerNumber":5}],"managers":[{"employeeId":"C11410","name":"Takayuki Naruo","status":"T","jobTitle":"*Mgr, Service \u0026 Bus Analysis","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTAS20","deptName":"TR Camera Service Admin","managerId":"T01592","managerLevel":"16","reportPath":"T01592,T02899,C05622","reportPathName":"Steve Mackay;Justin Lam;Taizaburo Ted Egawa"}]},{"salesRepresentativeId":"CIG008","salesRepresentativeName":"ANTHONY MASTRANGELO","user":{"employeeId":"C11410","name":"Takayuki Naruo","status":"T","jobTitle":"*Mgr, Service \u0026 Bus Analysis","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTAS20","deptName":"TR Camera Service Admin","managerId":"T01592","managerLevel":"16","reportPath":"T01592,T02899,C05622","reportPathName":"Steve Mackay;Justin Lam;Taizaburo Ted Egawa"},"allAccess":false,"tocs":[{"tocId":"TA451","tocName":"ANTHONY M-TAKNOLOGY PPG A","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"AO","orgCode":"CA30E","orgLayerNumber":5},{"tocId":"TB479","tocName":"N/A","coa_brCode":"125","coa_chCode":"241","coa_prodCode":"BO","orgCode":"CB10E","orgLayerNumber":5},{"tocId":"TB732","tocName":"ANTHONY M-TAKNOLOGY ISPG B","coa_brCode":"125","coa_chCode":"998","coa_prodCode":"BO","orgCode":"CB80E","orgLayerNumber":5}],"managers":[{"employeeId":"C05622","name":"Taizaburo Ted Egawa","status":"A","jobTitle":"* President","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTZ910","deptName":"General Administration","managerId":"C02924","managerLevel":"7"}]},{"salesRepresentativeId":"CIGIT","salesRepresentativeName":"HENRY YANG (IT)","user":{"employeeId":"C05622","name":"Taizaburo Ted Egawa","status":"A","jobTitle":"* President","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTZ910","deptName":"General Administration","managerId":"C02924","managerLevel":"7"},"allAccess":true,"tocs":[],"managers":[{"employeeId":"C05622","name":"Taizaburo Ted Egawa","status":"A","jobTitle":"* President","company":"CAN","location":"TMIS","locationName":"AVP company","deptId":"TTZ910","deptName":"General Administration","managerId":"C02924","managerLevel":"7"}]}]
		logger.debug("salesRepresentativeDaos json = "+jsonString);
		
	}
	
	//@ Test
	public void testQuotaUpdate(){
		QuotaDao quotaDao = quotaService.getQuotaById(1L);
		assertNotNull(quotaDao);
		logger.debug("quotaDao = '"+quotaDao+"')");
		int originalValue1 = quotaDao.getValue1();
		quotaDao.setValue1(originalValue1+1);
		quotaService.updateQuota(quotaDao);
		quotaDao = quotaService.getQuotaById(1L);
		assertEquals(originalValue1+1, quotaDao.getValue1());
		logger.debug("quotaDao Value1 updated from " + originalValue1 + " to "+quotaDao.getValue1());
	}

}

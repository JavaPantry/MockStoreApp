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
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.GeneralUtil;
//import org.avp.quota.kpi.util.GsonUtil;
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
public class QuotaExt6FilteringTest {
	private static Logger logger = Logger.getLogger(QuotaExt6FilteringTest.class);
	
	@Autowired
	private QuotaService quotaService;

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}


	/*
	 * see: QuotaController
	 * 	public ExtResponse findAjaxQuotas(
	 *		@RequestParam(value="limit", required=false) Integer limit, 
	 *		@RequestParam(value="page", required=false) Integer page, 
	 *		@RequestParam(value="start", required=false) Integer start,
	 *		@RequestParam(value="sort", required=false) String sort,
	 *		@RequestParam(value="filter", required=false) String filter
	 *		) throws AppSpecificException {
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
	 * !!!!! ********** Note that ExtJs 6 use property instead field  ********** !!!!!!
	 * 
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
	 * 
	 * Legacy ExtJs4 errors
	 * ",{\"type\":\"string\",\"value\":\"CIG\",\"field\":\"salesRepresentativeId\"}"
	 * cause
	 * java.lang.IllegalArgumentException: Unable to resolve attribute [salesRepresentativeId] against path
	 * 
	 * ",{\"type\":\"string\",\"value\":\"2014\",\"field\":\"year\"}"
	 * cause
	 * java.lang.IllegalArgumentException: Parameter value [%2014%] did not match expected type [java.lang.Integer (n/a)]
	 * suppose to be http://mis-jboss7dev/QuotaKPIWeb/ajax/quotas?filter=[{"type":"numeric","comparison":"gt","value":2014,"field":"year"}]
	 */
	
	@Test
	public void testSearchQuotasWithSalesRepresentativeCategoryYear(){
		
		//TODO - <AP> test 'property' instead 'field'
		String filterStr = "[{\"operator\":\"like\",\"value\":\"CIG\",\"field\":\"salesRepresentative.salesRepresentativeId\"}"
				+ ",{\"operator\":\"like\",\"value\":\"TBD\",\"field\":\"salesRepresentative.salesRepresentativeName\"}"
				+ ",{\"operator\":\"eq\",\"value\":\"2014\",\"field\":\"year\"}"
				+ ",{\"operator\":\"like\",\"value\":\"A9\",\"field\":\"category.categoryId\"}"
				+ ",{\"operator\":\"like\",\"value\":\"CE\",\"field\":\"category.categoryName\"}]";//
		FilterParameterExtJs6[] filterParameters = getFiltersFromJson(filterStr);
		SortParameter[] sortParameters = null;
		int limit = 1; 
		int page = 1;
		int start = 0;
		
		Page<QuotaDao> quotas1 = quotaService.getPaginatedFilteredQuotas(limit, page, start, null, null);
		assertNotNull(quotas1);

		Page<QuotaDao> quotas = quotaService.getPaginatedFilteredQuotas(limit, page, start, filterParameters, sortParameters);
		assertNotNull(quotas);

		//assertThat(actual, matcher);
	}

	/* copy to test case
	 * AbstractExtJsController:getFiltersFromJson
	 * 
	 * TODO - <AP> extract to static method in ExtJsUtility class
	 */
	protected FilterParameterExtJs6[] getFiltersFromJson(String filterStr) {
//		if(GeneralUtil.isEmpty(filterStr))
			return null;
//		FilterParameterExtJs6[] filterParameters  = GsonUtil.getArrayFromJson(filterStr, FilterParameterExtJs6[].class);
//		//sometime ExtJs sends not 'property' but 'field'
//		for (FilterParameterExtJs6 filterParameter : filterParameters) {
//			if(!GeneralUtil.isEmpty(filterParameter.getProperty()))
//					filterParameter.setField(filterParameter.getProperty());
//		}
//		return filterParameters;
	}

}

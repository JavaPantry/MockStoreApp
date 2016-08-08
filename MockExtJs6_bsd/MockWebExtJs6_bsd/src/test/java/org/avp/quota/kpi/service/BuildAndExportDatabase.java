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

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.service.BsdService;
import org.avp.quota.kpi.configuration.HsqlJUnitDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.JbossTestDataServiceModuleConfiguration;
import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfiguration;
import org.avp.quota.kpi.model.dao.AuthoritiesDao;
import org.avp.quota.kpi.model.dao.BudgetDao;
import org.avp.quota.kpi.model.dao.CategoryDao;
import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.ProductLine;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepEmployeePK;
import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.avp.quota.kpi.model.dao.UserDao;
import org.avp.quota.kpi.model.dto.BudgetDto;
import org.avp.quota.kpi.model.dto.ProductLineDTO;
import org.avp.quota.kpi.model.dto.QuotaDto;
import org.avp.quota.kpi.service.interfaces.QuotaService;
import org.avp.quota.kpi.util.DtoFactory;
import org.avp.quota.kpi.util.GsonUtil;
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

/*
 * See DbUnit maven plugin
 * http://mojo.codehaus.org/dbunit-maven-plugin/index.html
 * Use http://mvnrepository.com/artifact/org.codehaus.mojo/dbunit-maven-plugin/1.0-beta-3
 *		and 
 *		http://stackoverflow.com/questions/4966450/how-to-load-data-into-data-base-using-dbunit-in-maven
 *		> mvn dbunit:operation
 *
 * See: https://github.com/junit-team/junit/wiki/Test-execution-order
 * annotate your test class using @FixMethodOrder
 * @FixMethodOrder(MethodSorters.NAME_ASCENDING): Sorts the test methods by method name, in lexicographic order.
 * 
 * OR run test class with @ActiveProfiles("HSQL_JUNIT")
 *	1. - populate hypersonic memory database in setup
 *	2. - export to DBUNIT_INTEGRATION_DATA
 *
 * select * FROM mymindmap.Users;
 * select * FROM mymindmap.Authorities;
 * select * FROM mymindmap.ids_employee_profile;
 * select * FROM mymindmap.ProductLine;
 * select * FROM mymindmap.ids_security;
 * select * FROM mymindmap.ids_manager;
 * select * from mymindmap.ids_toc;
 * select * from mymindmap.ids_security_toc;
 * select * from mymindmap.ids_mdse_stru_lv3;
 * select * from mymindmap.ids_quota;
 * 
 * select * FROM mockstub.Users;
 * select * FROM mockstub.Authorities;
 * select * FROM mockstub.ids_employee_profile;
 * select * FROM mockstub.ProductLine;
 * select * FROM mockstub.ids_security;
 * select * FROM mockstub.ids_manager;
 * select * from mockstub.ids_toc;
 * select * from mockstub.ids_security_toc;
 * select * from mockstub.ids_mdse_stru_lv3;
 * select * from mockstub.ids_quota;
 * 
 * See Spring 3
 * support with @Transactional annotation
 * Also see @BeforeTransaction or @AfterTransaction. 
 * Notice that these methods will not be executed for test methods annotated with @NotTransactional
 */

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  //LOL 8))) A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class
						,DirtiesContextTestExecutionListener.class
						,TransactionalTestExecutionListener.class
						,DbUnitTestExecutionListener.class 
						})

/*
 * to run BuildAndExportDatabase with tomcat/mysql use ActiveProfiles("Tomcat") 
 * NOTE: change in TomcatDataServiceModuleConfiguration.java 
 * properties.setProperty("hibernate.hbm2ddl.auto", "create");//"create","validate"
 */

//@ActiveProfiles("JBossTest")
//@ContextConfiguration(classes = {JbossTestDataServiceModuleConfiguration.class})

@ActiveProfiles("Tomcat")
@ContextConfiguration(classes = {TomcatDataServiceModuleConfiguration.class})

//@ActiveProfiles("HSQL_JUNIT")
//@ContextConfiguration(classes = {HsqlJUnitDataServiceModuleConfiguration.class})

public class BuildAndExportDatabase {
	private static Logger logger = Logger.getLogger(BuildAndExportDatabase.class);
	//Use @ DatabaseSetup(ExportData.DBUNIT_INTEGRATION_DATA) in tests
	@Autowired
	DataSource dataSource;
	
	@Autowired
	QuotaService quotaService;
	

	@Autowired
	BsdService bsdService;

	/*
	 * org.springframework.beans.factory.BeanCreationException: 
	 * Error creating bean with name 'org.avp.quota.kpi.service.BuildAndExportDatabase': 
	 * Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: 
	 * Could not autowire field: org.springframework.security.crypto.password.PasswordEncoder org.avp.quota.kpi.service.BuildAndExportDatabase.passwordEncoder; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.springframework.security.crypto.password.PasswordEncoder] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
	 * 
    @Autowired
    PasswordEncoder passwordEncoder;
	*/
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void arunSetup(){
		
		setupUsers();
		setupQuotaKPI();
		setupBsd();		
		logger.debug("setup completed.");
	}
	private void setupBsd() {
		Product product1 = new Product("tst","test product","test product (fr)");
		bsdService.save(product1);
		Product product2 = new Product("tst1","test product 1","test product 1 (fr)");
		bsdService.save(product2);
		
		OrderHeader order = new OrderHeader(1L, 1L, 1L, "Alexei", "Ptitchkin", "ptit@gmail.com",	new Date());
		bsdService.save(order);
		OrderHeader order1 = new OrderHeader(2L, 1L, 1L, "Bohdan", "Valentovic", "bohd@gmail.com",	new Date());
		bsdService.save(order1);
		OrderHeader order2 = new OrderHeader(3L, 1L, 1L, "Hong", "Li", "hong@gmail.com",	new Date());
		bsdService.save(order2);
	}
	private void setupQuotaKPI() {
		EmployeeDao employee = new EmployeeDao("C05622","Taizaburo Ted Egawa","A", "* President", 
												"CAN","TMIS","AVP company","TTAS20",
												"TR Camera Service Admin","T01592","16",
												"T01592,T02899,C05622","Steve Mackay;Justin Lam;Taizaburo Ted Egawa" );
		EmployeeDao employee1 = new EmployeeDao("C08420","Takayuki Naruo","A", "*Mgr, Service & Bus Analysis", 
												"CAN","TMIS","AVP company","TTAS20",
												"TR Camera Service Admin","T01592","16",
												"T01592,T02899,C05622","Steve Mackay;Justin Lam;Taizaburo Ted Egawa" );
		EmployeeDao employee2 = new EmployeeDao("C08421","Fumihiko Ogihara","A", "*Mgr, Service & Bus Analysis", 
												"CAN","TMIS","AVP company","TTAS20",
												"TR Camera Service Admin","T01592","16",
												"T01592,T02899,C05622","Steve Mackay;Justin Lam;Taizaburo Ted Egawa" );
		EmployeeDao employee3 = new EmployeeDao("C08422","Jean-Claude Beland","A", "*Mgr, Service & Bus Analysis", 
												"CAN","TMIS","AVP company","TTAS20",
												"TR Camera Service Admin","T01592","16",
												"T01592,T02899,C05622","Steve Mackay;Justin Lam;Taizaburo Ted Egawa" );
		EmployeeDao employee4 = new EmployeeDao("C08423","Bohdan Valentovic","A", "*Mgr, Service & Bus Analysis", 
												"CAN","TMIS","AVP company","TTAS20",
												"TR Camera Service Admin","T01592","16",
												"T01592,T02899,C05622","Steve Mackay;Justin Lam;Taizaburo Ted Egawa" );
		

		quotaService.save(employee);
		quotaService.save(employee1);
		quotaService.save(employee2);
		quotaService.save(employee3);
		quotaService.save(employee4);

		TocDao toc = new TocDao("TOCID","TEST TOC");
		quotaService.save(toc);

		SalesRepresentativeDao salesRepresentative = new SalesRepresentativeDao("CIG001","TBD(WEST MANAGER)",employee, true );
		salesRepresentative.getTocs().add(toc);
		quotaService.saveSalesRepresentativeHeader(salesRepresentative);

		ProductLine lineA = new ProductLine("A", "CAMERA");quotaService.save(lineA);
		ProductLine lineB = new ProductLine("B", "CALCULATOR");quotaService.save(lineB);
		ProductLine lineC = new ProductLine("C", "MICRO");quotaService.save(lineC);
		ProductLine lineD = new ProductLine("D", "COPIER");quotaService.save(lineD);
		ProductLine lineE = new ProductLine("E", "BCD");quotaService.save(lineE);
		ProductLine lineF = new ProductLine("F", "SEMICOM");quotaService.save(lineF);
		ProductLine lineI = new ProductLine("I", "PC COPIER");quotaService.save(lineI);
		ProductLine lineJ = new ProductLine("J", "PERIPHERALS");quotaService.save(lineJ);
		ProductLine lineK = new ProductLine("K", "FAX");quotaService.save(lineK);
		ProductLine lineL = new ProductLine("L", "LBP");quotaService.save(lineL);
		ProductLine lineM = new ProductLine("M", "TYPE WRITER");quotaService.save(lineM);
		ProductLine lineN = new ProductLine("N", "MEDICAL");quotaService.save(lineN);
		ProductLine lineP = new ProductLine("P", "C-ETW");quotaService.save(lineP);
		ProductLine lineQ = new ProductLine("Q", "LFP");quotaService.save(lineQ);
		ProductLine lineT = new ProductLine("T", "PRINTER");quotaService.save(lineT);
		ProductLine lineU = new ProductLine("U", "FAX PHONE");quotaService.save(lineU);
		ProductLine lineV = new ProductLine("V", "VIDEO");quotaService.save(lineV);
		ProductLine lineX = new ProductLine("X", "VISUAL COMM SYSTEMS");quotaService.save(lineX);
		ProductLine lineY = new ProductLine("Y", "GRAPHICS");quotaService.save(lineY);
		ProductLine lineZ = new ProductLine("Z", "ADMIN");quotaService.save(lineZ);

		SalesRepEmployeeJoin salesRepEmployeeJoin = new SalesRepEmployeeJoin(new SalesRepEmployeePK(salesRepresentative, employee, lineA));
		quotaService.save(salesRepEmployeeJoin);
		
		CategoryDao categories[] = new CategoryDao[12];  
		categories[0] = new CategoryDao("A9J", "CEC","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[0]);
		categories[1] = new CategoryDao("A9Z", "* NO CATEGORY","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[1]);
		categories[2] = new CategoryDao("AAA", "DSLR ACCESSORIES","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[2]);
		categories[3] = new CategoryDao("AAE", "E-STORE","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[3]);
		categories[4] = new CategoryDao("AAG", "ANALOG","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[4]);
		categories[5] = new CategoryDao("AAH", "DSLR HARDWARE","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[5]);
		categories[6] = new CategoryDao("AAR", "DSLR REFURBISHED","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[6]);
		categories[7] = new CategoryDao("AAZ", "* NOT CATEGORIZED","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[7]);
		categories[8] = new CategoryDao("ADA", "TAMRAC","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[8]);
		categories[9] = new CategoryDao("ADB", "PHATSTRAPS","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[9]);
		categories[10] = new CategoryDao("ADC", "UMBRA","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[10]);
		categories[11] = new CategoryDao("ADD", "CAPTURING COUTURE","1A", "PHOTO", "A", "CAMERA");quotaService.save(categories[11]);

		int years[]={2013, 2014, 2015, 2016};
		for (int i = 0; i < categories.length; i++) {
			for (int y = 0; y < years.length; y++) {
				QuotaDao q = new QuotaDao(salesRepresentative, categories[i], 1, years[y]); quotaService.save(q); 
				BudgetDao b = new BudgetDao(categories[i], 1, years[y]); quotaService.save(b);
			}
		}
/*		
		salesRepresentative.getQuotas().add(q);
		salesRepresentative.getQuotas().add(q2);
		salesRepresentative.getQuotas().add(q3);
		salesRepresentative.getQuotas().add(q4);
		//cause StackOverfolw eception
		quotaService.save(salesRepresentative);
*/		
		salesRepresentative = quotaService.getSalesRepresentativeById("CIG001");
	}

	private void setupUsers() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		
//		<users id="1" enabled="true" password="password" userId="Alexei Ptitchkin"/>
		UserDao user = new UserDao();
		user.setUserId("Alexei Ptitchkin");
		//user.setEnabled(true);
		//user.setPassword("password");
		user.setPassword(passwordEncoder.encode("password"));
		quotaService.save(user);

		UserDao bsdUser = new UserDao();
		bsdUser.setUserId("Tim Adams");
		//user.setEnabled(true);
		bsdUser.setPassword(passwordEncoder.encode("password"));
		quotaService.save(bsdUser);

		UserDao angularUser = new UserDao();
		angularUser.setUserId("Angular User");
		//user.setEnabled(true);
		angularUser.setPassword(passwordEncoder.encode("password"));
		quotaService.save(angularUser);

		setupAuthorities(user, bsdUser, angularUser);

	}

	private void setupAuthorities(UserDao adminUser, UserDao bsdUser,
			UserDao angularUser) {
		/*
		 * app.auth.userGroup=QuotaKPI_USER
		 * app.auth.quotaGroup=ROLE_QuotaKPI_QUOTA
		 * app.auth.budgetGroup=ROLE_QuotaKPI_BUDGET
		 * app.auth.companyGroup=ROLE_QuotaKPI_COMPANY
		 * app.auth.reportGroup=ROLE_QuotaKPI_REPORT
		 * app.auth.adminGroup=ROLE_QuotaKPI_ADMIN
		 * 
		 */
				
				AuthoritiesDao authoritiy = new AuthoritiesDao();
				authoritiy.setUser(adminUser);
				authoritiy.setRole("ROLE_QuotaKPI_COMPANY");
				quotaService.save(authoritiy);
				
				AuthoritiesDao authoritiy2 = new AuthoritiesDao();
				authoritiy2.setUser(adminUser);
				authoritiy2.setRole("ROLE_QuotaKPI_QUOTA");
				quotaService.save(authoritiy2);
				
				AuthoritiesDao authoritiy3 = new AuthoritiesDao();
				authoritiy3.setUser(adminUser);
				authoritiy3.setRole("ROLE_QuotaKPI_BUDGET");
				quotaService.save(authoritiy3);
			
		
				AuthoritiesDao authoritiy4 = new AuthoritiesDao();
				authoritiy4.setUser(adminUser);
				authoritiy4.setRole("ROLE_QuotaKPI_ADMIN");
				quotaService.save(authoritiy4);
		
				AuthoritiesDao authoritiy5 = new AuthoritiesDao();
				authoritiy5.setUser(bsdUser);
				authoritiy5.setRole("ROLE_BSD_DEALER");
				quotaService.save(authoritiy5);
		
				AuthoritiesDao authoritiy6 = new AuthoritiesDao();
				authoritiy6.setUser(angularUser);
				authoritiy6.setRole("ROLE_QuotaKPI_ADMIN_ANGULAR");
				quotaService.save(authoritiy6);
	}

	@Test
	public void averifySetup(){
		UserDao user = quotaService.getUserById("Alexei Ptitchkin");
		assertNotNull(user);
		assertThat(user.getAuthorities().size(), is(4));
		logger.debug("user " + user + " in averifySetup()");
		SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById("CIG001");
		assertNotNull(salesRepresentative);
		EmployeeDao employee = quotaService.getEmployeeById("C05622");
		assertNotNull(employee);
		
		List<ProductLine> lines = quotaService.getProductLines();
		for (ProductLine productLine : lines) {
			assertNotNull(productLine);
			logger.debug("productLine = "+productLine);
		}

		ProductLine productLine = quotaService.getProductLineByCode("Q");
		assertNotNull(productLine);
		logger.debug("productLine = "+productLine);

		List<SalesRepEmployeeJoin> salesRepEmployeeJoins = quotaService.getSalesRepEmployeeJoinsFor("CIG001","C05622");
		assertNotNull(salesRepEmployeeJoins);
		assertThat(salesRepEmployeeJoins.size(), is(1));
		
		List<SalesRepEmployeeJoin> salesRepEmployeeJoinsWithProdLines = quotaService.getSalesRepEmployeeJoinsFor("CIG001","C05622","A");
		assertNotNull(salesRepEmployeeJoinsWithProdLines);
		assertThat(salesRepEmployeeJoinsWithProdLines.size(), is(1));

		SalesRepEmployeeJoin salesRepEmployeeJoinWithProdLine = quotaService.getOneSalesRepEmployeeJoinsFor("CIG001","C05622","A");
		assertNotNull(salesRepEmployeeJoinWithProdLine);
		
		List<SalesRepEmployeeJoin> salesRepEmployeeJoinsWithNotExistProdLine = quotaService.getSalesRepEmployeeJoinsFor("CIG001","C05622","Q");
		assertNotNull(salesRepEmployeeJoinsWithNotExistProdLine);
		assertThat(salesRepEmployeeJoinsWithNotExistProdLine.size(), is(0));

		SalesRepEmployeeJoin salesRepEmployeeJoinWithNotExistProdLine = quotaService.getOneSalesRepEmployeeJoinsFor("CIG001","C05622","Q");
		assertNull(salesRepEmployeeJoinWithNotExistProdLine);
		
		//test create new join with new prodLine
		//see above: SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById("CIG001");
		//see above: EmployeeDao employee = quotaService.getEmployeeById("C05622");
		ProductLine line = quotaService.getProductLineByCode("Q");
		SalesRepEmployeeJoin salesRepEmployeeJoin = new SalesRepEmployeeJoin(new SalesRepEmployeePK(salesRepresentative, employee, line));
		quotaService.save(salesRepEmployeeJoin);
		
		salesRepEmployeeJoins = quotaService.getSalesRepEmployeeJoinsFor("CIG001","C05622");
		assertNotNull(salesRepEmployeeJoins);
		assertThat(salesRepEmployeeJoins.size(), is(2));
		
		//and test to delete that just created join 
		SalesRepEmployeeJoin salesRepEmployeeJoinWith_Q_ExistProdLine = quotaService.getOneSalesRepEmployeeJoinsFor("CIG001","C05622","Q");
		quotaService.delete(salesRepEmployeeJoinWith_Q_ExistProdLine);
		salesRepEmployeeJoins = quotaService.getSalesRepEmployeeJoinsFor("CIG001","C05622");
		assertNotNull(salesRepEmployeeJoins);
		assertThat(salesRepEmployeeJoins.size(), is(1));

		List<QuotaDto> quotaDtos = new ArrayList<QuotaDto>();
		List<QuotaDao> quotas = quotaService.getQuotas();
		for (QuotaDao quota : quotas) {
			assertNotNull(quota);
			assertNotNull(quota.getSalesRepresentative());
			logger.debug("quota = " + quota);
			
			QuotaDto dto = DtoFactory.createDtoFromDao(quota);
			assertNotNull(dto.getSalesRepresentativeId());
			assertNotNull(dto.getSalesRepresentativeName());
			quotaDtos.add(dto);
		}
		
		List<QuotaDto> quotaDtos2 = DtoFactory.createQuotaDtoList(quotas);
		//assertThat(quotaDtos2.size(), is(4));

		QuotaDto first = quotaDtos2.get(0);
		first.setValue1(1);
		first.setValue2(2);
		QuotaDao dao = quotaService.getQuotaById(first.getId());
		dao.setValue1(first.getValue1());
		dao.setValue2(first.getValue2());
		
		quotaService.updateQuotasValues(dao);
		
		QuotaDao dao2 = quotaService.getQuotaById(first.getId());
		assertThat(dao2.getValue1(), is(first.getValue1()));
		assertThat(dao2.getValue2(), is(first.getValue2()));
		
/*
		//ERROR: obj2json(quotas) throws StackOverflowException
		String jsonStr = GsonUtil.obj2json(quotas);
		logger.debug("quotas jsonStr = " + jsonStr);
*/		
		List<BudgetDao> budgets = quotaService.getBudgets();
		for (BudgetDao budget : budgets) {
			assertNotNull(budget);
			logger.debug("budget = " + budget);
		}

		List<BudgetDto> budgetDtos = DtoFactory.createBudgetDtoList(budgets);
		//assertThat(budgetDtos.size(), is(4));

		logger.debug("end");
	}

	//See: http://ralf.schaeftlein.de/2009/01/05/dbunit-with-junit-4x-and-spring-for-testing-oracle-db-application/
	@Test
	public void exportData() {
		try {
			Connection con = dataSource.getConnection();
			IDatabaseConnection connection = new DatabaseConnection(con);
			ITableFilter filter = new DatabaseSequenceFilter(connection);
			IDataSet dataset    = new FilteredDataSet(filter , connection.createDataSet());
			//FlatXmlDataSet.write(dataset, new FileOutputStream(ExportData.DBUNIT_HSQLDB_INTEGRATION_DATA));
			FlatXmlDataSet.write(dataset, new FileOutputStream(ExportData.DBUNIT_INTEGRATION_DATA));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//moved to QuotaServiceImplTest.java @ Test
	public void testSalesRepDelete(){
		SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById("CIG001");
		quotaService.deleteSalesRepresentative(salesRepresentative.getSalesRepresentativeId());
		salesRepresentative = quotaService.getSalesRepresentativeById("CIG001");
		logger.debug("salesRepresentative = "+salesRepresentative);
		//assertNull(salesRepresentative);
	}

	//@ - Test
	public void verifyData() {
		String salesRepresentativeId = "CIG001";
		String managerId = "C05622";

		SalesRepresentativeDao salesRepresentative = quotaService.getSalesRepresentativeById("CIG001");
		assertNotNull(salesRepresentative);
		EmployeeDao employee = quotaService.getEmployeeById("C05622");
		assertNotNull(employee);
		ProductLine line = quotaService.getProductLineByCode("A");
		assertNotNull(line);
		
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


}
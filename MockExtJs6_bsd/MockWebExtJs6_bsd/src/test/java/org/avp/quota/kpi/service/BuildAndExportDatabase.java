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
import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfigurationForBuild;
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

/*
 * To run BuildAndExportDatabase with tomcat/mysql use ActiveProfiles("Tomcat") 
 * NOTE: To really build database from scratch change in TomcatDataServiceModuleConfiguration.java 
 * set BULD_DATABASE constant to true
 * 
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
 */

//@ActiveProfiles("JBossTest")
//@ContextConfiguration(classes = {JbossTestDataServiceModuleConfiguration.class})
//@ContextConfiguration(classes = {{TomcatDataServiceModuleConfiguration.class})
//@ActiveProfiles("HSQL_JUNIT")
//@ContextConfiguration(classes = {HsqlJUnitDataServiceModuleConfiguration.class})

@ActiveProfiles("Tomcat")
@ContextConfiguration(classes = {TomcatDataServiceModuleConfigurationForBuild.class})


public class BuildAndExportDatabase {
	private static Logger logger = Logger.getLogger(BuildAndExportDatabase.class);
	//Use @ DatabaseSetup(ExportData.DBUNIT_INTEGRATION_DATA) in tests
	@Autowired
	DataSource dataSource;
	
	@Autowired
	QuotaService quotaService;
	

	@Autowired
	BsdService bsdService;

	@Autowired
	CustomUserService userService;
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
		
		setupQuotaUsers();
		setupQuotaKPI();
		setupBsd();		
		logger.debug("setup completed.");
	}
	
	private void setupBsd() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		
		Store store = new Store("MissisaugaStore",
				"Tim Adams", "store in Missisauga", true/*Boolean attSecurity*/,
				"en", "clientLogo.gif");
		bsdService.save(store);

		Store storeHq = new Store("HQStore",
				"Hong Li", "Head Quotar store", true/*Boolean attSecurity*/,
				"en", "clientLogo.gif");
		bsdService.save(storeHq);

		/*
		 * Users setup
		 */ 		
		BsdUser timAdams = new BsdUser();
		timAdams.setUserId("Tim Adams");
		timAdams.setFirstName("Tim");
		timAdams.setLastName("Adams");
		timAdams.setPassword(passwordEncoder.encode("password"));
		timAdams.setEmail("timAdams@gmail.com");
		timAdams.setStore(store);
		userService.save(timAdams);
		
		Authority timAdamsAuthoritiy = new Authority();
		timAdamsAuthoritiy.setUser(timAdams);
		timAdamsAuthoritiy.setRole("ROLE_BSD_DEALER");
		userService.save(timAdamsAuthoritiy);
		
		BsdUser hongLi = new BsdUser();
		hongLi.setUserId("Hong Li");
		hongLi.setFirstName("Hong");
		hongLi.setLastName("Li");
		hongLi.setPassword(passwordEncoder.encode("password"));
		hongLi.setEmail("hongLi@gmail.com");
		hongLi.setStore(storeHq);
		userService.save(hongLi);
		
		Authority hongLiAuthoritiy = new Authority();
		hongLiAuthoritiy.setUser(hongLi);
		hongLiAuthoritiy.setRole("ROLE_BSD_DEALER");
		userService.save(hongLiAuthoritiy);
		
		BsdUser angularUser = new BsdUser();
		angularUser.setUserId("Angular User");
		angularUser.setFirstName("Angular");
		angularUser.setLastName("User");
		//user.setEnabled(true);
		angularUser.setPassword(passwordEncoder.encode("password"));
		angularUser.setEmail("angular@gmail.com");
		angularUser.setStore(storeHq);
		userService.save(angularUser);

		Authority angularUserAuthoritiy = new Authority();
		angularUserAuthoritiy.setUser(angularUser);
		angularUserAuthoritiy.setRole("ROLE_QuotaKPI_ADMIN_ANGULAR");
		userService.save(angularUserAuthoritiy);
		
		/*
		 * Products setup
		 */
		Product product1 = new Product("tst1","test product 1","test product 1(fr)", "cinemaeos_120.gif");
		bsdService.save(product1);
		Product product2 = new Product("tst2","test product 2","test product 2 (fr)", "cinemalenses_120.gif");
		bsdService.save(product2);
		Product product3 = new Product("tst3","test product 3","test product 3 (fr)", "digisuper76_small.gif");
		bsdService.save(product3);
		Product product4 = new Product("tst4","test product 4","test product 4 (fr)", "fixed-network-thumb_120x80.gif");
		bsdService.save(product4);
		Product product5 = new Product("tst5","test product 5","test product 5 (fr)", "GPS Receiver Small.gif");
		bsdService.save(product5);
		Product product6 = new Product("tst6","test product 6","test product 6 (fr)", "High Definition PTZ Cameras small thumbnail.gif");
		bsdService.save(product6);
		Product product7 = new Product("tst7","test product 7","test product 7 (fr)", "LFP Small.gif");
		bsdService.save(product7);
		Product product8 = new Product("tst8","test product 1","test product 1 (fr)", "ME20F-SH_120x80.gif");
		bsdService.save(product8);
		Product product9 = new Product("tst9","test product 1","test product 1 (fr)", "portable_80x120.gif");
		bsdService.save(product9);
		Product product10 = new Product("tst10","test product 1","test product 1 (fr)", "Pro Photo Inkjet.gif");
		bsdService.save(product10);
		Product product11 = new Product("tst11","test product 1","test product 1 (fr)", "ptz-network-thumb_120x80.gif");
		bsdService.save(product11);
		Product product12 = new Product("tst12","test product 1","test product 1 (fr)", "subcat_EFL_120x80.gif");
		bsdService.save(product12);
		Product product13 = new Product("tst13","test product","test product (fr)", "subcat_SLF_120x80.gif");
		bsdService.save(product13);
		Product product14 = new Product("tst14","test product 1","test product 1 (fr)", "subcat-dsc_120x80.gif");
		bsdService.save(product14);
		Product product15 = new Product("tst15","test product 1","test product 1 (fr)", "subcat-dslr_120x80.gif");
		bsdService.save(product15);
		Product product16 = new Product("tst16","test product 1","test product 1 (fr)", "vb-s30d_120x80.gif");
		bsdService.save(product16);
		Product product17 = new Product("tst17","test product 1","test product 1 (fr)", "Wireless File Transmitter Small.gif");
		bsdService.save(product17);
		Product product18 = new Product("tst18","test product 1","test product 1 (fr)", "WUX400ST_120x80.gif");
		bsdService.save(product18);
		Product product19 = new Product("tst19","test product 1","test product 1 (fr)", "WUX5000_120x80.gif");
		bsdService.save(product19);
		Product product20 = new Product("tst20","test product 1","test product 1 (fr)", "XA35 90x60.gif");
		bsdService.save(product20);
		Product product21 = new Product("tst21","test product 1","test product 1 (fr)", "XC10_90x60.gif");
		bsdService.save(product21);
		Product product22 = new Product("tst22","test product 1","test product 1 (fr)", "XF205_90x60.gif");
		bsdService.save(product22);
		Product product23 = new Product("tst23","test product 1","test product 1 (fr)", "XF305_Thumbnail_120x80.gif");
		bsdService.save(product23);
		
		
		ProductPriceInStore productPriceInStore1 = new ProductPriceInStore(new StoreProductPK(store, product1), 9.99, 15.99, new Date());
		bsdService.save(productPriceInStore1);
		ProductPriceInStore productPriceInStore2 = new ProductPriceInStore(new StoreProductPK(store, product2), 6.99, 5.99, new Date());
		bsdService.save(productPriceInStore2);
		ProductPriceInStore productPriceInStore3 = new ProductPriceInStore(new StoreProductPK(store, product3), 136.99, 136.99, new Date());
		bsdService.save(productPriceInStore3);
		ProductPriceInStore productPriceInStore4 = new ProductPriceInStore(new StoreProductPK(store, product4), 16.99, 15.66, new Date());
		bsdService.save(productPriceInStore4);
		ProductPriceInStore productPriceInStore5 = new ProductPriceInStore(new StoreProductPK(store, product5), 16.99, 15.89, new Date());
		bsdService.save(productPriceInStore5);

		//storeHq
		ProductPriceInStore productPriceInStore6 = new ProductPriceInStore(new StoreProductPK(storeHq, product6), 9.99, 8.99, new Date());
		bsdService.save(productPriceInStore6);
		ProductPriceInStore productPriceInStore7 = new ProductPriceInStore(new StoreProductPK(storeHq, product7), 1.99, 7.99, new Date());
		bsdService.save(productPriceInStore7);
		
		
		OrderHeader bsdUserOrder = new OrderHeader(timAdams);
		bsdService.save(bsdUserOrder);
		OrderHeader bsdUserOrder1 = new OrderHeader(timAdams);
		bsdService.save(bsdUserOrder1);
		OrderHeader bsdUserOrder2 = new OrderHeader(hongLi);
		bsdService.save(bsdUserOrder2);
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

	private void setupQuotaUsers() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		
//		<users id="1" enabled="true" password="password" userId="Alexei Ptitchkin"/>
		QuotaUser adminUser = new QuotaUser();
		adminUser.setUserId("Alexei Ptitchkin");
		adminUser.setFirstName("Alexei");
		adminUser.setLastName("Ptitchkin");
		adminUser.setPassword(passwordEncoder.encode("password"));
		userService.save(adminUser);

		
		/*
		 * app.auth.userGroup=QuotaKPI_USER
		 * app.auth.quotaGroup=ROLE_QuotaKPI_QUOTA
		 * app.auth.budgetGroup=ROLE_QuotaKPI_BUDGET
		 * app.auth.companyGroup=ROLE_QuotaKPI_COMPANY
		 * app.auth.reportGroup=ROLE_QuotaKPI_REPORT
		 * app.auth.adminGroup=ROLE_QuotaKPI_ADMIN
		 * 
		 */
		Authority authoritiy = new Authority();
		authoritiy.setUser(adminUser);
		authoritiy.setRole("ROLE_QuotaKPI_COMPANY");
		userService.save(authoritiy);
		
		Authority authoritiy2 = new Authority();
		authoritiy2.setUser(adminUser);
		authoritiy2.setRole("ROLE_QuotaKPI_QUOTA");
		userService.save(authoritiy2);
		
		Authority authoritiy3 = new Authority();
		authoritiy3.setUser(adminUser);
		authoritiy3.setRole("ROLE_QuotaKPI_BUDGET");
		userService.save(authoritiy3);

		Authority authoritiy4 = new Authority();
		authoritiy4.setUser(adminUser);
		authoritiy4.setRole("ROLE_QuotaKPI_ADMIN");
		userService.save(authoritiy4);
		
/*
		QuotaUser angularUser = new QuotaUser();
		angularUser.setUserId("Angular User");
		angularUser.setFirstName("Angular");
		angularUser.setLastName("User");
		//user.setEnabled(true);
		angularUser.setPassword(passwordEncoder.encode("password"));
		userService.save(angularUser);
*/
		

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
}
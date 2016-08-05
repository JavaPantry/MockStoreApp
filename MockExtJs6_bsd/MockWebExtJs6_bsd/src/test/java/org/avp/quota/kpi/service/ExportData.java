package org.avp.quota.kpi.service;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.avp.quota.kpi.configuration.TomcatDataServiceModuleConfiguration;
import org.avp.quota.kpi.service.interfaces.QuotaService;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

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
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TomcatDataServiceModuleConfiguration.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class
						,DirtiesContextTestExecutionListener.class
						,TransactionalTestExecutionListener.class
						,DbUnitTestExecutionListener.class 
						})
@ActiveProfiles("Tomcat")
public class ExportData {
	//to use @DatabaseSetup(ExportData.DBUNIT_INTEGRATION_DATA)
	static final String DBUNIT_INTEGRATION_DATA = "dbUnitIntegrationData.data";
	static final String DBUNIT_HSQLDB_INTEGRATION_DATA = "hsqlDbUnitIntegrationData.data";
	@Autowired
	DataSource dataSource;
	//QuotaService qotaService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//See: http://ralf.schaeftlein.de/2009/01/05/dbunit-with-junit-4x-and-spring-for-testing-oracle-db-application/
	@Test
	public void extractData() {
		try {
			Connection con = dataSource.getConnection();
			IDatabaseConnection connection = new DatabaseConnection(con);
			//IDataSet dataset    = new FilteredDataSet({"users","authorities","","","",""} , connection.createDataSet());
			ITableFilter filter = new DatabaseSequenceFilter(connection);
			IDataSet dataset    = new FilteredDataSet(filter , connection.createDataSet());
			FlatXmlDataSet.write(dataset, new FileOutputStream(DBUNIT_INTEGRATION_DATA));
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
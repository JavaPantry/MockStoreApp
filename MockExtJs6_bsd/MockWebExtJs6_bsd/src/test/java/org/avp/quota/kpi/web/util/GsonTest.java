package org.avp.quota.kpi.web.util;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.model.StoreProductPK;
import org.avp.quota.kpi.util.UtilDateSerializer;
import org.avp.security.model.Authority;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {
	private static Logger logger = Logger.getLogger(GsonTest.class);
	Gson gson;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
	
	Store store;
	Store storeHq;
	BsdUser bsdUser;
	Authority bsdUserAuthoritiy;
	BsdUser bsdUser2;
	Authority bsdUser2Authoritiy;
	
	Product product1;
	Product product2;
	Product product3;
	Product product4;
	Product product5;
	Product product6;
	Product product7;
	
	ProductPriceInStore productPriceInStore1;
	ProductPriceInStore productPriceInStore2;
	ProductPriceInStore productPriceInStore3;
	ProductPriceInStore productPriceInStore4;
	ProductPriceInStore productPriceInStore5;

	//storeHq
	ProductPriceInStore productPriceInStore6;
	ProductPriceInStore productPriceInStore7;
	
	OrderHeader bsdUserOrder;
	OrderHeader bsdUserOrder1;
	OrderHeader bsdUserOrder2;
	
	
	@Before
	public void setUp() throws Exception {
		gson = new GsonBuilder()//.serializeNulls()
						.setPrettyPrinting()
						.excludeFieldsWithoutExposeAnnotation()
						.registerTypeAdapter(java.util.Date.class, new UtilDateSerializer())// TODO - <AP> get rid of it
						.setDateFormat(DateFormat.LONG)// TODO - <AP> 
						.create();

		
		store = new Store("MissisaugaStore",
				"Tim Adams", "store in Missisauga", true/*Boolean attSecurity*/,
				"en", "clientLogo.gif");

		storeHq = new Store("HQStore",
				"Hong Li", "Head Quotar store", true/*Boolean attSecurity*/,
				"en", "clientLogo.gif");

		bsdUser = new BsdUser();
		bsdUser.setUserId("Tim Adams");
		bsdUser.setFirstName("Tim");
		bsdUser.setLastName("Adams");
		bsdUser.setActive(true);
		bsdUser.setClientAdmin(true);
		bsdUser.setEnabled(true);
		bsdUser.setFax("1234");
		bsdUser.setPhone("1234");
		bsdUser.setSubDivision("test");
		bsdUser.setPassword(passwordEncoder.encode("password"));
		bsdUser.setEmail("timAdams@gmail.com");
		bsdUser.setStore(store);

		
		bsdUserAuthoritiy = new Authority();
		bsdUserAuthoritiy.setUser(bsdUser);
		bsdUserAuthoritiy.setRole("ROLE_BSD_DEALER");
		
		bsdUser2 = new BsdUser();
		bsdUser2.setUserId("Hong Li");
		bsdUser2.setFirstName("Hong");
		bsdUser2.setLastName("Li");
		bsdUser2.setPassword(passwordEncoder.encode("password"));
		bsdUser2.setEmail("hongLi@gmail.com");
		bsdUser2.setStore(storeHq);
		
		bsdUser2Authoritiy = new Authority();
		bsdUser2Authoritiy.setUser(bsdUser2);
		bsdUser2Authoritiy.setRole("ROLE_BSD_DEALER");

		product1 = new Product("tst1","test product","test product (fr)");
		product2 = new Product("tst2","test product 1","test product 1 (fr)");
		product3 = new Product("tst3","test product 1","test product 1 (fr)");
		product4 = new Product("tst4","test product 1","test product 1 (fr)");
		product5 = new Product("tst5","test product 1","test product 1 (fr)");
		product6 = new Product("tst6","test product 1","test product 1 (fr)");
		product7 = new Product("tst7","test product 1","test product 1 (fr)");
		
		productPriceInStore1 = new ProductPriceInStore(new StoreProductPK(store, product1), 9.99, 15.99, new Date());
		productPriceInStore2 = new ProductPriceInStore(new StoreProductPK(store, product2), 6.99, 5.99, new Date());
		productPriceInStore3 = new ProductPriceInStore(new StoreProductPK(store, product3), 136.99, 136.99, new Date());
		productPriceInStore4 = new ProductPriceInStore(new StoreProductPK(store, product4), 16.99, 15.66, new Date());
		productPriceInStore5 = new ProductPriceInStore(new StoreProductPK(store, product5), 16.99, 15.89, new Date());

		productPriceInStore6 = new ProductPriceInStore(new StoreProductPK(storeHq, product6), 9.99, 8.99, new Date());
		productPriceInStore7 = new ProductPriceInStore(new StoreProductPK(storeHq, product7), 1.99, 7.99, new Date());
		
		bsdUserOrder = new OrderHeader(bsdUser);
		bsdUserOrder1 = new OrderHeader(bsdUser);
		bsdUserOrder2 = new OrderHeader(bsdUser2);
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testClientSerialization() {
		String jsonStr = gson.toJson(bsdUser);
		System.out.println("GsonTest.testClientSerialization() bsdUser = \n"+jsonStr);
		
		jsonStr = gson.toJson(bsdUser2);
		System.out.println("GsonTest.testClientSerialization() bsdUser2 = \n"+jsonStr);

		jsonStr = gson.toJson(store);
		System.out.println("GsonTest.testClientSerialization() store = \n"+jsonStr);
		
		jsonStr = gson.toJson(product1);
		System.out.println("GsonTest.testClientSerialization() product1 = \n"+jsonStr);
	}

}

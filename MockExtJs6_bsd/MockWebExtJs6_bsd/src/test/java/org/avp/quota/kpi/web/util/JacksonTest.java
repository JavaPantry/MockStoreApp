package org.avp.quota.kpi.web.util;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.apache.log4j.Logger;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.model.StoreProductPK;
import org.avp.security.model.Authority;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	private static Logger logger = Logger.getLogger(JacksonTest.class);

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
	
	ObjectMapper mapper = null;
	
	@Before
	public void setUp() throws Exception {

		mapper = new ObjectMapper();
		//mapper.
		
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
	
	/*
	 * Jackson JSON Tutorial: http://www.baeldung.com/jackson
	 */
	@Test
	public void whenParsingJsonStringIntoJsonNode_thenCorrect() throws JsonParseException, IOException {
	    String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
	 
	    //ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree(jsonString);
	    assertNotNull(actualObj);
	}
	
	@Test
	public void testClientSerialization() throws IOException {
		
		try {
		String jsonStr = null;
		boolean can = mapper.canSerialize(BsdUser.class);
		System.out.println("GsonTest.testClientSerialization() canSerialize bsdUser = "+can);
		jsonStr = mapper.writeValueAsString(bsdUser);
		assertThat(jsonStr, not(containsString("store")));
		assertThat(jsonStr, not(containsString("orders")));
		System.out.println("GsonTest.testClientSerialization() bsdUser = \n"+jsonStr);
		
		can = mapper.canSerialize(Store.class);
		System.out.println("GsonTest.testClientSerialization() canSerialize Store= "+can);
		jsonStr = mapper.writeValueAsString(store);
		assertThat(jsonStr, not(containsString("productsInStore")));
		assertThat(jsonStr, not(containsString("users")));
		System.out.println("GsonTest.testClientSerialization() store = \n"+jsonStr);
		
		can = mapper.canSerialize(Product.class);
		System.out.println("GsonTest.testClientSerialization() canSerialize Product= "+can);
		jsonStr = mapper.writeValueAsString(product1);
		assertThat(jsonStr, not(containsString("productsInStore")));
		System.out.println("GsonTest.testClientSerialization() product1 = \n"+jsonStr);

		//productPriceInStore1 = new ProductPriceInStore
		can = mapper.canSerialize(ProductPriceInStore.class);
		System.out.println("GsonTest.testClientSerialization() canSerialize productPriceInStore1= "+can);
		jsonStr = mapper.writeValueAsString(productPriceInStore1);
		//assertThat(jsonStr, not(containsString("productsInStore")));
		System.out.println("GsonTest.testClientSerialization() productPriceInStore1 = \n"+jsonStr);
		
		ProductPriceInStore productPriceInStoreFromJson = mapper.readValue(jsonStr, ProductPriceInStore.class);
		assertNotNull(productPriceInStoreFromJson);
		//assertEquals(productPriceInStoreFromJson, productPriceInStore1);
		
		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

	/*	@Test
	public void testRequestParsing(){
		ExtData response = new ExtData();

		ProductInStoreJsonData requestProductInStore = gson.fromJson(priceInStoreJsonStr, ProductInStoreJsonData.class);

		logger.debug("GsonTest.testRequestParsing() jsonStr = \n"+ requestProductInStore);
		assertNotNull(requestProductInStore.getData());
		System.out.println("GsonTest.testRequestParsing() requestProductInStore.getData() = \n"+ requestProductInStore.getData());

	}*/
}

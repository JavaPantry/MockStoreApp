package org.avp.quota.kpi.web.util;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.model.StoreProductPK;
import org.avp.quota.kpi.util.FilterParameterExtJs6;
import org.avp.quota.kpi.util.SortParameter;
import org.avp.quota.kpi.util.UtilDateSerializer;
import org.avp.quota.kpi.web.web.BsdController.ProductInStoreJsonData;
import org.avp.security.model.Authority;
import org.codehaus.jackson.annotate.JsonProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sporcic.extjs.ExtData;
import org.sporcic.extjs.ExtResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {
	private static Logger logger = Logger.getLogger(GsonTest.class);
	Gson gson;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
	
	
	String priceInStoreJsonStr = "{\"data\":"
									+ "["
										+ "{\"pk\":{"
													+ "\"product\":{"
																+ "\"sku\":\"tst7\""
																+ ",\"EProductName\":\"test product 1\""
																+ ",\"FProductName\":\"test product 1 (fr)\""
																+ "}"
													+ "}"
										//	+ ",\"sku\":\"tst7\""
										//	+ ",\"EProductName\":\"test product 1\""
										//	+ ",\"EProductDescription\":\"\""
											+ ",\"price\":99.77"
											+ ",\"priceScheduled\":0"
											+ ",\"priceSchedule\":null"
										//	+ ",\"id\":\"QuotaKPI.model.siteManagement.ProductPriceInStore-7\""
										+ "}"
									+ "]"
								+ "}";
	/*
	{"data":[{"pk":{"product":{"sku":"tst7","EProductName":"test product 1","FProductName":"test product 1 (fr)"}},"sku":"tst7","EProductName":"test product 1","EProductDescription":"","price":99.77,"priceScheduled":0,"priceSchedule":null,"id":"QuotaKPI.model.siteManagement.ProductPriceInStore-7"}]}
	*/
	
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
						.setDateFormat(DateFormat.LONG)// TODO - <AP> make date format same as set in spring mvc config
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
		bsdUserOrder.setId(1L);
		bsdUserOrder.setShippingFirstName("Tim");
		bsdUserOrder.setShippingLastName("Adams");
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
		
		jsonStr = gson.toJson(store);
		System.out.println("GsonTest.testClientSerialization() store = \n"+jsonStr);
		
		jsonStr = gson.toJson(product1);
		System.out.println("GsonTest.testClientSerialization() product1 = \n"+jsonStr);
		
		jsonStr = gson.toJson(productPriceInStore1);
		System.out.println("GsonTest.testClientSerialization() productPriceInStore1 = \n"+jsonStr);
		
		jsonStr = gson.toJson(bsdUserOrder);
		System.out.println("GsonTest.testClientSerialization() bsdUserOrder = \n"+jsonStr);

	}
	@Test
	public void testExtDataResponse(){
		ExtData response = new ExtData();
    	
    	List<BsdUser> users = new ArrayList<BsdUser>();
    	users.add(bsdUser);
    	users.add(bsdUser2);
    	response.add(users);//bsdUserDtos
    	response.setTotal(users.size());//bsdUserDtos
        response.setSuccess(true);
		String jsonStr = gson.toJson(response);
		System.out.println("GsonTest.testClientSerialization() ExtDataResponse = \n"+jsonStr);
	}
/*
 * http://stackoverflow.com/questions/18397342/deserializing-generic-types-with-gson
 *  -> Google Gson - deserialize list<class> object? (generic type) -> http://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type
 *  
 *  -> Gson fromJson to java class structure where property names are not known [duplicate] -> http://stackoverflow.com/questions/28548380/gson-fromjson-to-java-class-structure-where-property-names-are-not-known
 * Very Close!!! -> Using gson fromjson to parse sub classes -> http://stackoverflow.com/questions/23749161/using-gson-fromjson-to-parse-sub-classes?rq=1
 * Gson cheatsheet for Map, List и Array -> https://habrahabr.ru/post/253266/ 	
 */
	@Test
	public void testRequestParsing(){
		ExtData response = new ExtData();

		ProductInStoreJsonData requestProductInStore = gson.fromJson(priceInStoreJsonStr, ProductInStoreJsonData.class);

		logger.debug("GsonTest.testRequestParsing() jsonStr = \n"+ requestProductInStore);
		assertNotNull(requestProductInStore.getData());
		System.out.println("GsonTest.testRequestParsing() requestProductInStore.getData() = \n"+ requestProductInStore.getData());

	}
	/*class ProductInStoreJsonData extends ExtResponse {
		@JsonProperty("data")
		private List<ProductPriceInStore> data = new ArrayList<ProductPriceInStore>();
	    //private List<ProductDto> data = new ArrayList<ProductDto>();
		public List<ProductPriceInStore> getProducts() {return data;}
		public void setProducts(List<ProductPriceInStore> quotas) {this.data = quotas;}
	}*/

}

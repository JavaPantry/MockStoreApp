package org.avp.bsd.service;

import java.util.List;

import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.model.Address;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.model.StoreProductPK;
import org.avp.security.model.User;

public interface BsdService {

	public List<BsdUser> getBsdUsers();
	public List<Address> getAddresses();
	public List<Product> getProducts();
	public Product getProduct(String id);
	public void save(Product product);
	public void save(ProductPriceInStore productPriceInStore);
	
	public List<OrderHeader> getOrderHeaders();
	public List<OrderHeader> getOrderHeadersByUser(User user);
	public void save(OrderHeader orderHeader);

	public void save(Store store);
	
	public List<Store> getStores();
	
	public List<ProductDto> getProductPriceInStore(Long storeId);
	public List<ProductDto> getProductNotInStore(Long storeId);
	
	public ProductPriceInStore findProductPriceInStoreByPk(StoreProductPK pk);
	public ProductPriceInStore findProductPriceInStoreByStoreIdAndProductSku(Long storeId, String sku);
	public void updateProductsPricesInStore(Long storeId, List<ProductDto> products) throws Exception;
	public void deleteProductsFromStore(Long storeId, List<ProductDto> products) throws Exception;
	public Product findProductBySku(String sku);
	public Store findStoreById(Long id);
	
}

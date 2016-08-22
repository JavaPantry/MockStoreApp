package org.avp.bsd.service;

import java.util.List;

import org.avp.bsd.model.Address;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.repository.AddressRepository;
import org.avp.bsd.repository.OrderHeaderRepository;
import org.avp.bsd.repository.ProductRepository;
import org.avp.bsd.repository.StoreRepository;
import org.avp.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public interface BsdService {

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
}

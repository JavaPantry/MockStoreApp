package org.avp.bsd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.avp.bsd.model.Address;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.repository.AddressRepository;
import org.avp.bsd.repository.BsdUserRepository;
import org.avp.bsd.repository.OrderHeaderRepository;
import org.avp.bsd.repository.ProductPriceInStoreRepository;
import org.avp.bsd.repository.ProductRepository;
import org.avp.bsd.repository.StoreRepository;
import org.avp.quota.kpi.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service()
@Transactional
public class BsdServiceImpl implements BsdService {
	private static Logger logger = Logger.getLogger(BsdServiceImpl.class);
	
	@Autowired
	private BsdUserRepository bsdUserRepository;
	
	
	@Transactional(readOnly=true)
	public List<BsdUser> getBsdUsers(){
		return bsdUserRepository.findAll();
	}

	@Autowired
	private AddressRepository addressRepository;
	
	@Transactional(readOnly=true)
	public List<Address> getAddresses(){
		return addressRepository.findAll();
	}
	
	
	@Autowired
	private OrderHeaderRepository orderHeaderRepository;
	
	@Transactional(readOnly=true)
	public List<OrderHeader> getOrderHeaders(){
		return orderHeaderRepository.findAll();
	}
	
	@Transactional()
	public void save(OrderHeader orderHeader){
		orderHeaderRepository.save(orderHeader);
	}

	@Autowired
	private StoreRepository storeHeaderRepository;
	
	@Transactional(readOnly=true)
	public List<Store> getStores(){
		return storeHeaderRepository.findAll();
	}

	@Transactional()
	public void save(Store store){
		storeHeaderRepository.save(store);
	}

	@Autowired
	private ProductPriceInStoreRepository productPriceInStoreRepository;
	
	public void save(ProductPriceInStore productPriceInStore){
		productPriceInStoreRepository.save(productPriceInStore);
	}

	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly=true)
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Product getProduct(String id){
		return productRepository.findOne(id);
	}	
	
	@Transactional()
	public void save(Product product){
		productRepository.save(product);
	}
	
	
}

package org.avp.bsd.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.avp.bsd.dto.ProductDto;
import org.avp.bsd.model.Address;
import org.avp.bsd.model.BsdUser;
import org.avp.bsd.model.OrderHeader;
import org.avp.bsd.model.Product;
import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.Store;
import org.avp.bsd.model.StoreProductPK;
import org.avp.bsd.repository.AddressRepository;
import org.avp.bsd.repository.BsdUserRepository;
import org.avp.bsd.repository.OrderHeaderRepository;
import org.avp.bsd.repository.ProductPriceInStoreRepository;
import org.avp.bsd.repository.ProductRepository;
import org.avp.bsd.repository.StoreRepository;
import org.avp.security.model.User;
import org.avp.security.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service()
@Transactional
public class BsdServiceImpl implements BsdService {
	private static Logger logger = Logger.getLogger(BsdServiceImpl.class);
	
	@Autowired
	private CustomUserService userService;

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
	
	@Transactional(readOnly=true)
	public List<OrderHeader> getOrderHeadersByUser(User user){
		return orderHeaderRepository.findByUser(user);
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
	
	@Transactional()
	public List<ProductDto> getProductPriceInStore(Long storeId){
		List<ProductPriceInStore> productPricesInStore = productPriceInStoreRepository.findAll(findProductInStore(storeId));
		// !!! - very dummy implementation - !!!
		List<Product> products = new ArrayList<Product>();
		for (ProductPriceInStore productPriceInStore : productPricesInStore) {
			products.add(productPriceInStore.getPk().getProduct());
		}
		//List<ProductDto> productsInStore = DtoFactory.createProductDtoList(products);//new ArrayList<ProductDto>();
		List<ProductDto> productsInStore = DtoFactory.createProductDtoListFromProductPriceInStore(productPricesInStore);
		return productsInStore;
	}
	private Specification<ProductPriceInStore> findProductInStore( final Long storeId) {
		return new Specification<ProductPriceInStore>() {
			@Override
			public Predicate toPredicate(Root<ProductPriceInStore> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate storeIdPredicate = cb.equal(root.get("pk").get("store").get("id"), storeId);
				predicates.add(storeIdPredicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}
	
	/*
	 * Use findProductPriceInStoreByStoreIdAndProductSku(Long storeId, String sku) instead
	 */
	@Transactional()
	public ProductPriceInStore findProductPriceInStoreByPk(StoreProductPK pk){
		//StoreProductPK pk = new StoreProductPK();
		ProductPriceInStore ppis =  productPriceInStoreRepository.findOne(pk);
		return ppis;
	}
	/*
	 * Use findProductPriceInStoreByStoreIdAndProductSku(Long storeId, String sku)
	 * findProductPriceInStoreByPk(StoreProductPK pk)works but not used
	 */
	@Transactional()
	public ProductPriceInStore findProductPriceInStoreByStoreIdAndProductSku(Long storeId, String sku){
		ProductPriceInStore ppis =  productPriceInStoreRepository.findOne(findProductInStore( storeId, sku));
		return ppis;
	}

	public Product findProductBySku(String sku){
		return productRepository.findOne(sku);
	}
	public Store findStoreById(Long id){
		return storeHeaderRepository.findOne(id);
	}

	
	private Specification<ProductPriceInStore> findProductInStore( final Long storeId, final String sku) {
		return new Specification<ProductPriceInStore>() {
			@Override
			public Predicate toPredicate(Root<ProductPriceInStore> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate storeIdPredicate = cb.and(cb.equal(root.get("pk").get("store").get("id"), storeId), cb.equal(root.get("pk").get("product").get("sku"), sku));
				predicates.add(storeIdPredicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}
	
	@Transactional()
	public void updateProductsPricesInStore(Long storeId, List<ProductDto> products) throws Exception{

		Store store = storeHeaderRepository.findOne(storeId);
		if (store == null) {
			throw new Exception("Could not find '"+storeId+"' store id");
		}
		for (ProductDto productDto : products) {
			ProductPriceInStore productPriceInStore = findProductPriceInStoreByStoreIdAndProductSku(storeId, productDto.getSku());
			if(productPriceInStore == null){
				productPriceInStore = new ProductPriceInStore();
				Product product = productRepository.findOne(productDto.getSku());
				StoreProductPK pk = new StoreProductPK(store, product);
				productPriceInStore.setPk(pk);
			}
			// update from extended ProductDto fields
			productPriceInStore.setPrice(productDto.getPrice());
			productPriceInStore.setPriceSchedule(productDto.getPriceSchedule());
			productPriceInStore.setPriceScheduled(productDto.getPriceScheduled());
			productPriceInStoreRepository.save(productPriceInStore);
		}
	}
	
	@Transactional()
	public void deleteProductsFromStore(Long storeId, List<ProductDto> products) throws Exception{
		/*Store store = storeHeaderRepository.findOne(storeId);
		if (store == null) {
			throw new Exception("Could not find '"+storeId+"' store id");
		}*/
		for (ProductDto productDto : products) {
			ProductPriceInStore productPriceInStore = findProductPriceInStoreByStoreIdAndProductSku(storeId, productDto.getSku());
			if(productPriceInStore == null){
				continue;
			}
			Store store = productPriceInStore.getPk().getStore();
			Set<ProductPriceInStore> productsInStore = store.getProductsInStore();
			productsInStore.remove(productPriceInStore);
			productPriceInStoreRepository.delete(productPriceInStore);
			storeHeaderRepository.save(store);
		}
	}
	
	@Transactional()
	public List<ProductDto> getProductNotInStore(Long storeId){
		List<ProductPriceInStore> productPricesInStore = productPriceInStoreRepository.findAll(findProductInStore(storeId));
		List<String> skus = new ArrayList<String>();
		for (ProductPriceInStore productPriceInStore : productPricesInStore) {
			skus.add(productPriceInStore.getPk().getProduct().getSku());
		}
		List<Product> products = null;
		if (skus.isEmpty()){
			products = productRepository.findAll();
		}else{
			products = productRepository.findAll(findProductsNotInStore(skus));
		}
		List<ProductDto> productsNotInStore = DtoFactory.createProductDtoList(products); //new ArrayList<ProductDto>();
		return productsNotInStore;
	}
	private Specification<Product> findProductsNotInStore( final List<String> skus) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Collection<Predicate> predicates = new ArrayList<Predicate>();
				Predicate predicate = cb.not(root.get("sku").in(skus));
				predicates.add(predicate);
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}

}

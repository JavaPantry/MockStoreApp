package org.avp.bsd.repository;

import org.avp.bsd.model.ProductPriceInStore;
import org.avp.bsd.model.StoreProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPriceInStoreRepository extends JpaRepository<ProductPriceInStore, StoreProductPK>, JpaSpecificationExecutor<ProductPriceInStore> {

}

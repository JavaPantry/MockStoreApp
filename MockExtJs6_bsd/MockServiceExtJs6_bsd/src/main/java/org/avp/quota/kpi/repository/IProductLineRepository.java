package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductLineRepository extends JpaRepository<ProductLine, String>, JpaSpecificationExecutor<ProductLine> {

}

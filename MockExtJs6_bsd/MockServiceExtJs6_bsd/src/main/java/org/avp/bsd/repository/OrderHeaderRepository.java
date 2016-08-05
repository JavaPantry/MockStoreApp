package org.avp.bsd.repository;

import org.avp.bsd.model.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long>, JpaSpecificationExecutor<OrderHeader> {

}
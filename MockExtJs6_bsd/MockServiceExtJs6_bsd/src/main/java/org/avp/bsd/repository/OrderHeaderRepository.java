package org.avp.bsd.repository;

import java.util.List;

import org.avp.bsd.model.OrderHeader;
import org.avp.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long>, JpaSpecificationExecutor<OrderHeader> {
	public List<OrderHeader> findByUser(User user);
}
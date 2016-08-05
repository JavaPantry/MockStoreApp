package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.SalesRepEmployeeJoin;
import org.avp.quota.kpi.model.dao.SalesRepEmployeePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ISalesRepEmployeeJoinRepository extends JpaRepository<SalesRepEmployeeJoin, SalesRepEmployeePK>, JpaSpecificationExecutor<SalesRepEmployeeJoin> {

}

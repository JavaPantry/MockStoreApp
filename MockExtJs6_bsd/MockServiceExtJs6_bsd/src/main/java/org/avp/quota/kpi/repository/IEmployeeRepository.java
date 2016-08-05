package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.EmployeeDao;
import org.avp.quota.kpi.model.dao.QuotaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEmployeeRepository extends JpaRepository<EmployeeDao, String>, JpaSpecificationExecutor<EmployeeDao> {

}

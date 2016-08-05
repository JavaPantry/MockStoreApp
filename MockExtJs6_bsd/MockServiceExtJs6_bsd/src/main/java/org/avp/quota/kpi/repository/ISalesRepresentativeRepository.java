package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dao.TocDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ISalesRepresentativeRepository extends JpaRepository<SalesRepresentativeDao, String>, JpaSpecificationExecutor<SalesRepresentativeDao> {

}

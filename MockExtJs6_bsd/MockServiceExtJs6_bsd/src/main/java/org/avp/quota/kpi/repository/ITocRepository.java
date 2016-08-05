package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.TocDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITocRepository extends JpaRepository<TocDao, String>, JpaSpecificationExecutor<TocDao> {

}

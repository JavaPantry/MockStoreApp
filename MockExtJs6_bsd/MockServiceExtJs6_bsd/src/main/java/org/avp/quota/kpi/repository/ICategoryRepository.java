package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.CategoryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICategoryRepository extends JpaRepository<CategoryDao, String>, JpaSpecificationExecutor<CategoryDao> {

}

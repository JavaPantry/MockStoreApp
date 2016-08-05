package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.BudgetDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBudgetRepository extends JpaRepository<BudgetDao, Long>, JpaSpecificationExecutor<BudgetDao>, IQuotaRepositoryCustom {

}

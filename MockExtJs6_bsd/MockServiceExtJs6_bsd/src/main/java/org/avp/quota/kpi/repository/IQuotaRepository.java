package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.QuotaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;




public interface IQuotaRepository extends JpaRepository<QuotaDao, Long>, JpaSpecificationExecutor<QuotaDao>, IQuotaRepositoryCustom {
}

package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.QuotaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserRepository extends JpaRepository<QuotaUser, Long>, JpaSpecificationExecutor<QuotaUser> {
	public QuotaUser findByUserId(final String userId);
}

package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserRepository extends JpaRepository<UserDao, Long>, JpaSpecificationExecutor<UserDao> {
	public UserDao findByUserId(final String userId);
}

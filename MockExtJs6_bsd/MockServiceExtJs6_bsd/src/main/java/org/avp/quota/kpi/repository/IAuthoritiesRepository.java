package org.avp.quota.kpi.repository;

import org.avp.quota.kpi.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAuthoritiesRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {

}

package org.avp.security.repository;

import org.avp.security.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthoritiesRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {

}
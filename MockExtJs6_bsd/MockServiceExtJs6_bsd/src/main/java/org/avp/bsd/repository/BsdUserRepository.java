package org.avp.bsd.repository;

import org.avp.bsd.model.BsdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface BsdUserRepository extends JpaRepository<BsdUser, Long>, JpaSpecificationExecutor<BsdUser> {

}

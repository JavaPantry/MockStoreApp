package org.avp.bsd.repository;

import org.avp.bsd.model.BsdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*
 * TODO - <AP>: do I really need this custom repository while evrything supported by UserRepository and/or UserService from /org/avp/security/service
 */
public interface BsdUserRepository extends JpaRepository<BsdUser, Long>, JpaSpecificationExecutor<BsdUser> {

}

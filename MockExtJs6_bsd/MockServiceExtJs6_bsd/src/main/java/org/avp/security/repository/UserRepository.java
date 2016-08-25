package org.avp.security.repository;

import org.avp.bsd.model.BsdUser;
import org.avp.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    public User findByEmail(String email);
    public User findByUserId(String userId);
    @Override 
    public void delete(User user);

}

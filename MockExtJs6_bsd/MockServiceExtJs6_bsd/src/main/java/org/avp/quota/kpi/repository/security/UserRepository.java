package org.avp.quota.kpi.repository.security;

import org.avp.quota.kpi.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findByUserId(String userId);
    @Override 
    public void delete(User user);

}

package org.avp.security.repository;

import org.avp.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findByUserId(String userId);
    @Override 
    public void delete(User user);

}

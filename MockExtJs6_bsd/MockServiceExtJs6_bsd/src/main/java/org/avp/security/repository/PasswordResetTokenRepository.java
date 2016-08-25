package org.avp.security.repository;

import org.avp.security.model.PasswordResetToken;
import org.avp.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

}

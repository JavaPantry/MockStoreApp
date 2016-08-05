package org.avp.quota.kpi.repository.security;

import org.avp.quota.kpi.model.security.PasswordResetToken;
import org.avp.quota.kpi.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

}

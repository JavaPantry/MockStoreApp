package org.avp.quota.kpi.repository.security;

import org.avp.quota.kpi.model.security.User;
import org.avp.quota.kpi.model.security.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}

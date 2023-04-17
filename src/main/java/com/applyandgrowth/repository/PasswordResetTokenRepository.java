package com.applyandgrowth.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.applyandgrowth.models.PasswordResetToken;
import com.applyandgrowth.models.User;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUsuario(User usuario);

    List<PasswordResetToken> findAllByDataExpiracaoBefore(Date now);

    @Modifying
    @Query("delete from PasswordResetToken t where t.dataExpiracao <= ?1")
    void deleteAllExpiredSince(Date now);
}


package com.example.timesheet.repository;

import com.example.timesheet.model.PasswordResetToken;
import com.example.timesheet.model.Userbase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, String> {
    PasswordResetToken findByUser_IdUser(Double idUser);

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(Userbase user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    /*@Modifying
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);*/
}

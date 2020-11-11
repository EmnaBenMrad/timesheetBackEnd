package com.example.timesheet.service;


import com.example.timesheet.model.PasswordResetToken;
import com.example.timesheet.model.Userbase;
import com.example.timesheet.repository.PasswordResetTokenRepository;
import com.example.timesheet.repository.UserbaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserbaseService {
    @Autowired
    private UserbaseRepository userbaseRepository;
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    /*public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";*/
    public Userbase findByIdUser1(Double idUser) {
        return userbaseRepository.findByIdUser(idUser);
    }

    public Userbase findByUserName(String username) {
        return userbaseRepository.findUserbaseByUsername(username);
    }

    public PasswordResetToken generateNewVerificationToken(final String existingVerificationToken) {
        PasswordResetToken vToken = passwordTokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = passwordTokenRepository.save(vToken);
        return vToken;
    }

    public PasswordResetToken createPasswordResetTokenForUser(final Userbase user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
        return myToken;
    }

    public Optional<Userbase> getUserByPasswordResetToken(final String token) {
        return Optional.ofNullable(passwordTokenRepository.findByToken(token).getUser());
    }

    public void changeUserPassword(final Userbase user, final String password) {
        user.setPasswordHash(passwordEncoder.encode(password));
        userbaseRepository.save(user);
    }

    public boolean checkIfValidOldPassword(final Userbase user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPasswordHash());
    }

    public boolean checkIfValidPassword(String password1, String password2) {
        return password1.matches(password2);
    }

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

}

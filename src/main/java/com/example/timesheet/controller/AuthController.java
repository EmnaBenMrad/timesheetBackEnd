package com.example.timesheet.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.timesheet.exceptions.InvalidOldPasswordException;
import com.example.timesheet.model.*;
import com.example.timesheet.payload.request.SignupRequest;
import com.example.timesheet.service.DTO.PasswordDto;
import com.example.timesheet.service.MembershipbaseService;
import com.example.timesheet.service.PropertyUserService;
import com.example.timesheet.service.UserbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.example.timesheet.payload.request.LoginRequest;
import com.example.timesheet.payload.response.JwtResponse;
import com.example.timesheet.payload.response.MessageResponse;
import com.example.timesheet.repository.UserbaseRepository;
import com.example.timesheet.security.jwt.JwtUtils;
import com.example.timesheet.security.services.UserDetailsImpl;
import org.springframework.web.servlet.ModelAndView;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserbaseRepository userRepository;
    @Autowired
    PropertyUserService propertyUserService;
    @Autowired
    UserbaseService userbaseService;
    @Autowired
    MembershipbaseService membershipbaseService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;


    /***Se connecter avec la création de jwt***/
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        //authenticate { username, pasword }
        UsernamePasswordAuthenticationToken userX = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPasswordHash());
        Authentication authentication = authenticationManager.authenticate(userX);
        //update SecurityContext using Authentication object
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //generate JWT
        String jwt = jwtUtils.generateJwtToken(authentication);
        //get UserDetails from Authentication object
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = membershipbaseService.getGroupName(userDetails.getUsername()).stream()
                .map(Membershipbase::getGroupbase)
                .map(Groupbase::getGroupname)
                .collect(Collectors.toList());
        String username = propertyUserService.getUserName(userDetails.getUsername()).entrySet().stream().findFirst().get().getKey();
        //response contains JWT and UserDetails data
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getIdUser(),
                userDetails.getUsername(),
                username,
                roles
        ));
    }

    /***Créer un nouveau user***/
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        // Create new user's account
        Userbase user = new Userbase(signUpRequest.getUsername(),
                //signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPasswordHash()));
        user.setUsername(signUpRequest.getUsername());
        user.setPasswordHash(passwordEncoder.encode(signUpRequest.getPasswordHash()));
        user.setIdUser(2000.0);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    /***Créer un token et envoyer un lien de réintialisation***/
    @PostMapping("/resetPassword")
    public PasswordResetToken resetPassword(HttpServletRequest request,
                                            @RequestParam("username") String username,
                                            HttpServletResponse response) throws IOException {
        PasswordResetToken passwordResetToken;
        if (username.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Saisissez un login!");
            return null;
        } else {
            Userbase user = userbaseService.findByUserName(username);
            if (user != null) {
                String userEmail = propertyUserService.getUserEmail(username).get(0);
                //token pour voir l'unicité
                String token = UUID.randomUUID().toString();
                //creer un token pour l'utilisateur en question
                passwordResetToken = userbaseService.createPasswordResetTokenForUser(user, token);
                //Envoyer le lien de réintialisation au mail de l'utilisateur en question
                mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, userEmail));
                return passwordResetToken;
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ce login n'existe pas!");
                return null;
            }
        }


    }

    /***Lien mail --> Redirection vers la page de modification ou vers la page d'erreur***/
    @GetMapping("/changePassword")
    public ModelAndView showChangePasswordPage(Model model,
                                               @RequestParam("token") String token) {
        String result = userbaseService.validatePasswordResetToken(token);
        if (result != null && result.equals("expired")) {
            String projectUrl = "http://localhost:3000/error";
            return new ModelAndView("redirect:" + projectUrl);
        } else {
            model.addAttribute("token", token);
            String projectUrl = "http://localhost:3000/updatePassword/" + token;
            return new ModelAndView("redirect:" + projectUrl);
        }
    }


    /*** Change user password***/
    @PostMapping("/updatePassword")
    public void changeUserPassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final Userbase user = userbaseService.findByIdUser1(((Userbase) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser());
        if (!userbaseService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        userbaseService.changeUserPassword(user, passwordDto.getNewPassword());
        // return new GenericResponse(messages.getMessage("Password updated successfully", null, locale));
    }

    /***Enregistrer le nouveau mot de passe***/
    @PostMapping("/savePassword")
    public void savePassword(final Locale locale, @RequestParam("token") String token, @Valid @RequestParam("password") String password, @Valid @RequestParam("passwordConfirm") String passwordConfirm, HttpServletResponse response) throws IOException {
        if (!userbaseService.checkIfValidPassword(password, passwordConfirm)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les mots de passe saisis ne sont pas identiques!");
            //throw new InvalidOldPasswordException();
        } else if (password.isEmpty() && passwordConfirm.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Entrer un mot de passe!");
        } else {
            Optional<Userbase> user = userbaseService.getUserByPasswordResetToken(token);
            if (user.isPresent() && userbaseService.checkIfValidPassword(password, passwordConfirm)) {
                userbaseService.changeUserPassword(user.get(), password);
            }
        }
    }


    // ============== NON-API ============

    /***Le message du mail***/
    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final String userEmail) {
        final String url = contextPath + "/api/auth/changePassword?token=" + token;
        String url2 = "http://localhost:3000/resetPassword";
        return constructEmail("Bonjour," + " \r\n" + "Nous avons reçu votre demande de récupération de l'accès au compte Time&Decision. " + " \r\n" + "Veuillez cliquer sur le lien ci-dessous pour changer votre mot de passe. " + " \r\n" + url + "\n" + "Afin de protéger votre compte, ce lien expirera dans 2 heures. Passé ce délai, vous devrez envoyer une nouvelle demande à l'adresse " + url2
                        + " \r\n" + " \r\n" +
                        "Cordialement,\n" +
                        "L'équipe Business&Decision.",
                userEmail);
    }

    /***Fonction pour construire le mail***/
    private SimpleMailMessage constructEmail(String body, String mail) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Demande de réinitialisation du mot de passe associé à votre compte Time&Decision");
        email.setText(body);
        email.setTo(mail);
        email.setFrom("timedecisionbd@gmail.com");
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
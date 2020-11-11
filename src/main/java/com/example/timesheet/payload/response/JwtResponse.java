package com.example.timesheet.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private Double idUser;
    private String login;
    private String username;
    //private String email;
    private List<String> roles;

    //public JwtResponse(String accessToken, String id, String username,  List<String> roles) {
    public JwtResponse(String accessToken, String id, Double idUser, String login, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.idUser = idUser;
        this.login = login;
        this.username = username;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getIdUser() {
        return idUser;
    }

    public void setIdUser(Double idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}


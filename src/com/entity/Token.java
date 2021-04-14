package com.entity;

public class Token {
    private String token;
    Boolean isLogin;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public Token(String token, Boolean isLogin) {
        this.token = token;
        this.isLogin = isLogin;
    }
}

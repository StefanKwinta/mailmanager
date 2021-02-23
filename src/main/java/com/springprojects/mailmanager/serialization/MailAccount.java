package com.springprojects.mailmanager.serialization;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class MailAccount {
    @Id private String login;
    private String password;
    private String address;

    public MailAccount(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public MailAccount() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof MailAccount))
            return false;
        MailAccount mailAccount = (MailAccount) o;
        return Objects.equals(this.login, mailAccount.login)
                && Objects.equals(this.password, mailAccount.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.login, this.login);
    }

    @Override
    public String toString() {
        return "MailAccount{login='" + this.login + '\''
                + '\'' + ", address='" + this.address + '\'' + '}';
    }
}

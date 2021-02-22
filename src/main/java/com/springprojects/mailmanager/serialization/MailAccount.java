package com.springprojects.mailmanager.serialization;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class MailAccount {
    private String login;
    private String password;
    private String address;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public MailAccount(String login, String password,long id) {
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public MailAccount() {
        id = Long.valueOf(8);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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


    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof MailAccount))
            return false;
        MailAccount employee = (MailAccount) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.login, employee.login)
                && Objects.equals(this.password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.login);
    }

    @Override
    public String toString() {
        return "MailAccount{" + "id=" + this.id + ", login='" + this.login + '\'' + ", password='" + this.password
                + '\'' + ", address='" + this.address + '\'' + '}';
    }
}

package com.leumi.coupon_project.helpers;

import java.util.Objects;

public class Credentials {

    private String email;
    private String password;
    private LoginManager.ClientType clientType;

    public Credentials() {
        super();
    }

    public Credentials(String email, String password, LoginManager.ClientType clientType) {
        super();
        this.email = email;
        this.password = password;
        this.clientType = clientType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginManager.ClientType getClientType() {
        return clientType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credentials)) return false;
        Credentials that = (Credentials) o;
        return getEmail().equals(that.getEmail()) && getPassword().equals(that.getPassword()) && clientType == that.clientType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), clientType);
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", clientType=" + clientType +
                '}';
    }

    //    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((email == null) ? 0 : email.hashCode());
//        result = prime * result + ((password == null) ? 0 : password.hashCode());
//        result = prime * result + ((clientType == null) ? 0 : clientType.hashCode());
//        return result;
//    }
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Credentials other = (Credentials) obj;
//        if (email == null) {
//            if (other.email != null)
//                return false;
//        } else if (!email.equals(other.email))
//            return false;
//        if (password == null) {
//            if (other.password != null)
//                return false;
//        } else if (!password.equals(other.password))
//            return false;
//        if (role == null) {
//            if (other.role != null)
//                return false;
//        } else if (!role.equals(other.role))
//            return false;
//        return true;
//    }
//    @Override
//    public String toString() {
//        return "Credentials [email=" + email + ", pwd=" + password + ", role=" + role + "]";
//    }


}

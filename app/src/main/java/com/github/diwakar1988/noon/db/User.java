package com.github.diwakar1988.noon.db;

/**
 * Created by 'Diwakar Mishra' on 17,November,2018
 */
public class User {
    private String name;
    private String countryISO;
    private String phoneCode;
    private String number;
    /**
     * Encrypted password
     */
    private String password;
    private String email;
    private boolean uploadedNationalId;

    public User(String name,String countryISO, String phoneCode, String number, String password) {
        this.name = name;
        this.countryISO = countryISO;
        this.phoneCode = phoneCode;
        this.number = number;
        //Encrypt password before saving
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getCountryISO() {
        return countryISO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public boolean isUploadedNationalId() {
        return uploadedNationalId;
    }

    public void setUploadedNationalId(boolean uploadedNationalId) {
        this.uploadedNationalId = uploadedNationalId;
    }
}

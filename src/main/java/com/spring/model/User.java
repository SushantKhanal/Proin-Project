package com.spring.model;

import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

import java.util.Date;

//@Getter
//@Setter
@AllArgsConstructor
public class User {
//    vm.user={id: null, firstName:'', lastName:'', bio:'', nation:'', clientType:'', username:'', password:'', address:'',email:'',agenda:'',academics: '',experience: '', marketDomain: ''};

    private Long id;

    private String firstName;

    private String lastName;

    private String dob;

    private String bio;

    private String nation;

    private String clientType;

    private String username;

    private String password;

    private String address;

    private String email;

    private String agenda;

    private String joinDate;

    private String academics;

    private String experience;

    private String marketDomain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }


    public String getAcademics() {
        return academics;
    }

    public void setAcademics(String academics) {
        this.academics = academics;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getMarketDomain() {
        return marketDomain;
    }

    public void setMarketDomain(String marketDomain) {
        this.marketDomain = marketDomain;
    }



    public User() {
        id = 0L;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", bio='" + bio + '\'' +
                ", nation='" + nation + '\'' +
                ", clientType='" + clientType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", agenda='" + agenda + '\'' +
                ", joinDate=" + joinDate +
                ", academics='" + academics + '\'' +
                ", experience='" + experience + '\'' +
                ", marketDomain='" + marketDomain + '\'' +
                '}';
    }
}


package com.spring.model;


public class User {
//        vm.user={id: null, clientType:'', username:'',address:'',email:'',agenda:'',academics: '',experience: '', marketDomain: ''};
    private long id;

    private String clientType;

    private String username;

    private String address;

    private String email;

    private String agenda;

    private String academics;

    private String experience;

    private String marketDomain;


    public User(){
        id=0;
    }


    public User(long id, String clientType, String username, String address, String email, String agenda, String academics, String experience, String marketDomain) {
        this.id = id;
        this.clientType = clientType;
        this.username = username;
        this.address = address;
        this.email = email;
        this.agenda = agenda;
        this.academics = academics;
        this.experience = experience;
        this.marketDomain = marketDomain;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", address=" + address
                + ", email=" + email + "]";
    }



}

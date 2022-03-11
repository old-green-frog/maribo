package com.networks.maribo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.networks.maribo.service.mappers.MariboUserMapper;
import org.springframework.jdbc.core.RowMapper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class MariboUser extends Model {

    protected int id;
    private int role_id;
    private String name, email, password;
    private Date birthday;

    public static String allQuery = "SELECT * FROM MariboUser";

    public MariboUser() {}
    public MariboUser(int id) {
        this.id = id;

        this.oneQuery = String.format("SELECT * FROM MariboUser WHERE id='%s'", this.id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int num) {
        this.id = num;
        this.updateField("id", num);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.updateField("name", name);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.updateField("email", email);
    }

    public int getAge() {
        return Period.between(
                LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(this.birthday)),
                LocalDateTime.now().toLocalDate()
        ).getYears();
    }

    public String getRole() {
        return Role.fromId(this.role_id).getRole();
    }

    public void setRole_id(int role) {
        this.role_id = role;
        this.updateField("role_id", role);
    }

    public void setBirthday(Date newDate) {
        this.birthday = newDate;
        this.updateField("birthday", newDate);
    }

    public void setPassword(String passwd) {
        this.password = passwd;
        this.updateField("password", passwd);
    }

    public String toString() {
        return String.format("MariboUser(%s, %s, %s, %s)", id, name, email, birthday);
    }
}

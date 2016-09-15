package com.matsnowak.piguard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by Mateusz Nowak on 01.09.2016.
 */
@Entity
public class Settings {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer exitDelay; // TODO add validation positives and max

    @Column(nullable = false)
    private Integer disarmDelay;// TODO add validation positives and max

    @Column(nullable = false)
    @JsonIgnore
    private String pass;

    @Column(nullable = false)
    @JsonIgnore
    private String username;

    public Integer getId() {
        return id;
    }

    public Integer getExitDelay() {
        return exitDelay;
    }

    public void setExitDelay(Integer exitDelay) {
        this.exitDelay = exitDelay;
    }

    public Integer getDisarmDelay() {
        return disarmDelay;
    }

    public void setDisarmDelay(Integer disarmDelay) {
        this.disarmDelay = disarmDelay;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settings settings = (Settings) o;
        return Objects.equals(getId(), settings.getId()) &&
                Objects.equals(getExitDelay(), settings.getExitDelay()) &&
                Objects.equals(getDisarmDelay(), settings.getDisarmDelay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getExitDelay(), getDisarmDelay());
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", exitDelay=" + exitDelay +
                ", disarmDelay=" + disarmDelay +
                '}';
    }
}

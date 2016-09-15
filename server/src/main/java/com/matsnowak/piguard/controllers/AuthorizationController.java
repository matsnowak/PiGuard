package com.matsnowak.piguard.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matsnowak.piguard.main.ApiUrls;
import com.matsnowak.piguard.model.Settings;
import com.matsnowak.piguard.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz Nowak on 15.09.2016.
 */

@RestController
@RequestMapping(ApiUrls.API_AUTH)
public class AuthorizationController {

    @Autowired
    SettingsRepository settingsRepository;

    @RequestMapping("login")
    public String login() {
        return "Login OK";
    }

    @RequestMapping("logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Logout OK";
    }

    @RequestMapping(value = "changepin", method = RequestMethod.POST)
    public ResponseEntity<String> changePin(@RequestBody PinChange pinChange) {
        Settings one = settingsRepository.findOne(1);
        if (one == null) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Can not find old pin");
        }
        if (!pinChange.getOldPin().equals(one.getPass())) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Old pin not match");
        }

        one.setPass(pinChange.getNewPin());
        settingsRepository.save(one);
        return ResponseEntity.status(HttpStatus.OK).body("Pin changed");
    }

    public static class PinChange {
        private String oldPin;
        private String newPin;

        public PinChange() {
        }

        public PinChange(@JsonProperty("oldPin") String oldPin, @JsonProperty("newPin")String newPin) {
            this.oldPin = oldPin;
            this.newPin = newPin;
        }

        public String getOldPin() {
            return oldPin;
        }

        public void setOldPin(String oldPin) {
            this.oldPin = oldPin;
        }

        public String getNewPin() {
            return newPin;
        }

        public void setNewPin(String newPin) {
            this.newPin = newPin;
        }
    }
}

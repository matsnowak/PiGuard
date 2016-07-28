package com.matsnowak.smartalarm.validators;

import com.matsnowak.smartalarm.model.CommunicationSlot;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Mateusz on 28.07.2016.
 */
public class BeforeSaveSlotValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CommunicationSlot.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}

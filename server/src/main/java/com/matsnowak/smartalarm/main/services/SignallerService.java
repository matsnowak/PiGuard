package com.matsnowak.smartalarm.main.services;

import com.matsnowak.smartalarm.main.repositories.SignallerRepository;
import com.matsnowak.smartalarm.model.Signaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 24.06.2016.
 */
@Service
public class SignallerService {

    @Autowired
    SignallerRepository signallers;

    public List<Signaller> findAll() {
        return signallers.findAll();
    }

    public Optional<Signaller> find(Integer id) {
        return signallers.findById(id);
    }

    public Signaller add(Signaller newSignaller) {
        return signallers.save(newSignaller);
    }

    public void delete(Signaller signaller) {
        signallers.delete(signaller);
    }
}

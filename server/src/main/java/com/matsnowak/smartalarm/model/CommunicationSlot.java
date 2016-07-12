package com.matsnowak.smartalarm.model;

import javax.persistence.*;

/**
 * Created by Mateusz on 19.06.2016.
 */
@Entity
public class CommunicationSlot {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CommunicationSlotAddress address;

    @Column(nullable = false)
    private CommunicationSlotState state;

    public void setState(CommunicationSlotState state) {
        this.state = state;
    }


    public CommunicationSlot(CommunicationSlotAddress address, CommunicationSlotState state) {
        this.address = address;
        this.state = state;
    }

    protected CommunicationSlot() {}

    public CommunicationSlotAddress getAddress() {
        return address;
    }

    public CommunicationSlotState getState() {
        return state;
    }

    public Integer getId() {
        return id;
    }



}

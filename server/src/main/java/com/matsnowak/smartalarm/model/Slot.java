package com.matsnowak.smartalarm.model;

import javax.persistence.*;

/**
 * Created by Mateusz on 19.06.2016.
 */
@Entity
public class Slot {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private SlotAddress address;

    @Column(nullable = false)
    private SlotState state;

    public void setState(SlotState state) {
        this.state = state;
    }


    public Slot(SlotAddress address, SlotState state) {
        this.address = address;
        this.state = state;
    }

    protected Slot() {}

    public SlotAddress getAddress() {
        return address;
    }

    public SlotState getState() {
        return state;
    }

    public Integer getId() {
        return id;
    }



}

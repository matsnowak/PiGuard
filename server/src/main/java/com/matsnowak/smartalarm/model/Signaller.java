package com.matsnowak.smartalarm.model;

import javax.persistence.*;

/**
 * Created by Mateusz on 24.06.2016.
 */
@Entity
public class Signaller {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;


    @OneToOne()
    @JoinColumn(name = "slotKey")
    private Slot slot;

    protected Signaller() {}

    public Signaller(String name, Slot slot) {
        this.name = name;
        this.slot = slot;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}

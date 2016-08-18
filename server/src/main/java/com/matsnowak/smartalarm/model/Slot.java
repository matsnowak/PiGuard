package com.matsnowak.smartalarm.model;

import javax.persistence.*;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(getId(), slot.getId()) &&
                getAddress() == slot.getAddress() &&
                getState() == slot.getState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getState());
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", address=" + address +
                ", state=" + state +
                '}';
    }
}

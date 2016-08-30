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


    // TODO should be deprecated
    @Column(nullable = false)
    private SlotMode mode;


    @Column(nullable = true)
    private String description;

    public Slot(SlotAddress address, SlotMode mode) {
        this.address = address;
        this.mode = mode;
    }
    public Slot(SlotAddress address, SlotMode state, String description) {
        this(address, state);
        this.setDescription(description);
    }


    protected Slot() {}

    public Integer getId() {
        return id;
    }

    public SlotMode getMode() {
        return mode;
    }

    public void setMode(SlotMode mode) {
        this.mode = mode;
    }


    public SlotAddress getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(getId(), slot.getId()) &&
                getAddress() == slot.getAddress() &&
                getMode() == slot.getMode() &&
                Objects.equals(getDescription(), slot.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getMode(), getDescription());
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", address=" + address +
                ", mode=" + mode +
                ", description='" + description + '\'' +
                '}';
    }
}

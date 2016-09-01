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



    @Column(nullable = true)
    private String description;

    public Slot(SlotAddress address) {
        this.address = address;
    }
    public Slot(SlotAddress address, String description) {
        this(address);
        this.setDescription(description);
    }


    protected Slot() {}

    public Integer getId() {
        return id;
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
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", address=" + address +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(getId(), slot.getId()) &&
                getAddress() == slot.getAddress() &&
                Objects.equals(getDescription(), slot.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getDescription());
    }

}

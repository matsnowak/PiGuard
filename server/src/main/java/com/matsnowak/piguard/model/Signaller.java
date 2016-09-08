package com.matsnowak.piguard.model;

import javax.persistence.*;
import java.util.Objects;

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

    private Integer slotId;

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

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }


    @Override
    public String toString() {
        return "Signaller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slot=" + slot +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Signaller signaller = (Signaller) o;
        return Objects.equals(getId(), signaller.getId()) &&
                Objects.equals(getName(), signaller.getName()) &&
                Objects.equals(getSlot(), signaller.getSlot());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSlot());
    }
}

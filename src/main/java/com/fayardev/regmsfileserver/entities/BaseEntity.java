package com.fayardev.regmsfileserver.entities;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    protected int ID;

    protected BaseEntity() {
        super();
    }

    public int getId() {
        return this.ID;
    }

    public abstract String toString();
}

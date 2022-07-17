package com.fayardev.regmsfileserver.entities;

public class BlankEntity extends BaseEntity {

    public BlankEntity() {
        super.ID = -1;
    }

    @Override
    public String toString() {
        return "-1";
    }
}

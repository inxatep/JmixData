package com.sample.carmarket.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Status implements EnumClass<String> {

    IN_STOCK("В наличии"),
    SOLD("Продано");

    private String id;

    Status(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Status fromId(String id) {
        for (Status at : Status.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
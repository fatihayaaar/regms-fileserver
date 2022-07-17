package com.fayardev.regmsfileserver.repositories.abstracts;

import com.fayardev.regmsfileserver.entities.BaseEntity;

public interface IUserRepository<T extends BaseEntity> extends IRepository<T> {

    BaseEntity getEntityByUsername(String username);
}

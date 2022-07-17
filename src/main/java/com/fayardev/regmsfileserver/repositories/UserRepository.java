package com.fayardev.regmsfileserver.repositories;

import com.fayardev.regmsfileserver.entities.BaseEntity;
import com.fayardev.regmsfileserver.entities.User;
import com.fayardev.regmsfileserver.repositories.abstracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class UserRepository extends BaseRepository<User> implements IUserRepository<User> {

    @Autowired
    protected UserRepository(EntityManager entityManager) {
        super(entityManager);
        super.setClazz(User.class);
    }

    @Override
    @Transactional
    public BaseEntity getEntityByUsername(String username) {
        return super.listToEntity(this.session
                .getNamedQuery("User.findByUsername")
                .setParameter("username", username)
                .list());
    }
}

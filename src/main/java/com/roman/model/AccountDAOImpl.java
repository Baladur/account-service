package com.roman.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by roman on 18.06.2017.
 */
@Repository("accountDAOImpl")
@Slf4j
public class AccountDAOImpl extends AbstractDao<Long, Account> implements AccountDAO {

    @Override
    public Account findAccountById(Long id) {
        log.debug("Find account by id: {}.", id);
        return getByKey(id);
    }

    @Override
    public void updateAccount(Account account) {
        getSession().saveOrUpdate(account);
    }
}

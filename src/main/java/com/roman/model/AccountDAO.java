package com.roman.model;

/**
 * Created by roman on 17.06.2017.
 */
public interface AccountDAO {
    Account findAccountById(Long id);
    void updateAccount(Account account);
}

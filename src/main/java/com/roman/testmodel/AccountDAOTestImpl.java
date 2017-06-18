package com.roman.testmodel;

import com.roman.model.Account;
import com.roman.model.AccountDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by roman on 17.06.2017.
 */
@Repository("testAccountDAO")
public class AccountDAOTestImpl implements AccountDAO {
    private List<Account> accounts = new ArrayList<>(Arrays.asList(
            new Account(1L, "Pupkin", "Vasya", "Sergeevich", 504000f),
            new Account(2L, "Pupkin", "Alexander", "Sergeevich", 204954f),
            new Account(3L, "Savin", "Vasya", "Olegovich", 444540f),
            new Account(4L, "Weesly", "Ronald", "", 100000f),
            new Account(5L, "Sawyer", "Tom", "", 33f)
    ));

    @Override
    public Account findAccountById(Long id) {
        return accounts.stream().filter(account -> account.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void updateAccount(Account account) {
        Account fromList = findAccountById(account.getId());
        fromList.setSum(account.getSum());
    }
}

package com.roman.api;

import com.roman.model.Account;
import com.roman.model.AccountDAO;
import com.roman.model.Operation;
import com.roman.model.OperationDAO;
import com.roman.util.LogicException;
import com.roman.util.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by roman on 17.06.2017.
 */
@Service("accountService")
@Transactional
@Slf4j
public class AccountService {
    @Autowired @Qualifier("accountDAOImpl") private AccountDAO accountDAO;
    @Autowired @Qualifier("operationDAOImpl") private OperationDAO operationDAO;

    public Account findAccountById(Long id) {
        return accountDAO.findAccountById(id);
    }

    public List<Operation> getOperationsOfAccount(Long accId) throws LogicException {
        return operationDAO.getOperationsOfAccount(accId);
    }

    public void performOperation(Operation operation) throws LogicException {
        log.debug("Perform operation.");
        Account source = accountDAO.findAccountById(operation.getSourceAccId());
        Account dest = accountDAO.findAccountById(operation.getDestAccId());
        float sum = operation.getSum();
        if (source == null || dest == null) {
            throw new LogicException(Messages.ACCOUNT_NOT_FOUND);
        }
        if (source.getSum() - sum < 0) {
            throw new LogicException(Messages.INSUFFICIENT_ACCOUNT_SUM);
        }
        source.setSum(source.getSum() - sum);
        dest.setSum(dest.getSum() + sum);

        accountDAO.updateAccount(source);
        accountDAO.updateAccount(dest);
        operation.setDate(Date.valueOf(LocalDate.now()));
        operationDAO.insertOperation(operation);
    }
}

package com.roman.api;

import com.roman.model.Account;
import com.roman.model.Operation;
import com.roman.util.LogicException;
import com.roman.util.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by roman on 17.06.2017.
 */
@RestController
@Slf4j
public class MainController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/accounts/{acc_id}", method = RequestMethod.GET)
    public ResponseEntity findAccById(@PathVariable("acc_id") Long accId) {
        Account account = accountService.findAccountById(accId);
        if (account == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/plain; charset=utf-8");
            return new ResponseEntity(Messages.ACCOUNT_NOT_FOUND, headers, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/operations/{acc_id}", method = RequestMethod.GET)
    public ResponseEntity getOperationsOfAccount(@PathVariable("acc_id") Long accId) {
        try {
            List<Operation> operations = accountService.getOperationsOfAccount(accId);
            return new ResponseEntity(operations, HttpStatus.OK);
        } catch (LogicException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/plain; charset=utf-8");
            if (e.getMessage().equals(Messages.ACCOUNT_NOT_FOUND)) {
                return new ResponseEntity(Messages.ACCOUNT_NOT_FOUND, headers, HttpStatus.NOT_FOUND);
            } else {
                log.error(e.getMessage());
                return new ResponseEntity(Messages.INTERNAL_ERROR, headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @RequestMapping(value = "/operations/new", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity performOperation(@RequestBody Operation operation) {
        try {
            accountService.performOperation(operation);
            return new ResponseEntity(Messages.OPERATION_OK, HttpStatus.OK);
        } catch (LogicException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/plain; charset=utf-8");
            if (e.getMessage().equals(Messages.ACCOUNT_NOT_FOUND)) {
                return new ResponseEntity(Messages.ACCOUNT_NOT_FOUND, headers, HttpStatus.NOT_FOUND);
            } else if (e.getMessage().equals(Messages.INSUFFICIENT_ACCOUNT_SUM)) {
                return new ResponseEntity(Messages.INSUFFICIENT_ACCOUNT_SUM, headers, HttpStatus.CONFLICT);
            } else {
                log.error(e.getMessage());
                return new ResponseEntity(Messages.INTERNAL_ERROR, headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}

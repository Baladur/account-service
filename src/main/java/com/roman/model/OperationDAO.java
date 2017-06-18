package com.roman.model;

import com.roman.util.LogicException;

import java.util.List;

/**
 * Created by roman on 17.06.2017.
 */
public interface OperationDAO {
    List<Operation> getOperationsOfAccount(Long accId) throws LogicException;
    void insertOperation(Operation operation);
}

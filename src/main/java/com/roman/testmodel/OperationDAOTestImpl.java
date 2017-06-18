package com.roman.testmodel;

import com.roman.model.Operation;
import com.roman.model.OperationDAO;
import com.roman.util.LogicException;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman on 17.06.2017.
 */
@Repository("testOperationDAO")
public class OperationDAOTestImpl implements OperationDAO {
    public List<Operation> operations = new ArrayList<>(Arrays.asList(
            new Operation(1L, 1L, 2L, 30000f, Date.valueOf(LocalDate.of(2004,3,5))),
            new Operation(2L, 1L, 3L, 3000f, Date.valueOf(LocalDate.of(2005,3,5))),
            new Operation(3L, 4L, 1L, 40000f, Date.valueOf(LocalDate.of(2006,3,5))),
            new Operation(4L, 5L, 2L, 50000f, Date.valueOf(LocalDate.of(2007,3,5))),
            new Operation(5L, 5L, 1L, 60000f, Date.valueOf(LocalDate.of(2008,3,5)))
    ));

    @Override
    public List<Operation> getOperationsOfAccount(Long accId) throws LogicException {
        return operations.stream().filter(operation -> operation.getSourceAccId().equals(accId)).collect(Collectors.toList());
    }

    @Override
    public void insertOperation(Operation operation) {
        Long operationId = getMaxId() + 1;
        operation.setId(operationId);
        operations.add(operation);
    }

    private Long getMaxId() {
        return operations.stream().max((op1, op2) -> op1.getId().compareTo(op2.getId())).get().getId();
    }
}

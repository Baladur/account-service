package com.roman.model;

import com.roman.util.LogicException;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by roman on 18.06.2017.
 */
@Repository("operationDAOImpl")
public class OperationDAOImpl extends AbstractDao<Long, Operation> implements OperationDAO {

    @Override
    public List<Operation> getOperationsOfAccount(Long accId) throws LogicException {
        return createEntityCriteria().add(Restrictions.eq("sourceAccId", accId)).list();

    }

    @Override
    public void insertOperation(Operation operation) {
        getSession().persist(operation);
    }
}

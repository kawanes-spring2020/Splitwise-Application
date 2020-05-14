package com.splitwise.info6250.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
 
import com.splitwise.info6250.model.ExpenseDocument;
 
@Repository("expenseDocumentDao")
public class ExpenseDocumentDaoImpl extends AbstractDao<Integer, ExpenseDocument> implements ExpenseDocumentDao{
 
    @SuppressWarnings("unchecked")
    public List<ExpenseDocument> findAll() {
        Criteria crit = createEntityCriteria();
        return (List<ExpenseDocument>)crit.list();
    }
 
    public void save(ExpenseDocument document) {
        persist(document);
    }
 
     
    public ExpenseDocument findById(int id) {
        return getByKey(id);
    }
 
    @SuppressWarnings("unchecked")
    public List<ExpenseDocument> findAllByUserId(int userId){
        Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("expense");
        userCriteria.add(Restrictions.eq("id", userId));
        return (List<ExpenseDocument>)crit.list();
    }
 
     
    public void deleteById(int id) {
    	ExpenseDocument document =  getByKey(id);
        delete(document);
    }
 
}
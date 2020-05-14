package com.splitwise.info6250.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.splitwise.info6250.dao.ExpenseDocumentDao;
import com.splitwise.info6250.model.ExpenseDocument;
 
@Service("expenseDocumentService")
@Transactional
public class ExpenseDocumentServiceImpl implements ExpenseDocumentService{
 
    @Autowired
    ExpenseDocumentDao dao;
 
    public ExpenseDocument findById(int id) {
        return dao.findById(id);
    }
 
    public List<ExpenseDocument> findAll() {
        return dao.findAll();
    }
 
    public List<ExpenseDocument> findAllByUserId(int userId) {
        return dao.findAllByUserId(userId);
    }
     
    public void saveDocument(ExpenseDocument document){
        dao.save(document);
    }
 
    public void deleteById(int id){
        dao.deleteById(id);
    }
     
}
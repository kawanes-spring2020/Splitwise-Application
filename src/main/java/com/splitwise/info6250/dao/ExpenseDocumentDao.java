package com.splitwise.info6250.dao;

import java.util.List;

import com.splitwise.info6250.model.ExpenseDocument;

public interface ExpenseDocumentDao {
	 
    List<ExpenseDocument> findAll();
     
    ExpenseDocument findById(int id);
     
    void save(ExpenseDocument document);
     
    List<ExpenseDocument> findAllByUserId(int userId);
     
    void deleteById(int id);
}
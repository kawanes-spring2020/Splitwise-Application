package com.splitwise.info6250.service;

import java.util.List;

import com.splitwise.info6250.model.ExpenseDocument;
 
public interface ExpenseDocumentService {
 
	ExpenseDocument findById(int id);
 
    List<ExpenseDocument> findAll();
     
    List<ExpenseDocument> findAllByUserId(int id);
     
    void saveDocument(ExpenseDocument document);
     
    void deleteById(int id);
}

package com.amigos.yeah.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timestamp) {
        super(status, msg, timestamp);
        
    }
    public List<FieldMessage> getErrors() {
        return errors;
    }
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
    
}

package ch.hesge.vaadin.ui.common.components;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.AbstractValidator;


public class YearFieldValidator extends AbstractValidator<String> implements Validator<String> {

    YearFieldValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public ValidationResult apply(String value, ValueContext valueContext) {
        return this.toResult(value, isValid(value));
    }

    private boolean isValid(String value) {
        if (value.length() < 4) return false;
        if (value.trim().isEmpty()) return false;
        int year = 0;
        try {
            year = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return year > 0 ;
    }
}
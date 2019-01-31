package ch.hesge.vaadin.ui.common.formfields;

import com.google.common.eventbus.Subscribe;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.HasValidator;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;

public class YearField extends TextField implements HasValidator<String>, IsValidable {

    private boolean isValid;

    public YearField() {
        setLabel("Annee");
        setPlaceholder("1994");
        setWidth("100px");
        setMaxLength(4);
        isRequired();
        addValueChangeListener(event -> validate());
        addKeyDownListener(event -> validate());
    }

    @Override
    protected void validate() {
        ValidationResult result = getDefaultValidator().apply(getValue(), null);
        if (result.isError()) {
            setInvalid(true);
            isValid = false;
            setErrorMessage(result.getErrorMessage());
        } else {
            setInvalid(false);
            isValid = true;
        }
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public Validator<String> getDefaultValidator() {
        return new YearFieldValidator("Veuillez entrer une anneé au format YYYY.");
    }

    @Subscribe
    public void validateOn(CreationEvent event) {
        validate();
    }
}

package ch.hesge.vaadin.ui.common.components;

import com.google.common.eventbus.Subscribe;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.HasValidator;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;

public class YearField extends TextField implements HasValidator<String> {

    public YearField() {
        setLabel("Annee");
        setPlaceholder("1994");
        setMaxLength(4);
        isRequired();
        addValueChangeListener(event -> validate());
    }

    @Override
    protected void validate() {
        ValidationResult result = getDefaultValidator().apply(getValue(), null);
        if (result.isError()) {
            setInvalid(true);
            setErrorMessage("Veuillez entrer une annee au format YYYY.");
        } else {
            setInvalid(false);
        }
    }

    @Override
    public Validator<String> getDefaultValidator() {
        return new YearFieldValidator("'" + getValue() + "' is not a valid value");
    }

    @Subscribe
    public void validateOn(CreationEvent event) {
        validate();
    }
}

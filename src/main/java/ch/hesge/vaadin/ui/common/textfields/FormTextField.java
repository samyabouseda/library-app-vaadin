package ch.hesge.vaadin.ui.common.textfields;

import ch.hesge.vaadin.ui.common.events.CreationEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.HasValidator;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.validator.StringLengthValidator;



public class FormTextField extends TextField implements HasValidator<String>, IsValidable {

    private EventBus eventBus;
    private boolean isValid = false;

    public FormTextField(String label) {
        super(label);
        addValueChangeListener(event -> {
            validate();
        });
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
            isValid = true;
            setInvalid(false);
        }
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public Validator<String> getDefaultValidator() {
        return new StringLengthValidator("Vous devez remplir ce champ.", 1, 100);
    }

    @Subscribe
    public void validateOn(CreationEvent event) {
        validate();
    }
}

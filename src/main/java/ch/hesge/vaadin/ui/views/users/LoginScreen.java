package ch.hesge.vaadin.ui.views.users;

import ch.hesge.vaadin.ui.common.components.LoginButton;
import ch.hesge.vaadin.ui.common.components.LoginEvent;
import ch.hesge.vaadin.ui.common.components.NavBar;
import com.google.common.eventbus.EventBus;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("login")
@PageTitle("Login")
public class LoginScreen extends VerticalLayout {

    private final TextField usernameField;
    private final PasswordField passwordField;
    private final NavBar navBar;
    private final Button loginButton;

    public LoginScreen() {
        EventBus eventBus = new EventBus();

        navBar = new NavBar();
        navBar.hideConnexionButton();
        usernameField = new TextField("Identifiant");
        usernameField.focus();
        passwordField = new PasswordField("Mot de passe");
        loginButton = new LoginButton();
        loginButton.addClickListener(buttonClickEvent -> {
            eventBus.post(new LoginEvent(usernameField.getValue(), passwordField.getValue()));
        });

        add(navBar, usernameField, passwordField, loginButton);
    }

}

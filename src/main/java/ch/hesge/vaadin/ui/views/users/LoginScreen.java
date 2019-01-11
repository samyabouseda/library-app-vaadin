package ch.hesge.vaadin.ui.views.users;

import ch.hesge.vaadin.ui.common.components.LoginButton;
import ch.hesge.vaadin.ui.common.components.NavBar;
import com.google.common.eventbus.EventBus;
import com.sun.security.auth.UserPrincipal;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;

import javax.servlet.ServletException;


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
        navBar.hideLoginButtons();
        usernameField = new TextField("Identifiant");
        usernameField.focus();
        passwordField = new PasswordField("Mot de passe");
        loginButton = new LoginButton();
        loginButton.addClickListener(buttonClickEvent -> doLogin());
        add(navBar, usernameField, passwordField, loginButton);
    }

    private void doLogin() {
        VaadinServletRequest request =  VaadinServletRequest.getCurrent();
        if (! isUserAuthenticated(request)) {
            //check if credentials are not empty ==> let the component handle that (eventBus, etc)
            String username = usernameField.getValue();
            String password = passwordField.getValue();
            try {
                request.login(username, password);
                VaadinSession.getCurrent().getSession().setAttribute("logged_user", new UserPrincipal(username));
                VaadinSession.getCurrent().getSession().setAttribute("logged", true);
                this.getUI().ifPresent(ui -> ui.navigate(""));
                Notification.show(String.format("Bonjour, %s!", request.getUserPrincipal().getName()));
            } catch (ServletException e) {
                Notification.show("Identifiant ou mot de passe erroné.", 3000, Notification.Position.MIDDLE);
            }
        }
    }

    private boolean isUserAuthenticated(VaadinServletRequest request) {
        return request.isUserInRole("ADMIN");
    }

}

package ch.hesge.vaadin.ui.views.users;

import ch.hesge.vaadin.ui.common.components.LoginButton;
import ch.hesge.vaadin.ui.common.components.NavBar;
import com.google.common.eventbus.EventBus;
import com.sun.security.auth.UserPrincipal;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WrappedSession;

import javax.servlet.ServletException;


@Route("login")
@PageTitle("Login")
public class LoginScreen extends VerticalLayout {

    private final TextField usernameField = new TextField("Identifiant");;
    private final PasswordField passwordField = new PasswordField("Mot de passe");
    private final NavBar navBar = new NavBar();
    private final Button loginButton = new LoginButton();
    private EventBus eventBus = new EventBus();

    public LoginScreen() {
        initView();
    }

    private void initView() {
        navBar.hideLoginButtons();
        loginButton.addClickListener(buttonClickEvent -> doLogin());
        usernameField.focus();
        add(navBar, usernameField, passwordField, loginButton);
    }

    private void doLogin() {
        VaadinServletRequest request =  VaadinServletRequest.getCurrent();
        if (! isUserAuthenticated(request)) {
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

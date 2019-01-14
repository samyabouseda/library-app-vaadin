package ch.hesge.vaadin.ui.common.components;

import ch.hesge.vaadin.ui.views.books.BooksList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WrappedSession;

import javax.servlet.ServletException;


public class NavBar extends HorizontalLayout {

    private final Label title = new Label("Simple Library");
    private final RouterLink bookListLink = new RouterLink("Livre", BooksList.class);
    private Div header = new Div();
    private final Button loginBtn = new Button("Connexion");
    private final Button logoutBtn = new Button("Déconnexion");
    private boolean isUserAuthenticated = false;

    public NavBar() {
        WrappedSession session = VaadinSession.getCurrent().getSession();
        try {
            isUserAuthenticated = (boolean)session.getAttribute("logged");
        } catch (Exception e) {
            isUserAuthenticated = false;
        }
        initHeader();
    }

    private void initHeader() {
        logoutBtn.addClickListener(buttonClickEvent -> {
            WrappedSession session = VaadinSession.getCurrent().getSession();
            VaadinServletRequest request = VaadinServletRequest.getCurrent();
            session.setAttribute("logged", false);
            session.invalidate();
            try {
                request.logout();
            } catch (ServletException e) { }
            this.getUI().ifPresent(ui -> ui.navigate(""));
        });

        loginBtn.addClickListener(buttonClickEvent -> {
            this.getUI().ifPresent(ui -> ui.navigate("login"));
        });

        header.add(title, bookListLink, loginBtn, logoutBtn);

        if (isUserAuthenticated) {
            showLogoutButton();
        } else {
            showLoginButton();
        }

        header.setSizeFull();
        add(header);
    }

    public void hideLoginButtons() {
        this.loginBtn.setVisible(false);
        this.logoutBtn.setVisible(false);
    }

    public void showLoginButton() {
        this.loginBtn.setVisible(true);
        this.logoutBtn.setVisible(false);
    }

    public void showLogoutButton() {
        this.loginBtn.setVisible(false);
        this.logoutBtn.setVisible(true);
    }

}

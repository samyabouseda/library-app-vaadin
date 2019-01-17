package ch.hesge.vaadin.ui.common;

import ch.hesge.vaadin.ui.views.books.BooksList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WrappedSession;

import javax.servlet.ServletException;

public class NavBar extends HorizontalLayout {

    private final Label title = new Label("Simple Library");
    private final RouterLink bookListLink = new RouterLink("Livres", BooksList.class);
    private final Button loginBtn = new Button("Connexion");
    private final Button logoutBtn = new Button("Déconnexion");
    private boolean isUserAuthenticated = false;

    public NavBar() {
        setUserStatus();
        initView();
        addStyling();
    }

    private void setUserStatus() {
        WrappedSession session = VaadinSession.getCurrent().getSession();
        try {
            isUserAuthenticated = (boolean)session.getAttribute("logged");
        } catch (Exception e) {
            isUserAuthenticated = false;
        }
    }

    private void initView() {
        logoutBtn.addClickListener(buttonClickEvent -> doLogout());

        loginBtn.addClickListener(buttonClickEvent -> {
            this.getUI().ifPresent(ui -> ui.navigate("login"));
        });

        if (isUserAuthenticated) showLogoutButton();
        else showLoginButton();

        add(title, bookListLink, loginBtn, logoutBtn);
    }

    private void doLogout() {
        WrappedSession session = VaadinSession.getCurrent().getSession();
        VaadinServletRequest request = VaadinServletRequest.getCurrent();
        session.setAttribute("logged", false);
        session.invalidate();
        try {
            request.logout();
        } catch (ServletException e) {
            // TODO: Figure out what to do here...
        }
        this.getUI().ifPresent(ui -> ui.navigate(""));
    }

    public void showLoginButton() {
        this.loginBtn.setVisible(true);
        this.logoutBtn.setVisible(false);
    }

    public void showLogoutButton() {
        this.loginBtn.setVisible(false);
        this.logoutBtn.setVisible(true);
    }

    public void hideLoginButtons() {
        this.loginBtn.setVisible(false);
        this.logoutBtn.setVisible(false);
    }

    private void addStyling() {
        Style style = getStyle();

        // General
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        style.set("width", "100%");
        style.set("height", "55px");
        style.set("margin", "0");
        style.set("position", "relative");
        style.set("border-bottom", ".5px solid #ccc");
        style.set("font-familly", "Verdana, Geneva, sans-serif");

        // Buttons
        Style btnStyle = loginBtn.getStyle();
        btnStyle.set("position", "absolute");
        btnStyle.set("right", "0");

        btnStyle = logoutBtn.getStyle();
        btnStyle.set("position", "absolute");
        btnStyle.set("right", "0");

        // Title
        Style titleStyle = title.getStyle();
        titleStyle.set("position", "absolute");
        titleStyle.set("left", "0");
        titleStyle.set("font-size", "24px");
    }


}

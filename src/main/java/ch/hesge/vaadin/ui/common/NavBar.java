package ch.hesge.vaadin.ui.common;

import ch.hesge.vaadin.ui.views.books.BooksList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;


public class NavBar extends HorizontalLayout {

    private final H2 title = new H2("Simple Library");
    private final RouterLink bookListLink = new RouterLink("Livre", BooksList.class);
    private Div header = new Div();
    private final Button connexionButton = new Button("Connexion");
    private final Button deconnexionButton = new Button("Déconnexion");
    private boolean isAuthenticated = false;

    public NavBar() {
        initView();
    }
    
    private void initView() {
        initHeader();
        add(header);
    }

    private void initHeader() {
        header.add(title, bookListLink);
        if(isAuthenticated) {
            deconnexionButton.addClickListener(buttonClickEvent -> {
                System.out.println("Logout");
            });
            header.add(deconnexionButton);
        } else {
            connexionButton.addClickListener(buttonClickEvent -> {
                this.getUI().ifPresent(ui -> ui.navigate("login"));
            });
            header.add(connexionButton);
        }
    }

    public void hideConnexionButton() {
        this.connexionButton.setVisible(false);
        this.deconnexionButton.setVisible(false);
    }

}

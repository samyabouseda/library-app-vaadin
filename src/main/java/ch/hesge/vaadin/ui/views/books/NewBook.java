package ch.hesge.vaadin.ui.views.books;


import ch.hesge.vaadin.backend.Book;
import ch.hesge.vaadin.backend.BookManager;
import ch.hesge.vaadin.ui.common.NavBar;
import ch.hesge.vaadin.ui.common.components.YearField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.ejb.EJB;


@Route("new-book")
@PageTitle("New Book")
public class NewBook extends VerticalLayout {

    private final NavBar navBar = new NavBar();
    private final TextField title = new TextField("Titre");
    private final TextField author = new TextField("Auteur");
    private final TextField editor = new TextField("Editeur");
    private final TextField year = new YearField();
    private final Button createBtn = new Button("Créer");

    @EJB
    BookManager bookManager;

    public NewBook() {
        initView();
    }

    private void initView() {
        title.isRequired();
        author.isRequired();
        editor.isRequired();
        createBtn.addClickListener(buttonClickEvent -> saveBook());
        add(navBar, title, author, editor, year, createBtn);
    }

    private void saveBook() {
        bookManager.addBook(new Book(title.getValue(), author.getValue(), editor.getValue(), year.getValue()));
        this.getUI().ifPresent(ui -> ui.navigate("books"));
        Notification.show("Livre "+title.getValue()+" cree avec succes!.", 3000, Notification.Position.BOTTOM_START);
    }

}

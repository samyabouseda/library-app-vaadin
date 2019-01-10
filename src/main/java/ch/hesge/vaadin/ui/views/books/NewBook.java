package ch.hesge.vaadin.ui.views.books;


import ch.hesge.vaadin.backend.Book;
import ch.hesge.vaadin.backend.BookManager;
import ch.hesge.vaadin.ui.common.components.CreationEvent;
import ch.hesge.vaadin.ui.common.components.NavBar;
import ch.hesge.vaadin.ui.common.components.YearField;
import com.google.common.eventbus.EventBus;
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
    private final YearField year = new YearField();
    private final Button createBtn = new Button("Créer");
    private EventBus eventBus = new EventBus();

    @EJB
    private BookManager bookManager;

    public NewBook() {
        initView();
    }

    private void initView() {
        eventBus.register(createBtn);
        createBtn.addClickListener(buttonClickEvent -> saveBook());

        add(navBar, title, author, editor, year, createBtn);
    }

    private void saveBook() {
        //TODO: Validate fields properly before creating new book.
        eventBus.post(new CreationEvent());
        bookManager.addBook(new Book(title.getValue(), author.getValue(), editor.getValue(), year.getValue()));
        this.getUI().ifPresent(ui -> ui.navigate("books"));
        Notification.show("Livre "+title.getValue()+" cree avec succes!.", 3000, Notification.Position.BOTTOM_START);
    }

}

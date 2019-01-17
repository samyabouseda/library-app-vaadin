package ch.hesge.vaadin.ui.views.books;


import ch.hesge.vaadin.backend.Book;
import ch.hesge.vaadin.backend.BookManager;
import ch.hesge.vaadin.ui.common.components.*;
import com.google.common.eventbus.EventBus;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WrappedSession;


import javax.inject.Inject;


@Route("new-book")
@PageTitle("New Book")
public class NewBook extends VerticalLayout {

    private final NavBar navBar = new NavBar();
    private final TextField title = new FormTextField("Titre");
    private final TextField author = new FormTextField("Auteur");
    private final TextField editor = new FormTextField("Editeur");
    private final YearField year = new YearField();
    private final Button createBtn = new Button("Créer");
    private VerticalLayout form = new VerticalLayout();
    private EventBus eventBus = new EventBus();
    private WrappedSession session;

    private BookManager bookManager;

    @Inject
    public NewBook(BookManager bookManager) {
        this.bookManager = bookManager;
        session = VaadinSession.getCurrent().getSession();
        if(userSessionExist()) {
            if(userIsLogged()) { initView(); }
            else { redirectTo("403"); }
        } else {
            redirectTo("403");
        }
    }

    private boolean userIsLogged() {
        return (boolean)session.getAttribute("logged");
    }

    private boolean userSessionExist() {
        return this.session.getAttribute("logged") != null;
    }

    private void redirectTo(String location) {
        UI.getCurrent().navigate("403");
        UI.getCurrent().getPage().reload();
    }

    private void initView() {
        createBtn.addClickListener(buttonClickEvent -> saveBook());
        eventBus.register(createBtn);
        form.add(title, author, editor, year);
        add(navBar, form, createBtn); // Should create a Form component extending VerticalLayout
    }

    private void saveBook() {
        eventBus.post(new CreationEvent());
        if(formIsValid()) {
            bookManager.addBook(new Book(title.getValue(), author.getValue(), editor.getValue(), year.getValue()));
            this.getUI().ifPresent(ui -> ui.navigate("books"));
            Notification.show("Livre "+title.getValue()+" cree avec succes!.", 3000, Notification.Position.BOTTOM_START);
        }
    }

    private boolean formIsValid() {
        //This should be handled by the Form component (if created)
        return form.getChildren()
                .filter(c ->  c instanceof IsValidable)
                .allMatch(c -> ((IsValidable)c).isValid());
    }

}

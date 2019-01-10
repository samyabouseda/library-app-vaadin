package ch.hesge.vaadin.ui.views.books;


import ch.hesge.vaadin.backend.Book;
import ch.hesge.vaadin.backend.BookManager;
import ch.hesge.vaadin.ui.common.components.NavBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.inject.Inject;

@Route("books")
@PageTitle("Book List")
public class BooksList extends VerticalLayout {

    private final NavBar navBar = new NavBar();
    private final Button newBookBtn = new Button("Nouveau");
    private final Grid<Book> grid = new Grid<>();

    private boolean isAuthenticated = true;

    BookManager bookManager;

    @Inject
    public BooksList(BookManager bookManager) {
        this.bookManager = bookManager;
        initView();
        updateView();
    }

    private void initView() {
        if(isAuthenticated) {
            initViewForAuthenticatedUser();
        } else {
            initBasicView();
        }
    }

    private void initBasicView() {
        grid.addColumn(Book::getTitle).setHeader("Titre").setResizable(true);
        grid.addColumn(Book::getAuthor).setHeader("Auteur").setResizable(true);
        grid.addColumn(Book::getEditor).setHeader("Editeur").setResizable(true);
        grid.addColumn(Book::getYear).setHeader("Annee").setResizable(true);

        add(navBar, grid);
    }

    private void initViewForAuthenticatedUser() {
        grid.addColumn(new ComponentRenderer<>(book -> {
            TextField title = new TextField();
            title.setValue(book.getTitle());
            return title;
        }));

        grid.addColumn(new ComponentRenderer<>(book -> {
            TextField author = new TextField();
            author.setValue(book.getAuthor());
            return author;
        }));

        grid.addColumn(new ComponentRenderer<>(book -> {
            TextField editor = new TextField();
            editor.setValue(book.getEditor());
            return editor;
        }));

        grid.addColumn(new ComponentRenderer<>(book -> {
            TextField year = new TextField();
            year.setValue(book.getYear());
            return year;
        }));

        grid.addColumn(new NativeButtonRenderer<Book>(
                "Supprimer",
                event -> deleteBook()
        ));

        newBookBtn.addClickListener(
                buttonClickEvent -> this.getUI().ifPresent(ui -> ui.navigate("new-book")));

        add(navBar, grid, newBookBtn);
    }

    private void deleteBook() {
        bookManager.deleteBook(0);
        updateView();
        Notification.show("Livre supprimer avec succes.", 3000, Notification.Position.BOTTOM_START);
    }

    private void updateView() {
        grid.setItems(bookManager.getBooks());
    }


}

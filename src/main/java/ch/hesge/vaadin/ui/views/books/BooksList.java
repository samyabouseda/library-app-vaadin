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

    private BookManager bookManager;

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
            title.addValueChangeListener(event -> {
                book.setTitle(event.getValue());
                updateBook(book);
            });

            return title;
        }));

        grid.addColumn(new ComponentRenderer<>(book -> {
            TextField author = new TextField();
            author.setValue(book.getAuthor());
            author.addValueChangeListener(event -> {
                book.setAuthor(event.getValue());
                updateBook(book);
            });
            return author;
        }));

        grid.addColumn(new ComponentRenderer<>(book -> {
            TextField editor = new TextField();
            editor.setValue(book.getEditor());
            editor.addValueChangeListener(event -> {
                book.setEditor(event.getValue());
                updateBook(book);
            });
            return editor;
        }));

        grid.addColumn(new ComponentRenderer<>(book -> {
            TextField year = new TextField();
            year.setValue(book.getYear());
            year.addValueChangeListener(event -> {
                book.setYear(event.getValue());
                updateBook(book);
            });
            return year;
        }));

        grid.addColumn(new NativeButtonRenderer<Book>(
                "Supprimer",
                event -> deleteBook(event.getId(), event.getTitle())
        ));

        newBookBtn.addClickListener(
                buttonClickEvent -> this.getUI().ifPresent(ui -> ui.navigate("new-book")));

        add(navBar, grid, newBookBtn);
    }

    private void deleteBook(int id, String name) {
        bookManager.deleteBook(id);
        updateView();
        Notification.show("Livre \""+name+"\" supprimé avec succes.", 3000, Notification.Position.BOTTOM_START);
    }

    private void updateBook(Book book) {
        bookManager.updateBook(book);
        updateView();
    }

    private void updateView() {
        grid.setItems(bookManager.getBooks());
    }


}

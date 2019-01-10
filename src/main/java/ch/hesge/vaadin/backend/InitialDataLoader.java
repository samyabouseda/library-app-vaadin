package ch.hesge.vaadin.backend;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitialDataLoader implements ServletContextListener {

    BookManager bookManager;

    @Inject
    public InitialDataLoader(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        bookManager.addBook(new Book("Atomic Habits", "James Clear", "Avery", "2018"));
        bookManager.addBook(new Book("The power of habits", "Charles Duhigg", "Avery", "2014"));
        bookManager.addBook(new Book("Zero to One", "Peter Thiel", "Currency", "2014"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}

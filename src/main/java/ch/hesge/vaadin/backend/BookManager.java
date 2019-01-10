package ch.hesge.vaadin.backend;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
@SessionScoped
public class BookManager {

    @PersistenceContext(unitName = "book-persistence-unit")
    EntityManager entityManager;

    public BookManager() {
    }

    public void addBook(Book book){
        entityManager.persist(book);
    }

    public void deleteBook(int id) {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
    }

    public List<Book> getBooks() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        cq.from(Book.class);
        TypedQuery<Book> q = entityManager.createQuery(cq);
        return q.getResultList();
    }

    public void updateBook(Book modifiedBook) {
        Book book = entityManager.find(Book.class, modifiedBook.getId());
        book.setTitle(modifiedBook.getTitle());
        book.setAuthor(modifiedBook.getAuthor());
        book.setEditor(modifiedBook.getEditor());
        book.setYear(modifiedBook.getYear());
        entityManager.merge(book);
    }
}


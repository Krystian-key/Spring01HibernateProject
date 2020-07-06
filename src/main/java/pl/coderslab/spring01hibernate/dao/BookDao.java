package pl.coderslab.spring01hibernate.dao;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.spring01hibernate.entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class BookDao {


    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }


    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }


    public void update(Book book) {
        entityManager.merge(book);
    }


    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book));
    }


    public List<Book> readAll() {
        Query q = this.entityManager.createQuery("SELECT b FROM Book b");
        return q.getResultList();
    }


    public List<Book> getRatingList(int rating) {
        Query q = this.em.createQuery("SELECT b FROM Book b WHERE b.rating >= :rating");
        q.setParameter("rating", rating);

        return q.getResultList();
    }

    public List<Book> getByAuthor(Author author) {
        Query q = this.em.createQuery("SELECT b FROM Book b WHERE :author MEMBER OF b.authors");
        q.setParameter("author", author);

        return q.getResultList();
    }


}

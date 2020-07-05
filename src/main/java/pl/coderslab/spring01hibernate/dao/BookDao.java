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

    // zapisywanie
    @PersistenceContext
    private EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    //pobieranie

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    //updatowanie

    public void update(Book book) {
        entityManager.merge(book);
    }

    //deletowanie

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book));
    }

    //pobierania listy wszystkich obiektów

    public List<Book> readAll(){
        Query q = this.entityManager.createQuery("SELECT b FROM Book b");
        return q.getResultList();
    }


    public List<Book> getRatingList(int rating){
        Query q = this.entityManager.createQuery("SELECT b FROM Book b WHERE b.rating >= :rating");
        q.setParameter("rating", rating);
        return q.getResultList();
    }


}

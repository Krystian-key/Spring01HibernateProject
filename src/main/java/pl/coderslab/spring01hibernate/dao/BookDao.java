package pl.coderslab.spring01hibernate.dao;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    //    - zapis encji
    @PersistenceContext
    EntityManager entityManager;

    public void create(Book book) {
        entityManager.persist(book);
    }

    //- edycja encji
    public void update(Book book) {
        entityManager.merge(book);
    }

    //- pobieranie po id
    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    //- usuwanie po id
    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    public List<Book> readAll(){
        Query query = this.entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    public List<Book> getRatingList(int rating){
        Query query = this.entityManager.createQuery("SELECT b FROM Book b WHERE b.rating >= :rating");
        query.setParameter("rating", rating);
        return query.getResultList();
    }

    public List<Book> getPublisherIsNotNull(){
        Query query = this.entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher IS NOT NULL");
        return query.getResultList();
    }

    public List<Book> getPublisherById(long publisherId){
        Query query = this.entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher.id = :publisherId");
        query.setParameter("publisherId", publisherId);
        return query.getResultList();
    }

    public List<Book> getByAuthor(String author){
        Query query = this.entityManager.createQuery("SELECT b FROM Book b WHERE b.authors = :author");
        query.setParameter("author", author);
        return query.getResultList();
    }

}

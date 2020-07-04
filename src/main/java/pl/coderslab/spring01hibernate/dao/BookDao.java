package pl.coderslab.spring01hibernate.dao;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.spring01hibernate.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
                book : entityManager.merge(book)); }


}

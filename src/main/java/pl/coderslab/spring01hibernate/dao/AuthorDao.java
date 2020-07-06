package pl.coderslab.spring01hibernate.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.spring01hibernate.entity.Author;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {


    @PersistenceContext
    EntityManager entityManager;

    public void save(Author author) {
        entityManager.persist(author);
    }


    public void update(Author author) {
        entityManager.merge(author);
    }


    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }


    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    public List<Author> readAll() {
        Query query = this.entityManager.createQuery("SELECT a FROM Author a");
        return query.getResultList();

    }
}

package pl.coderslab.spring01hibernate.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.spring01hibernate.entity.Publisher;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {

    //    - zapis encji
    @PersistenceContext
    EntityManager entityManager;

    public void savePublisher(Publisher publisher) {
        entityManager.persist(publisher);
    }

    //- edycja encji
    public void update(Publisher publisher) {
        entityManager.merge(publisher);
    }

    //- pobieranie po id
    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    //- usuwanie po id
    public void delete(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }

    public List<Publisher> readAll(){
        Query query = this.entityManager.createQuery("SELECT p FROM Publisher p");
        return query.getResultList();
    }
}

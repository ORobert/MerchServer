package Persistence;

import Models.Products;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by orobe on 29/12/2016.
 */
public class StockRepository implements IRepository{

    public List<Products> getAll() {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("get all stock products");
        List<Products> resultList  = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultList;
    }

    public List<Products> getByName(String name) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("get by name stock product").setParameter("name",name);
        List<Products> resultList  = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultList;
    }

    public Products getById(int id) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("get by id stock product").setParameter("id",id);
        List<Products> resultList  = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultList.get(0);
    }
}

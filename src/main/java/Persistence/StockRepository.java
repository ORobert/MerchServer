//package Persistence;
//
//import Models.Product;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
//import java.util.List;
//
///**
// * Created by orobe on 29/12/2016.
// */
//public class StockRepository implements IRepository{
//
//    public List<Product> getAll() {
//        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        Query query = entityManager.createNamedQuery("get all stock products");
//        List<Product> resultList  = query.getResultList();
//
//        entityManager.close();
//        entityManagerFactory.close();
//
//        return resultList;
//    }
//
//    public List<Product> getByName(String name) {
//        return null;
//    }
//
//    public Product getById(int id) {
//        return null;
//    }
//
//    //    public List<Product> getByName(String name) {
////        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////
////        Query query = entityManager.createNamedQuery("get by name stock product").setParameter("name",name);
////        List<Product> resultList  = query.getResultList();
////
////        entityManager.close();
////        entityManagerFactory.close();
////
////        return resultList;
////    }
//
////    public Product getById(int id) {
////        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
////
////        Query query = entityManager.createNamedQuery("get by id stock product").setParameter("id",id);
////        List<Product> resultList  = query.getResultList();
////
////        entityManager.close();
////        entityManagerFactory.close();
////
////        return resultList.get(0);
////    }
//}

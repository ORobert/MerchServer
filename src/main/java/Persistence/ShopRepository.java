package Persistence;

import Models.Products;
import Models.Shop_Products;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by orobe on 28/12/2016.
 */
public class ShopRepository implements IRepository {


    public List<Products> getAll() {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("get all shop products");
        List<Products> resultList  = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultList;
    }



    public Products getById(int id) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("get by id shop product").setParameter("id",id);
        List<Products> resultList  = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultList.get(0);
    }



    public List<Products> getByName(String name) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("get by name shop product").setParameter("name",name);
        List<Products> resultList  = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultList;
    }

    public List<Products> getByPrice(Double price) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createNamedQuery("get by price shop product").setParameter("price",price);
        List<Products> resultList  = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return resultList;
    }


    public void order(Products product) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return;
    }


    public void seed(String xmlFile) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("merch");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        if(getAll().size()==0) {
            try {
                File file = new File(xmlFile);
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();

                Document document = builder.parse(file);
                document.getDocumentElement().normalize();

                NodeList nodeList = document.getElementsByTagName("product");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        Products product = new Shop_Products(
                                element.getElementsByTagName("name").item(0).getNodeValue(),
                                Float.parseFloat(element.getElementsByTagName("price").item(0).getNodeValue())
                        );
                        entityManager.getTransaction().begin();
                        entityManager.persist(product);
                        entityManager.getTransaction().commit();
                    }
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        entityManager.close();
        entityManagerFactory.close();

        return;
    }
}

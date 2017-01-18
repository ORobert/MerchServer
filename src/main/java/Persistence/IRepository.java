package Persistence;

import Models.Products;

import java.util.List;

/**
 * Created by orobe on 29/12/2016.
 */
public interface IRepository {
    List<Products> getAll();
    List<Products> getByName(String name);
    Products getById(int id);
}

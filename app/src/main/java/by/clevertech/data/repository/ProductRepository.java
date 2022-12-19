package by.clevertech.data.repository;

import by.clevertech.data.entity.Product;

/**
 * Extends the {@link CrudRepository}
 * <p>
 * Interface for performing CRUD-operations with {@link Product}
 * 
 * @author Nikita Semeniuk
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}

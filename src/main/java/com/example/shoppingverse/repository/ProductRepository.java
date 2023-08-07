package com.example.shoppingverse.repository;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByProductCategory(ProductCategory productCategory);

    List<Product> findTop5ByOrderByPriceAsc();

    List<Product> findTop5ByOrderByPriceDesc();
    @Query("select p from Product p where p.price >= :price and p.category=:category")
    public List<Product> getProductByCategoryAndPriceGreaterThan(int price, ProductCategory category);

//    @Query(value = "select order_id, total_value from orders ORDER BY toatal_value DESC LIMIT 5",nativeQuery = true);
//    public int getTop5orderwithHighestValue();

    @Query(value = "SELECT product_id,product_name,price FROM products WHERE category = FOOD ORDER BY price ASC LIMIT 5",nativeQuery = true)
    public List<Product> getTop5Prod(ProductCategory category);
}

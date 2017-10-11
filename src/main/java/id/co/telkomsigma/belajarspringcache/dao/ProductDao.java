package id.co.telkomsigma.belajarspringcache.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import id.co.telkomsigma.belajarspringcache.domain.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, String>{
	
	public Product findByName(String name);
	public Product findByCode(String code);
	
    @Query("select p from Product p where p.name=:name")
    public List<Product> findDataByname (@Param("name") String name);
}

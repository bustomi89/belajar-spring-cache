package id.co.telkomsigma.belajarspringcache.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import id.co.telkomsigma.belajarspringcache.dao.ProductDao;
import id.co.telkomsigma.belajarspringcache.domain.Product;

@Service
@CacheConfig(cacheNames = "products")
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
    @Cacheable(key = "#name")
    public List<Product> findProductListByName(String name) {
        logger.info("MEMANGGIL SERVICE FIND PRODUCT BY NAME !!");
        return productDao.findDataByname(name);
    }
    
	@Cacheable(key="#name") //, condition = "#name == 'YOSEP'")
	public Product findProductByName(String name){
		logger.info("PANGGIL SERVICE FIND PRODUCT BY NAME !!");
		return productDao.findByName(name);
	}
	
	@Cacheable(key="#code") //, unless = "#result.name=='YOSEP'")
	public Product findProductByCode(String code){
		logger.info("PANGGIL SERVICE FIND PRODUCT BY CODE !!");
		return productDao.findByCode(code);
	}
	
	@CachePut(key="#name")
	public Product updateProductName(String id, String name){
		logger.info("PANGGIL SERVICE UPDATE PRODUCT !!");
		
		Product p = productDao.findOne(id);
		p.setName(name);
		productDao.save(p);
		
		return p;
	}
	
   @CacheEvict(key="#name")
    public void deleteCacheProductByName(String name){
    }
}









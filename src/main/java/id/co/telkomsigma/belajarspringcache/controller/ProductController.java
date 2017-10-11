package id.co.telkomsigma.belajarspringcache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import id.co.telkomsigma.belajarspringcache.domain.Product;
import id.co.telkomsigma.belajarspringcache.service.ProductService;

@RestController
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    ProductService productService;
    
    @Autowired
    private CacheManager cacheManager;

	@GetMapping("/findProductByName/name/{name}")
	public Product findProductByName(@PathVariable("name") String name){
		
		logger.info("CONTROLLER UNTUK FIND PRODUCT BY NAME !!");
        logger.info("PROVIDER YANG DIGUNAKAN : {}",cacheManager.getClass().getName());
		
		return productService.findProductByName(name);
	}
	
	@GetMapping("/findProductByCode/code/{code}")
	public Product findProductByCode(@PathVariable("code") String code){
		
		logger.info("CONTROLLER UNTUK FIND PRODUCT BY CODE !!");
		
		return productService.findProductByCode(code);
	}
	
	@GetMapping("/updateProduct/name/{id}/{name}")
	public String UpdateProduct(
				@PathVariable("id") String id,
				@PathVariable("name") String name){
		
		logger.info("UPDATE PRODUCT NAME DENGAN ID {} MENJADI {}",id,name);
		
		productService.updateProductName(id, name);
		
		return "Data Berhasil Di Update";
	}
	
    @GetMapping("/deleteCacheProduct/name/{name}")
    public String deleteCacheProductByName(@PathVariable("name") String name){
        
    	productService.deleteCacheProductByName(name);
        
        return "cache berhasil dihapus !!";
    }
}

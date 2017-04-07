package kr.co.tworld.freebill.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tworld.freebill.service.FreeBillService;

@RestController
public class FreeBillController {
	
//    @Autowired 
//    private ProductRepository productRepository;
    
    @Autowired
    private FreeBillService freeBillService;
    
//    @RequestMapping("/product")
//    public List getProduct(){
//    	System.out.println("productRepository2 = " + productRepository);
//    	List<Product> list = (List<Product>) productRepository.findAll();
//    	return list;
//    }
    
    @RequestMapping("/freebill/detail")
    public HashMap getFreeBillDetail(@RequestParam("svcMgmtNum") String svcMgmtNum){
    	HashMap result = freeBillService.getFreeBillDetail(svcMgmtNum);
    	
    	return result;
    } 
	
    @RequestMapping("/freebill/main")
    public HashMap getFreeBillMain(@RequestParam("svcMgmtNum") String svcMgmtNum){
    	HashMap result = freeBillService.freeBillMain(svcMgmtNum);
    	
    	return result;
    } 
}

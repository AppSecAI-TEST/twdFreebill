package kr.co.tworld.freebill.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tworld.freebill.service.FreeBillService;

@RestController
public class FreeBillController {
	
	private static Logger logger = LoggerFactory.getLogger(FreeBillController.class);

	@Resource(name="redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
	
    @Autowired
    private FreeBillService freeBillService;
    
    @RequestMapping(value="/freebill/detail")
    public HashMap getFreeBillDetail(@RequestParam("tokenId") String token){
    	String svcMgmtNum = (String) redisTemplate.opsForHash().get(token,"selected");
    	
    	logger.debug("token == " + token);
    	logger.debug("svcMgmtNum == " + svcMgmtNum);
    	
    	HashMap result = freeBillService.getFreeBillDetail(svcMgmtNum);
    	return result;
    } 

	@RequestMapping("/freebill/main")
	public HashMap getFreeBillMain(@RequestParam("tokenId") String token){
    	String svcMgmtNum = (String) redisTemplate.opsForHash().get(token,"selected");
    	
    	logger.debug("token == " + token);
    	logger.debug("svcMgmtNum == " + svcMgmtNum);
    	logger.error("error log level~");
    	
		HashMap result = freeBillService.freeBillMain(svcMgmtNum);
		return result;
	} 
   
	@RequestMapping("/freebill/hystrixtest")
	public ResponseEntity<String> getHystrixTest(@RequestParam("svcMgmtNum") String svcMgmtNum){
		ResponseEntity<String> result = freeBillService.getHystrixTest(svcMgmtNum);
		return result;
	}
}

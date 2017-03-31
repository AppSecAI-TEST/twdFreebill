package kr.co.tworld.freebill.web;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tworld.freebill.service.FreeBillService;

@RestController
public class FreeBillController {
	
    @RequestMapping("/freebill_detail")
    public HashMap getFreeBillDetail(@RequestParam("svcMgmtNum") String svcMgmtNum){
    	
    	FreeBillService freeBillService = new FreeBillService();
    	HashMap result = freeBillService.getFreeBillDetail(svcMgmtNum);
    	
    	return result;
    } 
	
    @RequestMapping("/freebill_main")
    public HashMap getFreeBillMain(@RequestParam("svcMgmtNum") String svcMgmtNum){
    	
    	FreeBillService freeBillService = new FreeBillService();
    	HashMap result = freeBillService.freeBillMain(svcMgmtNum);
    	
    	return result;
    }    
}

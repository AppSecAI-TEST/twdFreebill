package kr.co.tworld.freebill.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CDRSRepository {
    /**
     * 잔여기본통화 CDRS 데이터를 가져온다.
     * @param svcMgmtNum
     * @param channel (1: 모티 2: 온티)
     * @return
     */
    public List getCDRSData(String svcMgmtNum, String channel) throws Exception{
        
        StringBuffer buffer = new StringBuffer();
        ArrayList list = new ArrayList();
        String[] keys = new String[]{"PROD_ID","PROD_NM","SKIP_ID","SKIP_NM","TOTAL","USE","REMAIN","UNIT","REG_DT","UNLIMIT","NO_USE","TYPE","ORDER"};
        
        try{
          //0.5초 지연
          this.sleep(500); 
          
          String rcvMsg = "{\"message\":\"무료통화 내역 조회\",\"result\":[{\"PROD_ID\":\"NA00004120\",\"PROD_NM\":\"기본형 프리존(T-Map)\",\"SKIP_ID\":\"DDX51\",\"SKIP_NM\":\"교통정보 무제한               \",\"TOTAL\":\"0\",\"USE\":\"0\",\"REMAIN\":\"0\",\"UNIT\":\"310\",\"REG_DT\":\"\",\"UNLIMIT\":\"1\",\"NO_USE\":\"N\",\"TYPE\":\"0\",\"ORDER\":\"11200\"},{\"PROD_ID\":\"NA00004120\",\"PROD_NM\":\"기본형 프리존22(T-Map)\",\"SKIP_ID\":\"DDX51\",\"SKIP_NM\":\"교통정보 무제한               \",\"TOTAL\":\"0\",\"USE\":\"0\",\"REMAIN\":\"0\",\"UNIT\":\"310\",\"REG_DT\":\"\",\"UNLIMIT\":\"1\",\"NO_USE\":\"N\",\"TYPE\":\"0\",\"ORDER\":\"11200\"}],\"status\":\"1\",\"count\":\"13\"}";
          
          HashMap map = this.convertJSONToMap(rcvMsg);
          
          String message = (String) map.get("message");
          String status = (String) map.get("status"); //-1:에러, 1:조회성공
          String count = (String) map.get("count");
          
          if("1".equals(status)){
              list = changeArrayTolist((String) map.get("result"), keys);
          } 
          
        }catch(Exception ex){
            System.out.println("Exception = " + ex.toString());
        }
        
        return list;
    }
    
    /**
     * JSON --> MAP 으로 변환
     * @param jsonString
     * @return
     * @throws Exception
     */
    private HashMap<String, String> convertJSONToMap(String jsonString) throws Exception {
        
        JSONObject jsonObject = null;
        HashMap<String, String> hm = new HashMap<String, String>();
        
        jsonObject= new JSONObject(jsonString);
        Iterator<String> itr = jsonObject.keys();

        String key = "";
        String value = "";
        while(itr.hasNext()) {
            key = itr.next();
            value = jsonObject.get(key).toString();

            hm.put(key, value);                
        }
        
        return hm;
    }
    
    /**
     * Array -> list 형태로 변환한다.
     * @param data 원본데이터
     * @param keys 추출할 키값들
     * @return
     * @throws Exception
     */
    private ArrayList changeArrayTolist(String data, String[] keys) throws Exception{
        
        ArrayList list = new ArrayList();
        
        JSONArray jsonArry = new JSONArray(data);
        ArrayList subList = null;
        
        for(int i=0, len=jsonArry.length(); i<len; i++){
            subList = new ArrayList();
            JSONObject obj = jsonArry.getJSONObject(i);
            
            for(int j=0, size=keys.length; j<size; j++){
                subList.add(obj.get(keys[j]));
            }
            list.add(subList);
        }
        
        return list;
    }
    
    private void sleep(int time){
        try {
          Thread.sleep(time);
        } catch (InterruptedException e) { }
    }
    
}

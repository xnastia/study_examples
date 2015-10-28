package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class CacheLimitException extends Exception {
	   public CacheLimitException(String msg){
	      super(msg);
	   }
	}
public class Cache{
	
	private HashMap<String, String> container = new HashMap<String, String>();
	private HashMap<String, Date> timeouts = new HashMap<String, Date>();
	int limit;
	public Cache(){
		this.limit = -1;
	}		
	public Cache(int lim){
		this.limit = lim;
	}		

	public void put(String key, String value) throws CacheLimitException{
		if (this.limit > -1  && this.limit <= container.size()){
			throw new CacheLimitException("Limit reached");
		} 
		put(key, value, 0);
	}
	
	public void put(String key, String value, int keepalive){
		container.put(key, value);
		if (keepalive > 0){
			Date date = new Date();
			date.setTime(date.getTime() + keepalive);
			timeouts.put(key, date);
		}
		
	}
	
	public String get(String key){
		Date timeout = timeouts.get(key);
		if (timeout != null && timeout.before(new Date())){			
			return null;			
		}
		return container.get(key);
	}
	
	public String[] getKeys(){
	  return (String[]) this.container.keySet().toArray();
	}
}
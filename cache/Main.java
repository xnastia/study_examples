package test;


public class Main {
	
 
	public static void main(String[] args) {
			Cache unlimitedCache = new Cache(1);
			try{
			unlimitedCache.put("key1", "data", 1000);
			
			}
			catch(Exception ex){
				System.out.println("too bed");	
			}
			
			System.out.println(unlimitedCache.get("dddd"));
	}

}

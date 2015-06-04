package com.shashajing.benison.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

public class Test extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
       /* response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //将要被返回到客户端的对象
        UserRole userRole = new UserRole();
        userRole.setId(11L);
        userRole.setRoleId(33L);	
        userRole.setRoleName("roleName");
        userRole.setUserName("userName");
        JSONObject json = new JSONObject();
        json.accumulate("success", true);
        json.accumulate("userRole", userRole);
        //out.println(json.toString());
//      因为JSON数据在传递过程中是以普通字符串形式传递的，所以我们也可以手动拼接符合JSON语法规范的字符串输出到客户端
//      以下这两句的作用与38-46行代码的作用是一样的，将向客户端返回一个User对象，和一个success字段
      String jsonString="{\"id\":\"123\",\"roleName\":\"JSONServlet\",\"userName\":\"Hello\"}";
      out.println(jsonString);
        out.flush();
        out.close();*/
		System.out.println(request.getContentType());
		String jsondata = "{\"page\":\"1\"," + " \"total\":2," + " \"records\":\"13\"," + " \"rows\":" + " [" + " {" + " \"id\":\"13\"," + " \"cell\":" + " [\"13\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]" + " }," 
        + " {" + " \"id\":\"12\"," + " \"cell\":" + " [\"12\",\"管理员\",\"张氏\"]" + " }," 
        + " {" + " \"id\":\"4\"," + " \"cell\":" + " [\"4\",\"管理员\",\"Client 3\"]" + " }" + " ]," 
		+ " \"userdata\":{\"amount\":3220,\"tax\":342,\"total\":3564,\"name\":\"Totals:\"}" + " }"; 
		response.getWriter().write(jsondata);
    }
 
    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    public String nameLocal;
    public String firstName;
    
    public String getName(){
    	return nameLocal;
    }
    public void setName(String name){
    	nameLocal = name;
    }
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
    public int hashCode(){
    	return firstName.hashCode();
    }
    
    @Override
    public boolean equals(Object object){
    	Test bb = (Test)object;
    	return firstName.equals(bb.getFirstName());
    }
    
    @Override
    public String toString(){
    	return firstName+nameLocal;
    }
    
    
    static class ThreadTest extends Thread {
    	private String bString;
    	public List<String> bList;
    	public ThreadTest(String aString){
    		bString = aString; 
    	}
    	public ThreadTest(List<String> aList){
    		bList = aList; 
    	}
    	
    	@Override
    	public void run() {
    		System.out.println(",,,,,,");
		}
    	
    }
    
    static class TestCallable implements Callable{

    	public String aString = "436";
    	private int dss = 88;
    	public List<String> bList;
		@Override
		public String call() throws Exception {
			System.out.println(bList.add("7"));
			return aString;
		}
		public TestCallable(String bString){
			aString = bString;
		}
		public TestCallable(){
		}
		
		public void testprivate(){
			
			System.out.println("dsgdsg");
		}
    }
    
    
    
    public static Integer aObject = new Integer(1);
    private static Integer bObject = new Integer(2);
    private static ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock locka = new ReentrantLock();
    private static ReentrantReadWriteLock rlock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = rlock.writeLock();
    private static ReentrantReadWriteLock.ReadLock readLock = rlock.readLock();
    private static Condition condition = lock.newCondition();
    private static Condition conditiona = locka.newCondition();
    
    public static void main(String[] aStrings){
    	try {
            Jedis jedis = new Jedis("120.24.57.200");
            long a = new Date().getTime();
            
            for (int i = 0; i < 1000; i++) {
            	jedis.set("money", "wen"+i);
			}
            System.out.println((new Date().getTime() - a)/1000);
    		
//    		FileInputStream inputStream = new FileInputStream("D:\\csw.log");
//    		OutputStream out = new FileOutputStream("D:\\asw.log");
    		
       } catch (Exception e) {
    	   e.printStackTrace();
       }
       finally{
       }
   }
    
   
    

    
}





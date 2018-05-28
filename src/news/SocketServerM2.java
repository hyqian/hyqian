package news;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerM2 {

	 public static void main(String[] args)  {

		 
		 
	        int port = 7000;
	        int clientNo = 1;

	        System.out.println("开始打开服务器");
	        
	        try {
				
			
	        
	        ServerSocket serverSocket = new ServerSocket(port);

	        // 创建线程池
	        ExecutorService exec = Executors.newCachedThreadPool();

	        try {

	            while (true) {
	                Socket socket = serverSocket.accept();
	                exec.execute(new SingleServer2(socket, clientNo));
	                clientNo++;
	            }

	        } finally {
	            serverSocket.close();
	        }

	        
	        } catch (Exception e) {
				// TODO: handle exception
			}
	        
	    }
	        
	        
	        
	        
	        
	}

	class SingleServer2 implements Runnable {

	    private Socket socket;
	    private int clientNo;

	    public SingleServer2(Socket socket, int clientNo) {
	        this.socket = socket;
	        this.clientNo = clientNo;
	    }

	    @Override
	    public void run() {

	    	boolean b = socket.isConnected();
	    	
	    	
	    	while(b) {
	    		
	    		
	    		
	    		 try {

	 	        	//3.获得输入流  
	 	            InputStream is=socket.getInputStream();  
	 	            BufferedReader br=new BufferedReader(new InputStreamReader(is));  
	 	            //获得输出流  
	 	            OutputStream os=socket.getOutputStream();  
	 	            PrintWriter pw=new PrintWriter(os);  
	 	            //4.读取用户输入信息  
	 	            String info=null;  
	 	            while(!((info=br.readLine())==null)){  
	 	                System.out.println("我是服务器，用户信息为："+info);  
	 	            }  
	 	            //给客户一个响应  
	 	            String reply="welcome";  
	 	            pw.write(reply);  
	 	            pw.flush(); 
	 	            
	 	            
	 	            
	 	            //5.关闭资源  
	 	            pw.close();  
	 	            os.close();  
	 	            br.close();  
	 	            is.close();  
	 	            
	 	            
	 	            
	 	        } catch (IOException e) {
	 	            System.out.println("与客户端"+clientNo+"失去连接");
	 	        } 
	    		 
	    		 
	    		 
	    		 try {
	 				Thread.sleep(1000);
	 			} catch (InterruptedException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	    		 
	 	        
	    	}
	    	
	       
	        
	        
	        
	    }
	}
	
	


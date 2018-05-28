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

	        System.out.println("��ʼ�򿪷�����");
	        
	        try {
				
			
	        
	        ServerSocket serverSocket = new ServerSocket(port);

	        // �����̳߳�
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

	 	        	//3.���������  
	 	            InputStream is=socket.getInputStream();  
	 	            BufferedReader br=new BufferedReader(new InputStreamReader(is));  
	 	            //��������  
	 	            OutputStream os=socket.getOutputStream();  
	 	            PrintWriter pw=new PrintWriter(os);  
	 	            //4.��ȡ�û�������Ϣ  
	 	            String info=null;  
	 	            while(!((info=br.readLine())==null)){  
	 	                System.out.println("���Ƿ��������û���ϢΪ��"+info);  
	 	            }  
	 	            //���ͻ�һ����Ӧ  
	 	            String reply="welcome";  
	 	            pw.write(reply);  
	 	            pw.flush(); 
	 	            
	 	            
	 	            
	 	            //5.�ر���Դ  
	 	            pw.close();  
	 	            os.close();  
	 	            br.close();  
	 	            is.close();  
	 	            
	 	            
	 	            
	 	        } catch (IOException e) {
	 	            System.out.println("��ͻ���"+clientNo+"ʧȥ����");
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
	
	


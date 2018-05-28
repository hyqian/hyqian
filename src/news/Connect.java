package news;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/** 
 * @author ĳ��: 
 * @version ����ʱ�䣺2015��8��17�� ����3:04:14 
 * ��˵�� 
 */
public class Connect {
    private static final ThreadLocal<Socket> threadConnect = new ThreadLocal<Socket>(); 
    
    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8080;
    
    private static Socket client;
    
    private static OutputStream outStr = null;
    
    private static InputStream inStr = null;
    
    
    private static String localip = "";
    
    private static Thread tRecv = new Thread(new RecvThread());
    private static Thread tKeep = new Thread(new KeepThread());

    public static void connect() throws UnknownHostException, IOException  {
        client = threadConnect.get();
        if(client == null){
            client = new Socket(HOST, PORT);
            threadConnect.set(client);
            tKeep.start();
            System.out.println("========���ӿ�ʼ��========");
        }
       
        outStr = client.getOutputStream();
        inStr = client.getInputStream();
    }
    
    public static void disconnect() {
        try {
            outStr.close();
            inStr.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static class KeepThread implements Runnable {
        public void run() {
            try {
                System.out.println("=====================��ʼ����������==============");
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("�����������ݰ�");
                    
                    
                    if(localip.equals("")) {
                    	 InetAddress ia = null;
         				 ia = ia.getLocalHost();
         				 localip = ia.getHostAddress();
                         outStr.write(localip.getBytes());
                    }
                    
                   
                
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    private static class RecvThread implements Runnable {
        public void run() {
            try {
                System.out.println("==============��ʼ��������===============");
                while (true) {
                    byte[] b = new byte[1024];
                    int r = inStr.read(b);
                    if(r>-1){
                        String str = new String(b);
                        System.out.println( str );
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    public static void main(String[] args) {
        try {
            Connect.connect();
            tRecv.start();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
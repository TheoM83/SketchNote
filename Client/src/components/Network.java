package components;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Network {

	public Network(){
		
	}
	
	public static InetAddress serverIP;
	
	public void getNetworkActiveIPs(int port) {
	    final byte[] ip;
	    try {
	        ip = InetAddress.getLocalHost().getAddress();
	    } catch (Exception e) {
	        return;     // exit method, otherwise "ip might not have been initialized"
	    }

	    for(int i=1;i<=254;i++) {
	        final int j = i;  // i as non-final variable cannot be referenced from inner class
	        
	        new Thread(new Runnable() {   // new thread for parallel execution
	            public void run() {
	                try {
	                    ip[3] = (byte)j;
	                    InetAddress address = InetAddress.getByAddress(ip);
	                    String output = address.toString().substring(1);
	                    if (address.isReachable(5000)) {
	                        if(this.isActive(address, port));
	                        	
	                    } else {
	                    }
	                } catch (Exception e) {
	                }
	            }
	           
	            //subfunction to check if the port is reachable
	            public boolean isActive(InetAddress host, int port){
	        	    Socket s = null;
	        	    try {
	        	        s = new Socket();
	        	        s.setReuseAddress(true);
	        	        SocketAddress sa = new InetSocketAddress(host.getHostAddress(), port);
	        	        s.connect(sa, 3000);
	        	        System.out.println("port "+port+" reachable on "+host);
	        	        serverIP= host;
	        	        return true;
	        	    } catch (IOException e) {
	        	        System.out.println("port "+port+" unreachable on "+host.getHostName());
	        	    } finally {
	        	        if (s != null) {
	        	            try {
	        	                s.close();
	        	            } catch (IOException e) {
	        	            }
	        	        }
	        	    }
	        	    return false;
	        	}
	        }).start();     // dont forget to start the thread
	    }
	}
	

	
}

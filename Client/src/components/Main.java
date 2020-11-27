package components;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
		Network n = new Network();
		n.getNetworkActiveIPs(9879);
		Thread.sleep(1000);
//		System.out.println(Network.serverIP.getHostAddress());
		Client.StartConnection(Network.serverIP.getHostAddress(), 9879);
//		Client.StartConnection("192.168.1.12", 9879);
	}

}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//AKKA
public class Server {

	public Server() throws IOException {
		ServerSocket ss = new ServerSocket(5055);
		ArrayList<ClienteThread> cli = new ArrayList<ClienteThread>();
		System.out.println("Começando servidor...");
		Thread t1 = new Thread() {
			public void run() {
				Socket s;
				while (true)
					try {
						s = ss.accept();
						cli.add(new ClienteThread(s));
						System.out.println("Cliente conectou : " + s);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			@Override
			public void run() {
				while (true)
					pool(cli);
			}
		};
		t2.start();

	}

	public void disparar(ArrayList<ClienteThread> arr) throws IOException {
		for (ClienteThread ct : arr) {
			if (ct.atual) {
				System.out.println("Mensagem para: " + ct.getS());
				ct.atual = false;
				ct.getDos().writeUTF("Aviso: ...");
			}
		}
	}

	public void pool(ArrayList<ClienteThread> arr) {
		try {
			disparar(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new Server();
	}

}

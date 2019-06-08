import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		try {
			Scanner scn = new Scanner(System.in);

			InetAddress ip = InetAddress.getByName("");

			Socket s = new Socket(ip, 5055);

			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			Thread t1 = new Thread() {
				@Override
				public void run() {
					try {
						runAux();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				public void runAux() throws Exception{
					while (true) {
						//System.out.println(dis.readUTF());
						String tosend = scn.nextLine();
						dos.writeUTF(tosend);

						if (tosend.toUpperCase().equals("EXIT")) {
							System.out.println("Fechando: " + s);
							s.close();
							break;
						}

					}
				}
			};
			Thread t2 = new Thread() {
				@Override
				public void run() {
					String received;
					while(true)
					try {
						received = dis.readUTF();
						System.out.println(received);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
			};
			//t1.setDaemon(true);
			t1.start();
			//t2.setDaemon(true);
			t2.start();
			
			if(!t1.isAlive() || !t2.isAlive()) {
				scn.close();
				dis.close();
				dos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

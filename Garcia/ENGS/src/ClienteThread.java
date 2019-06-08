import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//Runnable indica que a classe rodara concorrentemente
public class ClienteThread {
	private Socket s;
	String numero1;
	    String numero2;
	    double resultado;
	    int controle;
	    
	
	private DataOutputStream dos;
	public boolean atual;
	// Constructor
	public DataOutputStream getDos() {
		return dos;
	}
	
	public ClienteThread(Socket s) throws IOException  {
		this.s = s;
		atual = false;
		DataInputStream dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		Thread t = new Thread() {
			@Override
			public void run() {
				cThread(dis,dos);
			}
		};
		t.start();
	}

	public Socket getS() {
		return s;
	}

	public void cThread(DataInputStream dis, DataOutputStream dos) {
		String received;
		
		while (true) {
			try {
				dos.writeUTF("Hello server: ");
				dos.writeUTF("Digite Soma ou exit ");
				received = dis.readUTF().toUpperCase();
				System.out.println("Cliente " + s + " mandou:");
				System.out.println(received);
				Message msg = Message.valueOf(received);
		
				if (msg == Message.EXIT) {
					System.out.println(this.s + " Fechou...");
					this.s.close();
					break;
				}else if (msg == Message.SOMA) {
					dos.writeUTF("digite o 1 numero");
					numero1 = dis.readUTF();
					dos.writeUTF("digite o 2 numero");
					numero2 = dis.readUTF();
					controle = Integer.parseInt(numero1) + Integer.parseInt(numero2);
					dos.writeUTF("Resultado: "+controle);
					
				}else {
					handleMessage(dis,dos,msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e1) {
				try {
					dos.writeUTF("Opc inválida");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			dis.close();
			dos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleMessage(DataInputStream dis, DataOutputStream dos2, Message msg) {
		// TODO Auto-generated method stub
		
	}

	private String valuOf(String received) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

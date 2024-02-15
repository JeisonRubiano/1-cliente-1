package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.File;
import model.ImageConverter;
import present.IPresenter;

public class Client {
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	public static final int PORT = 4200;
	 private IPresenter iPresenter;
	
	public Client(IPresenter presenter) throws UnknownHostException, IOException {
		this.iPresenter = presenter;
		this.socket = new Socket("127.0.0.1", PORT);
		this.inputStream = new DataInputStream(socket.getInputStream());
		this.outputStream = new DataOutputStream(socket.getOutputStream());
		System.out.println("comenzo cliente..");
		readResponse();
	}
	
	public void sendImage (String pathImage) throws IOException {
		byte[] imgBytes = ImageConverter.toBinary(pathImage);
		
		String[] formatImageName = pathImage.split("/");
		
		String nameImage = formatImageName[formatImageName.length-1];
		
		outputStream.writeUTF("/add-image");
		outputStream.writeUTF(nameImage);
		outputStream.writeInt(imgBytes.length);
		outputStream.write(imgBytes);
		System.out.println("hizo envio");

	}
	
	public void getImages() throws IOException {
		outputStream.writeUTF("/get-images");
		
		System.out.println("hizo envio");
	}
	
	public void readResponse() {
		new Thread(() -> {
            while (!socket.isClosed()){
                try {
                    if(socket.getInputStream().available() > 0) {
                        String response = inputStream.readUTF();
                        switch (response) {
                            case "/get-images":
                            	
                            	ArrayList<File> files = new ArrayList<>();
                                int totalFiles = inputStream.readInt();
                                for (int i = 0; i < totalFiles; i++) {
									int sizeFile = inputStream.readInt();
									String imageName = inputStream.readUTF();
									byte[] fileBytes = new byte[sizeFile];
									inputStream.read(fileBytes);
									files.add(ImageConverter.toImage(fileBytes, imageName));
								}
                                iPresenter.sendImagesPresenter(files);
                                
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

	}
	
}

package present;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import network.Client;
import view.ClienteView;


public class Presenter implements IPresenter,ActionListener {
	
	private ClienteView clientview;
	private Client client;
	
	public Presenter() throws IOException{
		clientview = new ClienteView(this);
		client = new Client(this);
		//client.sendImage("data/paisaje-3.jpeg");
		
	}

	public void mostrarcliente(){
		clientview.show();
	}

	@Override
	public void sendImagesPresenter(ArrayList<File> files) {
		
		for (int i = 0; i < files.size(); i++) {
			System.out.println("file "+(i+1) + " es "+ files.get(i).getName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
        switch (action) {
            case "Subir": {            
				try {
					clientview.addimagenVariable();
					client.sendImage(clientview.getImagen().getAbsolutePath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                break;
            }
			case "Mostrar": {
				
                clientview.ponerImagenes();

                break;
            }
	}
}
		
	public static void main(String[] args) {
		try {
			Presenter presenter = new Presenter();
			presenter.mostrarcliente();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

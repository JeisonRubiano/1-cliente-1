package view;

import javax.swing.*;

import present.IPresenter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClienteView implements IPresenter{

    private JFrame frame;
    private JButton uploadButton;
    private JButton viewImagesButton;
    private JPanel imagesPanel;
    private File Imagen;
    

    public ClienteView(ActionListener listener) {
        initComponents(listener);

    }
    private void initComponents(ActionListener listener){

        frame = new JFrame("Cliente");
        frame.setSize(300, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        
        uploadButton = new JButton("Subir Imagen");
        uploadButton.addActionListener(listener);
        uploadButton.setActionCommand("Subir");

        viewImagesButton = new JButton("Ver Imágenes");
        viewImagesButton.addActionListener(listener);
        viewImagesButton.setActionCommand("Mostrar");
    
        imagesPanel = new JPanel();
        imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.Y_AXIS));
        JScrollPane imagesScrollPane = new JScrollPane(imagesPanel); 
        imagesScrollPane.setPreferredSize(new Dimension(800, 550)); 

   
        frame.setLayout(new GridLayout(2, 1));
        frame.add(imagesScrollPane);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(uploadButton);
        buttonsPanel.add(viewImagesButton);
        frame.setLayout(new BorderLayout());
        frame.add(imagesScrollPane, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
    }



    public void addImage(ImageIcon imageIcon) {
        JLabel imageLabel = new JLabel(imageIcon);
        imagesPanel.add(imageLabel);
        frame.revalidate();
        frame.repaint();
    }

    public void addimagenVariable() throws IOException{
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " + selectedFile);
            Imagen= selectedFile;
           
        }
    }
    public void ponerImagenes(){
        JPanel colorPanel = new JPanel();
                colorPanel.setBackground(getRandomColor());
                colorPanel.setPreferredSize(new Dimension(250, 250)); 
                colorPanel.setMaximumSize(new Dimension(250, 250));
                imagesPanel.add(colorPanel);
                frame.revalidate();
                frame.repaint();
    }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    private Color getRandomColor() {
        return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }

    public File getImagen() {
        return Imagen;
    }
    @Override
    public void sendImagesPresenter(ArrayList<File> files) {
        
        for (int i = 0; i < files.size(); i++) {
			System.out.println("file "+(i+1) + " es "+ files.get(i).getName());
		}
        throw new UnsupportedOperationException("Unimplemented method 'sendImagesPresenter'");
    }
    
}


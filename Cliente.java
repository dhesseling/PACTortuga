package PacTortugas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Cliente {
	public static void main(String[] args) {
		MarcoCliente mimarco=new MarcoCliente();  
                JOptionPane.showMessageDialog(null,"Bienvenido al juego de las tortugas"
                        + "\n Elija una de las siguientes 5 opciones:"
                        + "\n nº1) Crear una nueva tortuga"
                        + "\n nº2) Eliminar una tortuga"
                        + "\n nº3) Mostrar tortugas"
                        + "\n nº4) Iniciar una carrera"
                        + "\n nº5) Salir");
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		setBounds(200,200,200,150);
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		add(milamina);	
		setVisible(true);
		}	
}

class LaminaMarcoCliente extends JPanel{
	public LaminaMarcoCliente(){
		JLabel texto=new JLabel("Turtle & Furious");
		add(texto);
                
                campo1=new JTextField(10);
		add(campo1);
	
		miboton=new JButton("Enviar");
                EnviaTexto mievento = new EnviaTexto();
                miboton.addActionListener(mievento);
		add(miboton);	
	}
	
	private class EnviaTexto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //Creamos un socket para que se crea un socket cada vez que pulsemos el botón.
                Socket miSocket = new Socket ("192.168.173.2",1234);
                
                DataOutputStream flujoSalida = new DataOutputStream(miSocket.getOutputStream());
                flujoSalida.writeUTF(campo1.getText());

            } catch (IOException ex) {
                Logger.getLogger(LaminaMarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
    }	
    private JTextField campo1;
    private JButton miboton;
}

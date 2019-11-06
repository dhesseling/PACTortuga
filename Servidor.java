package PacTortugas;

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
/*import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;*/
import java.net.ServerSocket;
import java.net.Socket;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/

public class Servidor  {

	public static void main(String[] args) {
		MarcoServidor mimarco=new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);	
		JPanel milamina= new JPanel();
		milamina.setLayout(new BorderLayout());
		areatexto=new JTextArea();
		milamina.add(areatexto,BorderLayout.CENTER);
		add(milamina);
		setVisible(true);
                Thread miHilo = new Thread(this);
                miHilo.start();
		}
        
    @Override
    public void run() {
            try {
                ServerSocket servidor = new ServerSocket(1234);
                
                while(true){
                    Socket miSocket = servidor.accept();
                    DataInputStream flujoEntrada = new DataInputStream(miSocket.getInputStream());
                    String mensajeTexto = flujoEntrada.readUTF();
                    Carrera carrera = new Carrera();
                     
                    //Tortuga tortuga = new Tortuga();
                    
                    if(mensajeTexto.equals("1")){
                        areatexto.append("\n Ha seleccionado la primera opción"
                                + "\n El resultado aparecerá en la consola del Servidor");
                        
                        carrera.start();
                        
                        //En esta bloque intentaba crear un objeto tortuga, para enviarlos al cliente.
                        
                        /*ObjectOutputStream outObjeto = new ObjectOutputStream(miSocket.getOutputStream());
                        outObjeto.writeObject(tortuga);
                        System.out.println("Envio: " + tortuga.getTortuga());
                        ObjectInputStream inObjeto = new ObjectInputStream(miSocket.getInputStream()); 
                        Tortuga tortu = (Tortuga) inObjeto.readObject();
                        
                        outObjeto.close();
                        inObjeto.close();*/
                        
                    }else if(mensajeTexto.equals("2")){
                        areatexto.append("\n Ha seleccionado la segunda opción");
                    }else if(mensajeTexto.equals("3")){
                        areatexto.append("\n Ha seleccionado la tercera opción");
                    }
                    else{
                        areatexto.append("Por favor, introduzca una de las siguientes opciones:"
                                + "\n 1 - Crear tortuga "
                                + "\n 2 - Eliminar tortuga"
                                + "\n 3 - Mostrar tortugas"
                                + "\n 4 - Carrera"
                                + "\n 5 - Salir");
                    }
                    
                    miSocket.close();
                
                }
          
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } /*catch (ClassNotFoundException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        
    }
    private JTextArea areatexto;
}

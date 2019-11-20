package PacTortugas;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


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
                int gana=0;
                ArrayList <Tortuga> listaTortuga = new ArrayList();
                ArrayList <Carrera> listaCarrera = new ArrayList();
                areatexto.append("\n Por favor, introduzca una de las siguientes opciones:"
                                + "\n 1 - Crear tortuga "
                                + "\n 2 - Eliminar tortuga"
                                + "\n 3 - Mostrar tortugas"
                                + "\n 4 - Carrera"
                                + "\n 5 - Salir");
                     
                
                while(true){
                    Socket miSocket = servidor.accept();
                    DataInputStream flujoEntrada = new DataInputStream(miSocket.getInputStream());
                    String mensajeTexto = flujoEntrada.readUTF();
                    
                    if(mensajeTexto.equals("1")){
                        areatexto.append("\n Ha seleccionado la primera opción");
                        Tortuga tortuga = new Tortuga();
                        tortuga.setNombre();
                        tortuga.setDorsal();
                        Carrera carrera = new Carrera();
                       
                        listaTortuga.add(tortuga);
                        listaCarrera.add(carrera);
                        areatexto.append("\n Se ha agregado una tortuga");
                        areatexto.append(tortuga.getTortuga());
                        
                        
                    }else if(mensajeTexto.equals("2")){
                        areatexto.append("\n Cuenta con "+ listaTortuga.size() + " tortugas"
                        +"\n ¿Cuál desea eliminar? Siendo la primera 0, la segunda 1...");
                        listaTortuga.remove(Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca el número:")));
                        areatexto.append("\n Ha eliminado una tortuga");
                        
                        
                    }else if(mensajeTexto.equals("3")){
                        areatexto.append("\n Ha seleccionado la tercera opción");
                        for(int i=0; i<listaTortuga.size();i++){
                            areatexto.append("\n"+listaTortuga.get(i).getTortuga());
                        }   
                    }else if(mensajeTexto.equals("4")){
                        areatexto.append("\n Ha seleccionado la opción carrera");
                          for(int i=0; i<listaTortuga.size();i++){
                            listaCarrera.get(i).start();
                            gana = i;
                        } 
                        areatexto.append("\n Ha ganado la tortuga: " + listaTortuga.get(gana).getTortuga());
                    }else if(mensajeTexto.equals("5")){
                        areatexto.append("\n Ha seleccionado salir");
                        servidor.close();
                        
                    }
                    else{
                        areatexto.append("\n Por favor, introduzca una de las siguientes opciones:"
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
            }
    }
    private JTextArea areatexto;
}

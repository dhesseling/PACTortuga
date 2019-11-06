package PacTortugas;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Tortuga {
    
    private String nombre;
    private String dorsal;
    Scanner scan = new Scanner(System.in);
    
    public Tortuga(){
    }
    public void setNombre(){
        nombre = JOptionPane.showInputDialog(null,"Introduzca el nombre de la tortuga");
    }
    public void setDorsal(){
        dorsal = JOptionPane.showInputDialog(null,"Introduzca el dorsal de la tortuga");
    }
    public String getTortuga(){
        return (" la tortuga: " + nombre + " y su dorsal es el número: " + dorsal + " ha ganado la carrera");
    }
}

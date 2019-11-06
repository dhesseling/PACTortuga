package PacTortugas;
import java.util.Random;

public class Carrera extends Thread{
    
    private boolean activado;
    
    public Carrera(){//Constructor
        activado = true;
}
    public void desactivado(){
        activado = false;
    }
    
    @Override
    public void run(){
        while(activado){
            Random r1 = new Random();
            int numero = r1.nextInt(50);
            Tortuga tortuga = new Tortuga();
            tortuga.setNombre();
            tortuga.setDorsal();
            
            while(numero < 500){
                numero = numero + r1.nextInt(50);
                System.out.println(numero);
            }
            System.out.println("Ha ganado" + tortuga.getTortuga());
            desactivado();
        }
    }

    
}

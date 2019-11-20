package PacTortugas;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            try {
                Thread.sleep(10000);
                System.out.println("Carrera " + getName());
            } catch (InterruptedException ex) {
                Logger.getLogger(Carrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            while(numero < 500){
                numero = numero + r1.nextInt(50);
                System.out.println(numero + getName());
            }
            System.out.println("WINNER "+getName());
            desactivado();
        }
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableroVirtual;

import comunicacion.Comunicacion;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author carop
 */
public abstract class Tablero extends JFrame {

    Comunicacion comuni = new Comunicacion();
    String elturno;
    String turnoLocal;
    public static int banderaAntiJugada = 0;

    public Tablero() {

    }

    public void setBanderaAntiJugada(int banderaAntiJugada) {
        this.banderaAntiJugada = banderaAntiJugada;
    }

    public void intruccionEmpezarPartida(String Estacion) {
        //   vistaDeUsuario.jButton2.setEnabled(false);
        //    vistaDeUsuario.jLabel1.setText(Estacion);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void setComunicacion(Comunicacion comu) {
        this.comuni = comu;
    }

    /* public void enviar(byte [] trama) {
     trama[2] = (byte) Short.parseShort("11111111", 2); // instruccion
        

     /* System.out.print("Iniciar P Mensaje enviado: "
     + " " + pasarByteAString(enviolocal[0])
     + " " + pasarByteAString(enviolocal[1])
     + " " + pasarByteAString(enviolocal[2])
     + "\n");
     comuni.puertoSalida.writeBytes(enviolocal, enviolocal.length);
     comuni.Escribir2(trama);   
     }*/
    public void jugada(int jugador, int count, byte[] trama) {
        trama[2] = (byte) Short.parseShort("11111111", 2); // instruccion
        trama[3] = (byte) Short.parseShort(comuni.pasarEnteroaByte(jugador), 2); //jugador a cambiar
        trama[4] = (byte) Short.parseShort(comuni.pasarEnteroaByte(count), 2); //numero de casillas a moverse
        trama[5] = (byte) Short.parseShort(comuni.getEstacion(), 2); //origen de trama

        comuni.Escribir2(trama);
    }

    public void antijugada(int jugador, int count, byte[] trama) {
        trama[2] = (byte) Short.parseShort("11111111", 2); // instruccion
        trama[3] = (byte) Short.parseShort(comuni.pasarEnteroaByte(jugador), 2); //jugador a cambiar
        trama[4] = (byte) Short.parseShort(comuni.pasarEnteroaByte(count), 2); //numero de casillas a moverse
        trama[5] = (byte) Short.parseShort(comuni.getEstacion(), 2); //origen de trama
        trama[6] = (byte) Short.parseShort("11111111", 2); // sentido

        System.out.println("CAMBIO EL SENTIDO");
        comuni.Escribir2(trama);
    }

    public void recibirtrama(byte[] trama) {
        int jugador = comuni.pasarByteaEntero(comuni.pasarByteAString(trama[3]));
        int cuenta = comuni.pasarByteaEntero(comuni.pasarByteAString(trama[4]));
        //  this.elturno = comuni.pasarByteAString(trama[5]);
        coinPosition(jugador, cuenta);

    }

    /* public void recibirtramamov (byte [] trama){
     this.elturno =   comuni.pasarByteAString(trama[5]);
     //return turno;
     //  coinPosition(jugador,cuenta);
       
     }*/
 /*public void tramaturno (byte [] trama, int jugador){
     trama[6]=(byte) Short.parseShort(comuni.pasarEnteroaByte(jugador-1), 2);
     comuni.Escribir2(trama);
     }*/
 /* public String cambiarturno(String estacion) {
     System.out.println("es el turno de " + estacion);
     return estacion;

     }*/

 /*  public void habilitarturno (JButton boton){
          
     }*/
 /* public void estacion (JLabel estacion){
     estacion.setText(comuni.getEstacion());
     }*/
    public int asignarturno(JLabel turno, JLabel estacion, String Estacion) {

        String jugadoractual;

        if (Estacion != null) {
            jugadoractual = Estacion;
        } else {
            jugadoractual = comuni.getEstacion();
        }

        if (jugadoractual.equals("00000000")) {
            System.out.println("entro al primer if");
            turno.setText("Jugador A");
            estacion.setText(comuni.getEstacionShort());

            return 1;
        }
        if (jugadoractual.equals("00000001")) {
            turno.setText("Jugador B");
            estacion.setText(comuni.getEstacionShort());
            return 2;

        }
        if (jugadoractual.equals("00000010")) {
            turno.setText("Jugador C");
            estacion.setText(comuni.getEstacionShort());
            return 3;
        }
        if (jugadoractual.equals("00000011")) {
            turno.setText("Jugador D");
            estacion.setText(comuni.getEstacionShort());
            return 4;
        }

        return 0;
    }

    public void coinPosition(int a, int b) {

    }

    public String getturno() {
        return this.turnoLocal;
    }

    public abstract void turno(String turno);

    public abstract void chequear(String turno);

}

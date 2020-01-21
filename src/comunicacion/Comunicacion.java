/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import TableroVirtual.Tablero;
import TableroVirtual.TableroVirtual1;
import TableroVirtual.TableroVirtual2;
import TableroVirtual.TableroVirtual3;
import TableroVirtual.TableroVirtual4;
import TableroVirtual.TableroVirtual5;
import TableroVirtual.TableroVirtual6;
import com.fazecast.jSerialComm.SerialPort;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Comunicacion implements Runnable {

    SerialPort puertoEntrada;
    SerialPort puertoSalida;
    int diesResult;
    String estacion;
    String tablero;
    Tablero tablerogenerico;
    ArrayList<String> jugadores = new ArrayList<String>();
    public static Tablero mitablero;
    // String[] jugadores = new String[5];

    public Comunicacion(int entrada, int salida) {
        puertoEntrada = SerialPort.getCommPorts()[entrada];
        puertoEntrada.setComPortParameters(2400, 8, 0, 1);
        puertoEntrada.openPort();
        if (entrada != salida) {
            // Los puertos de salida y entrada son diferentes(para realizar pruebas en una sola maquina)
            puertoSalida = SerialPort.getCommPorts()[salida];
            puertoSalida.setComPortParameters(2400, 8, 0, 1);
            //puertoSalida.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 1, 1);
            puertoSalida.openPort();
        } else {
            // COnfiguracion real en el laboratorio
            puertoSalida = puertoEntrada;
        }

    }

    public Comunicacion() {

    }

    public void Escuchar() {
        Comunicacion proceso1 = this;
        new Thread(proceso1).start();
    }

    public void Escribir() {

        do {

            Random dies = new Random();
            diesResult = dies.nextInt(7);
            
        } while ((diesResult == 0) ||(diesResult == 1)||(diesResult == 5));
        
        diesResult=3;

        System.out.print(diesResult);
        byte[] envio = new byte[7];
        envio[0] = (byte) Short.parseShort(this.estacion, 2); //estacion origen
        envio[1] = (byte) Short.parseShort(pasarEnteroaByte(diesResult), 2); //tablero
        envio[2] = (byte) Short.parseShort("00000000", 2); // instruccion
        envio[3] = (byte) Short.parseShort("00000000", 2); // ficha que se movera
        envio[4] = (byte) Short.parseShort("00000000", 2); // movimiento
        envio[5] = (byte) Short.parseShort(this.estacion, 2); // origen trama
        envio[6] = (byte) Short.parseShort("00000000", 2); // sentido horario
        System.out.print("Iniciar P Mensaje enviado: "
                + " " + pasarByteAString(envio[0])
                + " " + pasarByteAString(envio[1])
                + " " + pasarByteAString(envio[2])
                + " " + pasarByteAString(envio[3])
                + " " + pasarByteAString(envio[4])
                + " " + pasarByteAString(envio[5])
                + " " + pasarByteAString(envio[6])
                + "\n");
        this.puertoSalida.writeBytes(envio, envio.length);

    }

    public void Escribir2(byte[] enviolocal) {
        /*byte[] envio = new byte[3];
         envio[0] = (byte) Short.parseShort(pasarEnteroaByte(diesResult), 2);
         envio[1] = (byte) Short.parseShort("11110000", 2);
         envio[2] = (byte) Short.parseShort("00000000", 2);*/

        System.out.print("Iniciar P Mensaje enviado: "
                + " " + pasarByteAString(enviolocal[0])
                + " " + pasarByteAString(enviolocal[1])
                + " " + pasarByteAString(enviolocal[2])
                + " " + pasarByteAString(enviolocal[3])
                + " " + pasarByteAString(enviolocal[4])
                + " " + pasarByteAString(enviolocal[5])
                + " " + pasarByteAString(enviolocal[6])
                + "\n");
        this.puertoSalida.writeBytes(enviolocal, enviolocal.length);
    }

    public String pasarByteAString(byte b) {
        String retorno = Integer.toBinaryString(b & 0xFF);
        //Para asegurar que sean 8 caracteres(llenar de ceros a la izquierda)
        while (retorno.length() < 8) {
            retorno = "0" + retorno;
        }
        return retorno;
    }

    public String pasarEnteroaByte(int entero) {
        int numero = entero;
        String binario = "";
        if (numero > 0) {
            while (numero > 0) {
                if (numero % 2 == 0) {
                    binario = "0" + binario;
                } else {
                    binario = "1" + binario;
                }
                numero = (int) numero / 2;
            }
        } else if (numero == 0) {
            binario = "0";
        }
        return binario;
    }

    public int pasarByteaEntero(String entero) {
        int longitud = entero.length();//Numero de digitos que tiene nuestro binario
        int resultado = 0;//Aqui almacenaremos nuestra respuesta final
        int potencia = longitud - 1;
        for (int i = 0; i < longitud; i++) {//recorremos la cadena de numeros
            if (entero.charAt(i) == '1') {
                resultado += Math.pow(2, potencia);
            }
            potencia--;//drecremantamos la potencia
        }
        return resultado;
    }

    public String getEstacion() {
        return estacion;
    }

    public String getEstacionShort() {

        if (estacion.equals("00000000")) {
            return "00";
        }
        if (estacion.equals("00000001")) {
            return "01";
        }
        if (estacion.equals("00000010")) {
            return "10";
        }
        if (estacion.equals("00000011")) {
            return "11";
        }
        return "00";

    }

    public void setEstacion(String jugador) {
        this.estacion = jugador;
        jugadores.add(jugador);
        System.out.print("estacion" + estacion);

    }

    public void setTablero(Tablero tablero) {
        this.mitablero = tablero;
    }

    public String EstacionSiguiente(String estacion) {
        if (estacion.equals("00000000")) {
            return "00000001";
        }
        if (estacion.equals("00000001")) {
            return "00000010";
        }
        if (estacion.equals("00000010")) {
            return "00000011";
        }
        if (estacion.equals("00000011")) {
            return "00000000";
        }
        return "00000000";
    }

    public String EstacionAnterior(String estacion) {
        if (estacion.equals("00000000")) {
            return "00000011";
        }
        if (estacion.equals("00000001")) {
            return "00000000";
        }
        if (estacion.equals("00000010")) {
            return "00000001";
        }
        if (estacion.equals("00000011")) {
            return "00000010";
        }
        return "00000000";
    }

    public void seleccionartablero(byte[] envio) {
        tablero = pasarByteAString(envio[1]);
        System.out.println(tablero);
        switch (tablero) {

            case "00000001": //caso de tablero 1

                Tablero Mitablero1 = new TableroVirtual1(envio);
                setTablero(Mitablero1);
                Mitablero1.setComunicacion(this);
                Mitablero1.turno("00000000");

                break;
            case "00000010": //caso de tablero 2

                Tablero Mitablero2 = new TableroVirtual2(envio);
                setTablero(Mitablero2);
                Mitablero2.setComunicacion(this);
                Mitablero2.turno("00000000");

                break;
            case "00000011": //caso de tablero 3
                Tablero Mitablero3 = new TableroVirtual3(envio);
                setTablero(Mitablero3);
                Mitablero3.setComunicacion(this);
                Mitablero3.turno("00000000");

                break;
            case "00000100": //caso de tablero 4

                Tablero Mitablero4 = new TableroVirtual4(envio);
                setTablero(Mitablero4);
                Mitablero4.setComunicacion(this);
                Mitablero4.turno("00000000");
                break;

            case "00000101": //caso de tablero 5 
                Tablero Mitablero5 = new TableroVirtual5(envio);
                setTablero(Mitablero5);
                Mitablero5.setComunicacion(this);
                Mitablero5.turno("00000000");
                break;
            case "00000110": //caso de tablero 6 
                Tablero Mitablero6 = new TableroVirtual6(envio);
                setTablero(Mitablero6);
                Mitablero6.setComunicacion(this);
                Mitablero6.turno("00000000");
                break;
        }

    }

    @Override
    public void run() {

        do {
            byte[] readBuffer = null; // Bytes para almacenar la informacion
            try {

                while (puertoEntrada.bytesAvailable() < 7) {
                }
                readBuffer = new byte[7];
                int numRead = puertoEntrada.readBytes(readBuffer, 7);

                //Comprobacion de que se recibio
                System.out.print("Se encontro el mensaje:\n");
                for (int i = 0; i < numRead; i++) {
                    System.out.println(" "
                            + pasarByteAString(readBuffer[i]));
                }

                String instruccion = pasarByteAString(readBuffer[2]); //campo de instruccion
                String origenTrama = pasarByteAString(readBuffer[5]); //quien manda la trama
                String sentido = pasarByteAString(readBuffer[6]); //sentido 
                switch (instruccion) {
                    case "00000000": //caso de generar tableros

                        if (!(origenTrama.equals(estacion))) { //CAMBIAR PARA 4 A 00000000

                            seleccionartablero(readBuffer);
                            this.puertoSalida.writeBytes(readBuffer, readBuffer.length
                            );
                        } else {
                            seleccionartablero(readBuffer);
                            System.out.print("Completo el ciclo");

                        }
                        break;
                    case "11111111": //inicia turno de alguien
                        System.out.println("entro en instruccion1 " + this.getEstacion());
                        System.out.println("origen Trama" + origenTrama);

                        if (!(origenTrama.equals(estacion))) { //CAMBIAR PARA 4 A 00000000

                            //   if ("111".equals(EstacionSiguiente(this.estacion)) && !(origenTrama.equals(estacion))) {
                            System.out.println("entro en instruccion2");
                            this.mitablero.recibirtrama(readBuffer);
                            this.puertoSalida.writeBytes(readBuffer, readBuffer.length);
                            if (sentido.equals("00000000")) {
                                this.mitablero.turno(EstacionSiguiente(this.mitablero.getturno()));
                                this.mitablero.banderaAntiJugada = 0;

                                if (origenTrama.equals(this.mitablero.getturno())) {
                                    this.mitablero.turno(EstacionSiguiente(this.mitablero.getturno()));
                                }
                            } else {

                                this.mitablero.turno(EstacionAnterior(origenTrama)); //turno anterior al origen de trama
                                this.mitablero.banderaAntiJugada = 1;                 //bandera para saber si hubo cambio de sentido
                                this.mitablero.chequear(this.mitablero.getturno()); //chequeo boton

                                if (origenTrama.equals(this.mitablero.getturno())) {
                                    this.mitablero.turno(EstacionAnterior(this.mitablero.getturno()));
                                }

                            }
                            System.out.println("origen Trama" + origenTrama);
                            System.out.println("get turno" + this.mitablero.getturno());
                            System.out.println("TURNO ANTERIOR" + (this.mitablero.getturno()));
                        } else {

                            System.out.print("Completo el ciclo");
                        }

                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}

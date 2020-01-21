/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableroVirtual;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


import TableroVirtual.Tablero;
import comunicacion.Comunicacion;

/**
 *
 * @author carop
 */
public class TableroVirtual6 extends Tablero implements ActionListener {

//Comunicacion comuni = new Comunicacion();
    JLabel lblYou = new JLabel("Jugador A");
    JLabel lblComp = new JLabel("Jugador B");
    JLabel lblComp3 = new JLabel("Jugador C");
    JLabel lblComp4 = new JLabel("Jugador D");
    JLabel Turno = new JLabel("Es el turno de:");
    JLabel Estacion = new JLabel("Estacion");

    JButton jugar = new JButton("Lanzar Dado");
    JButton newGame = new JButton("Empezar partida");

    JLabel NumerodadoB = new JLabel("");
    JLabel NumerodadoA = new JLabel("");
    JLabel NumerodadoC = new JLabel("");
    JLabel NumerodadoD = new JLabel("");
    JLabel lblTurno = new JLabel("");
    JLabel lblEstacion = new JLabel("");

       JLabel BPosition = new JLabel(new ImageIcon("C:\\Users\\admin\\Desktop\\Proyecto Redes\\fichas2.png"));
    JLabel APosition = new JLabel(new ImageIcon("C:\\Users\\admin\\Desktop\\Proyecto Redes\\fichasaz.png"));
    JLabel CPosition = new JLabel(new ImageIcon("C:\\Users\\admin\\Desktop\\Proyecto Redes\\fichasn.png"));
    JLabel DPosition = new JLabel(new ImageIcon("C:\\Users\\admin\\Desktop\\Proyecto Redes\\fichasv.png"));
    
    HashMap<Integer, Integer> ladder = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> snake = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> sentido = new HashMap<Integer, Integer>();

    int ACount = 1;
    int BCount = 1;
    int CCount = 1;
    int DCount = 1;
    int jugador;
    int playAgain;

    int w = 30, h = 25; // tamaño de las fichas del jugador
    int x = 0, y = 0;

    //arreglos para guardar los valores del dado
    StringBuffer BList = new StringBuffer();
    StringBuffer AList = new StringBuffer();
    StringBuffer CList = new StringBuffer();
    StringBuffer DList = new StringBuffer();

    Random dies = new Random();
    byte[] enviolocal = new byte[7];

    public TableroVirtual6(byte[] envio) {
       super();
        enviolocal = envio;
        setLayout(null);
        // setLayout(new BorderLayout());
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //getContentPane().setBackground(Color.LIGHT_GRAY);
        setBounds(100, 100, 800, 800);
        JLabel background2 = new JLabel(new ImageIcon("C:\\Users\\admin\\Desktop\\Proyecto Redes\\fondoPrueba.jpg"));
        background2.setBounds(0, 0, 800, 800);
        add(background2);
        
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\admin\\Desktop\\Proyecto Redes\\tablero6.jpg"));
        background.setBounds(-15, -15, 490, 520);
        background2.add(background);

        JLabel background3 = new JLabel(new ImageIcon("C:\\Users\\admin\\Desktop\\Proyecto Redes\\serpientes_escaleras.jpg"));
        background3.setBounds(400, 600, 300, 200);
        background2.add(background3);

        Turno.setBounds(510, 150, 100, 30);
        background2.add(Turno);

        Estacion.setBounds(510, 130, 100, 30);
        background2.add(Estacion);

        jugar.setBounds(510, 200, 150, 30);
        background2.add(jugar);

        lblComp.setBounds(520, 300, 60, 30);
        background2.add(lblComp);

        lblYou.setBounds(520, 250, 60, 30);
        background2.add(lblYou);

        lblComp3.setBounds(520, 350, 60, 30);
        background2.add(lblComp3);

        lblComp4.setBounds(520, 400, 60, 30);
        background2.add(lblComp4);

        lblTurno.setBounds(600, 150, 60, 30);
        background2.add(lblTurno);

        lblEstacion.setBounds(600, 130, 60, 30);
        background2.add(lblEstacion);

//     estacion(lblEstacion);
        NumerodadoA.setBounds(600, 250, 60, 30);
        NumerodadoA.setOpaque(true);
        //lblYouNo.setBackground(Color.BLUE);
        NumerodadoA.setForeground(Color.BLACK);
        background2.add(NumerodadoA);

        NumerodadoB.setBounds(600, 300, 60, 30);
        NumerodadoB.setOpaque(true);
        // lblCompNo.setBackground(Color.RED);
        NumerodadoB.setForeground(Color.BLACK);
        background2.add(NumerodadoB);

        NumerodadoC.setBounds(600, 350, 60, 30);
        NumerodadoC.setOpaque(true);
        // lblCompNo.setBackground(Color.RED);
        NumerodadoC.setForeground(Color.BLACK);
        background2.add(NumerodadoC);

        NumerodadoD.setBounds(600, 400, 60, 30);
        NumerodadoD.setOpaque(true);
        // lblCompNo.setBackground(Color.RED);
        NumerodadoD.setForeground(Color.BLACK);
        background2.add(NumerodadoD);

        /*newGame.setBounds(510, 140, 100, 30);
        background2.add(newGame);*/
        coinPosition(2, BCount);
      
        BPosition.setBounds(x, y, w, h);
        
       
        BPosition.setBackground(Color.RED);
        background.add(BPosition);

        coinPosition(1, ACount);
     
        APosition.setBounds(x, y, w, h);
        
        APosition.setBackground(Color.BLUE);
        background.add(APosition);

        coinPosition(3, CCount);
        CPosition.setBounds(x, y, w, h);
       
        CPosition.setBackground(Color.GREEN);
        background.add(CPosition);

        coinPosition(4, DCount);
        DPosition.setBounds(x, y, w, h);
        
        DPosition.setBackground(Color.ORANGE);
        background.add(DPosition);

        repaint();

        snake.put(17,6);
        snake.put(40,23);
        snake.put(30,13);
        snake.put(75,32);
        snake.put(57,25);
        snake.put(61,41);
        snake.put(99,79);
        snake.put(93,73);
        snake.put(71,53);
        snake.put(85,65);

        ladder.put(2,22);
        ladder.put(15,27);
        ladder.put(12,48);
        ladder.put(24,88);
        ladder.put(50,52);
        ladder.put(43,80);
        ladder.put(63,86);
        ladder.put(84,98);
        ladder.put(72,92);

        sentido.put(17, 17);
        sentido.put(41, 41);
        sentido.put(81, 81);

        newGame.addActionListener(this);
        jugar.addActionListener(this);
        //jugar.setEnabled(false);

    }

    public void chequearBoton(String estacion) {

        if (this.getturno().equals(estacion)) {
            this.jugar.setEnabled(true);

        }

    }

    public void turno(String turno) {
        System.out.println("turno en tablero " + turno);
        if ("00000000".equals(turno)) {
            System.out.println("turno del jugador 1");

            if (!comuni.getEstacion().equals(turno)) {
                this.jugar.setEnabled(false);

            } else {
                this.jugar.setEnabled(true);
            }

        }
        if ("00000001".equals(turno)) {
            System.out.println("turno del jugador 2");
            System.out.println("estacion en tablero" + comuni.getEstacion());
            if (!comuni.getEstacion().equals(turno)) {

                System.out.println("entro al if del boton");

                this.jugar.setEnabled(false);
            } else {
                this.jugar.setEnabled(true);
            }

        }
        if ("00000010".equals(turno)) {
            System.out.println("turno del jugador 3");
            if (!comuni.getEstacion().equals(turno)) {
                this.jugar.setEnabled(false);
            } else {
                this.jugar.setEnabled(true);
            }

        }
        if ("00000011".equals(turno)) {
            System.out.println("turno del jugador 4");
            if (!comuni.getEstacion().equals(turno)) {
                this.jugar.setEnabled(false);
            } else {
                this.jugar.setEnabled(true);
            }

        }
        turnoLocal = turno;
         jugador =  asignarturno(lblTurno,lblEstacion,getturno());
    }

    public void setTurno(JLabel Turno) {
        this.Turno = Turno;
    }

    public void chequear(String turno) {
        if (this.comuni.getEstacion().equals(turno)) {
            this.jugar.setEnabled(true);
        }
    }

    public void desabilitar(String turno) {
        if (this.comuni.getEstacion().equals(turno)) {
            this.jugar.setEnabled(false);
        }
    }

    /*private void newGameActionPerformed (java.awt.event.ActionEvent evt) {                                          
     // TODO add your handling code here:
     comuni.setEstacion("00");
     enviolocal[0] =(byte) Short.parseShort("00000000", 2); //empezar partida
     enviolocal [1]=(byte) Short.parseShort("11111111", 2); //
     comuni.Escribir2(enviolocal);
     }*/
    public void main(String args[]) throws Throwable {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new TableroVirtual6(enviolocal);
    }

    public void actionPerformed(ActionEvent e) {
    
       
        if (e.getSource() == newGame) {
            ACount = 1;
            BCount = 1;
            coinPosition(2, BCount);
            BPosition.setBounds(x, y, w, h);
            coinPosition(1, ACount);
            APosition.setBounds(x, y, w, h);
            NumerodadoA.setText("");
            NumerodadoB.setText("");
            jugar.setVisible(true);
            repaint();
        } else if (e.getSource() == jugar) {

            this.jugar.setEnabled(false);
            BList = new StringBuffer();
            AList = new StringBuffer();
            CList = new StringBuffer();
            DList = new StringBuffer();

            if (jugador == 1) {
                playAgain = playDies(1);
                coinPosition(1, ACount);
                APosition.setBounds(x, y, w, h);
                repaint();
                if (ladder.containsKey(ACount)) {
                    ACount = ladder.get(ACount);
                } else if (snake.containsKey(ACount)) {
                    ACount = snake.get(ACount);
                }
                coinPosition(1, ACount);
                APosition.setBounds(x, y, w, h);
                System.out.println("Acount " + ACount);
                /*   System.out.print("envio local:\n");
                 for(int i=0; i<numRead;i++) System.out.println(" "+
                 pasarByteAString(readBuffer[i]));*/
                
                //Verifica si cayo en casilla especial y verifica bandera
                //Dependiendo de cual sea el caso se dara una antijugada o una jugada
                if (sentido.containsKey(ACount) && this.banderaAntiJugada == 1) {
                    antijugada(1, ACount, enviolocal);

                } else if (sentido.containsKey(ACount) && this.banderaAntiJugada == 0) {
                    TableroVirtual1.banderaAntiJugada = 1;
                    antijugada(1, ACount, enviolocal);

                } else if (!sentido.containsKey(ACount) && this.banderaAntiJugada == 1) {
                    antijugada(1, ACount, enviolocal);

                } else if (!sentido.containsKey(ACount) && this.banderaAntiJugada == 0) {
                    jugada(1, ACount, enviolocal);
                } else {
                    jugada(1, ACount, enviolocal);
                }

                repaint();
                if (ACount == 100) {
                    jugar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Jugador A ganó");
                } else if (playAgain == 0) {
                    AList = new StringBuffer();
                }

            }

            if (jugador == 2) { //turno del Jugador B
                playAgain = playDies(2);
                coinPosition(2, BCount);
                BPosition.setBounds(x, y, w, h);
                repaint();
                if (ladder.containsKey(BCount)) {
                    BCount = ladder.get(BCount);
                } else if (snake.containsKey(BCount)) {
                    BCount = snake.get(BCount);
                }
                coinPosition(2, BCount);
                BPosition.setBounds(x, y, w, h);

                if (sentido.containsKey(BCount) && this.banderaAntiJugada == 1) {
                    antijugada(2, BCount, enviolocal);

                } else if (sentido.containsKey(BCount) && this.banderaAntiJugada == 0) {
                    this.banderaAntiJugada = 1;
                    antijugada(2, BCount, enviolocal);

                } else if (!sentido.containsKey(BCount) && this.banderaAntiJugada == 1) {
                    antijugada(2, BCount, enviolocal);

                } else if (!sentido.containsKey(BCount) && this.banderaAntiJugada == 0) {
                    jugada(2, BCount, enviolocal);

                } else {
                    jugada(2, BCount, enviolocal);
                }

                repaint();
                if (BCount == 100) {
                    jugar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Jugador B ganó");
                    // break;
                } else if (playAgain == 0) {
                    BList = new StringBuffer();
                }

            }

            if (jugador == 3) { //turno del Jugador C
                playAgain = playDies(3);
                coinPosition(3, CCount);
                CPosition.setBounds(x, y, w, h);
                repaint();
                if (ladder.containsKey(CCount)) {
                    CCount = ladder.get(CCount);
                } else if (snake.containsKey(CCount)) {
                    CCount = snake.get(CCount);
                }
                coinPosition(3, CCount);
                CPosition.setBounds(x, y, w, h);

                if (sentido.containsKey(CCount) && this.banderaAntiJugada == 1) {
                    antijugada(3, CCount, enviolocal);

                } else if (sentido.containsKey(CCount) && this.banderaAntiJugada == 0) {
                    this.banderaAntiJugada = 1;
                    antijugada(3, CCount, enviolocal);

                } else if (!sentido.containsKey(CCount) && this.banderaAntiJugada == 1) {
                    antijugada(3, CCount, enviolocal);

                } else if (!sentido.containsKey(CCount) && this.banderaAntiJugada == 0) {
                    jugada(3, CCount, enviolocal);
                } else {
                    jugada(3, CCount, enviolocal);
                }

                repaint();
                if (CCount == 100) {
                    jugar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Jugador C ganó");
                    // break;
                } else if (playAgain == 0) {
                    CList = new StringBuffer();
                }

            }

            if (jugador == 4) { //turno del Jugador C
                playAgain = playDies(4);
                coinPosition(4, DCount);
                DPosition.setBounds(x, y, w, h);
                repaint();
                if (ladder.containsKey(DCount)) {
                    DCount = ladder.get(DCount);
                } else if (snake.containsKey(DCount)) {
                    DCount = snake.get(DCount);
                }
                coinPosition(4, DCount);
                DPosition.setBounds(x, y, w, h);

                if (sentido.containsKey(DCount) && this.banderaAntiJugada == 1) {
                    antijugada(4, DCount, enviolocal);

                } else if (sentido.containsKey(DCount) && this.banderaAntiJugada == 0) {
                    this.banderaAntiJugada = 1;
                    antijugada(4, DCount, enviolocal);

                } else if (!sentido.containsKey(DCount) && this.banderaAntiJugada == 1) {
                    antijugada(4, DCount, enviolocal);

                } else if (!sentido.containsKey(DCount) && this.banderaAntiJugada == 0) {
                    jugada(4, DCount, enviolocal);
                } else {
                    jugada(4, DCount, enviolocal);
                }

                repaint();
                if (DCount == 100) {
                    jugar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Jugador D ganó");
                    // break;
                } else if (playAgain == 0) {
                    DList = new StringBuffer();
                }

            }

        }
        //enviar(enviolocal);      
    }

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private int playDies(int player) {
        int playAgain = 0;
        int diesResult = 0;
        while (diesResult == 0) {
            diesResult = dies.nextInt(7);
        }

        if (player == 1) {
            AList.append(String.valueOf(diesResult));
            // AList.append(",");
            NumerodadoA.setText(" Saco: "+AList.toString());
            if (ACount + diesResult <= 100) {
                ACount = ACount + diesResult;
                if (diesResult == 1 || diesResult == 5 || diesResult == 6) {
                    playAgain = 1;
                }
            }
        }
        if (player == 2) {
            BList.append(String.valueOf(diesResult));
            //BList.append(",");
            NumerodadoB.setText(" Saco: "+BList.toString());
            if (BCount + diesResult <= 100) {
                BCount = BCount + diesResult;
                if (diesResult == 1 || diesResult == 5 || diesResult == 6) {
                    playAgain = 1;
                }
            }
        }

        if (player == 3) {
            CList.append(String.valueOf(diesResult));
            //BList.append(",");
            NumerodadoC.setText(" Saco: "+CList.toString());
            if (CCount + diesResult <= 100) {
                CCount = CCount + diesResult;
                if (diesResult == 1 || diesResult == 5 || diesResult == 6) {
                    playAgain = 1;
                }
            }
        }

        if (player == 4) {
            DList.append(String.valueOf(diesResult));
            //BList.append(",");
            NumerodadoD.setText(" Saco: "+DList.toString());
            if (DCount + diesResult <= 100) {
                DCount = DCount + diesResult;
                if (diesResult == 1 || diesResult == 5 || diesResult == 6) {
                    playAgain = 1;
                }
            }
        }

        return playAgain;
    }

    @Override
    public void coinPosition(int compOrYou, int count) {

        int xpos = count % 10;
        int ypos = count / 10;
        /* if (compOrYou ==2){
         }*/
 /*    
         if(xpos == 0) {
         xpos = 10;
         ypos = ypos-1;
         }*/
        if (ypos % 2 == 1) {
            xpos = 11 - xpos;
        }
        if (compOrYou == 1) {

           x = 15 + (xpos*45) - 45;
        y = 440- (ypos*45);
            APosition.setBounds(x, y, w, h);
        }
        if (compOrYou == 2) {
            /* if (ypos%2 == 1){
             xpos = 11 - xpos; 
             }*/
             x = 30 + (xpos*45) - 45;
        y = 440 - (ypos*45);
            BPosition.setBounds(x, y, w, h);
        }
        if (compOrYou == 3) {
            x = 15 +(xpos*45) - 45;
    y = 455 - (ypos*45);
            CPosition.setBounds(x, y, w, h);
        }
        if (compOrYou == 4) {
             x = 30 +(xpos*45) - 45;
    y = 455 -(ypos * 45);
            DPosition.setBounds(x, y, w, h);
        }

    }

}

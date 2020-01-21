/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import interfaces.IComunicacion;

/**
 *
 * @author admin
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IComunicacion configWindow= new IComunicacion();
        configWindow.setLocationRelativeTo(null);
        configWindow.setVisible(true);
        
    }
    
}

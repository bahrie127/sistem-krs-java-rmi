/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stta.bahrie.krsmhs;

import api.stta.bahrie.inter.DetailKrsInterf;
import api.stta.bahrie.inter.DosenInterf;
import api.stta.bahrie.inter.MahasiswaInterf;
import api.stta.bahrie.inter.MkInterf;
import api.stta.bahrie.inter.WaktuKrsInterf;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.imageio.spi.RegisterableService;
import stta.bahrie.gui.MenuUtama;

/**
 *
 * @author bahrie
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException,NotBoundException {
        // TODO code application logic here
        Registry registry=LocateRegistry.getRegistry("localhost", 4321);

        final MahasiswaInterf mhsService=(MahasiswaInterf) registry.lookup("mhsServer");
        final MkInterf mkService=(MkInterf) registry.lookup("mkServer");
        final DosenInterf dsnService=(DosenInterf) registry.lookup("dosenServer");
        final WaktuKrsInterf wktuSevice=(WaktuKrsInterf) registry.lookup("waktuServer");
        final DetailKrsInterf detailService=(DetailKrsInterf) registry.lookup("detailServer");

        MenuUtama menu=new MenuUtama(mhsService, dsnService, mkService, wktuSevice,detailService);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

}

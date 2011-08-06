/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stta.bahrie.krsserver;

import api.stta.bahrie.inter.DetailKrsInterf;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import stta.bahrie.implementserviceserver.ImplDetailKrs;
import stta.bahrie.implementserviceserver.ImplDosen;
import stta.bahrie.implementserviceserver.ImplMahasiswa;
import stta.bahrie.implementserviceserver.ImplMk;
import stta.bahrie.implementserviceserver.ImplWaktuKrs;

/**
 *
 * @author bahrie
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        // TODO code application logic here
        Registry server = LocateRegistry.createRegistry(4321);
        ImplDetailKrs detailServer=new ImplDetailKrs();
        server.rebind("detailServer", detailServer);

        ImplDosen dosenServer=new ImplDosen();
        server.rebind("dosenServer", dosenServer);

        ImplMahasiswa mhsServer=new ImplMahasiswa();
        server.rebind("mhsServer", mhsServer);

        ImplMk mkServer=new ImplMk();
        server.rebind("mkServer", mkServer);

        ImplWaktuKrs waktuServer=new ImplWaktuKrs();
        server.rebind("waktuServer", waktuServer);

        System.out.println("server jalan bro");
    } 

}

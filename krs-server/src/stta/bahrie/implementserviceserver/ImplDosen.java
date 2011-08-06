/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stta.bahrie.implementserviceserver;

import api.stta.bahrie.entiy.DetailKrs;
import api.stta.bahrie.entiy.Dosen;
import api.stta.bahrie.inter.DosenInterf;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import stta.bahrie.sisterserver.utilities.DatabaseUtilities;

/**
 *
 * @author bahrie
 */
public class ImplDosen extends UnicastRemoteObject implements DosenInterf {

    public ImplDosen() throws RemoteException {
    }

    public Dosen insert(Dosen o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement("INSERT INTO dosen (kd_dosen,dosen,pwd)"
                    + "VALUES(?,?,?)");
            statement.setString(1, o.getKd_dosen());
            statement.setString(2, o.getDosen());
            statement.setString(3, o.getPwd());
            statement.executeUpdate();

            return o;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void update(Dosen o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "UPDATE dosen SET kd_dosen=?,dosen=?,pwd=? where kd_dosen=?");
            statement.setString(1, o.getKd_dosen());
            statement.setString(2, o.getDosen());
            statement.setString(3, o.getPwd());
            statement.setString(4, o.getPwd());

            statement.executeUpdate();
        } catch (SQLException exception) {
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public List<Dosen> getAll() throws RemoteException {
        Statement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT *FROM dosen");
            List<Dosen> list = new ArrayList<Dosen>();
            while (result.next()) {
                Dosen o = new Dosen();
                o.setKd_dosen(result.getString("kd_dosen"));
                o.setDosen(result.getString("dosen"));
                o.setPwd(result.getString("pwd"));
                list.add(o);
            }
            result.close();
            return list;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

    }

    public void delete(String id) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "DELETE FROM dosen where kd_dosen=?");
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public Dosen getByKd(String kd) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM dosen where kd_dosen=?");
            statement.setString(1, kd);
            ResultSet result = statement.executeQuery();
            Dosen o = null;
            if (result.next()) {
                o = new Dosen();
                o.setKd_dosen(result.getString("kd_dosen"));
                o.setDosen(result.getString("dosen"));
                o.setPwd(result.getString("pwd"));
            }
            return o;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        } finally {
            try {
                if (statement != null) {
                    statement.close();

                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}

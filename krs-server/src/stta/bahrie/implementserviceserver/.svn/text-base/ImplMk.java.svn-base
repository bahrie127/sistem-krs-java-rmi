/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stta.bahrie.implementserviceserver;

import api.stta.bahrie.entiy.Mk;
import api.stta.bahrie.inter.MkInterf;
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
public class ImplMk extends UnicastRemoteObject implements MkInterf {

    public ImplMk() throws RemoteException {
    }

    public Mk insert(Mk o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement("INSERT INTO mk (kd_mk,mk,sks,jurusan,kd_dosen)"
                    + "VALUES(?,?,?,?,?)");
            statement.setString(1, o.getKd_mk());
            statement.setString(2, o.getMk());
            statement.setInt(3,o.getSks());
            statement.setString(4, o.getJurusan());
            statement.setString(5, o.getKd_dosen());
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

    public void update(Mk o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "UPDATE mk SET kd_mk=?,mk=?,sks=?,jurusan=?,kd_dosen=? where kd_mk=?");
            statement.setString(1, o.getKd_mk());
            statement.setString(2, o.getMk());
            statement.setInt(3, o.getSks());
            statement.setString(4, o.getJurusan());
            statement.setString(5, o.getKd_dosen());
            statement.setString(6, o.getKd_mk());
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

 

 
    public List<Mk> getAll() throws RemoteException {
         PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM mk");
            ResultSet result = statement.executeQuery();
            List<Mk> list=new ArrayList<Mk>();
            while (result.next()) {
                Mk o = new Mk();
                o.setKd_mk(result.getString("kd_mk"));
                o.setMk(result.getString("mk"));
                o.setSks(result.getInt("sks"));
                o.setJurusan(result.getString("jurusan"));
                o.setKd_dosen(result.getString("kd_dosen"));
            list.add(o);
            }
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
                    "DELETE FROM mk where id=?");
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

    public Mk getByMk(String kode) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM mk where kd_mk=?");
            statement.setString(1, kode);
            ResultSet result = statement.executeQuery();
            Mk o = null;
            if (result.next()) {
                o = new Mk();
                o.setKd_mk(result.getString("kd_mk"));
                o.setMk(result.getString("mk"));
                o.setSks(result.getInt("sks"));
                o.setJurusan(result.getString("jurusan"));
                o.setKd_dosen(result.getString("kd_dosen"));
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

    public List<Mk> getByProdi(String prodi) throws RemoteException {
         PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM mk where jurusan=?");
            statement.setString(1, prodi);
            ResultSet result = statement.executeQuery();
            List<Mk> list=new ArrayList<Mk>();
            while (result.next()) {
                Mk o = new Mk();
                o.setKd_mk(result.getString("kd_mk"));
                o.setMk(result.getString("mk"));
                o.setSks(result.getInt("sks"));
                o.setJurusan(result.getString("jurusan"));
                o.setKd_dosen(result.getString("kd_dosen"));
            list.add(o);
            }
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
}

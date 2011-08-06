/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stta.bahrie.implementserviceserver;

import api.stta.bahrie.entiy.WaktuKrs;
import api.stta.bahrie.inter.WaktuKrsInterf;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
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
public class ImplWaktuKrs extends UnicastRemoteObject implements WaktuKrsInterf {

    public ImplWaktuKrs() throws RemoteException {
    }

    public WaktuKrs insert(WaktuKrs o) throws RemoteException {
        PreparedStatement statement = null;
       
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement("INSERT INTO waktu_krs (id_wktu,nim,semester,ta,tgl_pengisian)"
                    + "VALUES(?,?,?,?,?)");
            statement.setString(1, o.getId_wktu());
            statement.setString(2, o.getNim());
            statement.setString(3, o.getSemester());
            statement.setString(4, o.getTa());
            statement.setDate(5, new Date(o.getTanggal_pengisian().getTime()));
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

    public void update(WaktuKrs o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "UPDATE waktu_krs SET nim=?,semester=?,ta=?,tanggal_pengisian=? WHERE id=?");
            statement.setString(1, o.getNim());
            statement.setString(2, o.getSemester());
            statement.setString(3, o.getTa());
            statement.setDate(4, new Date(o.getTanggal_pengisian().getTime()));
            statement.setString(5, o.getId_wktu());
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

    public void delete(int id) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "DELETE FROM waktu_krs where id=?");
            statement.setInt(1, id);
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

    public WaktuKrs getByNim(String nim) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM waktu_krs WHERE nim=?");
            statement.setString(1, nim);
            ResultSet result = statement.executeQuery();
            WaktuKrs o = null;
            if (result.next()) {
                o.setId_wktu(result.getString("id_wktu"));
                o.setNim(result.getString("nim"));
                o.setSemester(result.getString("semester"));
                o.setTa(result.getString("ta"));
                o.setTanggal_pengisian(result.getDate("tanggal_pengisian"));
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

    public List<WaktuKrs> getAll() throws RemoteException {
        Statement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT *FROM waktu_krs");
            List<WaktuKrs> list = new ArrayList<WaktuKrs>();
            while (result.next()) {
                WaktuKrs o = new WaktuKrs();
                o.setId_wktu(result.getString("id_wktu"));
                o.setNim(result.getString("nim"));
                o.setSemester(result.getString("semester"));
                o.setTa(result.getString("ta"));
                o.setTanggal_pengisian(result.getDate("tanggal_pengisian"));
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

    public WaktuKrs getById(int id) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM waktu_krs id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            WaktuKrs o = null;
            if (result.next()) {
                o.setId_wktu(result.getString("id_wktu"));
                o.setNim(result.getString("nim"));
                o.setSemester(result.getString("semester"));
                o.setTa(result.getString("ta"));
                o.setTanggal_pengisian(result.getDate("tanggal_pengisian"));
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

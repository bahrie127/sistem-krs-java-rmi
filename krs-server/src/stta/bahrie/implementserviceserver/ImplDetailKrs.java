/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stta.bahrie.implementserviceserver;

import api.stta.bahrie.entiy.DetailKrs;
import api.stta.bahrie.inter.DetailKrsInterf;
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
public class ImplDetailKrs extends UnicastRemoteObject implements DetailKrsInterf {

    public ImplDetailKrs() throws RemoteException {
    }

    public DetailKrs insert(DetailKrs o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement("INSERT INTO detail_krs (id,id_wktu,nim,kd_mk)"
                    + "VALUES(null,?,?,?)");

            statement.setString(1, o.getId_wktu());
            statement.setString(2, o.getNim());
            statement.setString(3, o.getKd_mk());
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                o.setId(result.getInt(1));
            }

            result.close();

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

    public void update(DetailKrs o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "UPDATE detail_krs SET id_wktu=?,nim=?,kd_ksr=? WHERE id=?");
            statement.setString(1, o.getId_wktu());
            statement.setString(2, o.getNim());
            statement.setString(3, o.getKd_mk());
            statement.setInt(4, o.getId());
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
                    "DELETE FROM detail_krs where id=?");
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

    public List<DetailKrs> getByNim(String nim) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM detail_krs where nim=?");
            statement.setString(1, nim);
            ResultSet result = statement.executeQuery();
            List<DetailKrs> list = new ArrayList<DetailKrs>();
            while (result.next()) {
                DetailKrs o = new DetailKrs();
                o.setId(result.getInt("id"));
                o.setId_wktu(result.getString("id_wktu"));
                o.setNim(result.getString("nim"));
                o.setKd_mk(result.getString("kd_mk"));
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

    public List<DetailKrs> getAll() throws RemoteException {
        Statement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT *FROM detail_krs");
            List<DetailKrs> list = new ArrayList<DetailKrs>();
            while (result.next()) {
                DetailKrs o = new DetailKrs();
                o.setId(result.getInt("id"));
                o.setId_wktu(result.getString("id_wktu"));
                o.setNim(result.getString("nim"));
                o.setKd_mk(result.getString("kd_mk"));
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
}

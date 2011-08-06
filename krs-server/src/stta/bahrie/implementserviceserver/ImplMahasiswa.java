/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stta.bahrie.implementserviceserver;

import api.stta.bahrie.entiy.Dosen;
import api.stta.bahrie.entiy.Mahasiswa;
import api.stta.bahrie.inter.MahasiswaInterf;
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
public class ImplMahasiswa extends UnicastRemoteObject implements MahasiswaInterf {

    public ImplMahasiswa() throws RemoteException {
    }

    public Mahasiswa insert(Mahasiswa o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement("INSERT INTO mahasiswa(nim,nama,prodi,kd_dosen,jumlah_krs,pwd)"
                    + "VALUES(?,?,?,?,?,?)");
            statement.setString(1, o.getNim());
            statement.setString(2, o.getNama());
            statement.setString(3, o.getProdi());
            statement.setString(4, o.getKd_dosen());
            statement.setInt(5, o.getJumlah_krs());
            statement.setString(6, o.getPwd());
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

    public void update(Mahasiswa o) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "UPDATE mahasiswa SET nim=?,nama=?,prodi=?,kd_dosen=?,jumlah_krs=?,pwd=? WHERE nim=?");
            statement.setString(1, o.getNim());
            statement.setString(2, o.getNama());
            statement.setString(3, o.getProdi());
            statement.setString(4, o.getKd_dosen());
            statement.setInt(5, o.getJumlah_krs());
            statement.setString(6, o.getPwd());
            statement.setString(7, o.getNim());
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

    public Mahasiswa getByNim(String nim) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM mahasiswa where nim=?");
            statement.setString(1, nim);
            ResultSet result = statement.executeQuery();
            Mahasiswa o = null;
            if (result.next()) {
                o = new Mahasiswa();
                o.setNim(result.getString("nim"));
                o.setNama(result.getString("nama"));
                o.setProdi(result.getString("prodi"));
                o.setKd_dosen(result.getString("kd_dosen"));
                o.setJumlah_krs(result.getInt("jumlah_krs"));
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

    public List<Mahasiswa> getAll() throws RemoteException {
        Statement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT *FROM mahasiswa");
           // ResultSet result2=statement.executeQuery("SELECT *FROM dosen");
            List<Mahasiswa> list = new ArrayList<Mahasiswa>();
            while (result.next()) {
                Mahasiswa o = new Mahasiswa();
                o.setNim(result.getString("nim"));
                o.setNama(result.getString("nama"));
                o.setProdi(result.getString("prodi"));
                o.setKd_dosen(result.getString("kd_dosen"));
                o.setJumlah_krs(result.getInt("jumlah_krs"));
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

    public void delete(String nim) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "DELETE FROM mahasiswa where nim=?");
            statement.setString(1, nim);
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

    public Mahasiswa getByDosen(String dosen) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM mahasiswa where kd_dosen=?");
            statement.setString(1, dosen);
            ResultSet result = statement.executeQuery();
            Mahasiswa o = null;
            if (result.next()) {
                o = new Mahasiswa();
                o.setNim(result.getString("nim"));
                o.setNama(result.getString("nama"));
                o.setProdi(result.getString("prodi"));
                o.setKd_dosen(result.getString("kd_dosen"));
                o.setJumlah_krs(result.getInt("jumlah_krs"));
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

    public Mahasiswa getByProdi(String prodi) throws RemoteException {
        PreparedStatement statement = null;
        try {
            statement = DatabaseUtilities.getConnection().prepareStatement(
                    "SELECT *FROM mahasiswa where prodi=?");
            statement.setString(1, prodi);
            ResultSet result = statement.executeQuery();
            Mahasiswa o = null;
            if (result.next()) {
                o = new Mahasiswa();
                o.setNim(result.getString("nim"));
                o.setNama(result.getString("nama"));
                o.setProdi(result.getString("prodi"));
                o.setKd_dosen(result.getString("kd_dosen"));
                o.setJumlah_krs(result.getInt("jumlah_krs"));
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

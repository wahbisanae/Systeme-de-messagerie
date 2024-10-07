
package ma.projet.service;

import java.sql.PreparedStatement; // Utiliser java.sql.PreparedStatement et non com.mysql.jdbc.PreparedStatement
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Employe;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe e) {
        try {
            String req = "insert into employe (nom, prenom) values(?, ?)";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());

            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Employe e) {
        try {
            String req = "update employe set nom = ?, prenom = ? where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setInt(3, e.getId());

            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Employe e) {
        try {
            String req = "delete from employe where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req); // Pas besoin du cast inutile

            ps.setInt(1, e.getId());

            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Employe getById(int id) {
        Employe employe = null;
       try {
            String req = "select * from employe where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req); // Pas besoin du cast inutile

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                employe = new Employe(rs.getInt("id"),
                rs.getString("nom"),rs.getString("prenom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return employe;
    }
    

   @Override
public List<Employe> getAll() {
    List<Employe> employes = new ArrayList<>();
    try {
        String req = "select * from employe";
        PreparedStatement ps = Connexion.getCn().prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Employe emp = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"));
            employes.add(emp);
        }
    } catch (SQLException ex) {
        Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
    }

    if (employes.isEmpty()) {
        System.out.println("Aucun employé trouvé dans la base de données.");
    }
    
    return employes;
}

}

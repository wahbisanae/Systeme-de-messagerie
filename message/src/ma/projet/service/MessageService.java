
package ma.projet.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Message;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

/**
 *
 * @author salam
 */
public class MessageService implements IDao<Message> {
    private EmployeService es ;
    public MessageService (){
        es = new EmployeService();
    }

    @Override
    public boolean create(Message o) { // Correction : ajout de l'accolade ouvrante
        try {
            String req = "insert into message (objet , sujet ,date , idE , idR) values(?, ? , ? , ? , ?)";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);
            ps.setString(1,o.getObject());
            ps.setString(2,o.getSujet() );
            ps.setDate(3, new Date(o.getDate().getTime()));
            ps.setInt(4, o.getEmEmetteur().getId());
            ps.setInt(5, o.getEmpRecepteur().getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Message e) {
       
        return false;
    }

    @Override
    public boolean delete(Message e) {
       
        return false;
    }

    @Override
    public Message getById(int id) {
        Message message = null;
        try {
            String req = "select * from message where id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(req);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                message = new Message(rs.getInt("id"),
                rs.getString("objet"), rs.getString("sujet"),rs.getDate("date"),es.getById(rs.getInt("idE")),es.getById(rs.getInt("idR")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    @Override
 
public List<Message> getAll() {
    List<Message> messages = new ArrayList<>();
    try {
        String req = "select * from message";
        PreparedStatement ps = Connexion.getCn().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            messages.add(new Message(
                rs.getInt("id"),
                rs.getString("objet"),
                rs.getString("sujet"),
                rs.getDate("date"),
                es.getById(rs.getInt("idE")), // Assurez-vous que 'es' est bien initialisé et représente un objet de type EmployeService
                es.getById(rs.getInt("idR"))
            )); // Fermeture de la parenthèse
        }
    } catch (SQLException ex) {
        Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return messages; // Retour de la liste messages
}

}

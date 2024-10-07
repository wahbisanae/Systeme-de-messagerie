
package ma.projet.beans;

import java.sql.Date;

public class Message {
    private int id;
    private String object;
    private String sujet;
    private Date date;
    private Employe emEmetteur;
    private Employe empRecepteur;

    public Message(String object, String sujet, Date date, Employe emEmetteur, Employe empRecepteur) {
        this.object = object;
        this.sujet = sujet;
        this.date = date;
        this.emEmetteur = emEmetteur;
        this.empRecepteur = empRecepteur;
    }

    public Message(int id, String object, String sujet, Date date, Employe emEmetteur, Employe empRecepteur) {
        this.id = id;
        this.object = object;
        this.sujet = sujet;
        this.date = date;
        this.emEmetteur = emEmetteur;
        this.empRecepteur = empRecepteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employe getEmEmetteur() {
        return emEmetteur;
    }

    public void setEmEmetteur(Employe emEmetteur) {
        this.emEmetteur = emEmetteur;
    }

    public Employe getEmpRecepteur() {
        return empRecepteur;
    }

    public void setEmpRecepteur(Employe empRecepteur) {
        this.empRecepteur = empRecepteur;
    }
    
    
}

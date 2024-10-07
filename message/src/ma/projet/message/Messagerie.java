package ma.projet.message;

import java.sql.Date;
import ma.projet.beans.Employe;
import ma.projet.beans.Message;
import ma.projet.service.EmployeService;
import ma.projet.service.MessageService;

public class Messagerie {
    public static void main(String[] args) {
        // Création d'employés
        EmployeService es = new EmployeService();
        es.create(new Employe("Wahbi", "Sanae"));
        es.create(new Employe("Wahbi", "Youssef"));
        es.create(new Employe("Wahbi", "Sanae"));

        // Modification d'un employé
        Employe e = es.getById(11);
        if (e != null) { 
            e.setNom("Wahbi");
            e.setPrenom("Sanae"); 
            es.update(e);
        }

        // Suppression d'un employé
        Employe empToDelete = es.getById(28); // Vérifiez que cet ID existe dans votre base de données
        if (empToDelete != null) {
            es.delete(empToDelete);
            System.out.println("L'employé avec l'ID 28 a été supprimé.");
        } else {
            System.out.println("L'employé avec l'ID 28 n'existe pas.");
        }

        // Liste des employés
        for (Employe emp : es.getAll()) {
            System.out.println(" " + emp.getNom());
        }

        // Création de messages
        MessageService ms = new MessageService();

        // Utiliser la date actuelle pour les messages et la convertir en java.sql.Date
        java.util.Date currentDate = new java.util.Date();
        Date sqlDate = new Date(currentDate.getTime());

        // Vérification et création de messages
        Employe emetteur1 = es.getById(23);
        Employe recepteur1 = es.getById(24);
        if (emetteur1 != null && recepteur1 != null) {
            ms.create(new Message("Réunion", "Réunion de fin d'année", sqlDate, emetteur1, recepteur1));
        } else {
            System.out.println("L'un des employés avec l'ID 23 ou 24 n'existe pas.");
        }

        Employe emetteur2 = es.getById(25);
        Employe recepteur2 = es.getById(26);
        if (emetteur2 != null && recepteur2 != null) {
            ms.create(new Message("Réunion", "Réunion de fin d'année", sqlDate, emetteur2, recepteur2));
        } else {
            System.out.println("L'un des employés avec l'ID 25 ou 26 n'existe pas.");
        }

        Employe emetteur3 = es.getById(23);
        Employe recepteur3 = es.getById(24);
        if (emetteur3 != null && recepteur3 != null) {
            ms.create(new Message("Stage", "Stage à Marrakech", sqlDate, emetteur3, recepteur3));
        } else {
            System.out.println("L'un des employés avec l'ID 23 ou 24 n'existe pas.");
        }

        Employe emetteur4 = es.getById(25);
        Employe recepteur4 = es.getById(26);
        if (emetteur4 != null && recepteur4 != null) {
            ms.create(new Message("Stage", "Stage à Marrakech", sqlDate, emetteur4, recepteur4));
        } else {
            System.out.println("L'un des employés avec l'ID 25 ou 26 n'existe pas.");
        }
    }
}

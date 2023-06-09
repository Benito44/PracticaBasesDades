import java.sql.*;
import java.util.Calendar;
public class Insert {

    // Función que coge la información de las comunidades autónomas y lo inserta en la BBDD
    public static void comunitatsAutonomes(String nom, String ca, Connection con){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO comunitats_autonomes (nom,codi_ine)"
                    + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, nom);
            preparedStmt.setString (2, ca);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }
    }


    // Función que coge la información de las provincias y la inserta en la BBDD
    public static void provincies (String nom, String ine, String ca, int num_escons, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO provincies (comunitat_aut_id,nom,codi_ine,num_escons)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.provincies(con, ca));
            preparedStmt.setString (2, nom);
            preparedStmt.setString (3, ine);
            preparedStmt.setInt(4,num_escons);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }

    public static void municipis (String nom, String ine, String codi, String ine_prov, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO municipis (nom,codi_ine,provincia_id,districte)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, nom);
            preparedStmt.setString (2, ine_prov + ine + codi);
            preparedStmt.setInt (3, Select.municipis(con, ine_prov));
            preparedStmt.setString(4, codi);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }
    // Función que coge la info de las elecciones municipales y la introduce en la BBDD
    public static void elecciones_municipales(int any, int mes,String ine_prov,String ine,String codi_dist, int num_talues, int cens, int vots_cand, int vots_blanc, int vots_nuls, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO eleccions_municipis (eleccio_id,municipi_id,num_meses,cens," +
                    "vots_candidatures,vots_blanc,vots_nuls)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.elecciones(any, mes, con));
            preparedStmt.setInt (2, Select.municipis(ine_prov + ine + codi_dist, con));
            preparedStmt.setInt (3, num_talues);
            preparedStmt.setInt(4, cens);
            preparedStmt.setInt(5, vots_cand);
            preparedStmt.setInt(6, vots_blanc);
            preparedStmt.setInt(7, vots_nuls);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }

    public static void candidatures(String codi, String nomCurt, String nomLlarg, String codi_ac_prov, String codi_ac_ca, String codi_ac_nac, int any, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO candidatures (eleccio_id,codi_candidatura,nom_curt,nom_llarg," +
                    "codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulario_nacional)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.elecciones1(any, con));
            preparedStmt.setString (2, codi);
            preparedStmt.setString (3, nomCurt);
            preparedStmt.setString(4, nomLlarg);
            preparedStmt.setString(5, codi_ac_prov);
            preparedStmt.setString(6, codi_ac_ca);
            preparedStmt.setString(7, codi_ac_nac);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }
    public static void personas (String nom, String cog1, String cog2, String sexe,String dob, String dni, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            String query = " INSERT INTO persones (nom,cog1,cog2,sexe,data_naixement,dni)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, nom);
            preparedStmt.setString (2, cog1);
            preparedStmt.setString (3, cog2);
            preparedStmt.setString(4, sexe);
            preparedStmt.setString(5, dob);
            preparedStmt.setString(6, dni);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }
    public static void candidats(int ine, int cod_can, String tipus, int num, String dni,String nom,String cog1,String cog2, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO candidats (candidatura_id,persona_id,provincia_id,num_ordre,tipus)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.candidatsCod(cod_can, con));
            preparedStmt.setInt (2, Select.candidatsPersona(dni,nom,cog1,cog2, con));
            preparedStmt.setInt (3, Select.candidatsProvincia(ine, con));
            preparedStmt.setInt(4, num);
            preparedStmt.setString(5, tipus);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }
    public static void votos_municipales(int any, int mes,String codi_prov, String ine,String codi_dis, String codi_cand, int vots, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO vots_candidatures_mun (eleccio_id,municipi_id,candidatura_id,vots)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.elecciones(any, mes, con));
            preparedStmt.setInt(2, Select.municipis(codi_prov + ine + codi_dis, con));
            preparedStmt.setInt(3, Select.candidatura(codi_cand, con));
            preparedStmt.setInt(4, vots);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }
    // Función que coge la info de los votos de las comunidades autonomas y la introduce en la BBDD
    public static void votos_ca(String ine, String codi_cand, int vots, Connection con) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO vots_candidatures_ca (comunitat_autonoma_id,candidatura_id,vots)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.comunitats_autonomes(ine, con));
            preparedStmt.setInt(2, Select.candidatura(codi_cand, con));
            preparedStmt.setInt(3, vots);



            // execute the preparedstatement
            preparedStmt.execute();

        }catch(Exception e){
            System.out.println(e);
        }

    }
    public static void votsProvincials(int ine_provincia, String codi_cand, int vots, int candidats_obtinguts, Connection con) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            String query = " INSERT INTO vots_candidatures_prov (provincia_id,candidatura_id,vots,candidats_obtinguts)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Select.provincies1(con, ine_provincia));
            preparedStmt.setInt(2, Select.candidatura(codi_cand, con));
            preparedStmt.setInt(3, vots);
            preparedStmt.setInt(4, candidats_obtinguts);



            // execute the preparedstatement
            preparedStmt.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
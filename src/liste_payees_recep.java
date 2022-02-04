import java.io.Serializable;

public class liste_payees_recep implements Serializable {
    public String id_reservation;
    public String Full_name;
    public String ID_Client;
    public String ID_chambre;
    public String Nb_Jours;
    public  String Type;
    public  String Prix;

    liste_payees_recep(String id_reservation,String Full_name,String ID_Client,String ID_chambre,String Nb_Jours,String Type,String Prix){
        this.id_reservation=id_reservation;
        this.ID_chambre=ID_chambre;
        this.ID_Client=ID_Client;
        this.Nb_Jours=Nb_Jours;
        this.Type=Type;
        this.Prix=Prix;
        this.Full_name=Full_name;
    }

    @Override
    public String toString() {
        return "liste_payees_recep{" +
                "id_reservation='" + id_reservation + '\'' +
                ", Full_name='" + Full_name + '\'' +
                ", ID_Client='" + ID_Client + '\'' +
                ", ID_chambre='" + ID_chambre + '\'' +
                ", Nb_Jours='" + Nb_Jours + '\'' +
                ", Type='" + Type + '\'' +
                ", Prix='" + Prix + '\'' +
                '}';
    }
}

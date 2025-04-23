import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

class Kalender {
    private List<Activiteiten> activiteit = new ArrayList<>();
    private List<Groepsleden> groepslid = new ArrayList<>();
    private List<Reserveringen> reservering = new ArrayList<>();

    public void toevoegenActiviteiten (Activiteiten voegActiviteit) {
        activiteit.add(voegActiviteit);
    }
    public void verwijderActiviteiten (Activiteiten verwijderActiviteit) {
        activiteit.remove(verwijderActiviteit);
    }
    public void toevoegenGroepsleden (Groepsleden voegGroepslid) {
        groepslid.add(voegGroepslid);
    }
    public void verwijderGroepsleden (Groepsleden verwijderGroepslid) {
        groepslid.remove(verwijderGroepslid);
    }
    public void toevoegenReservering (Reserveringen voegReservering) {
        reservering.add(voegReservering);
    }
    public void verwijderReservering (Reserveringen verwijderReservering) {
        reservering.remove(verwijderReservering);
    }
    public List<Reserveringen> getReservering() {
        return new ArrayList<>(reservering);
    }
}
class Groepsleden {
    private int id;
    private String naam;

    public Groepsleden(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }
    public int getId() {
        return id;
    }
    public String getNaam() {
        return naam;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public String toString() {
        return naam;
    }
}
abstract class Activiteiten {
    private String naam;
    private String locatie;

    public Activiteiten (String naam, String locatie) {
        this.naam = naam;
        this.locatie = locatie;
    }
    public String getNaam() {
        return naam;
    }
    public String getLocatie() {
        return locatie;
    }
    public void setNaam() {
        this.naam = naam;
    }
    public void setLocatie() {
        this.locatie = locatie;
    }
    public abstract String getType();
    public String toString() {
        return getType() + ", Naam: " + naam + ", Locatie: " + locatie;
    }
}

class Afspraak extends Activiteiten {
    public Afspraak (String naam, String locatie) {
        super(naam, locatie);
    }
    @Override public String getType() {
        return "Afspraak";
    }
}
class Evenement extends Activiteiten {
    public Evenement (String naam, String locatie) {
        super(naam, locatie);
    }
    @Override public String getType() {
        return "Evenement";
    }
}
class Date extends Activiteiten {
    public Date (String naam, String locatie) {
        super(naam, locatie);
    }
    @Override public String getType() {
        return "Date";
    }
}
class Reserveringen {
    private List<Groepsleden> groepsleden;
    private Activiteiten activiteit;
    private LocalDate datum;
    private LocalTime tijdstip;

    public Reserveringen (Activiteiten activiteit, LocalDate datum, LocalTime tijdstip) {
        this.groepsleden = new ArrayList<>();
        this.activiteit = activiteit;
        this.datum = datum;
        this.tijdstip = tijdstip;
    }
    public void toevoegGroepslid(Groepsleden groepslid) {
        groepsleden.add(groepslid);
    }

    public void verwijderGroepslid(Groepsleden groepslid) {
        groepsleden.remove(groepslid);
    }
    public String toString() {
        return "Reservering = " + "groepsleden: " + groepsleden + ", activiteit: " + activiteit + ", datum: " + datum + ", tijdstip: " + tijdstip + "} \n";
    }
}


public class Main {
    public static void main(String[] args) {
        Kalender kalender = new Kalender();

        Groepsleden denie = new Groepsleden(1, "Denie");
        kalender.toevoegenGroepsleden(denie);
        Groepsleden stan = new Groepsleden(2, "Stan");
        kalender.toevoegenGroepsleden(stan);
        Groepsleden randomMeisje = new Groepsleden(3, "Sara");

        Afspraak tandarts = new Afspraak("Tandarts", "Tandartsen Groep M2");
        kalender.toevoegenActiviteiten(tandarts);

        Evenement feest = new Evenement("Feestje bij Stan", "Stans huis");
        kalender.toevoegenActiviteiten(feest);

        Date date = new Date("Date met vriendin", "Volle Maan");
        kalender.toevoegenActiviteiten(date);

        Reserveringen reservering1 = new Reserveringen(tandarts, LocalDate.of(2025,4,23), LocalTime.of(19, 30));
        reservering1.toevoegGroepslid(denie);
        kalender.toevoegenReservering(reservering1);

        Reserveringen reservering2 = new Reserveringen(feest, LocalDate.of(2025, 4, 25), LocalTime.of(21, 15));
        reservering2.toevoegGroepslid(denie);
        reservering2.toevoegGroepslid(stan);
        kalender.toevoegenReservering(reservering2);

        Reserveringen reservering3 = new Reserveringen(date, LocalDate.of(2025,4,27), LocalTime.of(16, 40));
        reservering3.toevoegGroepslid(denie);
        reservering3.toevoegGroepslid(randomMeisje);
        kalender.toevoegenReservering(reservering3);

        for(int i = 0; i < kalender.getReservering().size(); i++) {
            System.out.println(kalender.getReservering().get(i));
        }

    }
}
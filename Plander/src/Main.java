import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

class Kalender {
    private final List<Activiteiten> activiteit = new ArrayList<>();
    private final List<Groepsleden> groepslid = new ArrayList<>();
    private final List<Reserveringen> reservering = new ArrayList<>();

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
    private final int id;
    private String naam;

    public Groepsleden(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }
    public String toString() {
        return naam;
    }
}
abstract class Activiteiten {
    private final String naam;
    private final String locatie;

    public Activiteiten (String naam, String locatie) {
        this.naam = naam;
        this.locatie = locatie;
    }
    public abstract String getType();
    public String toString() {
        return getType() + ", Naam: " + naam + ", Locatie: " + locatie;
    }
}

class School extends Activiteiten {
    private String soortKlas;
    public School (String naam, String locatie, String soortKlas) {
        super(naam, locatie);
        this.soortKlas = soortKlas;
    }
    @Override public String getType() {
        return "School, Klas: " + soortKlas;
    }
}
class Evenement extends Activiteiten {
    private int aantalDeelnemers;
    public Evenement (String naam, String locatie, int aantalDeelnemers) {
        super(naam, locatie);
        this.aantalDeelnemers = aantalDeelnemers;
    }
    @Override public String getType() {
        return "Evenement, Aantal Deelnemers: " + aantalDeelnemers;
    }
}
class Date extends Activiteiten {
    private String relatieStatus;
    public Date (String naam, String locatie, String relatieStatus) {
        super(naam, locatie);
        this.relatieStatus = relatieStatus;
    }
    @Override public String getType() {
        return "Date, Relatieschip Status: " + relatieStatus;
    }
}
class Reserveringen {
    private final List<Groepsleden> groepsleden;
    private final Activiteiten activiteit;
    private final LocalDate datum;
    private final LocalTime tijdstip;

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
        Scanner scanner = new Scanner(System.in);
        Kalender kalender = new Kalender();

        Groepsleden denie = new Groepsleden(1, "Denie");
        Groepsleden stan = new Groepsleden(2, "Stan");
        Groepsleden randomMeisje = new Groepsleden(3, "Sara");
        Groepsleden marcel = new Groepsleden(4, "Marcel");

        School school = new School("De Haagse Hogeschool", "Johanna Westerdijkplein 75, 2521 EN Den Haag", "Scrums");
        kalender.toevoegenActiviteiten(school);

        Evenement feest = new Evenement("Feestje bij Stan", "Stans huis", 20);
        kalender.toevoegenActiviteiten(feest);

        Date date = new Date("Date met vriendin", "Volle Maan", "Talking Stage");
        kalender.toevoegenActiviteiten(date);

        School school1 = new School("De Haagse Hogeschool", "Johanna Westerdijkplein 75, 2521 EN Den Haag", "Review");

        Reserveringen reservering1 = new Reserveringen(school, LocalDate.of(2025,4,23), LocalTime.of(10, 30));
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

        Reserveringen reservering4 = new Reserveringen(school1, LocalDate.of(2025, 5, 1), LocalTime.of(12, 0));
        reservering4.toevoegGroepslid(marcel);
        reservering4.toevoegGroepslid(denie);
        kalender.toevoegenReservering(reservering4);

        int exit = 0;

        while (exit == 0) {
            System.out.print("Type een nummer (0-" + (kalender.getReservering().size() - 1) + ") om een reservatie te bekijken, of enter -1 om te verlaten: ");
            if (scanner.hasNextInt()) {
                int userInput = scanner.nextInt();

                if (userInput >= 0 && userInput < kalender.getReservering().size()) {
                    System.out.println(kalender.getReservering().get(userInput));
                } else if (userInput == -1) {
                    exit = 1;
                    System.out.println("Exiting the program.");
                } else {
                    System.out.println("Foute Input. Type een nummer tussen 0 en " + (kalender.getReservering().size() - 1) + " of enter -1 om te verlaten");
                }
            } else {
                System.out.println("Foute Input");
                scanner.nextInt();
            }
        }
    }
}
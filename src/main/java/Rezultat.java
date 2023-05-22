//klasa koja prima vrijednosti ime i rezultat koji služe za kreiranje liste koja sprema podatka u bazu podataka

public class Rezultat {

    String ime;
    int rezultat;

    public Rezultat(String ime, int rezultat) {
        this.ime = ime;
        this.rezultat = rezultat;
    }

    @Override
    public String toString() {
        return this.ime + ": " + rezultat;
    }
}

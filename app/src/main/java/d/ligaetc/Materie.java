package d.ligaetc;

/**
 * Created by Andrei on 10.01.2017.
 */

class Materie {
    private String nume;
    private String tip;
    private String prof;
    private String ora_i;
    private String ora_f;

    public Materie(String nume, String tip, String prof, String ora_i, String ora_f){
        this.nume = nume;
        this.tip = tip;
        this.prof = prof;
        this.ora_i = ora_i;
        this.ora_f = ora_f;
    }

    public String getNume(){
        return nume;
    }

    public String getTip() {
        return tip;
    }

    public String getProf() {
        return prof;
    }

}

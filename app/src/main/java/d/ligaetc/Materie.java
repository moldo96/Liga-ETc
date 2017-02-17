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
    private String sala;
    private String G;
    private String g;
    private String additionalCommentString;

    public Materie(String nume, String tip, String prof, String ora_i, String ora_f, String sala){
        this.nume = nume;
        this.tip = tip;
        this.prof = prof;
        this.ora_i = ora_i;
        this.ora_f = ora_f;
        this.sala = sala;
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

    public String getOra_i() { return ora_i;}

    public String getOra_f() { return ora_f;}

    public String getSala() {return sala;}

    public String getG() {
        return G;
    }

    public String getg() {
        return g;
    }

    public void setAdditionalCommentString(String a){
        additionalCommentString = a;
    }
}

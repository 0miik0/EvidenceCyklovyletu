import java.time.LocalDate;

public class Cyklovylet {
    private String cil;
    private int delka;
    private LocalDate datum;

    public Cyklovylet(String cil, int delka, LocalDate datum) {
        this.cil = cil;
        this.delka = delka;
        this.datum = datum;
    }

    public String getCil() {
        return cil;
    }

    public void setCil(String cil) {
        this.cil = cil;
    }

    public int getDelka() {
        return delka;
    }

    public void setDelka(int delka) {
        this.delka = delka;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}

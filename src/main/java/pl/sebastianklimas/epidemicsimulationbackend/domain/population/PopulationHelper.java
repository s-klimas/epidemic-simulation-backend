package pl.sebastianklimas.epidemicsimulationbackend.domain.population;

public class PopulationHelper {
    private int p;
    private int pi;
    private int pv;
    private int pm;
    private int pr;
    private int newCases;
    private int dead;
    private int cured;

    public PopulationHelper() {
    }

    public PopulationHelper(int p, int pi, int pv, int pm, int pr, int newCases, int dead, int cured) {
        this.p = p;
        this.pi = pi;
        this.pv = pv;
        this.pm = pm;
        this.pr = pr;
        this.newCases = newCases;
        this.dead = dead;
        this.cured = cured;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getPi() {
        return pi;
    }

    public void setPi(int pi) {
        this.pi = pi;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

    public int getPr() {
        return pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getCured() {
        return cured;
    }

    public void setCured(int cured) {
        this.cured = cured;
    }
}

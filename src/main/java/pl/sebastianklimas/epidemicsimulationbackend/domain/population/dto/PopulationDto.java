package pl.sebastianklimas.epidemicsimulationbackend.domain.population.dto;

public class PopulationDto {
    private int pi;
    private int pv;
    private int pm;
    private int pr;

    public PopulationDto() {
    }

    public PopulationDto(int pi, int pv, int pm, int pr) {
        this.pi = pi;
        this.pv = pv;
        this.pm = pm;
        this.pr = pr;
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
}

package pl.sebastianklimas.epidemicsimulationbackend.domain.population;

import jakarta.persistence.*;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.Simulation;

@Entity
@Table(name = "population")
public class Population {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "simulation_id", referencedColumnName = "id")
    private Simulation simulation;
    private int pi;
    private int pv;
    private int pm;
    private int pr;

    public Population() {
    }

    public Population(Long id, Simulation simulation, int pi, int pv, int pm, int pr) {
        this.id = id;
        this.simulation = simulation;
        this.pi = pi;
        this.pv = pv;
        this.pm = pm;
        this.pr = pr;
    }

    public Population(int pi, int pv, int pm, int pr) {
        this.pi = pi;
        this.pv = pv;
        this.pm = pm;
        this.pr = pr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
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

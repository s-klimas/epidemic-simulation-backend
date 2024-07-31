package pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto;

import pl.sebastianklimas.epidemicsimulationbackend.domain.population.dto.PopulationDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SimulationDetailedDto {
    private Long id;
    private String n;
    private int p;
    private int i;
    private BigDecimal r;
    private BigDecimal m;
    private int ti;
    private int tm;
    private int ts;
    private List<PopulationDto> populations = new ArrayList<>();

    public SimulationDetailedDto() {
    }

    public SimulationDetailedDto(Long id, String n, int p, int i, BigDecimal r, BigDecimal m, int ti, int tm, int ts) {
        this.id = id;
        this.n = n;
        this.p = p;
        this.i = i;
        this.r = r;
        this.m = m;
        this.ti = ti;
        this.tm = tm;
        this.ts = ts;
    }

    public SimulationDetailedDto(Long id, String n, int p, int i, BigDecimal r, BigDecimal m, int ti, int tm, int ts, List<PopulationDto> populations) {
        this.id = id;
        this.n = n;
        this.p = p;
        this.i = i;
        this.r = r;
        this.m = m;
        this.ti = ti;
        this.tm = tm;
        this.ts = ts;
        this.populations = populations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public BigDecimal getM() {
        return m;
    }

    public void setM(BigDecimal m) {
        this.m = m;
    }

    public int getTi() {
        return ti;
    }

    public void setTi(int ti) {
        this.ti = ti;
    }

    public int getTm() {
        return tm;
    }

    public void setTm(int tm) {
        this.tm = tm;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public List<PopulationDto> getPopulations() {
        return populations;
    }

    public void setPopulations(List<PopulationDto> populations) {
        this.populations = populations;
    }
}

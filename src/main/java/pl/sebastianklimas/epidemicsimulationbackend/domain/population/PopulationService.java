package pl.sebastianklimas.epidemicsimulationbackend.domain.population;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.Simulation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopulationService {
    private final PopulationRepository populationRepository;

    public PopulationService(PopulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }

    @Transactional
    public List<Population> calculatePopulation(Simulation simulation) {
        int p = simulation.getP(); // total population
        int pi = simulation.getI(); // all cases
        int pv = simulation.getP() - pi; // healthy population
        int pm = 0; // deceased
        int pr = 0; // recovered
        int ti = simulation.getTi(); // number of days to recovery
        int tm = simulation.getTm(); // number of days to death
        BigDecimal r = simulation.getR(); // R
        BigDecimal m = simulation.getM(); // M
        int newCases = pi; // new cases in current day

        List<PopulationHelper> pHList = new ArrayList<>();
        pHList.add(new PopulationHelper(p, pi, pv, pm, pr, newCases, 0, 0));

        for (int i = 1; i < simulation.getTs(); i++) {
            int currentDead = 0;
            int currentRecovered  = 0;
            if (i >= tm) { // Calculating dead cases
                currentDead = m.multiply(BigDecimal.valueOf(pHList.get(i - tm).getNewCases())).intValue();
                pi -= currentDead;
                pm += currentDead;
            }
            if (i >= ti) { // Calculating recovered cases
                int newCasesFromTiDaysAgo = BigDecimal.valueOf(pHList.get(i - ti).getNewCases()).intValue();
                int deadCasesFromTiDaysAgo = pHList.get(i - (ti - tm)).getDead();
                currentRecovered = newCasesFromTiDaysAgo - deadCasesFromTiDaysAgo;
                pi -= currentRecovered;
                pr += currentRecovered;
            }

            // Calculating new cases
            newCases = r.multiply(BigDecimal.valueOf(pHList.get(i - 1).getNewCases())).intValue();

            if (pi + pm + pr + newCases >= p) {
                newCases = pv;
                pv = 0;
                pi = p - pm - pr;
            } else {
                pi += newCases;
                pv = p - pi - pm - pr;
            }

            PopulationHelper populationHelper = new PopulationHelper(p, pi, pv, pm, pr, newCases, currentDead, currentRecovered);
            pHList.add(populationHelper);
        }

//        List<Population> populationList = pHList.stream().map(PopulationHelperMapper::map).toList();
//        populationList.forEach(population -> {
//            population.setSimulation(simulation);
//            populationRepository.save(population);
//        });

        List<Population> populationList = new ArrayList<>();
        pHList.forEach(ph -> {
            Population population = PopulationHelperMapper.map(ph);
            populationList.add(population);
            population.setSimulation(simulation);
        });

        populationRepository.saveAll(populationList);

        return populationList;
    }

    public void deletePopulation(List<Population> populations) {
        populationRepository.deleteAll(populations);
    }
}
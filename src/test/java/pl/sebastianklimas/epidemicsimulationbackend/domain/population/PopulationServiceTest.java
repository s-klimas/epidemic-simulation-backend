package pl.sebastianklimas.epidemicsimulationbackend.domain.population;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.Simulation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PopulationServiceTest {

    @Mock
    private PopulationRepository populationRepository;

    @InjectMocks
    private PopulationService populationService;

    @BeforeAll
    public static void setUp() {
        mockStatic(PopulationHelperMapper.class);
        mockStatic(PopulationDtoMapper.class);
    }

    @BeforeEach
    public void init() {
        populationRepository = mock(PopulationRepository.class);
        populationService = new PopulationService(populationRepository);
    }

    @Test
    @Transactional
    public void test_calculatePopulation_whenNoError_shouldReturnCorrectPopulationList() {
        // given
        Simulation simulation;
        simulation = new Simulation();
        simulation.setId(1L);
        simulation.setP(1000);
        simulation.setI(10);
        simulation.setR(new BigDecimal("1.2"));
        simulation.setM(new BigDecimal("0.02"));
        simulation.setTi(5);
        simulation.setTm(3);
        simulation.setTs(10);
        List<Population> simulationsPopulations = new ArrayList<>();
        Population population1 = new Population();
        Population population2 = new Population();
        population1.setSimulation(simulation);
        population2.setSimulation(simulation);
        simulationsPopulations.add(population1);
        simulationsPopulations.add(population2);

        // when

        //then
    }

    @Test
    public void test_deletePopulation_whenPopulationExist_shouldDeletePopulations() {
        // given
        List<Population> populations = new ArrayList<>();
        populations.add(new Population());

        // when

        // then
        populationService.deletePopulation(populations);
        verify(populationRepository, times(1)).deleteAll(populations);
    }
}
package pl.sebastianklimas.epidemicsimulationbackend.domain.simulation;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.sebastianklimas.epidemicsimulationbackend.domain.population.Population;
import pl.sebastianklimas.epidemicsimulationbackend.domain.population.PopulationService;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationAddDto;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationGetUpdateDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SimulationServiceTest {

    @Mock
    private SimulationRepository simulationRepository;
    @Mock
    private PopulationService populationService;
    @InjectMocks
    private SimulationService simulationService;

    @BeforeAll
    public static void setUp() {
        mockStatic(SimulationGetUpdateDtoMapper.class);
        mockStatic(SimulationAddDtoMapper.class);
    }

    @BeforeEach
    public void init() {
        simulationRepository = mock(SimulationRepository.class);
        populationService = mock(PopulationService.class);
        simulationService = new SimulationService(simulationRepository, populationService);
    }

    @Test
    public void test_findAll_whenSimulationsExist_ShouldReturnListOfSimulationsDto() {
        //given
        List<Simulation> simulations;
        List<SimulationGetUpdateDto> simulationsDto;
        Simulation simulation1 = new Simulation();
        Simulation simulation2 = new Simulation();
        simulations = Arrays.asList(simulation1, simulation2);
        SimulationGetUpdateDto simulationDto1 = new SimulationGetUpdateDto();
        SimulationGetUpdateDto simulationDto2 = new SimulationGetUpdateDto();
        simulationsDto = Arrays.asList(simulationDto1, simulationDto2);

        // when
        when(simulationRepository.findAll()).thenReturn(simulations);
        when(SimulationGetUpdateDtoMapper.map(simulations.get(0))).thenReturn(simulationsDto.get(0));
        when(SimulationGetUpdateDtoMapper.map(simulations.get(1))).thenReturn(simulationsDto.get(1));

        // then
        List<SimulationGetUpdateDto> result = simulationService.findAll();
        assertEquals(simulationsDto, result);
        verify(simulationRepository, times(1)).findAll();
    }

    @Test
    public void test_findAll_whenSimulationsDontExist_ShouldReturnEmpty() {
        //given
        ArrayList<SimulationGetUpdateDto> emptyList = new ArrayList<>();

        // when
        when(simulationRepository.findAll()).thenReturn(new ArrayList<>());

        // then
        List<SimulationGetUpdateDto> result = simulationService.findAll();
        assertEquals(emptyList, result);
        verify(simulationRepository, times(1)).findAll();
    }

    @Test
    @Transactional
    public void test_addSimulation_whenSimulationsExist_ShouldAddSimulationAndReturnSimulationDto() {
        //given
        SimulationAddDto simulationAddDtoFromForm = new SimulationAddDto();
        SimulationGetUpdateDto simulationGetUpdateDtoFromSave = new SimulationGetUpdateDto();
        Simulation simulation = new Simulation();
        Simulation savedSimulation = new Simulation();
        Population population1 = new Population();
        Population population2 = new Population();
        List<Population> populations = Arrays.asList(population1, population2);

        // when
        when(populationService.calculatePopulation(simulation)).thenReturn(populations);
        when(simulationRepository.save(simulation)).thenReturn(savedSimulation);
        when(SimulationAddDtoMapper.map(simulationAddDtoFromForm)).thenReturn(simulation);
        when(SimulationGetUpdateDtoMapper.map(savedSimulation)).thenReturn(simulationGetUpdateDtoFromSave);

        // then
        SimulationGetUpdateDto result = simulationService.addSimulation(simulationAddDtoFromForm);
        assertEquals(simulationGetUpdateDtoFromSave, result);
        verify(populationService, times(1)).calculatePopulation(simulation);
        verify(simulationRepository, times(1)).save(simulation);
    }

    @Test
    public void test_deleteSimulationById_whenSimulationsExist_ShouldDeleteSimulationAndReturnSimulationDto() {
        // given
        Long id = 1L;
        Simulation simulation = new Simulation();
        SimulationGetUpdateDto simulationGetUpdateDto = new SimulationGetUpdateDto();

        // when
        when(simulationRepository.findById(id)).thenReturn(Optional.of(simulation));
        when(SimulationGetUpdateDtoMapper.map(simulation)).thenReturn(simulationGetUpdateDto);

        // then
        SimulationGetUpdateDto result = simulationService.deleteSimulationById(id);
        assertEquals(simulationGetUpdateDto, result);
        verify(simulationRepository, times(1)).delete(simulation);
    }

    @Test
    @Transactional
    public void test_updateSimulationById_whenSimulationsExist_ShouldUpdateSimulationAndReturnSimulationDto() {
        // given
        Long id = 1L;
        SimulationGetUpdateDto simulationGetUpdateDtoFromForm = new SimulationGetUpdateDto();
        simulationGetUpdateDtoFromForm.setId(id);
        Simulation simulation = new Simulation();
        Population populationOld1 = new Population();
        Population populationOld2 = new Population();
        List<Population> populationsOld = new ArrayList<>();
        populationsOld.add(populationOld1);
        populationsOld.add(populationOld2);
        simulation.setPopulations(populationsOld);
        Simulation updatedSimulation = new Simulation();
        SimulationGetUpdateDto updatedSimulationGetUpdateDto = new SimulationGetUpdateDto();
        Population populationNew1 = new Population();
        Population populationNew2 = new Population();
        List<Population> populationsNew = new ArrayList<>();
        populationsNew.add(populationNew1);
        populationsNew.add(populationNew2);

        // when
        when(simulationRepository.findById(id)).thenReturn(Optional.of(simulation));
        doNothing().when(populationService).deletePopulation(simulation.getPopulations());
        when(populationService.calculatePopulation(simulation)).thenReturn(populationsNew);
        when(simulationRepository.save(simulation)).thenReturn(updatedSimulation);
        when(SimulationGetUpdateDtoMapper.map(updatedSimulation)).thenReturn(updatedSimulationGetUpdateDto);

        // then
        SimulationGetUpdateDto result = simulationService.updateSimulationById(String.valueOf(id), simulationGetUpdateDtoFromForm);
        assertEquals(updatedSimulationGetUpdateDto, result);
        verify(populationService, times(1)).deletePopulation(populationsOld);
        verify(populationService, times(1)).calculatePopulation(simulation);
        verify(simulationRepository, times(1)).save(simulation);

    }
}
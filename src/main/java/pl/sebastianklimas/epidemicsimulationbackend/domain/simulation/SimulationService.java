package pl.sebastianklimas.epidemicsimulationbackend.domain.simulation;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.sebastianklimas.epidemicsimulationbackend.domain.population.PopulationService;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationAddDto;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationDetailedDto;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationGetUpdateDto;
import pl.sebastianklimas.epidemicsimulationbackend.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class SimulationService {
    private final SimulationRepository simulationRepository;
    private final PopulationService populationService;

    public SimulationService(SimulationRepository simulationRepository, PopulationService populationService) {
        this.simulationRepository = simulationRepository;
        this.populationService = populationService;
    }

    public List<SimulationGetUpdateDto> findAll() {
        return StreamSupport.stream(simulationRepository.findAll().spliterator(), false).map(SimulationGetUpdateDtoMapper::map).toList();
    }

    @Transactional
    public SimulationGetUpdateDto addSimulation(SimulationAddDto simulationAddDto) {
        Simulation simulation = SimulationAddDtoMapper.map(simulationAddDto);

        simulation.setPopulations(populationService.calculatePopulation(simulation));

        return SimulationGetUpdateDtoMapper.map(simulationRepository.save(simulation));
    }
    public SimulationGetUpdateDto deleteSimulationById(Long id) {
        Simulation simulation = simulationRepository.findById(id).orElseThrow(() -> new NotFoundException("Simulation not found"));
        SimulationGetUpdateDto simulationGetUpdateDto = SimulationGetUpdateDtoMapper.map(simulation);
        simulationRepository.delete(simulation);
        return simulationGetUpdateDto;
    }

    @Transactional
    public SimulationGetUpdateDto updateSimulationById(String id, SimulationGetUpdateDto simulationGetUpdateDto) {
        Simulation simulation = simulationRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("Simulation not found"));

        populationService.deletePopulation(simulation.getPopulations());
        modifySimulation(simulation, simulationGetUpdateDto);
        simulation.setPopulations(populationService.calculatePopulation(simulation));

        return SimulationGetUpdateDtoMapper.map(simulationRepository.save(simulation));
    }

    private void modifySimulation(Simulation simulation, SimulationGetUpdateDto simulationGetUpdateDto) {
        simulation.setN(simulationGetUpdateDto.getN());
        simulation.setP(simulationGetUpdateDto.getP());
        simulation.setI(simulationGetUpdateDto.getI());
        simulation.setR(simulationGetUpdateDto.getR());
        simulation.setM(simulationGetUpdateDto.getM());
        simulation.setTi(simulationGetUpdateDto.getTi());
        simulation.setTm(simulationGetUpdateDto.getTm());
        simulation.setTs(simulationGetUpdateDto.getTs());

        simulation.getPopulations().clear();
    }

    public SimulationDetailedDto findById(String id) {
        return SimulationDetailedDtoMapper.map(simulationRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("Simulation not found")));
    }
}

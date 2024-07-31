package pl.sebastianklimas.epidemicsimulationbackend.domain.simulation;

import pl.sebastianklimas.epidemicsimulationbackend.domain.population.PopulationDtoMapper;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationDetailedDto;

public class SimulationDetailedDtoMapper {
    public static SimulationDetailedDto map(Simulation simulation) {
        return new SimulationDetailedDto(
                simulation.getId(),
                simulation.getN(),
                simulation.getP(),
                simulation.getI(),
                simulation.getR(),
                simulation.getM(),
                simulation.getTi(),
                simulation.getTm(),
                simulation.getTs(),
                simulation.getPopulations().stream().map(PopulationDtoMapper::map).toList()
        );
    }
}

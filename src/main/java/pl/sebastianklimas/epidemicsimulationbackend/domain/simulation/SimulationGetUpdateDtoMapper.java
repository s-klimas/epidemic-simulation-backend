package pl.sebastianklimas.epidemicsimulationbackend.domain.simulation;

import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationGetUpdateDto;

public class SimulationGetUpdateDtoMapper {
    public static SimulationGetUpdateDto map(Simulation simulation) {
        return new SimulationGetUpdateDto(
                simulation.getId(),
                simulation.getN(),
                simulation.getP(),
                simulation.getI(),
                simulation.getR(),
                simulation.getM(),
                simulation.getTi(),
                simulation.getTm(),
                simulation.getTs()
        );
    }
}

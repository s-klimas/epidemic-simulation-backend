package pl.sebastianklimas.epidemicsimulationbackend.domain.simulation;

import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationAddDto;

public class SimulationAddDtoMapper {
    public static Simulation map(SimulationAddDto simulationAddDto) {
        return new Simulation(
                simulationAddDto.getN(),
                simulationAddDto.getP(),
                simulationAddDto.getI(),
                simulationAddDto.getR(),
                simulationAddDto.getM(),
                simulationAddDto.getTi(),
                simulationAddDto.getTm(),
                simulationAddDto.getTs()
        );
    }
}

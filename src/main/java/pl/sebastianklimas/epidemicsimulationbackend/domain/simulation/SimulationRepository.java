package pl.sebastianklimas.epidemicsimulationbackend.domain.simulation;

import org.springframework.data.repository.CrudRepository;

public interface SimulationRepository extends CrudRepository<Simulation, Long> {
}

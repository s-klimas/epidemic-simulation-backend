package pl.sebastianklimas.epidemicsimulationbackend.domain.population;

import pl.sebastianklimas.epidemicsimulationbackend.domain.population.dto.PopulationDto;

public class PopulationDtoMapper {
    public static PopulationDto map(Population population) {
        return new PopulationDto(
                population.getPi(),
                population.getPv(),
                population.getPm(),
                population.getPr()
        );
    }
}

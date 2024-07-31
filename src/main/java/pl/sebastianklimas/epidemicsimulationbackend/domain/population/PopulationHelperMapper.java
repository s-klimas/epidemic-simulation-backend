package pl.sebastianklimas.epidemicsimulationbackend.domain.population;

public class PopulationHelperMapper {
    public static Population map(PopulationHelper populationHelper) {
        return new Population(
                populationHelper.getPi(),
                populationHelper.getPv(),
                populationHelper.getPm(),
                populationHelper.getPr()
        );
    }
}

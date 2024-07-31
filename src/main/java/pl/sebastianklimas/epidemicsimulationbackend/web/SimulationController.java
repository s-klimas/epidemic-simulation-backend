package pl.sebastianklimas.epidemicsimulationbackend.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.SimulationService;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationAddDto;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationDetailedDto;
import pl.sebastianklimas.epidemicsimulationbackend.domain.simulation.dto.SimulationGetUpdateDto;
import pl.sebastianklimas.epidemicsimulationbackend.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SimulationController {
    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/simulation/{id}")
    @ResponseBody
    public ResponseEntity<SimulationDetailedDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(simulationService.findById(id));
    }

    @GetMapping("/simulation")
    @ResponseBody
    public ResponseEntity<List<SimulationGetUpdateDto>> get() {
        return ResponseEntity.ok(simulationService.findAll());
    }

    @PostMapping("/simulation")
    @ResponseBody
    public ResponseEntity<SimulationGetUpdateDto> add(@RequestBody SimulationAddDto simulationAddDto) {
        return new ResponseEntity<>(simulationService.addSimulation(simulationAddDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/simulation/{id}")
    @ResponseBody
    public ResponseEntity<SimulationGetUpdateDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(simulationService.deleteSimulationById(id),HttpStatus.ACCEPTED);
    }

    @PutMapping("/simulation/{id}")
    @ResponseBody
    public ResponseEntity<SimulationGetUpdateDto> update(@PathVariable String id, @RequestBody SimulationGetUpdateDto simulationGetUpdateDto) {
        return ResponseEntity.ok(simulationService.updateSimulationById(id, simulationGetUpdateDto));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity<?> handle() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

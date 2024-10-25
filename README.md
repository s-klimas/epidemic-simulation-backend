# Epidemic simulation

A simplified application to simulate the development of epidemics in a given population.

## Simulation parameters
 - N - name of the simulation
 - P - population size
 - I - initial number of infected people
 - R - indicator of how many people are infected by one infected person
 - M - mortality rate
 - Ti - the number of days that elapse between infection and recovery of the sick person
 - Tm - the number of days that elapse from the moment of infection to the death of the patient
 - Ts - Number of days for which the simulation is to be carried out

## Populations
After defining a simulation, the system will generate an initial population, that is, a record containing information about:
 - Pi - number of infected people
 - Pv - number of healthy people susceptible to infection
 - Pm - number of people who died
 - Pr - number of people who recovered and acquired immunity
It will then iteratively create successive objects containing the data set (Pi, Pv, Pm, Pr), for successive days of the simulation - in each successive object, the number of people in each group will change according to the rules:
 - M of those infected Tm days earlier will unfortunately move from the group of infected people to the group of dead people.
 - All the people infected Ti days earlier move from the group of infected people to the group of people who have recovered and acquired immunity.
 - Each infected person infects R healthy susceptible people.

## Database
The system stores data - simulation objects (N, P, I, R, M, Ti, Tm, Ts) and simulated data (Pi, Pv, Pm, Pr) for each simulation day in a postgresql database using JPA/Hibernate.

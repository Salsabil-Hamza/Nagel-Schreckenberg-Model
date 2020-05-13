package assignment3;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.List;

public class CarSource extends Actor {


    private double carEmittanceProbability;

    int incrementor1 = 0;
    int incrementor2 = 0;
    int incrementor3 = 0;


    int outflow = 0;
    double averageOutflow;

    List<Integer> speedValuesForOneLane = new ArrayList<Integer>();

    public CarSource(double carEmittanceProbability) {

        this.carEmittanceProbability = carEmittanceProbability;
    }


    @Override
    public void act() {

        generateCars();
        searchMeanSpeedLane();
        searchDensityLane();
        searchOutflowLane();


    }

    private void generateCars() {

        Location loc = this.getLocation();
        Grid<Actor> gr = this.getGrid();

        Location firstStepState = new Location(loc.getRow(), 1);

        if (Math.random() >= carEmittanceProbability && gr.get(firstStepState) == null) {
            Car car = new Car(5);
            car.putSelfInGrid(gr, firstStepState);
        }
    }

    private void searchDensityLane() {

        Location loc = this.getLocation();
        Grid<Actor> gr = this.getGrid();
        int density = 0;
        incrementor1++;

        if (incrementor1 % 5 == 0) {

            for (int i = 1; i < 50; i++) {
                Location cell = new Location(loc.getRow(), i);

                if (gr.get(cell) instanceof Car) {
                    density++;
                }
            }

            System.out.println(incrementor1  + " " + loc.getRow() + "  " + density);

        }


    }

    private void searchMeanSpeedLane() {
        Location loc = this.getLocation();
        Grid<Actor> gr = this.getGrid();
        incrementor2++;

        if (incrementor2 % 5 == 0) {

            for (int i = 1; i < 50; i++) {

                Location cell = new Location(loc.getRow(), i);

                if (gr.get(cell) instanceof Car) {
                    speedValuesForOneLane.add(((Car) gr.get(cell)).speed);
                }

            }

            System.out.println(incrementor2  + " " + loc.getRow() + "  " + average(speedValuesForOneLane));
        }
    }


    void searchOutflowLane() {

        Location loc = this.getLocation();
        Grid<Actor> gr = this.getGrid();
        incrementor3++;

        Location c = new Location(loc.getRow(), 49);
        if (gr.get(c) instanceof Flower) {

            this.outflow = this.outflow + 1;
            gr.remove(c);
        }

        if (incrementor3 % 5 == 0) {

            averageOutflow = (double) outflow / 5;
            outflow = 0;

            System.out.println(incrementor3 + "   " + loc.getRow() + "   " + averageOutflow);
        }


    }

    private Double average(List<Integer> speedValuesForOneLane) {

        double sum = 0;
        int length = speedValuesForOneLane.size();
        for (int value : speedValuesForOneLane) {
            sum = sum + value;

        }
        if (length == 0) {
            return 0.0;
        } else {
            return sum / length;
        }
    }


}


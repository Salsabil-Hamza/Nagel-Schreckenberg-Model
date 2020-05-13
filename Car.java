package assignment3;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.*;

public class Car extends Actor {

    public int speed;
    private int steps;

    public Car(int speed) {

        this.setColor(Color.GREEN);
        this.speed = speed;
        super.setDirection(0);
    }

    public void act() {

        this.adjustColor();
        this.move();
        this.dawdle();

    }

    private void adjustColor() {
        switch (this.speed) {
            case 5:
                this.setColor(Color.GREEN);
                break;
            case 4:
                this.setColor(Color.getHSBColor(119, 100, 35));
                break;
            case 3:
                this.setColor(Color.getHSBColor(119, 100, 56));
                break;
            case 2:
                this.setColor(Color.getHSBColor(119, 100, 68));
                break;
            case 1:
                this.setColor(Color.getHSBColor(9, 94, 98));
                break;
            case 0:
                this.setColor(Color.RED);
                break;

        }
    }

    private void dawdle() {

        if (Math.random() <= 0.3) {

            if (this.speed >= 2) {

                if (Math.random() <= 0.5) {

                    this.speed -= this.speed;
                }
                else {

                    this.speed = this.speed - 2;
                }
            }
            else if (this.speed == 1) {

                this.speed -= this.speed;
            }
        }
        else {

            if (this.speed < 5) {

                this.speed++;
            }
        }
    }

    public void move() {

        Location loc = this.getLocation();
        Grid<Actor> gr = this.getGrid();
        Location next = new Location(loc.getRow(), loc.getCol() + this.speed);

        if (gr != null && gr.getNumCols() > next.getCol()) {

            for (int i = loc.getCol() + 1; i <= next.getCol(); i++) {

                this.steps = 51;
                Location stateOfCase = new Location(loc.getRow(), i);
                if (gr.get(stateOfCase) instanceof Car) {

                    this.steps = i - 1;
                    break;
                }
            }
            Location nextWithConcession = new Location(loc.getRow(), steps);
            Location nextWithoutBraking = new Location(loc.getRow(), loc.getCol() + speed);

            if (this.steps == 51) {

                this.moveTo(nextWithoutBraking);
            }
            else {

                this.moveTo(nextWithConcession);
            }

        }

        /* Here a flower object was created at the 49th cell whenever the car leaves the grid
        it looks like the car has crashed on the wall Kabooom!
        this has the same detector functionality since whenever a flower object shows up (car crashes into the wall)
        it is possible to count the outflow of the cars leaving the grid
         */

        else {

            Flower flower = new Flower();

            if (gr.get(new Location(getLocation().getRow(), 49)) == null) {

                flower.putSelfInGrid(gr, new Location(getLocation().getRow(), 49));
                this.removeSelfFromGrid();
            }
            else {

                flower.putSelfInGrid(gr, new Location(getLocation().getRow(), 49));
            }
        }

    }
}

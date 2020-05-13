package assignment3;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;


public class CarRunner {

    public static void main(String[] args) {

        BoundedGrid grid = new BoundedGrid(10, 50);
        ActorWorld world = new ActorWorld(grid);

        for (int i = 0; i < 10; i++) {

            CarSource carSource = new CarSource(0.7);
            carSource.setColor(Color.CYAN);
            world.add(new Location(i, 0), carSource);
      }

        world.show();

    }
}

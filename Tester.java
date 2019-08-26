import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class Tester {
  Algo algo = new Algo();
  Cell tempCell = new Cell(3, 4, 0, 0, 0);
  Cell tempCell2 = new Cell(.3, .4, 0, 0, 0);
  
  @Before
  public void setup() {
    
    ArrayList<Point> traj = new ArrayList<Point>();
    Point point1 = new Point(0.2, 0.3);
    Point point2 = new Point(0.3, 0.2);
    Point point3 = new Point(0.0, 0.8);
    traj.add(point1);
    traj.add(point2);
    traj.add(point3);
    
    algo.setupVal(traj, 10);
  }
  
  @Test
  public void testSetN() {
   assertEquals(algo.n, 2);
  }
  
  @Test
  public void testInitValues() {
    
    assertEquals(algo.minI, 0.2, 0.0001);
    assertEquals(algo.maxI, 0.8, 0.0001);
    assertEquals(algo.minV, 0.0, 0.0001);
    assertEquals(algo.maxV, 0.3, 0.0001);
  }
  
  @Test
  public void testInitVar() {
    
    assertEquals(algo.Io, 0.5, 0.0001);
    assertEquals(algo.Vo, 0.15, 0.0001);
    assertEquals(algo.deltaI, 0.03, 0.0001);
    assertEquals(algo.deltaV, 0.015, 0.0001);
  }
  
  @Test
  public void testInitializeGrid() {
    
    assertEquals(algo.grid.get(0).get(0).leftPoint, -1.65, 0.0001);
    assertEquals(algo.grid.get(0).get(0).rightPoint, -5.3, 0.0001);
    assertEquals(algo.grid.get(0).get(0).binP, 0);
    assertEquals(algo.grid.get(0).get(0).posx, 0);
    assertEquals(algo.grid.get(0).get(0).posy, 0);
    assertEquals(algo.grid.size(), 20);
    assertEquals(algo.grid.get(2).size(), 20);
    ArrayList<Integer> hello = new ArrayList<Integer>();
    hello.add(9);
    hello.add(3);
    
  }
  
  @Test
  public void testSetWinner() {
    
    algo.winner = tempCell;
    algo.setWinner(algo.winner, 0);
    assertEquals(algo.winner, tempCell);
    algo.setWinner(tempCell2, 0);
  }
}

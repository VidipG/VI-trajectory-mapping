import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class Tester {
  Algo algo = new Algo();
  
  @Before
  public void setup() {
    algo.setN();
    
    Point point1 = new Point(0.2, 0.3);
    Point point2 = new Point(0.3, 0.2);
    Point point3 = new Point(0.0, 0.8);
    algo.originalTraj.add(point1);
    algo.originalTraj.add(point2);
    algo.originalTraj.add(point3);
    algo.initValues();
  }
  
  @Test
  public void testSetN() {
    assertEquals(algo.n, 10);
  }
  
  @Test
  public void testInitValues() {
    
    assertEquals(algo.minI, 0.2, 0.0001);
    assertEquals(algo.maxI, 0.8, 0.001);
    assertEquals(algo.minV, 0.0, 0.001);
    assertEquals(algo.maxV, 0.3, 0.001);
    assertEquals(algo.Io, 0.5, 0.001);
    
  }
  
}

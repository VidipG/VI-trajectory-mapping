import java.util.ArrayList;
import java.util.ListIterator;

public class Algo {
  ArrayList<Point> originalTraj = new ArrayList<Point>();
  
  ArrayList<Double> VSeq = new ArrayList<Double>();
  ArrayList<Double> ISeq = new ArrayList<Double>();
  
  ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
  
  int n = 10;
  
  double maxV;
  double minV;
  
  double maxI;
  double minI; 
  
  <T extends Comparable<Point>> void findMinMax() {
    final ListIterator<Point> itr = this.originalTraj.listIterator();
    Point next = itr.next();
    maxV = next.vPoint;
    minV = next.vPoint;
    maxI = next.iPoint;
    minI = next.iPoint;
    
    
    while (itr.hasNext()) {
      final Point currP = itr.next();
      
      if (currP.vPoint > maxV) {
        maxV = currP.vPoint;
      } else if (currP.vPoint <= minV) {
        minV = currP.vPoint;
      }
      
      if (currP.iPoint > maxI) {
        maxI = currP.iPoint;
      } else if (currP.iPoint <= minI) {
        minI = currP.iPoint;
      }
    }
  }
  
  double Vo = 0.5 * (maxV + minV);
  double Io = 0.5 * (maxI + minI);
  
  double deltaV = (maxV - Vo) / n;
  double deltaI = (maxI - Io) / n;
}


class Cell{
  double leftPoint;
  double rightPoint;
  int binP;
  
  Cell(double leftPoint, double rightPoint, int binP) {
    this.leftPoint = leftPoint;
    this.rightPoint = rightPoint;
    this.binP = binP;
  }
}

class Point {
  double vPoint;
  double iPoint;
  
  Point(double vPoint, double iPoint) {
    this.vPoint = vPoint;
    this.iPoint = iPoint;
  }
}
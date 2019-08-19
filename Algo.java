import java.util.ArrayList;

public class Algo {
  ArrayList<Point> traj = new ArrayList<Point>();
  ArrayList<Double> VSeq = new ArrayList<Double>();
  ArrayList<Double> ISeq = new ArrayList<Double>();
  int n;
  

  
  double maxV = traj.get(0).vPoint;
  double minV = traj.get(0).vPoint;
  
  double maxI = traj.get(0).iPoint;
  double minI = traj.get(0).iPoint;
  
  void calculatestuff() {
  for (Point currP : traj) {
    if (currP.vPoint > maxV) {
      maxV = currP.vPoint;
    } else if (currP.vPoint <= minV) {
      minV = currP.vPoint;
    } else if (currP.iPoint > maxI) {
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
  
  void generateSeq1() {
    this.n = 10;
    int i = 0;
    while (i > 0) {
      
    }
    
  }
  
  ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
}



class Cell{
  double leftPoint;
  double rightPoint;
  int binP;
}














class Point {
  double vPoint;
  double iPoint;
  
  Point(double vPoint, double iPoint) {
    this.vPoint = vPoint;
    this.iPoint = iPoint;
  }
}

public class Cell{
  double leftPoint; //voltageValue
  double rightPoint; //currentValue
  int binP; //binaryPosition
  
  Cell(double leftPoint, double rightPoint, int binP) {
    this.leftPoint = leftPoint;
    this.rightPoint = rightPoint;
    this.binP = binP; 
  }
  
  Cell(double leftPoint, double rightPoint) {
    this.leftPoint = leftPoint;
    this.rightPoint = rightPoint;
    this.binP = 0; //set to 0 initially for all cells
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
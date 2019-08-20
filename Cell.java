
public class Cell{
  double leftPoint; //voltageValue
  double rightPoint; //currentValue
  int binP; //binaryPosition
  int posx;
  int posy;
  
  Cell(double leftPoint, double rightPoint, int binP, int posx, int posy) {
    this.leftPoint = leftPoint;
    this.rightPoint = rightPoint;
    this.binP = binP;
    this.posx = posx;
    this.posy = posy;
  }
  
  Cell(double leftPoint, double rightPoint, int posx, int posy) {
    this.leftPoint = leftPoint;
    this.rightPoint = rightPoint;
    this.binP = 0; //set to 0 initially for all cells
    this.posx = posx;
    this.posy = posy;
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
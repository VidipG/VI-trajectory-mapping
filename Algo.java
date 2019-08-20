import java.util.ArrayList;
import java.util.ListIterator;

public class Algo {
  //Aggregate sample
  ArrayList<Point> originalTraj = new ArrayList<Point>();
  
  ArrayList<Double> VSeq = new ArrayList<Double>();
  ArrayList<Double> ISeq = new ArrayList<Double>();
  
  
  //current and voltage values need to be normalized
  ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
  
  ArrayList<Cell> halfCycle;
  
  double maxV;
  double minV;
  
  double maxI;
  double minI;
  
  //need a setter function for n, based on the input sample
  //n refers to the size of the graph on the x-axis (time)
  //n will likely depend on the time of the sample
  int n = 10;
  
  Cell winner;

  //startcell needs to be set to the first zero crossing point
  //need a setter function for this.startCell based on input data from hardware
  Cell startCell = new Cell(0, 0, 0, 0);
  
  //find min and max values of V and I in originalTraj
  <T extends Comparable<Point>> void findMinMax() {
    ListIterator<Point> itr = this.originalTraj.listIterator();
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
  
  //this step might be redundant
  public void generateSequences() {
    int temp = this.n - 1;
    
    for (int i = 0; i <= temp; i++) {
      double currV = Vo - (this.n - i) * deltaV;
      double currI = Io - (this.n - i) * deltaI; 
      
      VSeq.add(currV);
      ISeq.add(currI);
    }
    
    for (int i = temp; i > 0; i--) {
      double currV = Vo + (this.n - i) * deltaV;
      double currI = Io + (this.n - i) * deltaI; 
      
      VSeq.add(currV);
      ISeq.add(currI);
    }
    if (this.VSeq.size() != 2 * this.n) {
      throw new RuntimeException("Length of VSeq is wrong");
    }
    
    if (this.ISeq.size() != 2 * this.n) {
      throw new RuntimeException("Length of ISeq is wrong");
    }
  }
  
  //sets positional values for every Cell in the grid
  void initializeGrid() {
    for (int i = 0; i <= 2 * n; i++) { //column
      for (int j = 0; j <= 2 * n; j++) { //row
        Cell currCell = this.grid.get(i).get(j);
        
        currCell.leftPoint = (Vo + deltaV * (i - n));
        currCell.rightPoint = (Io + deltaI * (j - n));
        currCell.binP = 0;
        currCell.posx = i;
        currCell.posy = j;
      }
    }
  }
  
  //populateGrid() needs to start from zero-crossing point
  void setWinner(Cell startPoint) {
   
    int y = (int)startPoint.leftPoint;
    int z = (int)startPoint.rightPoint;
    
    for (int i = this.n + 1; i <= 2 * this.n; i++) {
      Cell currCell = this.grid.get(this.n + 1).get(i);
      
      if ((y - this.Vo) < (this.deltaV / 2) &&
          (z - (this.Io + (i - this.n) * this.deltaI)) < (this.deltaI / 2)) {
        currCell.binP = 1;
        this.winner = currCell;
      }
    }
    halfCycle.remove(0);
  }
  
  void searchNeighbours(Cell winner) {
    Utils u = new Utils();
    int currX = winner.posx;
    int currY = winner.posy;
    if ((currX > 0 && currX < this.n - 1) &&
        (currY > 0 && currY < this.n - 1)) {
      u.topLeft(winner, this.Vo, this.deltaV, this.Io, this.deltaI, this.n, this.grid);
      u.topRight(winner, this.Vo, this.deltaV, this.Io, this.deltaI, this.n, this.grid);
      u.top(winner, this.Vo, this.deltaV, this.Io, this.deltaI, this.n, this.grid);
      u.left(winner, this.Vo, this.deltaV, this.Io, this.deltaI, this.n, this.grid);
      u.right(winner, this.Vo, this.deltaV, this.Io, this.deltaI, this.n, this.grid);
      u.bottomLeft(winner, this.Vo, this.deltaV, this.Io, this.deltaI, this.n, this.grid);
      u.bottom(winner, this.Vo, this.deltaV, this.Io, this.deltaI, this.n, this.grid);
      u.bottomRight(winner, this.Vo, this.deltaV, this.Io, this.deltaI, this.n, this.grid);
    }
  }
  
  <T extends Comparable<Cell>> void completePopulationGrid() {
    
    //halfCycle of data points, starting at zero-crossing point
    //need a getter function for this, based on hardware input
    this.halfCycle = new ArrayList<Cell>();
    
    //this step is only a temporary fix, needs to be implemented
    //in the getter function
    this.halfCycle.add(0, this.startCell);
    
    for (int i = 0; i <= this.halfCycle.size(); i++) {
      this.setWinner(this.halfCycle.get(i));
      if (i > 0) {
        this.searchNeighbours(this.winner);
      }
    }
  }
}


















import java.util.ArrayList;

public class Algo {
  
  //Aggregate sample
  ArrayList<Point> originalTraj;
  ArrayList<Double> VSeq = new ArrayList<Double>();
  ArrayList<Double> ISeq = new ArrayList<Double>();
  //current and voltage values need to be normalized
  ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
  ArrayList<Cell> halfCycle;
  
  double maxV;
  double minV;
  double maxI;
  double minI;
  double Vo;
  double Io;
  double deltaV;
  double deltaI;
  //need a setter function for n, based on the input sample
  //n refers to the size of the graph on the x-axis (time)
  //n will likely depend on the time of the sample
  int n;
  
  Cell winner;
  //startcell needs to be set to the first zero crossing point
  //need a setter function for this.startCell based on input data from hardware
  Cell startCell = new Cell(0, 0, 0, 0);
  
  //needs to work with incoming data, temporary fix
  public void setupVal(ArrayList<Point> traj, int nVal) {
    this.n = nVal;
    this.initValues(traj);
    this.initializeGrid();
  }
  
  
  //find min and max values of V and I in originalTraj
  <T extends Comparable<Point>> void initValues(ArrayList<Point> traj) {
    this.originalTraj = traj;
    
    double tempV = this.originalTraj.get(0).vPoint;
    double tempI = this.originalTraj.get(0).iPoint;
    
    maxV = tempV;
    minV = tempV;
    maxI = tempI;
    minI = tempI;
    
    for (int i = 0; i < this.originalTraj.size(); i++) {
      Point currP = this.originalTraj.get(i);
      
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
    this.initVar();
  }
  
  public void initVar() {
    Vo = 0.5 * (maxV + minV);
    Io = 0.5 * (maxI + minI);
    deltaV = (maxV - Vo) / n;
    deltaI = (maxI - Io) / n;
  }
  
  //sets positional values for every Cell in the grid
  void initializeGrid() {
    Cell cellTemp;
    
    for (int i = 0; i < 2 * n; i++) { //column
      this.grid.add(new ArrayList<Cell>());
      for (int j = 0; j < 2 * n; j++) { //row
        cellTemp = new Cell(0, 0, i, j);
        this.grid.get(i).add(cellTemp);
      }
    }
    for (int i = 0; i < 2 * n; i++) { //column
      for (int j = 0; j < 2 * n; j++) { //row
        
        Cell currCell = this.grid.get(i).get(j);
        
        currCell.leftPoint =  (Vo + deltaV) * (i - n); 
        currCell.rightPoint = (Io + deltaI) * (j - n) ;
        currCell.binP = 0;
        currCell.posx = i;
        currCell.posy = j;
      }
    }
  }
  
  
  void completeGrid() {
    
    //halfCycle of data points, starting at zero-crossing point
    //need a getter function for this, based on hardware readings/data
    this.halfCycle = new ArrayList<Cell>();
    
    //this step is only a temporary fix, needs to be implemented
    //in the getter function
    this.halfCycle.add(0, this.startCell);
    
    for (int i = 0; i < this.halfCycle.size(); i++) {
      this.setWinner(this.halfCycle.get(i), i);
    }
  }
  
  //populateGrid() needs to start from zero-crossing point
  void setWinner(Cell startPoint, int acc) {
   
    int y = (int)startPoint.leftPoint;
    int z = (int)startPoint.rightPoint;
    
    for (int i = this.n + 1; i < 2 * this.n; i++) {
      Cell currCell = this.grid.get(this.n + 1).get(i);
      
      if ((y - this.Vo) < (this.deltaV / 2) &&
          (z - (this.Io + (i - this.n) * this.deltaI)) < (this.deltaI / 2)) {
        currCell.binP = 1;
        this.winner = currCell;
      }
      if (acc >= 1) {
        this.searchNeighbours(this.winner); 
      }
    }
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
}
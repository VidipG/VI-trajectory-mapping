import java.util.ArrayList;

public class Utils {
  
  void topLeft(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid) {
    int newX = start.posx - 1;
    int newY = start.posy - 1;
    Cell currCell = grid.get(newX).get(newY);
    
    this.setBinP(start, Vo, deltaV, Io, deltaI, newY, grid, currCell);
    }
  
  void top(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid) {
    int newX = start.posx;
    int newY = start.posy - 1;
    Cell currCell = grid.get(newX).get(newY);
    
    this.setBinP(start, Vo, deltaV, Io, deltaI, newY, grid, currCell);
  }
  
  void topRight(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid) {
    int newX = start.posx + 1;
    int newY = start.posy - 1;
    Cell currCell = grid.get(newX).get(newY);
    
    this.setBinP(start, Vo, deltaV, Io, deltaI, newY, grid, currCell);
  }
  
  void left(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid) {
    int newX = start.posx - 1;
    int newY = start.posy;
    Cell currCell = grid.get(newX).get(newY);
    
    this.setBinP(start, Vo, deltaV, Io, deltaI, newY, grid, currCell);
  }
  
  void right(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid) {
    int newX = start.posx + 1;
    int newY = start.posy;
    Cell currCell = grid.get(newX).get(newY);
    
    this.setBinP(start, Vo, deltaV, Io, deltaI, newY, grid, currCell);
  }
  
  void bottomLeft(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid) {
    int newX = start.posx - 1;
    int newY = start.posy + 1;
    Cell currCell = grid.get(newX).get(newY);
    
    this.setBinP(start, Vo, deltaV, Io, deltaI, newY, grid, currCell);
  }
  
  void bottom(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid) {
    int newX = start.posx;
    int newY = start.posy + 1;
    Cell currCell = grid.get(newX).get(newY);
    
    this.setBinP(start, Vo, deltaV, Io, deltaI, newY, grid, currCell);
  }
  
  void bottomRight(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid) {
    int newX = start.posx + 1;
    int newY = start.posy + 1;
    Cell currCell = grid.get(newX).get(newY);
    
    this.setBinP(start, Vo, deltaV, Io, deltaI, newY, grid, currCell);
  }
  
  void setBinP(Cell start, double Vo, double deltaV, double Io, double deltaI, double n, ArrayList<ArrayList<Cell>> grid,
      Cell currCell) {
    
    if ((currCell.leftPoint - Vo) < (deltaV / 2) &&
        (currCell.rightPoint - (Io + (currCell.posy - n) * deltaI)) < (deltaI / 2)) {
      start.binP = 1;
      }
  }
}
  
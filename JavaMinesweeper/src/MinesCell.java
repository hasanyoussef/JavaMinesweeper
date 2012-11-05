public class MinesCell {
	public static enum BombState {
		EMPTY, NEAR, BOMB
	}
	public static enum CellState{
		UNKNOWN, OPENED, FLAGGED
	}
	
	
	public MinesCell(int aX, int aY){
		setX(aX);
		setY(aY);
	}
	public MinesCell(int x, int y, BombState aBombState){
		this(x, y);
		setBombState(aBombState);
	}
	
	
	public BombState getBombState(){
		return bombState;
	}
	public void setBombState(BombState aState){
		bombState = aState;
	}
	
	public CellState getCellState(){
		return cellState;
	}
	public void setCellState(CellState aState){
		cellState = aState;
	}
	
	public int getX(){
		return x;
	}
	public void setX(int aX){
		x = aX;
	}
	
	public int getY(){
		return y;
	}
	public void setY(int aY){
		y = aY;
	}
	
	private BombState bombState = BombState.EMPTY;
	private CellState cellState = CellState.UNKNOWN;
	private int x;
	private int y;
}

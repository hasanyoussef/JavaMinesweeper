import java.util.ArrayList;


public class MinesManager implements MinesEvents {
	public MinesManager(){}
	public MinesManager(MinesField aMinesField){setMinesField(aMinesField);}
	public MinesManager(MinesField aMinesField, int aWidth, int aHeight, int aBombs){
		setMinesField(aMinesField);
		width = aWidth;
		height = aHeight;
		bombs = aBombs;	
	}
	
	public void newGame(){
		minesField.clearField();
		createBlankField();
		setMines();
	}
	public void newGame(MinesField aMinesField, int aWidth, int aHeight, int aBombs){
		setMinesField(aMinesField);
		width = aWidth;
		height = aHeight;
		bombs = aBombs;
		
		newGame();
	}
	
	
	private void createBlankField(){
		minesCells.clear();
		minesCells.ensureCapacity(height);
		for(int y=0; y<height; y++){
			ArrayList<MinesCell> oneRow = new ArrayList<MinesCell>(width);
			for(int x=0; x<width; x++){
				MinesCell oneCell = new MinesCell(x, y);
				oneRow.add(oneCell);
			}
			minesCells.add(oneRow);
		}
		minesField.createBlankField(width, height);
	}
	
	private void setMines() {
		for(int x = 0; x < bombs; x++){
			do{
				int coorX = (int) Math.round(Math.random()*(width-1));
				int coorY = (int) Math.round(Math.random()*(height-1));
				MinesCell cell = getCell(coorX, coorY);
				if( cell.getBombState() != MinesCell.BombState.BOMB ){
					cell.setBombState(MinesCell.BombState.BOMB);
					break;
				}
			}
			while(true);
		}
	}
	
	public MinesCell getCell(int x, int y) throws IndexOutOfBoundsException{
		if( y >= minesCells.size() )
			throw new IndexOutOfBoundsException();
		ArrayList<MinesCell> row = minesCells.get(y);
		
		if( x >= row.size() )
			throw new IndexOutOfBoundsException();
		return row.get(x);
	}
	
	
	public void setMinesField(MinesField aMinesField){
		minesField = aMinesField;
	}
	
	private int width = 8;
	private int height = 8;
	private int bombs = 10;
	
	private ArrayList< ArrayList<MinesCell> > minesCells = new ArrayList< ArrayList<MinesCell> >();
	private MinesField minesField;
	
	
	@Override
	public void UserCheckedCell(MinesCell aMinesCell) {
		switch (aMinesCell.getBombState()) {
		case EMPTY:
			minesField.setCellEmpty(aMinesCell);
			break;
		case BOMB:
			minesField.setCellBombed(aMinesCell);
			break;
		}
	}
	@Override
	public void UserFlaggedCell(MinesCell aMinesCell) {
		if(aMinesCell.getCellState() == MinesCell.CellState.UNKNOWN){
			aMinesCell.setCellState(MinesCell.CellState.FLAGGED);
			minesField.setCellFlagged( aMinesCell );
		}
		else if (aMinesCell.getCellState() == MinesCell.CellState.FLAGGED){
			aMinesCell.setCellState(MinesCell.CellState.UNKNOWN);
			minesField.setCellDefault( aMinesCell );
		}
	}
}


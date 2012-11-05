import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class Minesweeper extends JFrame implements Runnable, MinesField, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -99383564894515418L;
	
	/**
	 * Статическая часть, для запуска
	 */
	public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Minesweeper());
	}
	public static JFrame frame;

	
	
	public Minesweeper(){
		setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setTitle(DEFAULT_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Minesweeper.frame = this;
	}
	public static int DEFAULT_WIDTH = 600;
	public static int DEFAULT_HEIGHT = 600;
	public static String DEFAULT_TITLE = "My Java Mines";
	
	/*
	 * (non-Javadoc)
	 * Элементы интерфейса
	 */
	public JPanel panelInfo, panelGame;
	public JButton buttonNewGame;
	
	@Override
	public void run() {
		setVisible(true);
		setLayout( new GridLayout(2, 1) );
		
		buttonNewGame = new JButtonCell("New");
		buttonNewGame.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame();
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
		});
		
		panelInfo = new JPanel(new BorderLayout());
		panelInfo.add( buttonNewGame );
		
		panelGame = new JPanel();
		
		add( panelInfo );
		add( panelGame );
		
		minesManager = new MinesManager(this);
		newGame();
	}
	
	public void newGame(){
		minesManager.newGame();
	}
	
	private MinesManager minesManager;
	private ArrayList<ArrayList<JButtonCell>> buttonCells = new ArrayList<ArrayList<JButtonCell>>();
	
	public static ImageIcon ICON_CELL_FLAGGED = new ImageIcon("//home//assargin//workspace//JavaMines//bin//cell_flagged.gif");
	public static ImageIcon ICON_CELL_BOMBED = new ImageIcon("//home//assargin//workspace//JavaMines//bin//cell_bombed.gif");
	public static Color[] colorsNear = {
		Color.BLUE, Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.PINK, Color.CYAN, Color.BLACK
	};	
	
	
	private JButtonCell getButtonCell(int x, int y) throws IndexOutOfBoundsException{
		if( y >= buttonCells.size() )
			throw new IndexOutOfBoundsException();
		ArrayList<JButtonCell> row = buttonCells.get(y);
		
		if( x >= row.size() )
			throw new IndexOutOfBoundsException();
		return row.get(x);
	}

	
	@Override
	public void prepareFieldFirst() {
		
	}
	
	@Override
	public void clearField() {
		panelGame.removeAll();
		panelGame.revalidate();
		panelGame.repaint();
	}
	
	@Override
	public void createBlankField(int aX, int aY) {
		panelGame.setLayout(new GridLayout(aX, aY, -1, -1));
		
		buttonCells.clear();
		buttonCells.ensureCapacity(aY);
		for(int y=0; y<aY; y++){
			ArrayList<JButtonCell> oneRow = new ArrayList<JButtonCell>(aX);
			for(int x=0; x<aX; x++){
				JButtonCell buttonCell = new JButtonCell("", minesManager.getCell(x, y));
				buttonCell.addMouseListener(this);
				panelGame.add(buttonCell);
				
				oneRow.add(buttonCell);
			}
			
			buttonCells.add(oneRow);
		}
	}

	@Override
	public void setCellDefault(MinesCell aMinesCell) {
		JButtonCell buttonCell = getButtonCell( aMinesCell.getX(), aMinesCell.getY() );
		
		buttonCell.setIcon(null);
		buttonCell.setText("");
		buttonCell.setVisible(true);
	}
	
	@Override
	public void setCellFlagged(MinesCell aMinesCell) {
		JButtonCell buttonCell = getButtonCell( aMinesCell.getX(), aMinesCell.getY() );
		buttonCell.setIcon( ICON_CELL_FLAGGED );
	}

	@Override
	public void setCellEmpty(MinesCell aMinesCell) {
		JButtonCell buttonCell = getButtonCell( aMinesCell.getX(), aMinesCell.getY() );
		buttonCell.setVisible( false );
	}

	@Override
	public void setCellNear(MinesCell aMinesCell, int aCount) {
		JButtonCell buttonCell = getButtonCell( aMinesCell.getX(), aMinesCell.getY() );
		
		Color color = colorsNear[aCount-1];
		buttonCell.setOpaque( true );
		buttonCell.setForeground( color);
	}

	@Override
	public void setCellBombed(MinesCell aMinesCell) {
		JButtonCell buttonCell = getButtonCell( aMinesCell.getX(), aMinesCell.getY() );
		buttonCell.setIcon( ICON_CELL_BOMBED );
	}

	
	/*
	 * 
	 * Отработка мышиных событий на кнопках
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JButtonCell){
			JButtonCell source = (JButtonCell) e.getSource();
			this.setTitle( "Нажата ячейка на поле с координатами: (" + source.getCell().getX() + "; " + source.getCell().getY() + ")" );
			
			if( e.getButton() == MouseEvent.BUTTON1 ) // левой кнопкой мыши ..
				minesManager.UserCheckedCell( source.getCell() ); // .. наступаем на ячейку
			else if( e.getButton() == MouseEvent.BUTTON3 ) // правой кнопкой мыши ..
				minesManager.UserFlaggedCell( source.getCell() ); // .. помечаем ячейку как заминированную
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}

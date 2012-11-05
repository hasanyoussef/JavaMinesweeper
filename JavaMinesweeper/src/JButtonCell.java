import javax.swing.Icon;
import javax.swing.JButton;


public class JButtonCell extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -982263507337288016L;
	
	public JButtonCell(){
		super();
	}
	
	public JButtonCell(Icon icon) {
        super(null, icon);
    }
    
    public JButtonCell(String text) {
        super(text, null);
    }
    public JButtonCell(String text, MinesCell aMinesCell) {
        this(text);
        setCell(aMinesCell);
    }
	
	
	public MinesCell getCell(){
		return minesCell;
	}

	public void setCell(MinesCell aMinesCell){
		minesCell = aMinesCell;
	}
	
	private MinesCell minesCell;

}

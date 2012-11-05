import java.util.EventListener;

public interface MinesEvents extends EventListener{
	public void UserCheckedCell(MinesCell aMinesCell);
	public void UserFlaggedCell(MinesCell aMinesCell);
}

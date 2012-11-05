
public interface MinesField {
	/*
	 * Первые приготовления поля после старта приложения
	 */
	public void prepareFieldFirst();
	
	/*
	 * очистка игрового поля
	 */
	public void clearField();
	
	/*
	 * создание нового игрового поля
	 */
	public void createBlankField(int aX, int aY);
	
	
	/*
	 * 
	 */
	public void setCellDefault(MinesCell aMinesCell);
	
	/*
	 * установка флага мины на поле
	 */
	public void setCellFlagged(MinesCell aMinesCell);
	
	/*
	 * 
	 */
	public void setCellEmpty(MinesCell aMinesCell);
	
	/*
	 * 
	 */
	public void setCellNear(MinesCell aMinesCell, int aCount);
	
	/*
	 * 
	 */
	public void setCellBombed(MinesCell aMinesCell);
}

package model.world;

public abstract class Cell {
	
	private boolean isVisible;
	
	/**
	 * @return the isVisible
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * @param isVisible the isVisible to set
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Cell() {
		isVisible = false;
	}

}

package generator;

public class Block {
	private static final int DIMENSIONS = 3;
	private int[][] block;
	
	public Block() {
		block = new int[DIMENSIONS][DIMENSIONS];
	}

	public String displayContent() {
		String result = "";
		for (int i = 0; i<DIMENSIONS; i++) {
			for (int j = 0; j<DIMENSIONS; j++) {
				result+=getBlock()[i][j];
			}
		}
		return result;
	}
	public int[][] getBlock() {
		return block;
	}

	public void setBlock(int[][] block) {
		this.block = block;
	}
}

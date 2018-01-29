package generator;

public class MainGenerator {
	
	
	public static void main (String args[]) {
		
		BlockGenerator blockGenerator = new BlockGenerator();
		try {
			blockGenerator.generateBlocks("mapblocks.txt");
		} catch (InvalidSquareNumberException error) {
			System.err.println(error);
		}
	}
}

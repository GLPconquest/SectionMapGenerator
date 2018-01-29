package generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BlockGenerator {
	private static final int GROUND_SQUARES = 30;
	private static final int BUILDING_SQUARES = 50;
	
	private static final int[] WATER_RANDOM = {10,5,1,3,1};
	private static final int[] LAND_RANDOM = {4,6,3,4,3};
	private static final int[] DESERT_RANDOM = {1,6,12,0,1};
	private static final int[] FOREST_RANDOM = {2,6,0,10,4};
	private static final int[] MONT_RANDOM = {1,4,2,5,8};

	private static final int[] MINE_RANDOM = {0,3,6,3,8};
	private static final int[] FARM_RANDOM = {4,7,0,7,2};
	private static final int[] OILWELL_RANDOM = {8,2,8,2,0};
	private static final int[] NUCLEARPLANT_RANDOM = {8,4,0,3,5};
	private static final int[] CITY_RANDOM = {4,4,4,4,4};
	
	
	private ArrayList<Block> blocks;
	private Random rng;
	
	public BlockGenerator(){
		setBlocks(new ArrayList<Block>());
		setRng(new Random());
	}
	
	public void generateBlocks(String filename) throws InvalidSquareNumberException{
			for(int square = 0; square < 10; square++) {
				try {
					if(square<5) {
						for(int i = 0; i<GROUND_SQUARES; i++){
							Block block = new Block();
							block = generateGroundBlock(square, block);
							block.getBlock()[1][1]=square;
							if(!getBlocks().contains(block)) {
								getBlocks().add(block);
							}
							else {
								i--;
							}
						}
					}
					else {
						for(int i = 0; i<BUILDING_SQUARES; i++){
							Block block = new Block();
							block = generateBuildingBlock(square, block);
							block.getBlock()[1][1]=square;
							if(!getBlocks().contains(block)) {
								getBlocks().add(block);
							}
							else {
								i--;
							}
						}
					}
				}
				catch(InvalidSquareNumberException error) {
					throw error;
				}
			}
			writeMapBlocks(filename);
	}
	public int[] randomNumberGenerator(int toGenerate, int max, int min) {
		int[] randomNumber = new int[toGenerate];
		for(int i = 0; i<randomNumber.length; i++) {
			randomNumber[i]=rng.nextInt((max-min)+1)+min;
		}
		return randomNumber;
	}
	public Block generateGroundBlock(int square, Block block) throws InvalidSquareNumberException{
		
		int[] randomNumber = randomNumberGenerator(8, 20, 0);

		switch(square) {
		case(0): block = generateSquares(block, randomNumber, WATER_RANDOM);
		break;
		case(1): block = generateSquares(block, randomNumber, LAND_RANDOM);
		break;
		case(2): block = generateSquares(block, randomNumber, DESERT_RANDOM);
		break;
		case(3): block = generateSquares(block, randomNumber, FOREST_RANDOM);
		break;
		case(4): block = generateSquares(block, randomNumber, MONT_RANDOM);
		break;
		default:throw new InvalidSquareNumberException(square);
		}
		return block;
			
	}
	public Block generateBuildingBlock(int square, Block block) throws InvalidSquareNumberException{
		int[] randomNumber = randomNumberGenerator(8, 20, 0);

		switch(square) {
		case(5): block = generateSquares(block, randomNumber, MINE_RANDOM);
		break;
		case(6): block = generateSquares(block, randomNumber, FARM_RANDOM);
		break;
		case(7): block = generateSquares(block, randomNumber, OILWELL_RANDOM);
		break;
		case(8): block = generateSquares(block, randomNumber, NUCLEARPLANT_RANDOM);
		break;
		case(9): block = generateSquares(block, randomNumber, CITY_RANDOM);
		break;
		default:throw new InvalidSquareNumberException(square);
		}
		return block;
			
	}
	public Block generateSquares(Block block, int[] randomNumber, int[] random) {
		int number = 0;
		for(int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				if (i!=1 || j!=1) {
					if(randomNumber[number]<random[0]) {
						block.getBlock()[i][j]=0;
					}
					else if(randomNumber[number]<random[0]+random[1]) {
						block.getBlock()[i][j]=1;
					}
					else if(randomNumber[number]<random[0]+random[1]+random[2]) {
						block.getBlock()[i][j]=2;
					}
					else if(randomNumber[number]<random[0]+random[1]+random[2]+random[3]) {
						block.getBlock()[i][j]=3;
					}
					else {
						block.getBlock()[i][j]=4;
					}
					number++;
				}
			}
		}
		return block;
	}
	public void writeMapBlocks(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			for(Block current : getBlocks()) {
				writer.write(current.getBlock()[1][1]+"#"+current.displayContent());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	public Random getRng() {
		return rng;
	}

	public void setRng(Random rng) {
		this.rng = rng;
	}
}

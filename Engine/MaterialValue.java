package bitBoards;

public class MaterialValue {
	public static void initializeMaterialValue() {
		
		//translation of piece location to internal structure, [horizontal], [vertical]
		int index_white[][] = {{1,2,3,4,5,6,7,8}, {8,7,6,5,4,3,2,1}};
		int index_black[][] = {{1,2,3,4,5,6,7,8}, {1,2,3,4,5,6,7,8}};
		
		//pawn positional power
		int pawn_mg[] = {
			0,0,0,0,0,0,0,0,
			-6,-4,1,1,1,1,-4,-6,
			-6,-4,1,2,2,1,-4,-1,
			-6,-4,2,8,8,2,-4,-6,
			-4,-4,5,10,10,5,-4,-6,
			
		}
		
	}
}

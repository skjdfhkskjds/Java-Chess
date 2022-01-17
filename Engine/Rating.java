package bitBoards;

public class Rating {
	public static int evaluate() {
		int rating = 0;
		/*
		 * combines all the following functions with some function to value all these terms
		 * this needs to be a good combination of all these values
		 * this is the most important part of the rating
		 */
		return rating; 
	}
	public static int material() {
		/*
		 * piece value
		 * incorporate the piece power function into the value of material
		 * player turn matters because a piece could be advantageous in from one player's perspective but not advantageous from another's 
		 */
	}
	public static int piecePower() {
		/*
		 * pawn structure, pawn center
		 * knights and rooks lose value as pawns disappear
		 * penalty for undefended minor pieces
		*/
	}
	public static int mobility() {
		/*
		 * more than just a sum of all possible moves
		 * oftentimes mobility of knights and bishops are valued more than pieces like pawns and rooks
		 * rooks could be valued more from vertical movement than horizontal movement
		 * doesn't need to be as complex as this, but create some idea of piece power in terms of mobility
		 * penalty for early queen movement (could measure based on positioning or number of moves made)
		 * trapped pieces: an extreme case of lack of mobility, prominent where minor pieces are stuck from pawns
		*/
	}
	public static int centerControl() {
		/*
		 * control of the center and the extended center
		 * center is the center four squares
		 * extended center is the square around the center
		 * value center control greater than extended center
		 */
	}
	public static int kingSafety() {
		/*
		 * castling: points awarded for a castled king
		 * pawn shield: if a king is castled, pawns blocking the opposing pieces from attacking it offers additional strength, rather a lack thereof is a weakness
		 * open files: further disadvantage if there is an open file next to your king
		 * pawn storm: if enemy pawns are near the king, it could signify a threat of an open file
		 * king tropism: simplified form of king safety evaluation, takes into account distance between attacking pieces and the king (can be crude and inaccurate)
		 * virtual mobility: temporarily swap the king for a queen, to use virtual mobility to measure the threat of opponent's sliding pieces
		 * attacking king zone: 
		 * square control:
		 * attack units: 
		 * 
		 */
	}
}

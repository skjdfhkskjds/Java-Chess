package piece;

import java.util.ArrayList;
import java.util.List;

import chessPlayer.ChessPlayer;
import coreBuild.Squares;
import coreBuild.Team;
import images.ImageMaker;

public class BlackPawn extends Piece{

	public BlackPawn(Squares square, int value, Team side) {
		super(square, value, side, ImageMaker.getImage("pawn", side));
	}
	
	@Override
	public List<Squares> allLegalMoves() {
		List<Squares> legalMoves = new ArrayList<Squares>();
		Squares endSquare = square.getAdjacentSquare(0,-1);
		
		if (endSquare != null && endSquare.getPiece() == null) {
			legalMoves.add(endSquare);
			endSquare = square.getAdjacentSquare(0,-2);
			if (square.getRow() == 6 && endSquare.getPiece() == null) {
				legalMoves.add(endSquare);
			}
		}
		endSquare = square.getAdjacentSquare(1,-1);
		
		if(endSquare!= null && endSquare.getPiece() != null && endSquare.getPiece().side != side){
			legalMoves.add(endSquare);			
		}
		
		endSquare = square.getAdjacentSquare(-1, -1);

		if (endSquare != null && endSquare.getPiece() != null && endSquare.getPiece().side != side) {
			legalMoves.add(endSquare);
		}
		//checking for en passant
		Squares history = ChessPlayer.history;
		boolean EP = ChessPlayer.EP;
		if (history != null && history.getRow() == square.getRow()) {
			// right side
			if (history.getCol() == (square.getCol() + 1)) {
				endSquare = square.getAdjacentSquare(1, -1);
				if (endSquare == null) {
					legalMoves.add(endSquare);
					EP = true;
				}
			}
			// left side
			else if (history.getCol() == (square.getCol() - 1)) {
				endSquare = square.getAdjacentSquare(-1, -1);
				if (endSquare == null) {
					legalMoves.add(endSquare);
					EP = true;
				}
			}
		}

		return legalMoves;
	}
}

package piece;

import java.util.ArrayList;
import java.util.List;

import coreBuild.Squares;
import coreBuild.Team;
import images.ImageMaker;

public class Knight extends Piece{

	public Knight(Squares square, int value, Team side) {
		super(square, value, side, ImageMaker.getImage("knight", side));
	}
	
	@Override
	public String toString() {
		return side == Team.white ? "N" : "n";
	}

	@Override
	public List<Squares> allLegalMoves() {
		List<Squares> legalMoves = new ArrayList<Squares>();
		Squares targetSquare = square.getAdjacentSquare(1, 2);
		checkSquare(targetSquare, legalMoves);
		targetSquare = square.getAdjacentSquare(-1, 2);
		checkSquare(targetSquare, legalMoves);
		targetSquare = square.getAdjacentSquare(1, -2);
		checkSquare(targetSquare, legalMoves);
		targetSquare = square.getAdjacentSquare(-1, -2);
		checkSquare(targetSquare, legalMoves);
		targetSquare = square.getAdjacentSquare(2, 1);
		checkSquare(targetSquare, legalMoves);
		targetSquare = square.getAdjacentSquare(-2, 1);
		checkSquare(targetSquare, legalMoves);
		targetSquare = square.getAdjacentSquare(2, -1);
		checkSquare(targetSquare, legalMoves);
		targetSquare = square.getAdjacentSquare(-2, -1);
		checkSquare(targetSquare, legalMoves);
		return legalMoves;
	}
}

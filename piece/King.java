package piece;

import java.util.ArrayList;
import java.util.List;

import coreBuild.Squares;
import coreBuild.Team;
import images.ImageMaker;

public class King extends Piece{
	public King (Squares square, int value, Team side) {
		super(square, value, side, ImageMaker.getImage("king", side));
	}
	
	@Override
	public String toString() {
		return side == Team.white ? "K" : "k";
	}

	@Override
	public List<Squares> allLegalMoves() {
		List<Squares> legalMoves = new ArrayList<Squares>();
		
		for(int hor = -1; hor <= 1; hor++) {
			for (int ver = -1; ver <= 1; ver++) {
				Squares targetSquare = square.getAdjacentSquare(hor, ver);
				checkSquare(targetSquare, legalMoves);
			}
		}
		return legalMoves;
	}
}

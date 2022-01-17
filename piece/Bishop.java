package piece;

import java.util.ArrayList;
import java.util.List;

import coreBuild.Squares;
import coreBuild.Team;
import images.ImageMaker;

public class Bishop extends Piece{

	public Bishop(Squares square, int value, Team side) {
		super(square, value, side, ImageMaker.getImage("bishop", side));
	}
	
	@Override
	public String toString() {
		return side == Team.white ? "B" : "b";
	}
	
	@Override
	public List<Squares> allLegalMoves() {
		List<Squares> legalMoves = new ArrayList<Squares>();
		legalMoves.addAll(allLinearMoves(1,1));
		legalMoves.addAll(allLinearMoves(1,-1));
		legalMoves.addAll(allLinearMoves(-1,-1));
		legalMoves.addAll(allLinearMoves(-1,1));
		return legalMoves;
	}
}

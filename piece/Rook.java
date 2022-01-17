package piece;

import java.util.ArrayList;
import java.util.List;

import coreBuild.Squares;
import coreBuild.Team;
import images.ImageMaker;

public class Rook extends Piece{

	public Rook(Squares square, int value, Team side) {
		super(square, value, side, ImageMaker.getImage("rook", side));
	}
	
	@Override
	public String toString() {
		return side == Team.white ? "R" : "r";
	}

	@Override
	public List<Squares> allLegalMoves() {
		List<Squares> legalMoves = new ArrayList<Squares>();
		legalMoves.addAll(allLinearMoves(1,0));
		legalMoves.addAll(allLinearMoves(-1,0));
		legalMoves.addAll(allLinearMoves(0,1));
		legalMoves.addAll(allLinearMoves(0,-1));
		return legalMoves;
	}
}

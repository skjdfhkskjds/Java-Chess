package piece;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import coreBuild.Squares;
import coreBuild.Team;

public abstract class Piece {
	
	/*
	 * for the value of the defined pieces, values are not hard coded unlike in the tutorial
	 * fix or change this later
	 */
	
	private final int value;
	protected Squares square;
	protected final Team side;
	protected final Image icon;
	
	public abstract List<Squares> allLegalMoves();
	
	protected Piece(Squares square, int value, Team side, Image icon) {
		this.square = square;
		this.square.setPiece(this);
		this.value = value;
		this.side = side;
		this.icon = icon;
	}
	public void setSquare(Squares square) {
		this.square = square;
	}
	public Squares getSquare() {
		return square;
	}
	public Image getIcon() {
		return icon;
	}
	public Team getTeam() {
		return side;
	}
	public int getValue() {
		return value;
	}
	
	protected void checkSquare(Squares targetSquare, List<Squares> legalMoves) {
		//add condition if move does not put king in check
		if (targetSquare != null && targetSquare.getPiece() == null || targetSquare.getPiece().side != side){
			legalMoves.add(targetSquare);
		}
	}
	protected List<Squares> allLinearMoves(int horizontal, int vertical) {
		List<Squares> legalMoves = new ArrayList<Squares>();
		Squares targetSquare = square.getAdjacentSquare(horizontal, vertical);
		
		while (targetSquare != null) {
			if (targetSquare.getPiece() == null) {
				legalMoves.add(targetSquare);
			}
			else if (targetSquare.getPiece().side == side) {
				break;
			}
			else {
				legalMoves.add(targetSquare);
				break;
			}
			targetSquare = targetSquare.getAdjacentSquare(horizontal, vertical);
		}
		return legalMoves;
		
	}

}

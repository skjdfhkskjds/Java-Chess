package coreBuild;

import piece.Piece;

public class ChessMove {
	private Squares initialSquare;
	private Squares targetSquare;
	private Piece targetPiece;
	
	public ChessMove(Squares initialSquare, Squares endSquare, Piece targetPiece) {
		this.initialSquare = initialSquare;
		this.targetSquare = endSquare;
		this.targetPiece = targetPiece;
	}

	public Squares getInitialSquare() {
		return initialSquare;
	}

	public void setInitialSquare(Squares initalSquare) {
		this.initialSquare = initalSquare;
	}

	public Squares getTargetSquare() {
		return targetSquare;
	}

	public void setEndSquare(Squares endSquare) {
		this.targetSquare = endSquare;
	}

	public Piece getTargetPiece() {
		return targetPiece;
	}

	public void setTargetPiece(Piece targetPiece) {
		this.targetPiece = targetPiece;
	}
	
}

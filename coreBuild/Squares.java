package coreBuild;
import piece.Piece;

public class Squares {
	private final Board board;
	private final int row;
	private final int col;
	private boolean legal;
	private Piece piece;
	
	public Squares(Board board, int row, int col) {
		this.board = board;
		this.row = row;
		this.col = col;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public Piece getPiece() {
		return piece;
	}
	public Squares getAdjacentSquare(int horizontal, int vertical) {
		return board.getSquare(vertical + row, horizontal + col);
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public boolean isLegal() {
		return legal;
	}
	public void setLegal(boolean legal) {
		this.legal = legal;
	}
}

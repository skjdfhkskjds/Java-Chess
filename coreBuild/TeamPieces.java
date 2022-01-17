package coreBuild;

import java.util.ArrayList;
import java.util.List;
import piece.Piece;

public class TeamPieces {
	private List<Piece> alivePieces;
	private List<Piece> capturedPieces;
	
	public TeamPieces() {
		alivePieces = new ArrayList<Piece>();
		capturedPieces = new ArrayList<Piece>();
	}
	
	public List<Piece> getAlivePieces() {
		return alivePieces;
	}
	
	public List<Piece> getCapturedPieces() {
		return capturedPieces;
	}
	
	public void addPiece(Piece piece) {
		alivePieces.add(piece);
	}
	public void removePiece(Piece piece) {
		alivePieces.remove(piece);
		capturedPieces.add(piece);
	}
}

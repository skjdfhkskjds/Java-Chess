package chessPlayer;

import java.util.ArrayList;
import java.util.List;

import coreBuild.TeamPieces;
import coreBuild.Board;
import coreBuild.ChessMove;
import coreBuild.Squares;
import coreBuild.Team;
import piece.Piece;

public class ChessPlayer {
	private Board board;
	private Team side;
	
	public ChessPlayer(Board board, Team side) {
		this.board = board;
		this.side = side;
	}
	
	public ChessMove decideMove() {
		return null;
	}
	
	public void makeMove(ChessMove move){
		if (move.getTargetPiece() != null) {
			TeamPieces army = board.getArmy(side.opposite());
			army.removePiece(move.getTargetPiece());
		}
		Piece movingPiece = move.getInitialSquare().getPiece();
		movingPiece.setSquare(move.getTargetSquare());
		move.getTargetSquare().setPiece(movingPiece);
		move.getInitialSquare().setPiece(null);
	}
	public List<ChessMove> allLegalMoves(){
		List<Piece> alivePieces = board.getArmy(side).getAlivePieces();
		List<ChessMove> legalMoves = new ArrayList<ChessMove>();
		
		for (Piece p: alivePieces) {
			for (Squares s : p.allLegalMoves()) {
				ChessMove move = new ChessMove(p.getSquare(), s, s.getPiece());
				legalMoves.add(move);
			}
		}
		return legalMoves;
	}
}

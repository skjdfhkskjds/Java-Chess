package coreBuild;

public class Board {
	
	public static final int length = 8;
	
	private Squares[][] squares;
	private TeamPieces whiteTeam;
	private TeamPieces blackTeam;

	public Board(TeamPieces whiteTeam, TeamPieces blackTeam) {
		this.whiteTeam = whiteTeam;
		this.blackTeam = blackTeam;
		squares = new Squares[length][length];
		
		for (int rows = 0; rows < length; rows++) {
			for (int cols = 0; cols < length; cols++) {
				squares[rows][cols] = new Squares(this, rows, cols);
			}
		}
	}
	
	public TeamPieces getTeam(Team side) {
		return side == Team.white ? whiteTeam : blackTeam;
	}
	
	public Squares getSquare(int row, int col) {
		return (row < 0 || row >= length || col < 0 || col >= length) ? null: squares[row][col];
	}

	public void show() {
		for (int rows = length-1; rows >= 0; rows--) {
			for (int cols = 0; cols < length; cols++) {
				if (squares[rows][cols].getPiece() != null) {
					System.out.print(squares[rows][cols].getPiece());
				}
				else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
}

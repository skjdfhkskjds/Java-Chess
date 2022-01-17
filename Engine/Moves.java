package bitBoards;

import pieces.Bishop;
import pieces.Pawn;
import pieces.King;
import pieces.Knight;
import pieces.Queen;
import pieces.Rook;

public class Moves {
	public static long FILE_A = 72340172838076673L;
	public static long FILE_H = -9187201950435737472L;
	public static long FILE_AB = 217020518514230019L;
	public static long FILE_GH = -4557430888798830400L;
	public static long RANK_1 = -72057594037927936L;
	public static long RANK_4 = 1095216660480L;
	public static long RANK_5 = 4278190080L;
	public static long RANK_8 = 255L;
	public static long CENTRE = 103481868288L;
	public static long EXTENDED_CENTRE = 66229406269440L;
	public static long KING_SIDE = -1085102592571150096L;
	public static long QUEEN_SIDE = 1085102592571150095L;
	public static long KING_SPAN=460039L;
    public static long KNIGHT_SPAN=43234889994L;
    public static long NOT_MY_PIECES;
	public static long MY_PIECES;
	public static long OCCUPIED;
	public static long EMPTY;
    public static long CASTLE_ROOKS[]={63,56,7,0};

	public static long RankMasks8[] =/*from rank1 to rank8*/
	    {
	        0xFFL, 0xFF00L, 0xFF0000L, 0xFF000000L, 0xFF00000000L, 0xFF0000000000L, 0xFF000000000000L, 0xFF00000000000000L
	    };
	public static long FileMasks8[] =/*from fileA to FileH*/
	    {
	        0x101010101010101L, 0x202020202020202L, 0x404040404040404L, 0x808080808080808L,
	        0x1010101010101010L, 0x2020202020202020L, 0x4040404040404040L, 0x8080808080808080L
	    };
	public static long DiagonalMasks8[] =/*from top left to bottom right*/
	    {
		0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L,
		0x102040810204080L, 0x204081020408000L, 0x408102040800000L, 0x810204080000000L,
		0x1020408000000000L, 0x2040800000000000L, 0x4080000000000000L, 0x8000000000000000L
	    };
	public static long AntiDiagonalMasks8[] =/*from top right to bottom left*/
	    {
		0x80L, 0x8040L, 0x804020L, 0x80402010L, 0x8040201008L, 0x804020100804L, 0x80402010080402L,
		0x8040201008040201L, 0x4020100804020100L, 0x2010080402010000L, 0x1008040201000000L,
		0x804020100000000L, 0x402010000000000L, 0x201000000000000L, 0x100000000000000L
	    };
	public static long HAndVMoves(int s)
	{
		long binaryS = 1L << s;
		long possibilitiesHorizontal = (OCCUPIED - 2 * binaryS)
				^ Long.reverse(Long.reverse(OCCUPIED) - 2 * Long.reverse(binaryS));
		long possibilitiesVertical = ((OCCUPIED & FileMasks8[s % 8]) - (2 * binaryS))
				^ Long.reverse(Long.reverse(OCCUPIED & FileMasks8[s % 8]) - (2 * Long.reverse(binaryS)));
		return (possibilitiesHorizontal & RankMasks8[s / 8]) | (possibilitiesVertical & FileMasks8[s % 8]);
	}

	public static long DAndAntiDMoves(int s) {
		long binaryS = 1L << s;
		long possibilitiesDiagonal = ((OCCUPIED & DiagonalMasks8[(s / 8) + (s % 8)]) - (2 * binaryS)) ^ Long.reverse(Long.reverse(OCCUPIED & DiagonalMasks8[(s / 8) + (s % 8)]) - (2 * Long.reverse(binaryS)));
		long possibilitiesAntiDiagonal = ((OCCUPIED & AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)]) - (2 * binaryS))^ Long.reverse(Long.reverse(OCCUPIED & AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)])- (2 * Long.reverse(binaryS)));
		return (possibilitiesDiagonal & DiagonalMasks8[(s / 8) + (s % 8)])| (possibilitiesAntiDiagonal & AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)]);
	}
	
    public static long makeMove(long board, String move, char type) {
        if (Character.isDigit(move.charAt(3))) {//'regular' move
            int start=(Character.getNumericValue(move.charAt(0))*8)+(Character.getNumericValue(move.charAt(1)));
            int end=(Character.getNumericValue(move.charAt(2))*8)+(Character.getNumericValue(move.charAt(3)));
            if (((board>>>start)&1)==1) {board&=~(1L<<start); board|=(1L<<end);} else {board&=~(1L<<end);}
        } else if (move.charAt(3)=='P') {//pawn promotion
            int start, end;
            if (Character.isUpperCase(move.charAt(2))) {
                start=Long.numberOfTrailingZeros(FileMasks8[move.charAt(0)-'0']&RankMasks8[1]);
                end=Long.numberOfTrailingZeros(FileMasks8[move.charAt(1)-'0']&RankMasks8[0]);
            } else {
                start=Long.numberOfTrailingZeros(FileMasks8[move.charAt(0)-'0']&RankMasks8[6]);
                end=Long.numberOfTrailingZeros(FileMasks8[move.charAt(1)-'0']&RankMasks8[7]);
            }
            if (type==move.charAt(2)) {board|=(1L<<end);} else {board&=~(1L<<start); board&=~(1L<<end);}
        } else if (move.charAt(3)=='E') {//en passant
            int start, end;
            if (move.charAt(2)=='W') {
                start=Long.numberOfTrailingZeros(FileMasks8[move.charAt(0)-'0']&RankMasks8[3]);
                end=Long.numberOfTrailingZeros(FileMasks8[move.charAt(1)-'0']&RankMasks8[2]);
                board&=~(FileMasks8[move.charAt(1)-'0']&RankMasks8[3]);
            } else {
                start=Long.numberOfTrailingZeros(FileMasks8[move.charAt(0)-'0']&RankMasks8[4]);
                end=Long.numberOfTrailingZeros(FileMasks8[move.charAt(1)-'0']&RankMasks8[5]);
                board&=~(FileMasks8[move.charAt(1)-'0']&RankMasks8[4]);
            }
            if (((board>>>start)&1)==1) {board&=~(1L<<start); board|=(1L<<end);}
        } else {
            System.out.print("ERROR: Invalid move type");
        }
        return board;
    }
    public static long makeMoveCastle(long rookBoard, long kingBoard, String move, char type) {
        int start=(Character.getNumericValue(move.charAt(0))*8)+(Character.getNumericValue(move.charAt(1)));
        if ((((kingBoard>>>start)&1)==1)&&(("0402".equals(move))||("0406".equals(move))||("7472".equals(move))||("7476".equals(move)))) {//'regular' move
            if (type=='R') {
                switch (move) {
                    case "7472": rookBoard&=~(1L<<CASTLE_ROOKS[1]); rookBoard|=(1L<<(CASTLE_ROOKS[1]+3));
                        break;
                    case "7476": rookBoard&=~(1L<<CASTLE_ROOKS[0]); rookBoard|=(1L<<(CASTLE_ROOKS[0]-2));
                        break;
                }
            } else {
                switch (move) {
                    case "0402": rookBoard&=~(1L<<CASTLE_ROOKS[3]); rookBoard|=(1L<<(CASTLE_ROOKS[3]+3));
                        break;
                    case "0406": rookBoard&=~(1L<<CASTLE_ROOKS[2]); rookBoard|=(1L<<(CASTLE_ROOKS[2]-2));
                        break;
                }
            }
        }
        return rookBoard;
    }
    public static long makeMoveEP(long board,String move) {
        if (Character.isDigit(move.charAt(3))) {
            int start=(Character.getNumericValue(move.charAt(0))*8)+(Character.getNumericValue(move.charAt(1)));
            if ((Math.abs(move.charAt(0)-move.charAt(2))==2)&&(((board>>>start)&1)==1)) {//pawn double push
                return FileMasks8[move.charAt(1)-'0'];
            }
        }
        return 0;
    }
	
	public static String possibleMovesW(long WP, long WN, long WB, long WR, long WQ, long WK, long BP, long BN, long BB, long BR, long BQ, long BK, long EP, boolean CWK,boolean CWQ,boolean CBK,boolean CBQ) {
		NOT_MY_PIECES = ~(WP | WN | WB | WR | WQ | WK | BK);// added BK to avoid illegal capture
		MY_PIECES = WP | WN | WB | WR | WQ ;// omitted BK to avoid illegal capture
        OCCUPIED=WP|WN|WB|WR|WQ|WK|BP|BN|BB|BR|BQ|BK;
        EMPTY=~OCCUPIED;
		String list = Pawn.possibleWP(WP, BP, EP)
		+ Knight.possibleN(OCCUPIED,WN)
		+ Bishop.possibleB(OCCUPIED,WB)
		+ Rook.possibleR(OCCUPIED,WR)
		+ Queen.possibleQ(OCCUPIED,WQ)
		+ King.possibleK(OCCUPIED,WK)
        + King.possibleCW(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,CWK,CWQ);
        unsafeForBlack(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
		return list;
	}
	public static String possibleMovesB(long WP, long WN, long WB, long WR, long WQ, long WK, long BP, long BN, long BB, long BR, long BQ, long BK, long EP, boolean CWK,boolean CWQ,boolean CBK,boolean CBQ) {
		NOT_MY_PIECES = ~(BP | BN | BB | BR | BQ | BK | WK);// added BK to avoid illegal capture
		MY_PIECES = BP | BN | BB | BR | BQ;// omitted BK to avoid illegal capture
        OCCUPIED=WP|WN|WB|WR|WQ|WK|BP|BN|BB|BR|BQ|BK;
        EMPTY=~OCCUPIED;
		String list = Pawn.possibleBP(WP, BP, EP)
		+ Knight.possibleN(OCCUPIED,BN)
		+ Bishop.possibleB(OCCUPIED,BB)
		+ Rook.possibleR(OCCUPIED,BR)
		+ Queen.possibleQ(OCCUPIED,BQ)
		+ King.possibleK(OCCUPIED,BK)
		+ King.possibleCB(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,CBK,CBQ);
        unsafeForWhite(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
		return list;
	}
	public static long unsafeForBlack(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK){
		long unsafe;
        OCCUPIED=WP|WN|WB|WR|WQ|WK|BP|BN|BB|BR|BQ|BK;
        //pawn
        unsafe=((WP>>>7)&~FILE_A);//pawn capture right
        unsafe|=((WP>>>9)&~FILE_H);//pawn capture left
        long possibility;
        //knight
        long i=WN&~(WN-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            if (iLocation>18)
            {
                possibility=KNIGHT_SPAN<<(iLocation-18);
            }
            else {
                possibility=KNIGHT_SPAN>>(18-iLocation);
            }
            if (iLocation%8<4)
            {
                possibility &=~FILE_GH;
            }
            else {
                possibility &=~FILE_AB;
            }
            unsafe |= possibility;
            WN&=~i;
            i=WN&~(WN-1);
        }
        //bishop/queen
        long QB=WQ|WB;
        i=QB&~(QB-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            possibility=DAndAntiDMoves(iLocation);
            unsafe |= possibility;
            QB&=~i;
            i=QB&~(QB-1);
        }
        //rook/queen
        long QR=WQ|WR;
        i=QR&~(QR-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            possibility=HAndVMoves(iLocation);
            unsafe |= possibility;
            QR&=~i;
            i=QR&~(QR-1);
        }
        //king
        int iLocation=Long.numberOfTrailingZeros(WK);
        if (iLocation>9)
        {
            possibility=KING_SPAN<<(iLocation-9);
        }
        else {
            possibility=KING_SPAN>>(9-iLocation);
        }
        if (iLocation%8<4)
        {
            possibility &=~FILE_GH;
        }
        else {
            possibility &=~FILE_AB;
        }
        unsafe |= possibility;
        return unsafe;
    }
	public static long unsafeForWhite(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK) {
		long unsafe;
        OCCUPIED=WP|WN|WB|WR|WQ|WK|BP|BN|BB|BR|BQ|BK;
        //pawn
        unsafe=((BP<<7)&~FILE_H);//pawn capture right
        unsafe|=((BP<<9)&~FILE_A);//pawn capture left
        long possibility;
        //knight
        long i=BN&~(BN-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            if (iLocation>18)
            {
                possibility=KNIGHT_SPAN<<(iLocation-18);
            }
            else {
                possibility=KNIGHT_SPAN>>(18-iLocation);
            }
            if (iLocation%8<4)
            {
                possibility &=~FILE_GH;
            }
            else {
                possibility &=~FILE_AB;
            }
            unsafe |= possibility;
            BN&=~i;
            i=BN&~(BN-1);
        }
        //bishop/queen
        long QB=BQ|BB;
        i=QB&~(QB-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            possibility=DAndAntiDMoves(iLocation);
            unsafe |= possibility;
            QB&=~i;
            i=QB&~(QB-1);
        }
        //rook/queen
        long QR=BQ|BR;
        i=QR&~(QR-1);
        while(i != 0)
        {
            int iLocation=Long.numberOfTrailingZeros(i);
            possibility=HAndVMoves(iLocation);
            unsafe |= possibility;
            QR&=~i;
            i=QR&~(QR-1);
        }
        //king
        int iLocation=Long.numberOfTrailingZeros(BK);
        if (iLocation>9)
        {
            possibility=KING_SPAN<<(iLocation-9);
        }
        else {
            possibility=KING_SPAN>>(9-iLocation);
        }
        if (iLocation%8<4)
        {
            possibility &=~FILE_GH;
        }
        else {
            possibility &=~FILE_AB;
        }
        unsafe |= possibility;
        return unsafe;
    }
}
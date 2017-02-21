/**
 * 
 */
package xiangqi.student.bgbianchi.betaxiangqi;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentbgbianchi.betaxiangqi.common.Board;
import xiangqi.studentbgbianchi.betaxiangqi.common.PieceImpl;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.AbsMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.AdvisorMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.SoldierMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

/**
 * BetaXiangqi is a more indepth attempt at Xianqi, it implements different peices and movement validation. Along with testing
 * if the game is over, draw or win.
 * 
 * @author ben
 *
 */
public class BetaXiangqiGame implements XiangqiGame {

	protected static final String NO_ERROR = "";
	protected final int BETA_XQ_MOVECOUNT = 20;
	protected final String ERROR_ILLEGAL_MOVE = "Error. The move you provided is illegal in Beta Xiangqi.";
	protected Board board;
	protected Integer[] palaceBoundaries = {0,2,1,6};
	protected String error;
	protected XiangqiColor color = XiangqiColor.RED;
	protected int moveCount = 0;
	
	

	public BetaXiangqiGame()
	{
		
		this.initialize();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate,
	 * xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {

		
		if (moveCount < 0)
			return MoveResult.DRAW;


		XiangqiPiece piece = getPieceAt(source, color);
		
		MoveResult result = ((PieceImpl) piece).validate(source, destination);
		
		if (piece.getColor() != color)
			result = MoveResult.ILLEGAL;
		
		
		if (result==MoveResult.ILLEGAL)
		{
			this.error = ERROR_ILLEGAL_MOVE;
		}	
		else
		{
			this.error = NO_ERROR;
			removePiece(source,color);
			XiangqiPiece xpiece = getPieceAt(source, color);
			placePiece(piece, destination,color );
			
			xpiece = getPieceAt(destination, color);
			
			moveCount--;
		}
		
		if (color == XiangqiColor.BLACK)
			color = XiangqiColor.RED;
		else
			color = XiangqiColor.BLACK;
		
		return result;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see xiangqi.common.XiangqiGame#getMoveMessage()
	 */
	@Override
	public String getMoveMessage() {
		// TODO Auto-generated method stub
		return error;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xiangqi.common.XiangqiGame#getPieceAt(xiangqi.common.XiangqiCoordinate,
	 * xiangqi.common.XiangqiColor)
	 */
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {

		if (aspect == XiangqiColor.BLACK)
			where = convertRedCoordToBlackAspect(this.getBoard(), where);

		return this.board.getPieceAt(where);

	}
	
	public XiangqiPiece removePiece(XiangqiCoordinate where, XiangqiColor aspect) {

		if (aspect == XiangqiColor.BLACK)
			where = convertRedCoordToBlackAspect(this.getBoard(), where);

		return this.board.removePiece(where);

	}

	public void placePiece(XiangqiPiece piece, XiangqiCoordinate where, XiangqiColor aspect) {

		if (aspect == XiangqiColor.BLACK)
			where = convertRedCoordToBlackAspect(this.getBoard(), where);

		this.board.placePiece(piece,where);

	}
	

	/**
	 * Getter for board
	 * @return board that is currently being used by game
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Used for test cases and custom preloading a board
	 */
	public void createTestBoard() {
		this.board = new Board(5, 5);
		AbsMovementValidator.setBounds(this.board);
		this.board.palaceBoundaries = this.palaceBoundaries;
	}
	/**
	 * Default initialization of board. Red Vs Black each team has a soldier, general, two chariots
	 * and two advisors
	 */
	public void initialize(Object... args) {

		this.board = new Board(5, 5);
		AbsMovementValidator.setBounds(this.board);
		this.board.palaceBoundaries = this.palaceBoundaries;
		
		this.moveCount = BETA_XQ_MOVECOUNT;
		
		/*
		 * Populate Red's pieces First
		 */
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				Coordinate.makeCoordinate(1, 3));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(2, 3));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(1, 2));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(1, 4));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 5));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 1));

		/*
		 * Populate Black's pieces next;
		 */
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				Coordinate.makeCoordinate(5, 3));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 3));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(5, 2));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(5, 4));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(5, 5));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(5, 1));

	}
	/**
	 * This is a helper function for changing aspects
	 * @param board the current board you are using (does some magic with the bounds)
	 * @param c the Coordinate transformed
	 * @return The new converted coordinate
	 */
	public static XiangqiCoordinate convertRedCoordToBlackAspect(Board board, XiangqiCoordinate c) {
		int conversionOffset = board.getBounds().clone()[0];

		conversionOffset = conversionOffset + 1 - c.getRank();
		// Rank File
		return Coordinate.makeCoordinate(conversionOffset, c.getFile());
	}

	/**
	 * Function that checks to see if a general may be in danger
	 * @param c the General's color you are checking for
	 * @return boolean, true if the general can still be taken.
	 */
	public boolean isGeneralUnderAttack(XiangqiColor c) {
		XiangqiCoordinate generalCoord = this.getBoard().getGeneralCoordinate(c);

		for (XiangqiCoordinate x : this.getBoard().getAllUsedCoords()) {
			/*
			 * Im not sure Why I am getting a 0,0 coordinate in here.
			 */
			if (!(x.equals(Coordinate.makeCoordinate(0, 0))))
			{
				XiangqiPiece potenetialAttacker = this.getBoard().getPieceAt(x);
				if (potenetialAttacker.getColor() != c)
					if (canAttack(potenetialAttacker, x, generalCoord))
						return true;
			}
		}

		return false;
	}

	/**
	 * Check to see if the game is over for a general
	 * @param c the General's color you are checking for.
	 * @return boolean, true if the general is checkmated
	 */
	public boolean isGeneralCheckmated(XiangqiColor c) {
		if (isGeneralUnderAttack(c)) {
			if (canGeneralMoveOutOfCheck(c))
				return false;

			return true;

		}

		return false;
	}

	/**
	 * Uses validators to check if a source piece can attack a destination
	 * @param p the source piece. We use this for validators
	 * @param from the attacker's position
	 * @param to the victim's position
	 * @return boolean true if the piece can take the destination
	 */
	protected boolean canAttack(XiangqiPiece p, XiangqiCoordinate from, XiangqiCoordinate to) {
		if (((PieceImpl) p).validate(from, to) == MoveResult.OK)
			return true;

		return false;
	}

	/**
	 * Check to see if the general can evade checkmate
	 * @param c the generals color
	 * @return boolean true if the general can move to a valid piece without triggering a checkmate
	 */
	protected boolean canGeneralMoveOutOfCheck(XiangqiColor c) {
		boolean canEvade = false;
		XiangqiCoordinate source = this.getBoard().getGeneralCoordinate(c);
		
		//Check spot to the left
		if (makeMove(source, Coordinate.makeCoordinate(source.getRank(), source.getFile() - 1)) == MoveResult.OK)
			if (isGeneralUnderAttack(c) == false) {
				// if we are succesfull
				moveCount++;
				return true;
			}
		
		//reset board to previous state
		getBoard().placePiece(getBoard().removePiece(source),
		Coordinate.makeCoordinate(source.getRank(), source.getFile() + 1));
		
		//Check spot to right
		if (makeMove(source, Coordinate.makeCoordinate(source.getRank(), source.getFile() + 1)) == MoveResult.OK)
			if (isGeneralUnderAttack(c) == false) {
				
				moveCount++;
				return true;
			}
		//Reset Board
		getBoard().placePiece(getBoard().removePiece(source),
		Coordinate.makeCoordinate(source.getRank(), source.getFile() - 1));

		//Reset Move Counts so that player is not victimized for us checking spots
		moveCount++;
		return false;
	}
}
/**
 * 
 */
package xiangqi.gammaxiangqi;

import common.TestCoordinate;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.gammaxiangqi.common.Board;
import xiangqi.gammaxiangqi.common.PieceImpl;
import xiangqi.gammaxiangqi.common.MovementValidators.AbsMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.AdvisorMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.SoldierMovementValidator;

/**
 * BetaXiangqi is a more indepth attempt at Xianqi, it implements different peices and movement validation. Along with testing
 * if the game is over, draw or win.
 * 
 * @author ben
 *
 */
public class BetaXiangqiGame implements XiangqiGame {

	private static final String NO_ERROR = "";
	private final int BETA_XQ_MOVECOUNT = 20;
	private final String ERROR_ILLEGAL_MOVE = "Error. The move you provided is illegal in Beta Xiangqi.";
	protected Board board;
	protected Integer[] palaceBoundaries = new Integer[4];
	private String error;
	private XiangqiColor color;
	protected int moveCount = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate,
	 * xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {

		moveCount--;
		if (moveCount < 0)
			return MoveResult.DRAW;

		if (color == XiangqiColor.BLACK) {
			destination = convertRedCoordToBlackAspect(this.getBoard(), destination);
			source = convertRedCoordToBlackAspect(this.getBoard(), source);
		}

		XiangqiPiece piece = getPieceAt(source, color);
		
		
		MoveResult result = ((PieceImpl) piece).validate(source, destination);
		
		if (result==MoveResult.ILLEGAL)
			this.error = ERROR_ILLEGAL_MOVE;
		else
			this.error = NO_ERROR;
		
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
	}
	/**
	 * Default initialization of board. Red Vs Black each team has a soldier, general, two chariots
	 * and two advisors
	 */
	public void initialize(Object... args) {

		this.board = new Board(5, 5);
		AbsMovementValidator.setBounds(this.board);
		this.moveCount = BETA_XQ_MOVECOUNT;
		
		/*
		 * Populate Red's pieces First
		 */
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				TestCoordinate.makeCoordinate(1, 3));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				TestCoordinate.makeCoordinate(2, 3));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				TestCoordinate.makeCoordinate(1, 2));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				TestCoordinate.makeCoordinate(1, 4));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(1, 5));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(1, 1));

		/*
		 * Populate Black's pieces next;
		 */
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				TestCoordinate.makeCoordinate(5, 3));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK, new SoldierMovementValidator()),
				TestCoordinate.makeCoordinate(4, 3));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK, new AdvisorMovementValidator()),
				TestCoordinate.makeCoordinate(5, 2));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK, new AdvisorMovementValidator()),
				TestCoordinate.makeCoordinate(5, 4));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(5, 5));
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(5, 1));

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
		return TestCoordinate.makeCoordinate(conversionOffset, c.getFile());
	}

	/**
	 * Function that checks to see if a general may be in danger
	 * @param c the General's color you are checking for
	 * @return boolean, true if the general can still be taken.
	 */
	public boolean isGeneralUnderAttack(XiangqiColor c) {
		XiangqiCoordinate generalCoord = this.board.getGeneralCoordinate(c);

		for (XiangqiCoordinate x : this.board.getAllUsedCoords()) {
			/*
			 * Im not sure Why I am getting a 0,0 coordinate in here.
			 */
			if (!(x.equals(TestCoordinate.makeCoordinate(0, 0))))
				if (canAttack(board.getPieceAt(x), x, generalCoord))
					return true;
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
	private boolean canAttack(XiangqiPiece p, XiangqiCoordinate from, XiangqiCoordinate to) {
		if (((PieceImpl) p).validate(from, to) == MoveResult.OK)
			return true;

		return false;
	}

	/**
	 * Check to see if the general can evade checkmate
	 * @param c the generals color
	 * @return boolean true if the general can move to a valid piece without triggering a checkmate
	 */
	private boolean canGeneralMoveOutOfCheck(XiangqiColor c) {
		boolean canEvade = false;
		XiangqiCoordinate source = this.board.getGeneralCoordinate(c);
		
		//Check spot to the left
		if (makeMove(source, TestCoordinate.makeCoordinate(source.getRank(), source.getFile() - 1)) == MoveResult.OK)
			if (isGeneralUnderAttack(c) == false) {
				// if we are succesfull
				moveCount++;
				return true;
			}
		
		//reset board to previous state
		board.placePiece(board.removePiece(source),
		TestCoordinate.makeCoordinate(source.getRank(), source.getFile() + 1));
		
		//Check spot to right
		if (makeMove(source, TestCoordinate.makeCoordinate(source.getRank(), source.getFile() + 1)) == MoveResult.OK)
			if (isGeneralUnderAttack(c) == false) {
				
				moveCount++;
				return true;
			}
		//Reset Board
		board.placePiece(board.removePiece(source),
		TestCoordinate.makeCoordinate(source.getRank(), source.getFile() - 1));

		//Reset Move Counts so that player is not victimized for us checking spots
		moveCount++;
		return false;
	}
}

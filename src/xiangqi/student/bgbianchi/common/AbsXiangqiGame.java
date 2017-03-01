/**
 * 
 */
package xiangqi.student.bgbianchi.common;

import java.util.concurrent.CompletionException;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;

/**
 * BetaXiangqi is a more indepth attempt at Xianqi, it implements different peices and movement validation. Along with testing
 * if the game is over, draw or win.
 * 
 * @author ben
 *
 */
public class AbsXiangqiGame implements XiangqiGame {

	protected static final String NO_ERROR = "";
	protected AbsBoard board;
	private Integer[] palaceBoundaries;
	private String error;
	private XiangqiColor color = XiangqiColor.RED;
	protected int moveCount = 0;
	
	

 	public AbsXiangqiGame()
	{
		moveCount=getMaximumMoveCount();
		this.initialize();
	}
	
	private int getMaximumMoveCount() {
		return 0;
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

		
		/**
		 * Check to see if we are out of moves.
		 */
		if (getMoveCount() < 0)
			return MoveResult.DRAW;

		
		 
		/**
		 * Perform move.
		 */
		XiangqiPiece piece = getPieceAt(source, color);
		
		if (piece.getColor() != color)
		{
			onPostMovementFailed();
			return MoveResult.ILLEGAL;
		}
			
		
		if (color == XiangqiColor.BLACK)
		{
			source = convertRedCoordToBlackAspect(getBoard(), source);
			destination = convertRedCoordToBlackAspect(getBoard(), destination);
		}
		MoveResult result = ((AbsPiece) piece).validate(source, destination);
		
		
		
		
		if (result==MoveResult.ILLEGAL)
		{
			onPostMovementFailed();
			return result;
		}	
		else
		{
			/**
			 * Succesful case: actually move the piece and change the turn.
			 */
			this.error = NO_ERROR;
			
			getBoard().removePiece(source);
			XiangqiPiece xpiece = getBoard().getPieceAt(source);
			getBoard().placePiece(piece, destination);
			
			
			onPostMovementSuccesful();
			
		}
	
		return result;
	}
	

	private void onPostMovementFailed() {
		this.error = getIllegalMoveError();
	}

	private void onPostMovementSuccesful() {
		
		if (color == XiangqiColor.BLACK)
			color = XiangqiColor.RED;
		else
			color = XiangqiColor.BLACK;
		
		moveCount--;
		
	}

	private int getMoveCount() {
		return this.moveCount;
	}
	
	protected void setMoveCount(int count) {
		this.moveCount = count;
	}
	

	private String getIllegalMoveError() {
		return "The move provided is illegal in "+toString();
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

		return getBoard().getPieceAt(where);

	}
	
	public XiangqiPiece removePiece(XiangqiCoordinate where, XiangqiColor aspect) {

		if (aspect == XiangqiColor.BLACK)
			where = convertRedCoordToBlackAspect(this.getBoard(), where);

		return getBoard().removePiece(where);

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
	public AbsBoard getBoard() {
		return this.board;
	}
	
	/**
	 * Setter for board
	 * @param b board that will be used by game
	 */
	public void setBoard(AbsBoard b) {
		this.board=b;
	}

	/**
	 * Default initialization of board. Red Vs Black each team has a soldier, general, two chariots
	 * and two advisors
	 */
	public void initialize(Object... args) {}
	/**
	 * This is a helper function for changing aspects
	 * @param board the current board you are using (does some magic with the bounds)
	 * @param c the Coordinate transformed
	 * @return The new converted coordinate
	 */
	public static XiangqiCoordinate convertRedCoordToBlackAspect(AbsBoard board, XiangqiCoordinate c) {
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
		if (((AbsPiece) p).validate(from, to) == MoveResult.OK)
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

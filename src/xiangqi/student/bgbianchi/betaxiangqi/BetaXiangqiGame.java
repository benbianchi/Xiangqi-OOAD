/**
 * 
 */
package xiangqi.student.bgbianchi.betaxiangqi;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.common.AbsBoard;
import xiangqi.student.bgbianchi.common.AbsMovementValidator;
import xiangqi.student.bgbianchi.common.AbsXiangqiGame;
import xiangqi.student.bgbianchi.common.impl.XQPiece;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.AdvisorMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.SoldierMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.student.bgbianchi.common.AbsPiece;


/**
 * BetaXiangqi is a more indepth attempt at Xianqi, it implements different peices and movement validation. Along with testing
 * if the game is over, draw or win.
 * 
 * @author ben
 *
 */
public class BetaXiangqiGame extends AbsXiangqiGame {

	protected static final String NO_ERROR = "";
	protected final int BETA_XQ_MOVECOUNT = 20;
	protected final String ERROR_ILLEGAL_MOVE = "Error. The move you provided is illegal in Beta Xiangqi.";
	protected AbsBoard board;
	protected Integer[] palaceBoundaries = {0,2,1,6};
	protected String error;
	protected XiangqiColor color = XiangqiColor.RED;
	protected int moveCount = 0;
	
	

	public BetaXiangqiGame()
	{
		setMoveCount(BETA_XQ_MOVECOUNT);
		this.initialize();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate,
	 * xiangqi.common.XiangqiCoordinate)
	 */
	
	public void createTestBoard()
	{
		setBoard(new  BetaBoard());
		AbsMovementValidator.setBounds(this.board);
	}
	
	public void initialize(Object... args) {

		
		setBoard(new BetaBoard());
		AbsMovementValidator.setBounds(getBoard());
		/*
		 * Populate Red's pieces First
		 */
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				Coordinate.makeCoordinate(1, 3));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(2, 3));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(1, 2));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(1, 4));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 5));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 1));

		/*
		 * Populate Black's pieces next;
		 */
		
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				Coordinate.makeCoordinate(5, 3));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 3));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(5, 2));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(5, 4));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(5, 5));
		getBoard().placePiece(
				XQPiece.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(5, 1));
	}
}
package xiangqi.studentbgbianchi.gammaxiangqi;

import java.util.concurrent.CompletionException;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.betaxiangqi.BetaXiangqiGame;
import xiangqi.student.bgbianchi.common.AbsBoard;
import xiangqi.student.bgbianchi.common.AbsXiangqiGame;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaBoard;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaPieceImpl;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.AdvisorMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.CannonMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.ElephantMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.HorseMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.SoldierMovementValidator;

public class GammaXiangqiGame extends AbsXiangqiGame{

	/*
	 * The default message for no error.
	 */
	protected static final String NO_ERROR = "";
	
	/*
	 * The Initial count of moves that decrements when makeMove is invoked
	 */
	private static final int GAMMA_XQ_MOVECOUNT = 25*2;
	/*
	 * The default message for illegal moves.
	 */
	protected final String ERROR_ILLEGAL_MOVE = "Error. The move you provided is illegal in Gamma Xiangqi.";
	/*
	 * The board used in GammaXiangqi
	 */
	protected GammaBoard board;
	/*
	 * The palace boundaries as described in the design doc.
	 */
	protected Integer[] palaceBoundaries = {0,4,3,7};
	/*
	 * The variable that is populated with error messages.
	 */
	protected String error;
	/*
	 * The default color
	 */
	protected XiangqiColor color = XiangqiColor.RED;
	/*
	 * The variable used in moveCount.
	 */
	protected int moveCount = GAMMA_XQ_MOVECOUNT;
	
	public GammaXiangqiGame()
	{
		this.initialize();
	}
	
	
	
	
	@Override
	public String getMoveMessage() {
		return this.error;
	}

	/**
	 * Used for test cases and custom preloading a board
	 */
	public void createTestBoard() {
		this.board = new GammaBoard();
		DefaultMovementValidator.setBounds(this.board);
		this.board.palaceBoundaries = this.palaceBoundaries;
	}
	/**
	 * Default initialization of board. Red Vs Black each team has a soldier, general, two chariots
	 * and two advisors
	 */
	public void initialize(Object... args) {

		this.board = new GammaBoard();
		DefaultMovementValidator.setBounds(this.board);
		this.board.palaceBoundaries = this.palaceBoundaries;
		
		this.moveCount = GAMMA_XQ_MOVECOUNT;
		/*
		 * Populate Red's pieces First
		 */
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				Coordinate.makeCoordinate(1, 5),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(1, 4),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(1, 6),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 1),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 9),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.RED, new ElephantMovementValidator()),
				Coordinate.makeCoordinate(1, 3),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.RED, new ElephantMovementValidator()),
				Coordinate.makeCoordinate(1, 7),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.RED, new HorseMovementValidator()),
				Coordinate.makeCoordinate(1, 2),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.RED, new HorseMovementValidator()),
				Coordinate.makeCoordinate(1, 8),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.RED, new CannonMovementValidator()),
				Coordinate.makeCoordinate(3, 8),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.RED, new CannonMovementValidator()),
				Coordinate.makeCoordinate(3, 2),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 1),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 3),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 5),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 7),XiangqiColor.RED);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 9),XiangqiColor.RED);
		
		/*
		 * Populate Black's pieces next;
		 */
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				Coordinate.makeCoordinate(1, 5),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(1, 4),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK, new AdvisorMovementValidator()),
				Coordinate.makeCoordinate(1, 6),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 1),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 9),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.BLACK, new ElephantMovementValidator()),
				Coordinate.makeCoordinate(1, 3),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.BLACK, new ElephantMovementValidator()),
				Coordinate.makeCoordinate(1, 7),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.BLACK, new HorseMovementValidator()),
				Coordinate.makeCoordinate(1, 2),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.BLACK, new HorseMovementValidator()),
				Coordinate.makeCoordinate(1, 8),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.BLACK, new CannonMovementValidator()),
				Coordinate.makeCoordinate(3, 8),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.BLACK, new CannonMovementValidator()),
				Coordinate.makeCoordinate(3, 2),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 1),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 3),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 5),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 7),XiangqiColor.BLACK);
		
		placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK, new SoldierMovementValidator()),
				Coordinate.makeCoordinate(4, 9),XiangqiColor.BLACK);
		
	}

	/**
	 * See BetaXiangqi.makeMove
	 * Works identically, however, the user is not penalyzed for supplying illegal moves.
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		
		/**
		 * Check to see if we are out of moves.
		 */
		if (moveCount < 0)
			return MoveResult.DRAW;


		/**
		 * Perform move.
		 */
		XiangqiPiece piece = getPieceAt(source, color);
		MoveResult result = ((GammaPieceImpl) piece).validate(source, destination);
		
		
		if (piece.getColor() != color)
			result = MoveResult.ILLEGAL;
		
		
		if (result==MoveResult.ILLEGAL)
		{
			this.error = ERROR_ILLEGAL_MOVE;
		}	
		else
		{
			/**
			 * Succesful case: actually move the piece and change the turn.
			 */
			this.error = NO_ERROR;
			removePiece(source,color);
			XiangqiPiece xpiece = getPieceAt(source, color);
			placePiece(piece, destination,color );
			
			xpiece = getPieceAt(destination, color);
			

				
			if (color == XiangqiColor.BLACK)
				{
				color = XiangqiColor.RED;
				if (isGeneralCheckmated(color))
					return MoveResult.BLACK_WINS;
					
				}
			else
				{
				color = XiangqiColor.BLACK;
				if (isGeneralCheckmated(color))
					return MoveResult.RED_WINS;
				}
			
			moveCount--;
		}
	
		return result;
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
	 * Removes the piece on the board. Uses aspect to choose coord.
	 */
	public XiangqiPiece removePiece(XiangqiCoordinate where, XiangqiColor aspect) {

		if (aspect == XiangqiColor.BLACK)
			where = convertRedCoordToBlackAspect(this.getBoard(), where);

		return this.board.removePiece(where);

	}
	/**
	 * places a piece within the board. Uses aspect to choose coord.
	 */
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
		return board;
	}

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
	 * See Function in BetaXiangqi.
	 */
	@Override
	protected boolean canAttack(XiangqiPiece p, XiangqiCoordinate from, XiangqiCoordinate to) {
		if (((GammaPieceImpl) p).validate(from, to) == MoveResult.OK)
			return true;

		return false;
	}

}

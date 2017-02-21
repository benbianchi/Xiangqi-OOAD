package xiangqi.studentbgbianchi.gammaxiangqi;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.betaxiangqi.BetaXiangqiGame;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaBoard;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaPieceImpl;
import xiangqi.studentbgbianchi.betaxiangqi.common.Board;
import xiangqi.studentbgbianchi.betaxiangqi.common.PieceImpl;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.AdvisorMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.CannonMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.ElephantMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.HorseMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.SoldierMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.exception.CompletionException;

public class GammaXiangqiGame extends BetaXiangqiGame{

	protected static final String NO_ERROR = "";
	protected final int BETA_XQ_MOVECOUNT = 20;
	protected final String ERROR_ILLEGAL_MOVE = "Error. The move you provided is illegal in Beta Xiangqi.";
	protected GammaBoard board;
	protected Integer[] palaceBoundaries = {0,4,3,7};
	protected String error;
	protected XiangqiColor color = XiangqiColor.RED;
	protected int moveCount = 0;
	
	public GammaXiangqiGame()
	{
		this.initialize();
	}
	private static final int GAMMA_XQ_MOVECOUNT = 25;
	
	
	
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

	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		if (moveCount < 0)
			return MoveResult.DRAW;

		if (!DefaultMovementValidator.isNewMovementWithinBounds(destination))
			throw new CompletionException("BAD COORDS");

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
			this.error = NO_ERROR;
			removePiece(source,color);
			XiangqiPiece xpiece = getPieceAt(source, color);
			placePiece(piece, destination,color );
			
			xpiece = getPieceAt(destination, color);
			
			if (color == XiangqiColor.BLACK)
				color = XiangqiColor.RED;
			else
				color = XiangqiColor.BLACK;
			
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
	@Override
	protected boolean canAttack(XiangqiPiece p, XiangqiCoordinate from, XiangqiCoordinate to) {
		if (((GammaPieceImpl) p).validate(from, to) == MoveResult.OK)
			return true;

		return false;
	}

}

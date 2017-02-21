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
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.CannonMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.ElephantMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.HorseMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.SoldierMovementValidator;

public class GammaXiangqiGame extends BetaXiangqiGame {

	
	private static final int GAMMA_XQ_MOVECOUNT = 0;
	
	Integer[] palaceBoundaries = {1,3,4,6};
	
	@Override
	public String getMoveMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public void initialize(Object... args) {

		
		this.board = new Board(10, 10);
		this.moveCount = GAMMA_XQ_MOVECOUNT;
		AbsMovementValidator.setBounds(this.board);

		/*
		 * Populate Red's pieces First
		 */
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				TestCoordinate.makeCoordinate(1, 5));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				TestCoordinate.makeCoordinate(1, 4));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED, new AdvisorMovementValidator()),
				TestCoordinate.makeCoordinate(1, 6));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(1, 1));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(1, 9));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.RED, new ElephantMovementValidator()),
				TestCoordinate.makeCoordinate(1, 3));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.ELEPHANT, XiangqiColor.RED, new ElephantMovementValidator()),
				TestCoordinate.makeCoordinate(1, 7));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.RED, new HorseMovementValidator()),
				TestCoordinate.makeCoordinate(1, 2));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.HORSE, XiangqiColor.RED, new HorseMovementValidator()),
				TestCoordinate.makeCoordinate(1, 8));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.RED, new CannonMovementValidator()),
				TestCoordinate.makeCoordinate(3, 8));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CANNON, XiangqiColor.RED, new CannonMovementValidator()),
				TestCoordinate.makeCoordinate(3, 2));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				TestCoordinate.makeCoordinate(4, 1));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				TestCoordinate.makeCoordinate(4, 3));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				TestCoordinate.makeCoordinate(4, 5));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				TestCoordinate.makeCoordinate(4, 7));
		
		this.board.placePiece(
				PieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED, new SoldierMovementValidator()),
				TestCoordinate.makeCoordinate(4, 9));
		
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

}

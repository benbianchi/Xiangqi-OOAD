/**
 * 
 */
package xiangqi.BetaXiangqi.endGame;

import static org.junit.Assert.*;

import org.junit.Test;

import common.TestCoordinate;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPieceType;
import xiangqi.gammaxiangqi.BetaXiangqiGame;
import xiangqi.gammaxiangqi.common.PieceImpl;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;

/**
 * @author ben
 *
 */
public class EndGameTest {

	private BetaXiangqiGame game;
	

	@Test
	public void testCheckMate() {

		game = (BetaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		game.createTestBoard();
		
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				TestCoordinate.makeCoordinate(5, 5));
		
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(4, 4));
		
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(4, 5));
		
		boolean checkmate = game.isGeneralCheckmated(XiangqiColor.BLACK);
		
		assertEquals(checkmate,true);
		/*
		 *5					G
		 
		 *4				C	C
		 
		 *3 
		 
		 *2
		
		 *1
		 * 1	2	3	4	5 
		 */
	}
	
	@Test
	public void testNotCheckMate() {

		game = (BetaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		game.createTestBoard();
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				TestCoordinate.makeCoordinate(5, 5));
		
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(1, 1));
		
		boolean checkmate = game.isGeneralCheckmated(XiangqiColor.BLACK);
		
		assertEquals(checkmate,false);

	}
	
	@Test
	public void testCanFlyingGeneral() {

		XiangqiCoordinate blackGeneralCoords = TestCoordinate.makeCoordinate(5, 3);
		XiangqiCoordinate redGeneralCoords = TestCoordinate.makeCoordinate(1, 3);
		
		game = (BetaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		game.createTestBoard();
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				blackGeneralCoords);
		
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				redGeneralCoords);
		
		MoveResult r = game.makeMove( redGeneralCoords, blackGeneralCoords );
		
		assertEquals(r,MoveResult.RED_WINS);

	}
	
	@Test
	public void testCANNOTFlyingGeneral() {

		game = (BetaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		game.createTestBoard();
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				TestCoordinate.makeCoordinate(5, 5));
		
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				TestCoordinate.makeCoordinate(5, 2));
		
		boolean checkmate = game.isGeneralCheckmated(XiangqiColor.BLACK);
		
		assertEquals(checkmate,false);

	}

}

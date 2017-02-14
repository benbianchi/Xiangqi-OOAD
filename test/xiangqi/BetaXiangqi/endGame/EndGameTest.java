/**
 * 
 */
package xiangqi.BetaXiangqi.endGame;

import static org.junit.Assert.*;

import org.junit.Test;

import common.TestCoordinate;
import xiangqi.XiangqiGameFactory;
import xiangqi.betaxiangqi.BetaXiangqiGame;
import xiangqi.betaxiangqi.common.PieceImpl;
import xiangqi.betaxiangqi.common.MovementValidators.ChariotMovementValidator;
import xiangqi.betaxiangqi.common.MovementValidators.GeneralMovementValidator;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPieceType;

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
				TestCoordinate.makeCoordinate(1, 4));
		
		game.getBoard().placePiece(
				PieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				TestCoordinate.makeCoordinate(1, 5));
		
		boolean checkmate = game.isGeneralCheckmated(XiangqiColor.BLACK);
		
		assertEquals(checkmate,true);

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
				TestCoordinate.makeCoordinate(1, 5));
		
		boolean checkmate = game.isGeneralCheckmated(XiangqiColor.BLACK);
		
		assertEquals(checkmate,false);

	}

}

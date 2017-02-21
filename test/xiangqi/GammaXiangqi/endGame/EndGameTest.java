/**
 * 
 */
package xiangqi.GammaXiangqi.endGame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.betaxiangqi.BetaXiangqiGame;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.PieceImpl;
import xiangqi.studentbgbianchi.gammaxiangqi.GammaXiangqiGame;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaPieceImpl;

/**
 * @author ben
 *
 */
public class EndGameTest {

	private GammaXiangqiGame game;
	

	@Before
	public void setup()
	{
		game = (GammaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.GAMMA_XQ);
		game.createTestBoard();
		
	}
	@Test
	public void testCheckMate() {
	
		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				Coordinate.makeCoordinate(10, 5));
		
		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 4));
		
		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 5));
		
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

		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				Coordinate.makeCoordinate(5, 5));
		
		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED, new ChariotMovementValidator()),
				Coordinate.makeCoordinate(1, 1));
		
		boolean checkmate = game.isGeneralCheckmated(XiangqiColor.BLACK);
		
		assertEquals(checkmate,false);

	}
	
	@Test
	public void testCanFlyingGeneral() {

		XiangqiCoordinate blackGeneralCoords = Coordinate.makeCoordinate(5, 3);
		XiangqiCoordinate redGeneralCoords = Coordinate.makeCoordinate(1, 3);
	
		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				blackGeneralCoords);
		
		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				redGeneralCoords);
		
		MoveResult r = game.makeMove( redGeneralCoords, blackGeneralCoords );
		
		assertEquals(r,MoveResult.RED_WINS);

	}
	
	@Test
	public void testCANNOTFlyingGeneral() {

		XiangqiCoordinate blackGeneralCoords = Coordinate.makeCoordinate(2, 2);
		XiangqiCoordinate redGeneralCoords = Coordinate.makeCoordinate(1, 1);
	
		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator()),
				blackGeneralCoords);
		
		game.getBoard().placePiece(
				GammaPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator()),
				redGeneralCoords);
		
		MoveResult r = game.makeMove( redGeneralCoords, blackGeneralCoords );
		
		assertEquals(r,MoveResult.ILLEGAL);
	}
}


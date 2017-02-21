package xiangqi.AlphaXiangqi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class AlphaXiangqiTestCases {

	private XiangqiGame game;
	
	@Before 
	public void setup()
	{
		game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.ALPHA_XQ);
	}
	
	@Test
	public void factoryProducesAlphaXiangqi() {
		assertNotNull(game);
	}
	
	@Test
	public void factoryProducesNULL() {
		assertEquals(null,XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.KAPPA_XQ));
	}

	@Test
	public void redMakesValidFirstMove()
	{
		assertEquals( MoveResult.OK, game.makeMove(Coordinate.makeCoordinate(1,1), Coordinate.makeCoordinate(1,2)));
	}
	
	@Test
	public void blackMakesValidSecondMove()
	{
		game.makeMove(Coordinate.makeCoordinate(1, 1), Coordinate.makeCoordinate(1, 2));
		assertEquals(MoveResult.RED_WINS,game.makeMove(Coordinate.makeCoordinate(1, 1),
				Coordinate.makeCoordinate(1, 2)));
	}
	
	@Test
	public void tryToMoveToInvalidLocation()
	{
		assertEquals(MoveResult.ILLEGAL, game.makeMove(Coordinate.makeCoordinate(1, 1),Coordinate.makeCoordinate(2, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
	}
	@Test
	public void getPieceAtReturnsNoneNone()
	{
		final XiangqiPiece p = game.getPieceAt(Coordinate.makeCoordinate(1, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.NONE, p.getPieceType());
		assertEquals(XiangqiColor.NONE, p.getColor());
	}
}

package xiangqi.AlphaXiangqi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.TestCoordinate;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

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
	public void redMakesValidFirstMove()
	{
		assertEquals( MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(1,2)));
	}
	
	@Test
	public void blackMakesValidSecondMove()
	{
		game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1, 2));
		assertEquals(MoveResult.RED_WINS,game.makeMove(TestCoordinate.makeCoordinate(1, 1),
				TestCoordinate.makeCoordinate(1, 2)));
	}
	
	@Test
	public void tryToMoveToInvalidLocation()
	{
		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 1),TestCoordinate.makeCoordinate(2, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
	}
	@Test
	public void getPieceAtReturnsNoneNone()
	{
		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.NONE, p.getPieceType());
		assertEquals(XiangqiColor.NONE, p.getColor());
	}
}

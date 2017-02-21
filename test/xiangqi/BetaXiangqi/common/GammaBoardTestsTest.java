package xiangqi.BetaXiangqi.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.gammaxiangqi.common.Board;

public class GammaBoardTestsTest {

	XiangqiGame gamma;
	Board board;
	
	@Before
	public void setup()
	{
		gamma = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.GAMMA_XQ);
		board = new Board(10,10);
	}
	
	@Test
	public void testBounds()
	{
		Integer[] expect = {10,10};
		
		assertArrayEquals(board.getBounds(), expect);
	}
	
	@Test
	public void testTrueWithinPalaceBoundaries()
	{
		
	}
	

}

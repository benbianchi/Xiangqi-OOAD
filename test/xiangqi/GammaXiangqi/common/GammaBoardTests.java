package xiangqi.GammaXiangqi.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.studentbgbianchi.gammaxiangqi.GammaXiangqiGame;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaBoard;

public class GammaBoardTests{

	GammaXiangqiGame gamma;
	GammaBoard board;
	
	@Before
	public void setup()
	{
		gamma = (GammaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.GAMMA_XQ);
		gamma.initialize();
		board = (GammaBoard) gamma.getBoard();
	}
	
	@Test
	public void testBounds()
	{
		Integer[] expect = {10,10};
		
		assertArrayEquals(board.getBounds(), expect);
	}
	@Test
	public void testInsidePalace()
	{
		assertTrue(board.isWithinPalace(Coordinate.makeCoordinate(1, 5)));
	}
	
	@Test
	public void testOutsidePalace()
	{
		assertFalse(board.isWithinPalace(Coordinate.makeCoordinate(2, 9)));
	}
	@Test
	public void testSameCoord()
	{
		assertEquals(Coordinate.makeCoordinate(1, 1),Coordinate.makeCoordinate(1, 1));
	}
	
	@Test
	public void testDifferentCoord()
	{
		assertNotEquals(Coordinate.makeCoordinate(1, 1),Coordinate.makeCoordinate(1, 3));
	}
	
	@Test
	public void testToString()
	{
		assertEquals(Coordinate.makeCoordinate(1, 1).toString(),"11");
	}
	
}

/**
 * 
 */
package xiangqi.BetaXiangqi.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentbgbianchi.betaxiangqi.common.Board;
import xiangqi.studentbgbianchi.betaxiangqi.common.PieceImpl;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

/**
 * @author ben
 *
 */
public class BetaBoardTests {
	
	private Board board;
	
	@Before
	public void setup()
	{
		board = new Board(5,5);
	}
	
	@Test
	public void testBounds()
	{
		Integer[] expect = {5,5};
		
		assertArrayEquals(board.getBounds(), expect);
	}
	
	@Test
	public void QueryPieceNotThereReturnsNone()
	{
		XiangqiPiece  p = board.getPieceAt(Coordinate.makeCoordinate(1, 1));
		
		assertEquals(p.getPieceType(),XiangqiPieceType.NONE);
		assertEquals(p.getColor(),XiangqiColor.NONE);
	}
	
	@Test
	public void QueryRedGeneralCoordinateReturnsRedGeneral()
	{
		XiangqiPiece general = PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator());
		Coordinate generalCoord = Coordinate.makeCoordinate(1,1);
		
		board.placePiece(general, generalCoord);
		
		XiangqiPiece  p = board.getPieceAt(generalCoord);
		
		assertEquals(p.getPieceType(),XiangqiPieceType.GENERAL);
		assertEquals(p.getColor(),XiangqiColor.RED);
	}
	
	@Test
	public void QueryBlackGeneralCoordinateReturnsBlackGeneral()
	{
		XiangqiPiece general = PieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator());
		Coordinate generalCoord = Coordinate.makeCoordinate(3,5);
		
		board.placePiece(general, generalCoord);
		
		XiangqiPiece  p = board.getPieceAt(generalCoord);
		
		assertEquals(p.getPieceType(),XiangqiPieceType.GENERAL);
		assertEquals(p.getColor(),XiangqiColor.BLACK);
	}
	
	@Test
	public void testUpdatePieceWorks()
	{
		
	}
}

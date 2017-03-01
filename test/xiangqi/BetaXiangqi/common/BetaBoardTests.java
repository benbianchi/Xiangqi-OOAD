/**
 * 
 */
package xiangqi.BetaXiangqi.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.betaxiangqi.BetaBoard;
import xiangqi.student.bgbianchi.common.impl.XQPiece;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

/**
 * @author ben
 *
 */
public class BetaBoardTests {
	
	private BetaBoard board;
	
	@Before
	public void setup()
	{
		board = new BetaBoard();
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
		XiangqiPiece general = XQPiece.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED, new GeneralMovementValidator());
		Coordinate generalCoord = Coordinate.makeCoordinate(1,1);
		
		board.placePiece(general, generalCoord);
		
		XiangqiPiece  p = board.getPieceAt(generalCoord);
		
		assertEquals(p.getPieceType(),XiangqiPieceType.GENERAL);
		assertEquals(p.getColor(),XiangqiColor.RED);
	}
	
	@Test
	public void QueryBlackGeneralCoordinateReturnsBlackGeneral()
	{
		XiangqiPiece general = XQPiece.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK, new GeneralMovementValidator());
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

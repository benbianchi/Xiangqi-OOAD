package xiangqi.BetaXiangqi.common.MovementValidators;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import common.TestCoordinate;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

public class GeneralMovementValidatorTest {

	XiangqiGame beta;
	XiangqiPiece blackPiece;
	XiangqiPiece redPiece;
	
	@Before
	public void setup() {
		
		beta  = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		
		beta.initialize();
		
		//Move Adivsors outta the way.
		

	}
	
	@Test
	public void GeneralCanMoveHorizontallyOneSpace()
	{

		
		beta.makeMove(TestCoordinate.makeCoordinate(1,2), TestCoordinate.makeCoordinate(2,1));
		beta.makeMove(TestCoordinate.makeCoordinate(1,4), TestCoordinate.makeCoordinate(2,5));
		
		XiangqiCoordinate redGeneralCoord = TestCoordinate.makeCoordinate(1,3);
		XiangqiCoordinate redGeneralNewCoord = TestCoordinate.makeCoordinate(1, 4);
		redPiece = beta.getPieceAt(redGeneralCoord, XiangqiColor.RED);
		
		XiangqiPiece query1 = beta.getPieceAt(redGeneralNewCoord, XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redGeneralCoord, redGeneralNewCoord);
		
		assertEquals(r,MoveResult.OK);
		
		
		XiangqiPiece query = beta.getPieceAt(redGeneralNewCoord, XiangqiColor.RED);

		assertEquals(query.getPieceType(),XiangqiPieceType.GENERAL);
	}

	@Test
	public void GeneralCANMoveFORWARD()
	{
		beta.makeMove(TestCoordinate.makeCoordinate(2,3), TestCoordinate.makeCoordinate(2,4));
		
		XiangqiCoordinate redGeneralCoord = TestCoordinate.makeCoordinate(1, 5);
		XiangqiCoordinate redGeneralNewCoord = TestCoordinate.makeCoordinate(1, 3);
		
		redPiece = beta.getPieceAt(TestCoordinate.makeCoordinate(1, 3), XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redGeneralCoord, redGeneralNewCoord);
		
		assertEquals(r,MoveResult.OK);
		assertEquals(true,beta.getMoveMessage().length() > 1);
		
		XiangqiPiece query = beta.getPieceAt(redGeneralCoord, XiangqiColor.RED);

		assertEquals(query.getPieceType(),XiangqiPieceType.GENERAL);
	}
	
	
	@Test
	public void GeneralCANTMoveDIAGONALLY()
	{
		
		
		XiangqiCoordinate redGeneralCoord = TestCoordinate.makeCoordinate(1, 3);
		XiangqiCoordinate redGeneralNewCoord = TestCoordinate.makeCoordinate(2, 4);
		
		redPiece = beta.getPieceAt(TestCoordinate.makeCoordinate(1, 3), XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redGeneralCoord, redGeneralNewCoord);
		
		assertEquals(r,MoveResult.ILLEGAL);
		assertEquals(true,beta.getMoveMessage().length() > 1);
		
		XiangqiPiece query = beta.getPieceAt(redGeneralCoord, XiangqiColor.RED);

		assertEquals(query.getPieceType(),XiangqiPieceType.GENERAL);
	}
}

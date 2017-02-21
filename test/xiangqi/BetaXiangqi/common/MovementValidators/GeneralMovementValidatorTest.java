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
import xiangqi.gammaxiangqi.BetaXiangqiGame;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;

public class GeneralMovementValidatorTest {

	BetaXiangqiGame beta;
	XiangqiPiece blackPiece;
	XiangqiPiece redPiece;
	
	@Before
	public void setup() {
		
		beta  = (BetaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		beta.createTestBoard();
		
		
		//Move Adivsors outta the way.
		

	}
	
	@Test
	public void GeneralCanMoveHorizontallyOneSpace()
	{

		
		GeneralMovementValidator gv = new GeneralMovementValidator();

		MoveResult m = gv.validate(TestCoordinate.makeCoordinate(1, 3), TestCoordinate.makeCoordinate(1, 4));
		
		assertEquals(m,MoveResult.OK);
	}

	@Test
	public void GeneralCANTMoveFORWARD()
	{
		GeneralMovementValidator gv = new GeneralMovementValidator();

		MoveResult m = gv.validate(TestCoordinate.makeCoordinate(2, 2), TestCoordinate.makeCoordinate(3, 2));
		
		assertEquals(m,MoveResult.ILLEGAL);
	}
	
	
	@Test
	public void GeneralCANTMoveDIAGONALLY()
	{
		
		GeneralMovementValidator gv = new GeneralMovementValidator();

		MoveResult m = gv.validate(TestCoordinate.makeCoordinate(2, 2), TestCoordinate.makeCoordinate(3, 3));
		
		assertEquals(m,MoveResult.ILLEGAL);
	}
}

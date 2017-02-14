package xiangqi.BetaXiangqi.common.MovementValidators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.TestCoordinate;
import xiangqi.XiangqiGameFactory;
import xiangqi.betaxiangqi.common.MovementValidators.ChariotMovementValidator;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

public class ChariotMovementValidatorTest {

	XiangqiGame beta;
	XiangqiPiece blackPiece;
	XiangqiPiece redPiece;
	ChariotMovementValidator v;
	
	@Before
	public void setup() {
		
		beta  = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		
		beta.initialize();
		
		v =new ChariotMovementValidator();
		//Move Adivsors outta the way.
		

	}
	
	@Test
	public void HorizontallyCanMoveHorizontally()
	{

		
		boolean shouldBeTrue = v.validate(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1, 2));
		assertTrue(shouldBeTrue);
		
	}
	
	@Test
	public void HorizontallyCanMoveVertically()
	{

		
		boolean shouldBeTrue = v.validate(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1));
		assertTrue(shouldBeTrue);
		
	}
	
	
	@Test
	public void ChariotCANTMoveDIAGONALLY()
	{
		
		
		boolean shouldBeFalse = v.validate(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 3));
		assertEquals(shouldBeFalse,false);
	}
}

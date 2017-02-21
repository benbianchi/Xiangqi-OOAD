package xiangqi.BetaXiangqi.common.MovementValidators;

import static org.junit.Assert.*;

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
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;

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

		
		MoveResult shouldBeOK = v.validate(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1, 2));
		assertEquals(shouldBeOK,MoveResult.OK);
		
	}
	
	@Test
	public void HorizontallyCanMoveVertically()
	{

		
		MoveResult shouldBeOK = v.validate(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(2, 1));
		assertEquals(shouldBeOK,MoveResult.OK);
		
	}
	
	
	@Test
	public void ChariotCANTMoveDIAGONALLY()
	{
		
		
		MoveResult shouldBeIllegal = v.validate(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(3, 3));
		assertEquals(shouldBeIllegal ,MoveResult.ILLEGAL);
	}
}

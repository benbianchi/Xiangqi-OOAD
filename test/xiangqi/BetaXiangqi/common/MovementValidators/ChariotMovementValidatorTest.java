package xiangqi.BetaXiangqi.common.MovementValidators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiGameVersion;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

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

		
		MoveResult shouldBeOK = v.validate(Coordinate.makeCoordinate(3, 3), Coordinate.makeCoordinate(3, 2));
		assertEquals(shouldBeOK,MoveResult.OK);
		
	}
	
	@Test
	public void HorizontallyCanMoveVertically()
	{

		
		MoveResult shouldBeOK = v.validate(Coordinate.makeCoordinate(1, 1), Coordinate.makeCoordinate(2, 1));
		assertEquals(shouldBeOK,MoveResult.OK);
		
	}
	
	
	@Test
	public void ChariotCANTMoveDIAGONALLY()
	{
		
		
		MoveResult shouldBeIllegal = v.validate(Coordinate.makeCoordinate(1, 1), Coordinate.makeCoordinate(3, 3));
		assertEquals(shouldBeIllegal ,MoveResult.ILLEGAL);
	}
}

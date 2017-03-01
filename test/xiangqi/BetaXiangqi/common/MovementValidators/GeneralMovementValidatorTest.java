package xiangqi.BetaXiangqi.common.MovementValidators;

import static org.junit.Assert.assertEquals;

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
import xiangqi.student.bgbianchi.betaxiangqi.BetaBoard;
import xiangqi.student.bgbianchi.betaxiangqi.BetaXiangqiGame;
import xiangqi.student.bgbianchi.common.AbsMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.ChariotMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.GeneralMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class GeneralMovementValidatorTest {

	BetaXiangqiGame beta;
	XiangqiPiece blackPiece;
	XiangqiPiece redPiece;
	
	@Before
	public void setup() {
		
		beta  = (BetaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		
		
		AbsMovementValidator.setBounds(new BetaBoard());
		//Move Adivsors outta the way.
		

	}
	
	@Test
	public void GeneralCanMoveHorizontallyOneSpace()
	{

		
		GeneralMovementValidator gv = new GeneralMovementValidator();

		MoveResult m = gv.validate(Coordinate.makeCoordinate(1, 3), Coordinate.makeCoordinate(1, 4));
		
		assertEquals(m,MoveResult.OK);
	}

	@Test
	public void GeneralCANTMoveFORWARD()
	{
		GeneralMovementValidator gv = new GeneralMovementValidator();

		MoveResult m = gv.validate(Coordinate.makeCoordinate(2, 2), Coordinate.makeCoordinate(3, 2));
		
		assertEquals(m,MoveResult.ILLEGAL);
	}
	
	
	@Test
	public void GeneralCANTMoveDIAGONALLY()
	{
		
		GeneralMovementValidator gv = new GeneralMovementValidator();

		MoveResult m = gv.validate(Coordinate.makeCoordinate(2, 2), Coordinate.makeCoordinate(3, 3));
		
		assertEquals(m,MoveResult.ILLEGAL);
	}
	
	@Test
	public void GeneralCANTStay()
	{
		
		GeneralMovementValidator gv = new GeneralMovementValidator();

		MoveResult m = gv.validate(Coordinate.makeCoordinate(2, 2), Coordinate.makeCoordinate(2, 2));
		
		assertEquals(m,MoveResult.ILLEGAL);
	}
	

	
	
}

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
import xiangqi.gammaxiangqi.common.PieceImpl;
import xiangqi.gammaxiangqi.common.MovementValidatorsImpl.SoldierMovementValidator;

public class SoldierMovementValidatorTest {

	XiangqiGame beta;
	XiangqiPiece blackPiece;
	XiangqiPiece redPiece;
	
	@Before
	public void setup() {
		
		beta  = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		
		beta.initialize();
		

	}
	
	@Test
	public void SoldierCanMoveForward()
	{
		XiangqiCoordinate redSoldierCoord = TestCoordinate.makeCoordinate(2, 3);
		XiangqiCoordinate redSoldierNewCoord = TestCoordinate.makeCoordinate(3, 3);
		redPiece = beta.getPieceAt(TestCoordinate.makeCoordinate(2, 3), XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redSoldierCoord, redSoldierNewCoord);
		
		assertEquals(r,MoveResult.OK);
		
		XiangqiPiece query = beta.getPieceAt(redSoldierNewCoord, XiangqiColor.RED);

		assertEquals(query.getPieceType(),XiangqiPieceType.SOLDIER);
	}

	@Test
	public void SoldierCANTMoveBACKWARD()
	{
		XiangqiCoordinate redSoldierCoord = TestCoordinate.makeCoordinate(3, 3);
		XiangqiCoordinate redSoldierForwardCoord = TestCoordinate.makeCoordinate(4,3);
		redPiece = beta.getPieceAt(TestCoordinate.makeCoordinate(2, 3), XiangqiColor.RED);
		
		beta.makeMove(redSoldierCoord, redSoldierForwardCoord);
		
		MoveResult r = beta.makeMove(redSoldierForwardCoord, redSoldierCoord);
		assertEquals(r,MoveResult.ILLEGAL);
		assertEquals(true,beta.getMoveMessage().length() > 1);
		
	}
	
	@Test
	public void SoldierCANTMoveSideways()
	{
		XiangqiCoordinate redSoldierCoord = TestCoordinate.makeCoordinate(2, 3);
		XiangqiCoordinate redSoldierForwardCoord = TestCoordinate.makeCoordinate(2,4);
		redPiece = beta.getPieceAt(TestCoordinate.makeCoordinate(2, 3), XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redSoldierCoord, redSoldierForwardCoord);
		
		assertEquals(r,MoveResult.ILLEGAL);
		assertEquals(true,beta.getMoveMessage().length() > 1);
	}
}

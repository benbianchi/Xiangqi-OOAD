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
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

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
		XiangqiCoordinate redSoldierCoord = Coordinate.makeCoordinate(2, 3);
		XiangqiCoordinate redSoldierNewCoord = Coordinate.makeCoordinate(3, 3);
		redPiece = beta.getPieceAt(Coordinate.makeCoordinate(2, 3), XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redSoldierCoord, redSoldierNewCoord);
		
		assertEquals(r,MoveResult.OK);
		
		XiangqiPiece query = beta.getPieceAt(redSoldierNewCoord, XiangqiColor.RED);

		assertEquals(query.getPieceType(),XiangqiPieceType.SOLDIER);
	}

	@Test
	public void SoldierCANTMoveBACKWARD()
	{
		XiangqiCoordinate redSoldierCoord = Coordinate.makeCoordinate(3, 3);
		XiangqiCoordinate redSoldierForwardCoord = Coordinate.makeCoordinate(4,3);
		redPiece = beta.getPieceAt(Coordinate.makeCoordinate(2, 3), XiangqiColor.RED);
		
		beta.makeMove(redSoldierCoord, redSoldierForwardCoord);
		beta.makeMove(redSoldierCoord, redSoldierForwardCoord);
		
		assertEquals(MoveResult.ILLEGAL,	beta.makeMove(redSoldierForwardCoord, redSoldierForwardCoord)); //Should be illegal
		
		MoveResult r = beta.makeMove(redSoldierForwardCoord, redSoldierForwardCoord); //This actually moves the black soldier back!
		assertEquals(r,MoveResult.ILLEGAL);
		assertEquals(true,beta.getMoveMessage().length() > 1);
		
	}
	
	@Test
	public void SoldierCANTMoveSideways()
	{
		XiangqiCoordinate redSoldierCoord = Coordinate.makeCoordinate(2, 3);
		XiangqiCoordinate redSoldierForwardCoord = Coordinate.makeCoordinate(2,4);
		redPiece = beta.getPieceAt(Coordinate.makeCoordinate(2, 3), XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redSoldierCoord, redSoldierForwardCoord);
		
		assertEquals(r,MoveResult.ILLEGAL);
		assertEquals(true,beta.getMoveMessage().length() > 1);
	}
}

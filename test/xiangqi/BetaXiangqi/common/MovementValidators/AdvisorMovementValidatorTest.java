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
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class AdvisorMovementValidatorTest {

	XiangqiGame beta;
	XiangqiPiece blackPiece;
	XiangqiPiece redPiece;
	
	@Before
	public void setup() {
		
		beta  = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		
		beta.initialize();
		

	}
	
	@Test
	public void AdvisorCanMoveDiagonally()
	{
		XiangqiCoordinate redAdvisorCoord = Coordinate.makeCoordinate(1, 4);
		XiangqiCoordinate redAdvisorNewCoord = Coordinate.makeCoordinate(2, 5);
		redPiece = beta.getPieceAt(Coordinate.makeCoordinate(1, 4), XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redAdvisorCoord, redAdvisorNewCoord);
		
		assertEquals(r,MoveResult.OK);
		
		XiangqiPiece query = beta.getPieceAt(redAdvisorNewCoord, XiangqiColor.RED);

		assertEquals(query.getPieceType(),XiangqiPieceType.ADVISOR);
	}

	@Test
	public void AdvisorCANTMoveFORWARD()
	{
		XiangqiCoordinate redSoldierCoord = Coordinate.makeCoordinate(1, 4);
		XiangqiCoordinate redSoldierNewCoord = Coordinate.makeCoordinate(2, 4);
		redPiece = beta.getPieceAt(Coordinate.makeCoordinate(1, 4), XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redSoldierCoord, redSoldierNewCoord);
		
		assertEquals(r,MoveResult.ILLEGAL);
		assertEquals(true,beta.getMoveMessage().length() > 1);
		
		XiangqiPiece query = beta.getPieceAt(redSoldierCoord, XiangqiColor.RED);

		assertEquals(query.getPieceType(),XiangqiPieceType.ADVISOR);
	}
	
	
	@Test
	public void AdvisorCANTMoveSideways()
	{
		XiangqiCoordinate redAdvisorCoord = Coordinate.makeCoordinate(1, 4);
		XiangqiCoordinate redAdvisorForwardCoord = Coordinate.makeCoordinate(1,5);
		redPiece = beta.getPieceAt(redAdvisorCoord , XiangqiColor.RED);
		
		MoveResult r = beta.makeMove(redAdvisorCoord, redAdvisorForwardCoord);
		
		assertEquals(r,MoveResult.ILLEGAL);
		assertEquals(true,beta.getMoveMessage().length() > 1);
	}
}

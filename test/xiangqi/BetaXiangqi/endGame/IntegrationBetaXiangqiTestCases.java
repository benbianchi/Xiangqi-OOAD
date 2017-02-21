package xiangqi.BetaXiangqi.endGame;

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
import xiangqi.student.bgbianchi.betaxiangqi.BetaXiangqiGame;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class IntegrationBetaXiangqiTestCases {

	private BetaXiangqiGame game;
	
	@Before 
	public void setup()
	{
		game = (BetaXiangqiGame) XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
		game.initialize();
	}
	
	@Test
	public void factoryProducesBetaXiangqi() {
		assertNotNull(game);
	}
	
	@Test
	public void ensureFiveByFiveBoard(){
		Integer[] fiveByFive = {5,5};
		assertArrayEquals(game.getBoard().getBounds(), fiveByFive);
	}
	
	@Test
	public void ensureGeneralIsFiveThreeFromRed()
	{
		XiangqiCoordinate q = Coordinate.makeCoordinate(5, 3);
		
		q = BetaXiangqiGame.convertRedCoordToBlackAspect(game.getBoard(), q);
		
		XiangqiCoordinate g = Coordinate.makeCoordinate(1, 3);
		
		assertEquals(q,g);
	}
	@Test
	public void ensureRedPiecesOnBoard(){
		
		XiangqiCoordinate generalCoord = Coordinate.makeCoordinate(1,3);
		XiangqiCoordinate soldierCoord = Coordinate.makeCoordinate(2,3);
		XiangqiCoordinate leftAdvisorCoord = Coordinate.makeCoordinate(1,2);
		XiangqiCoordinate rightAdvisorCoord = Coordinate.makeCoordinate(1,4);
		XiangqiCoordinate leftChariotCoord = Coordinate.makeCoordinate(1,1);
		XiangqiCoordinate rightChariotCoord = Coordinate.makeCoordinate(1,5);
		
		XiangqiPiece general = game.getPieceAt(generalCoord, XiangqiColor.RED);
		XiangqiPiece soldier = game.getPieceAt(soldierCoord,XiangqiColor.RED);
		XiangqiPiece leftAdvisor = game.getPieceAt(leftAdvisorCoord,XiangqiColor.RED);
		XiangqiPiece rightAdvisor = game.getPieceAt(rightAdvisorCoord,XiangqiColor.RED);
		XiangqiPiece rightChariot = game.getPieceAt(rightChariotCoord,XiangqiColor.RED);
		XiangqiPiece leftChariot= game.getPieceAt(leftChariotCoord,XiangqiColor.RED);
		
		assertEquals(general.getPieceType(), XiangqiPieceType.GENERAL);
		assertEquals(soldier.getPieceType(), XiangqiPieceType.SOLDIER);
		assertEquals(leftAdvisor.getPieceType(), XiangqiPieceType.ADVISOR);
		assertEquals(rightAdvisor.getPieceType(), XiangqiPieceType.ADVISOR);
		assertEquals(leftChariot.getPieceType(), XiangqiPieceType.CHARIOT);
		assertEquals(rightChariot.getPieceType(), XiangqiPieceType.CHARIOT);
		
		assertEquals(general.getColor(), XiangqiColor.RED);
		assertEquals(soldier.getColor(), XiangqiColor.RED);
		assertEquals(leftAdvisor.getColor(), XiangqiColor.RED);
		assertEquals(rightAdvisor.getColor(), XiangqiColor.RED);
		assertEquals(rightChariot.getColor(), XiangqiColor.RED);
		assertEquals(leftChariot.getColor(), XiangqiColor.RED);
		
	}
	@Test
	public void ensureBlackPiecesOnBoard(){
		
		XiangqiCoordinate generalCoord = Coordinate.makeCoordinate(1,3);
		XiangqiCoordinate soldierCoord = Coordinate.makeCoordinate(2,3);
		XiangqiCoordinate leftAdvisorCoord = Coordinate.makeCoordinate(1,2);
		XiangqiCoordinate rightAdvisorCoord = Coordinate.makeCoordinate(1,4);
		XiangqiCoordinate leftChariotCoord = Coordinate.makeCoordinate(1,1);
		XiangqiCoordinate rightChariotCoord = Coordinate.makeCoordinate(1,5);
		
		XiangqiPiece general = game.getPieceAt(generalCoord, XiangqiColor.BLACK);
		XiangqiPiece soldier = game.getPieceAt(soldierCoord, XiangqiColor.BLACK);
		XiangqiPiece leftAdvisor = game.getPieceAt(leftAdvisorCoord, XiangqiColor.BLACK);
		XiangqiPiece rightAdvisor = game.getPieceAt(rightAdvisorCoord, XiangqiColor.BLACK);
		XiangqiPiece rightChariot = game.getPieceAt(rightChariotCoord, XiangqiColor.BLACK);
		XiangqiPiece leftChariot= game.getPieceAt(leftChariotCoord, XiangqiColor.BLACK);
		
		assertEquals(general.getPieceType(), XiangqiPieceType.GENERAL);
		assertEquals(soldier.getPieceType(), XiangqiPieceType.SOLDIER);
		assertEquals(leftAdvisor.getPieceType(), XiangqiPieceType.ADVISOR);
		assertEquals(rightAdvisor.getPieceType(), XiangqiPieceType.ADVISOR);
		assertEquals(leftChariot.getPieceType(), XiangqiPieceType.CHARIOT);
		assertEquals(rightChariot.getPieceType(), XiangqiPieceType.CHARIOT);
		
		assertEquals(general.getColor(), XiangqiColor.BLACK);
		assertEquals(soldier.getColor(), XiangqiColor.BLACK);
		assertEquals(leftAdvisor.getColor(), XiangqiColor.BLACK);
		assertEquals(rightAdvisor.getColor(), XiangqiColor.BLACK);
		assertEquals(rightChariot.getColor(), XiangqiColor.BLACK);
		assertEquals(leftChariot.getColor(), XiangqiColor.BLACK);
		
	}
	
	@Test
	public void isBlockedVertically(){
		
		XiangqiCoordinate redGeneralCoord = Coordinate.makeCoordinate(1, 3);
		XiangqiCoordinate blackGeneralRedAspect= Coordinate.makeCoordinate(2, 3);
		
		boolean expectBlocked = game.getBoard().isBlocked(redGeneralCoord, blackGeneralRedAspect);
		
		
		assertEquals(expectBlocked,true);
		
	}
	
	
	@Test
	public void isBlockedHorizontally(){
		
		XiangqiCoordinate redGeneralCoord = Coordinate.makeCoordinate(1, 3);
		XiangqiCoordinate redChariotRight= Coordinate.makeCoordinate(1, 5);
		
		boolean expectBlocked = game.getBoard().isBlocked(redGeneralCoord, redChariotRight);
		
		
		assertEquals(expectBlocked,true);
		
	}
	
	@Test
	public void isBlockedDiagonally(){
		
		XiangqiCoordinate redLeftAdvisor= Coordinate.makeCoordinate(1, 2);
		XiangqiCoordinate dest= Coordinate.makeCoordinate(3,4);
		
		boolean expectBlocked = game.getBoard().isBlocked(redLeftAdvisor, dest);
		
		
		assertEquals(expectBlocked,true);
		
	}
	
	@Test
	public void isNOTBlockedVertically(){
		
		XiangqiCoordinate redGeneralCoord = Coordinate.makeCoordinate(2, 3);
		XiangqiCoordinate blackGeneralRedAspect= Coordinate.makeCoordinate(3, 3);
		
		boolean expectBlocked = game.getBoard().isBlocked(redGeneralCoord, blackGeneralRedAspect);
		
		
		assertEquals(expectBlocked,false);
		
	}
	
	
	@Test
	public void isNOTBlockedHorizontally(){
		
		XiangqiCoordinate redGeneralCoord = Coordinate.makeCoordinate(2, 3);
		XiangqiCoordinate redChariotRight= Coordinate.makeCoordinate(2, 4);
		
		boolean expectBlocked = game.getBoard().isBlocked(redGeneralCoord, redChariotRight);
		
		
		assertEquals(expectBlocked,false);
		
	}
	
	@Test
	public void isNOTBlockedDiagonally(){
		
		XiangqiCoordinate redLeftAdvisor= Coordinate.makeCoordinate(1, 1);
		XiangqiCoordinate dest= Coordinate.makeCoordinate(2, 2);
		
		boolean expectBlocked = game.getBoard().isBlocked(redLeftAdvisor, dest);
		
		
		assertEquals(expectBlocked,false);
		
	}
	

//
//	@Test
//	public void redMakesValidFirstMove()
//	{
//		assertEquals( MoveResult.OK, game.makeMove(TestCoordinate.makeCoordinate(1,1), TestCoordinate.makeCoordinate(1,2)));
//	}
//	
//	@Test
//	public void blackMakesValidSecondMove()
//	{
//		game.makeMove(TestCoordinate.makeCoordinate(1, 1), TestCoordinate.makeCoordinate(1, 2));
//		assertEquals(MoveResult.RED_WINS,game.makeMove(TestCoordinate.makeCoordinate(1, 1),
//				TestCoordinate.makeCoordinate(1, 2)));
//	}
//	
//	@Test
//	public void tryToMoveToInvalidLocation()
//	{
//		assertEquals(MoveResult.ILLEGAL, game.makeMove(TestCoordinate.makeCoordinate(1, 1),TestCoordinate.makeCoordinate(2, 1)));
//		assertTrue(game.getMoveMessage().length() >= 1);
//		
//	}
//	@Test
//	public void getPieceAtReturnsNoneNone()
//	{
//		final XiangqiPiece p = game.getPieceAt(TestCoordinate.makeCoordinate(1, 1), XiangqiColor.RED);
//		assertEquals(XiangqiPieceType.NONE, p.getPieceType());
//		assertEquals(XiangqiColor.NONE, p.getColor());
//	}
}

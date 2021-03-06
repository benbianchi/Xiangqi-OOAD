/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright �2016-2017 Gary F. Pollice
 *******************************************************************************/

package xiangqi.GammaXiangqi.endGame;

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
import xiangqi.studentbgbianchi.gammaxiangqi.GammaXiangqiGame;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaBoard;

import static xiangqi.common.MoveResult.*;
import static xiangqi.common.XiangqiColor.*;
import static xiangqi.common.XiangqiPieceType.*;

import java.util.concurrent.CompletionException;
/**
 * Self-contained master tests
 * @version Feb 14, 2017
 */
public class PolliceMimicTest
{
	//
	// Test case code begins here
	//
	private XiangqiGame game;
	
	private static XiangqiCoordinate c11 = makeCoordinate(1, 1),
			c12 = makeCoordinate(1, 2), c13 = makeCoordinate(1, 3),
			c14 = makeCoordinate(1, 4), c15 = makeCoordinate(1, 5),
			c21 = makeCoordinate(2, 1), c22 = makeCoordinate(2, 2),
			c23 = makeCoordinate(2, 3), c24 = makeCoordinate(2, 4),
			c25 = makeCoordinate(2, 5), c31 = makeCoordinate(3, 1),
			c32 = makeCoordinate(3, 2), c33 = makeCoordinate(3, 3),
			c34 = makeCoordinate(3, 4), c35 = makeCoordinate(3, 5),
			c41 = makeCoordinate(4, 1), c42 = makeCoordinate(4, 2),
			c43 = makeCoordinate(4, 3), c44 = makeCoordinate(4, 4),
			c45 = makeCoordinate(4, 5), c51 = makeCoordinate(5, 1),
			c52 = makeCoordinate(5, 2), c53 = makeCoordinate(5, 3),
			c54 = makeCoordinate(5, 4), c55 = makeCoordinate(5, 5);

	private XiangqiCoordinate c(int r, int f) {return makeCoordinate(r,f);}
	
	private static XiangqiPiece noPiece = 
			makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE), 
			redChariot = makePiece(CHARIOT, RED),
			redAdvisor = makePiece(ADVISOR, RED),
			redGeneral = makePiece(GENERAL, RED),
			redSoldier = makePiece(SOLDIER, RED),
			redElephant = makePiece(ELEPHANT, RED),
			blackChariot = makePiece(CHARIOT, BLACK),
			blackAdvisor = makePiece(ADVISOR, BLACK),
			blackGeneral = makePiece(GENERAL, BLACK),
			blackSoldier = makePiece(SOLDIER, BLACK),
			blackElephant = makePiece(ELEPHANT, BLACK);
	
	@Before
	public void setup()
	{
		game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.GAMMA_XQ);
	}
	
	@Test
	public void correctInitialPositions()
	{
				
		assertEquals(redGeneral, game.getPieceAt(c(1,5), RED));
		assertEquals(redChariot, game.getPieceAt(c(1,1), RED));
		assertEquals(redChariot, game.getPieceAt(c(1,9), RED));
		assertEquals(redAdvisor, game.getPieceAt(c(1,4), RED));
		assertEquals(redAdvisor, game.getPieceAt(c(1,6), RED));
		
		assertEquals(redElephant, game.getPieceAt(c(1,3), RED));
		assertEquals(redElephant, game.getPieceAt(c(1,7), RED));
		
		assertEquals(redSoldier, game.getPieceAt(c(4,1), RED));
		
		assertEquals(redSoldier, game.getPieceAt(c(4,3), RED));
		assertEquals(redSoldier, game.getPieceAt(c(4,5), RED));
		assertEquals(redSoldier, game.getPieceAt(c(4,7), RED));
		assertEquals(redSoldier, game.getPieceAt(c(4,9), RED));
		
		assertEquals(blackGeneral, game.getPieceAt(c(1,5), BLACK));
		assertEquals(blackChariot, game.getPieceAt(c(1,1), BLACK));
		assertEquals(blackChariot, game.getPieceAt(c(1,9), BLACK));
		assertEquals(blackAdvisor, game.getPieceAt(c(1,4), BLACK));
		assertEquals(blackAdvisor, game.getPieceAt(c(1,6), BLACK));
		
		assertEquals(blackElephant, game.getPieceAt(c(1,3), BLACK));
		assertEquals(blackElephant, game.getPieceAt(c(1,7), BLACK));
		
		assertEquals(blackSoldier, game.getPieceAt(c(4,1), BLACK));
		
		assertEquals(blackSoldier, game.getPieceAt(c(4,3), BLACK));
		assertEquals(blackSoldier, game.getPieceAt(c(4,5), BLACK));
		assertEquals(blackSoldier, game.getPieceAt(c(4,7), BLACK));
		assertEquals(blackSoldier, game.getPieceAt(c(4,9), BLACK));
	}

	@Test
	public void queryAnEmptyLocation()
	{
		assertEquals(noPiece, game.getPieceAt(c22, BLACK));
	}
	
	@Test
	public void makeMoveWithInvalidCoordinates()
	{
		assertEquals(ILLEGAL, game.makeMove(makeCoordinate(0, 3), c14));
	}
	
	@Test
	public void makeValidChariotMove()
	{
		assertEquals(OK, game.makeMove(c11, c21));
		assertEquals(redChariot, game.getPieceAt(c21, RED));
		assertEquals(noPiece, game.getPieceAt(c11, RED));
	}
	
	@Test
	public void makeMakeBadMoveButStillHaveControlThenMakeValidMove()
	{

		assertEquals(ILLEGAL, game.makeMove(c11, c11));
		assertEquals(OK, game.makeMove(c11, c21));
		assertEquals(redChariot, game.getPieceAt(c21, RED));
		assertEquals(noPiece, game.getPieceAt(c11, RED));
	}
	
	
	
	@Test
	public void attemptToMoveOpponentPiece()
	{
		assertEquals(ILLEGAL, game.makeMove(c51, c41));
	}
	
	@Test
	public void attemptToCaptureOwnPiece()
	{
		assertEquals(ILLEGAL, game.makeMove(c11, c12));
	}
	
	@Test
	public void ensureMessageOnIllegalMove()
	{
		game.makeMove(c(1,1), c(1,1));
		assertTrue(game.getMoveMessage().length() > 5);		// Minimum of 6 characters seems reasonable
	}
	
	@Test
	public void ensureRuntimeOnIllegalCoords()
	{
		try{
		game.makeMove(c(0,0), c(0,0));
		}
		catch (CompletionException e)
		{
		assertTrue(true);		// Minimum of 6 characters seems reasonable
		}
	}
	
	
	@Test
	public void tryToMoveChariotDiagonally()
	{
		assertEquals(ILLEGAL, game.makeMove(c(1,1), c(2,2)));
	}
		
	@Test
	public void validAdvisorMove()
	{
		assertEquals(OK, game.makeMove(c(1,4), c(2,5)));
		assertEquals(redAdvisor, game.getPieceAt(c(2,5), RED));
	}
	
	@Test
	public void invalidAdvisorMove()
	{
		assertEquals(ILLEGAL, game.makeMove(c14, c15));
	}
	
	@Test
	public void validSoldierMove()
	{
		assertEquals(OK, game.makeMove(c(4,3), c(5,3)));
		assertEquals(redSoldier, game.getPieceAt(c(5,3), RED));
	}
	
	@Test
	public void invalidSoldierMove()
	{
		assertEquals(ILLEGAL, game.makeMove(c(4,3), c(4,4)));
	}
	
	@Test
	public void soldierCanMoveAcrossRiverAndMoveHorizontally()
	{
		game.makeMove(c(4,3),c(5,3));//red
		game.makeMove(c(4,5),c(5,5));//black
		game.makeMove(c(5,3),c(6,3));//red
		game.makeMove(c(5,5),c(6,5));//black
		
		game.makeMove(c(6,3),c(6,4));//red
		game.makeMove(c(6,5),c(6,4));//Black
		assertEquals(blackSoldier, game.getPieceAt(c(6,4), BLACK));
	}
	
	@Test
	public void validGeneralMove()
	{
		game.makeMove(c(1,4), c(2,5));
		game.makeMove(c11, c21);
		assertEquals(OK, game.makeMove(c(1,5), c(1,4)));
		assertEquals(redGeneral, game.getPieceAt(c(1,4), RED));
	}
	
	@Test
	public void invalidGeneralMove()
	{
		assertEquals(ILLEGAL, game.makeMove(c15, c22));
	}
	@Test
	public void invalidGeneralMoveDiagonally()
	{
		assertEquals(ILLEGAL, game.makeMove(c15, c24));
	}
	
	@Test
	public void validElephantMove()
	{
		game.makeMove(c(1,3),c(3,4));
		
		assertEquals(OK,game.makeMove(c(1,3),c(3,1)));
	}
	
	@Test
	public void INVALIDElephantMove()
	{
		game.makeMove(c(1,3),c(2,3));
		
		assertEquals(ILLEGAL,game.makeMove(c(1,3),c(3,7)));
	}
	
	@Test
	public void ElephantCantCrossRiverMove()
	{
		game.makeMove(c(1,3),c(3,5));
		
		game.makeMove(c(1,3),c(3,1));
		
		game.makeMove(c(3,5),c(5,3));
		
		game.makeMove(c(3,4),c(5,2));
		
		assertEquals(ILLEGAL,game.makeMove(c(5,3),c(7,5)));
		
	}
	
	// Helper methods
	private static XiangqiCoordinate makeCoordinate(int rank, int file)
	{
		return new TestCoordinate(rank, file);
	}

	public static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color)
	{
		return new TestPiece(pieceType, color);
	}
}

class TestCoordinate implements XiangqiCoordinate
{
	private final int rank;
	private final int file;
	
	public TestCoordinate(int rank, int file)
	{
		this.rank = rank;
		this.file = file;
	}
	
	@Override
	public int getRank()
	{
		return rank;
	}

	@Override
	public int getFile()
	{
		return file;
	}
	@Override
	public boolean equals(Object obj)
	{
		XiangqiCoordinate other = (XiangqiCoordinate) obj;
		
		if (other.getFile() == this.file && other.getRank() == this.rank)
			return true;
			
			return false;
	}
}

class TestPiece implements XiangqiPiece
{
	private final XiangqiColor color;
	private final XiangqiPieceType pieceType;
	
	public TestPiece(XiangqiPieceType pieceType, XiangqiColor color)
	{
		this.pieceType = pieceType;
		this.color = color;
	}
	
	/*
	 * @see xiangqi.common.XiangqiPiece#getColor()
	 */
	@Override
	public XiangqiColor getColor()
	{
		return color;
	}

	/*
	 * @see xiangqi.common.XiangqiPiece#getPieceType()
	 */
	@Override
	public XiangqiPieceType getPieceType()
	{
		return pieceType;
	}

	@Override
	public boolean equals(Object obj)
	{
		XiangqiPiece other = (XiangqiPiece) obj;
		if (color != other.getColor())
			return false;
		if (pieceType != other.getPieceType())
			return false;
		return true;
	}
}
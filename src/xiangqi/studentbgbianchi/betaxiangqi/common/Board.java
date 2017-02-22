package xiangqi.studentbgbianchi.betaxiangqi.common;

import java.util.HashMap;
import java.util.Set;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.betaxiangqi.BetaXiangqiGame;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.NoneMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

/**
 * The board is a container for the state of the game. It implements a hashmap to keep track of what is where.
 * It also implements bounds.
 * 
 * @author ben
 *
 */
public class Board {

	/**
	 * The data structure housing the state of the game
	 */
	protected HashMap<XiangqiCoordinate, XiangqiPiece> mapCoordToPiece;
	
	/**
	 * The boundaries for the board
	 */
	private Integer[] bounds = new Integer[2];
	
	/**
	 * The integer array that is used to store the palace. It works as {MinRank,MaxRank,MinFile,MaxFile}
	 */
	public Integer[] palaceBoundaries = new Integer[4];

	/**
	 * Create a board
	 * @param maxRow the Maximum Row Count
	 * @param maxCol the Maximum Column Count
	 */
	public Board(int maxRow, int maxCol) {
		mapCoordToPiece = new HashMap<>();

		this.bounds[0] = maxRow;
		this.bounds[1] = maxCol;
	}

	/**
	 * Function to return the boundaries of the board
	 * @return the boundaries in the form of a two dimensional array
	 */
	public Integer[] getBounds() {
		return bounds;
	}
	
	/**
	 * A function that sees if a coordinate is within the palace. The Game will usually define what is the 
	 * palace.
	 * @param coord the coordinate we are testing whether it is within the palace
	 * @return a boolean denoting whether or not the coordinate is within the palace
	 */
	public boolean isWithinPalace(XiangqiCoordinate coord)
	{
		/**
		 * As defined in Design doc for beta, cannot leave row 1 and bounds.
		 */
		if (coord.getFile() > palaceBoundaries[2] && coord.getFile() < palaceBoundaries[3] && coord.getRank() == 1)
			return true;
		
		return false;
		
	}

	/**
	 * Query a position in the board for a piece.
	 * @param coord the absolute position to query
	 * @return XiangqiPiece piece found
	 */
	public XiangqiPiece getPieceAt(XiangqiCoordinate coord) {
		XiangqiPiece p = mapCoordToPiece.get(coord);
		XiangqiCoordinate c = Coordinate.makeCoordinate(coord.getRank(), coord.getFile());

		p = mapCoordToPiece.get(c);
	
		if (p != null)
			return p;

		/*
		 * if (mapCoordToPiece.containsKey(coord)) return
		 * mapCoordToPiece.get(coord); else
		 */
		return (XiangqiPiece) PieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE, new NoneMovementValidator());

	}

	/**
	 * A function to allow a save to the state of the game
	 * @param piece the piece to save at a point
	 * @param coord the key we will use to lookup the piece
	 */
	public void placePiece(XiangqiPiece piece, XiangqiCoordinate coord) {
			mapCoordToPiece.put(Coordinate.makeCoordinate(coord.getRank(), coord.getFile()), piece);
	}

	/**
	 * A function that removes pieces.
	 * @param coord the spot to remove a piece
	 * @return XiangqiPiece removed
	 */
	public XiangqiPiece removePiece(XiangqiCoordinate coord) {
		// Assume no Collision	
		
		XiangqiCoordinate c = Coordinate.makeCoordinate(coord.getRank(), coord.getFile());
		XiangqiPiece p = mapCoordToPiece.get(c);
				mapCoordToPiece.remove(Coordinate.makeCoordinate(coord.getRank(), coord.getFile()) );
		
	return p;
		
	
	}


	/**
	 * An ugly function that does its job -- check if there is something in the way of a piece moving
	 * to its destination
	 * @param from the source location
	 * @param to the destination coordinate
	 * @return boolean indicating if there is another piece blocking.
	 */
	public boolean isBlocked(XiangqiCoordinate from, XiangqiCoordinate to) {

		if (from.equals(to))
			return false;
		
		if (from.getRank() == to.getRank())
			return searchHorizontally(from,to);
		
		if (from.getFile() == to.getFile())
			return searchVertically(from,to);
		
		if (Math.abs(to.getRank() - from.getRank()) / Math.abs(to.getFile() - from.getFile()) == 1) 
			return searchDiagonally(from,to);
		
		return false;

	}

	/**
	 * SearchDiagonally searches from a coordinate to another one, seeing if there is anything inhabitting the 
	 * coordinate.
	 * @param from the Coordinate to start from
	 * @param to the Coordinate to end up at
	 * @return a boolean denoting if there is something blocking from to to
	 */
	private boolean searchDiagonally(XiangqiCoordinate from, XiangqiCoordinate to) {
		
		
			int i = from.getFile();

			int step = (to.getRank() - from.getRank()) / Math.abs(to.getFile() - from.getFile());
			while (i != to.getFile()) {
				
				if (i == to.getFile())
					break;
				i += step;
				XiangqiCoordinate x = Coordinate.makeCoordinate(step + from.getRank(), step + from.getFile());

				if (getPieceAt(x).getPieceType() != XiangqiPieceType.NONE)
					return true;
			}
		
		return false;

		
	}
	
	/**
	 * SearchVertically searches from coord to another one, vertically, seeing if there is anything inhabitting the 
	 * coordinate.
	 * @param from the Coordinate to start from
	 * @param to the Coordinate to end up at
	 * @return a boolean denoting if there is something blocking from to to
	 */
	private boolean searchVertically(XiangqiCoordinate from, XiangqiCoordinate to) {
		
			int i = from.getFile();
			int step = (to.getRank() - from.getRank()) / Math.abs(from.getRank() - to.getRank());
			while (i != to.getRank()) {
				i += step;
				
				if (i == to.getRank())
					break;
				
				XiangqiCoordinate x = Coordinate.makeCoordinate(i, to.getFile());

				if (getPieceAt(x).getPieceType() != XiangqiPieceType.NONE)
					return true;
			}

			return false;
			
		
	}
	/**
	 * SearchHorizontally searches from a coordinate to another one horizontally seeing if there is anything inhabitting the 
	 * coordinate.
	 * @param from the Coordinate to start from
	 * @param to the Coordinate to end up at
	 * @return a boolean denoting if there is something blocking from to to
	 */
	private boolean searchHorizontally(XiangqiCoordinate from, XiangqiCoordinate to) {
		
		
			int i = from.getFile();
			int step = (to.getFile() - from.getFile()) / Math.abs(from.getFile() - to.getFile());
			while (i != to.getFile()) {
				i += step;
				
				if (i == to.getFile())
					break;
				
				XiangqiCoordinate x = Coordinate.makeCoordinate(to.getRank(), i);
				if (getPieceAt(x).getPieceType() != XiangqiPieceType.NONE)
					return true;
			}

			return false;
		
	}

	/**
	 * A function used in checking for checkmates
	 * @return a set of all coordinates stored in the board
	 */
	public Set<XiangqiCoordinate> getAllUsedCoords() {
		return mapCoordToPiece.keySet();
	}

	/**
	 * a function that returns the coordinates of a general
	 * @param c the general's color you are searching for
	 * @return XiangqiCoordinate that represents the location of the general
	 */
	public XiangqiCoordinate getGeneralCoordinate(XiangqiColor c) {
		
		for (XiangqiCoordinate cord : mapCoordToPiece.keySet()) {
			XiangqiPiece p = mapCoordToPiece.get(cord);
			
			if (p.getPieceType() == XiangqiPieceType.GENERAL && p.getColor() == c )
				return cord;
			}
		
		return Coordinate.makeCoordinate(-1, -1);
		}

}

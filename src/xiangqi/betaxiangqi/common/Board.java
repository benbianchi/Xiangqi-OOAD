package xiangqi.betaxiangqi.common;

import java.util.HashMap;
import java.util.Set;

import common.TestCoordinate;
import xiangqi.betaxiangqi.BetaXiangqiGame;
import xiangqi.betaxiangqi.common.MovementValidators.NoneMovementValidator;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

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
	private HashMap<XiangqiCoordinate, XiangqiPiece> mapCoordToPiece;
	
	/**
	 * The boundaries for the board
	 */
	private Integer[] bounds = new Integer[2];
	

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
	 * Query a position in the board for a piece.
	 * @param coord the absolute position to query
	 * @return XiangqiPiece piece found
	 */
	public XiangqiPiece getPieceAt(XiangqiCoordinate coord) {

		for (XiangqiCoordinate c : mapCoordToPiece.keySet()) {
			if (coord.getFile() == c.getFile() && coord.getRank() == c.getRank())
				return mapCoordToPiece.get(c);
		}

		/*
		 * if (mapCoordToPiece.containsKey(coord)) return
		 * mapCoordToPiece.get(coord); else
		 */
		return PieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE, new NoneMovementValidator());

	}

	/**
	 * A function to allow a save to the state of the game
	 * @param piece the piece to save at a point
	 * @param coord the key we will use to lookup the piece
	 */
	public void placePiece(XiangqiPiece piece, XiangqiCoordinate coord) {

		if (!mapCoordToPiece.containsKey(coord))
			mapCoordToPiece.put(coord, piece);

	}

	/**
	 * A function that removes pieces.
	 * @param coord the spot to remove a piece
	 * @return XiangqiPiece removed
	 */
	public XiangqiPiece removePiece(XiangqiCoordinate coord) {
		// Assume no Collision

		for (XiangqiCoordinate c : mapCoordToPiece.keySet()) {
			if (coord.getFile() == c.getFile() && coord.getRank() == c.getRank()) {
				XiangqiPiece p = mapCoordToPiece.get(coord);
				mapCoordToPiece.remove(coord);
				return p;
			}
		}

		return PieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE, new NoneMovementValidator());

	}

	/**
	 * A function to take a piece, and move it to another location
	 * @param source the original spot queried
	 * @param destination the destination for the piece
	 * @param piece the piece moved, used to invoke validator
	 */
	public void updatePiece(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiPiece piece) {

		if (piece.getColor() == XiangqiColor.BLACK) {
			XiangqiCoordinate s = TestCoordinate.makeCoordinate(bounds[0] - source.getRank(),
					bounds[1] - source.getFile());
			XiangqiCoordinate d = TestCoordinate.makeCoordinate(bounds[0] - source.getRank(),
					bounds[1] - source.getFile());
			placePiece(removePiece(s), d);
		}

		placePiece(removePiece(source), destination);

	}

	/**
	 * An ugly function that does its job -- check if there is something in the way of a piece moving
	 * to its destination
	 * @param from the source location
	 * @param to the destination coordinate
	 * @return boolean indicating if there is another piece blocking.
	 */
	public boolean isBlocked(XiangqiCoordinate from, XiangqiCoordinate to) {

		if (from.getRank() == to.getRank()) {// Horizontal;
			int i = from.getFile();
			int step = (to.getFile() - from.getFile()) / Math.abs(from.getFile() - to.getFile());
			while (i != to.getFile()) {
				i += step;
				XiangqiCoordinate x = TestCoordinate.makeCoordinate(to.getRank(), i);
				if (getPieceAt(x).getPieceType() != XiangqiPieceType.NONE)
					return true;
			}

			return false;
		}
		if (from.getFile() == to.getFile()) {
			int i = from.getFile();
			int step = (to.getRank() - from.getRank()) / Math.abs(from.getRank() - to.getRank());
			while (i != to.getRank()) {
				i += step;
				XiangqiCoordinate x = TestCoordinate.makeCoordinate(i, to.getFile());

				if (getPieceAt(x).getPieceType() != XiangqiPieceType.NONE)
					return true;
			}

			return false;
		}
		if (Math.abs(to.getRank() - from.getRank()) / Math.abs(to.getFile() - from.getFile()) == 1) {
			int i = from.getFile();

			int step = (to.getRank() - from.getRank()) / Math.abs(to.getFile() - from.getFile());
			while (i != to.getFile()) {
				i += step;
				XiangqiCoordinate x = TestCoordinate.makeCoordinate(step + from.getRank(), step + from.getFile());

				if (getPieceAt(x).getPieceType() != XiangqiPieceType.NONE)
					return true;
			}
		}
		return false;

		// Diagonal

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
			if (mapCoordToPiece.get(cord).getPieceType() == XiangqiPieceType.GENERAL && mapCoordToPiece.get(cord).getColor() == c )
				return cord;
			}
		
		return TestCoordinate.makeCoordinate(-1, -1);
		}

}

package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators;


import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaBoard;

public class DefaultMovementValidator implements MovementValidator {

	/**
	 * The board that this validator has access to.
	 */
	public static GammaBoard board;
	
	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement inherits default behavior assumed by the game.
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		if (isSpaceNotOccupiedByAlly(fromCoord, toCoord) &&  isNewMovementWithinBounds(toCoord) && isNotSameCoord(fromCoord,toCoord))
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}
	/**
	 * Checks to see if coordinates are the same.
	 * @param fromCoord coordinate 1
	 * @param toCoord coordinate 2
	 * @return true if coord 1 and coord 2 have the same values.
	 */
	private boolean isNotSameCoord(Coordinate fromCoord, Coordinate toCoord) {
		if (fromCoord.equals(toCoord))
			return false;
		
		return true;
	}
	/**
	 * A function that checks to see if a piece is attempting to capture a friendly unit
	 * @param fromCoord	the attackers coordinate
	 * @param toCoord the victims coordinate
	 * @return a boolean denoting whether or not the two pieces are on the same team.
	 */
	public boolean isSpaceNotOccupiedByAlly(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord)
	{
		XiangqiPiece p = board.getPieceAt(toCoord);
		
		if (p.getColor() != XiangqiColor.NONE && p.getColor() == board.getPieceAt(fromCoord).getColor())
			return false;
		
		return true;
	}
	
	/**
	 * a function that checks to see that a coordinate is valid and within the board bounds
	 * @param toCoord the coordinate we are checking.
	 * @return a boolean that denotes whether or not the coordinate is valid.
	 */
	public static boolean isNewMovementWithinBounds(XiangqiCoordinate toCoord)
	{
		if (toCoord.getRank() > 0 && toCoord.getRank() <= board.getBounds()[0] &&
				toCoord.getFile() > 0 && toCoord.getFile() <= board.getBounds()[1] )
			return true;
		
		return false;
	}
	
	/**
	 * a function that checks to see if a piece is past the river
	 * @param toCoord the coordinate we are checking is past the river
	 * @return a boolean that represents if the coordinate is past the river.
	 */
	public boolean isNotCrossingRiver(XiangqiCoordinate toCoord)
	{
		if (toCoord.getRank() < DefaultMovementValidator.board.getRiverRow() )
			return true;
		
		return false;
	}
	
	/**
	 * Setter for board
	 * @param board the board that this defaultvalidator has access to.
	 */
	public static void setBounds(GammaBoard board)
	{
		DefaultMovementValidator.board = board;
	}

}

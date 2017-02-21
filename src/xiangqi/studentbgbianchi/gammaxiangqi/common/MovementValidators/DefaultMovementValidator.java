package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators;


import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.studentbgbianchi.betaxiangqi.common.Board;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.GammaBoard;

public class DefaultMovementValidator implements MovementValidator {


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
	
	private boolean isNotSameCoord(Coordinate fromCoord, Coordinate toCoord) {
		if (fromCoord.equals(toCoord))
			return false;
		
		return true;
	}

	public boolean isSpaceNotOccupiedByAlly(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord)
	{
		XiangqiPiece p = board.getPieceAt(toCoord);
		
		if (p.getColor() != XiangqiColor.NONE && p.getColor() == board.getPieceAt(fromCoord).getColor())
			return false;
		
		return true;
	}
	
	public static boolean isNewMovementWithinBounds(XiangqiCoordinate toCoord)
	{
		if (toCoord.getRank() > 0 && toCoord.getRank() <= board.getBounds()[0] &&
				toCoord.getFile() > 0 && toCoord.getFile() <= board.getBounds()[1] )
			return true;
		
		return false;
	}
	
	public boolean isNotCrossingRiver(XiangqiCoordinate toCoord)
	{
		if (toCoord.getRank() < DefaultMovementValidator.board.getRiverRow() )
			return true;
		
		return false;
	}
	
	
	public static void setBounds(GammaBoard board)
	{
		DefaultMovementValidator.board = board;
	}

}

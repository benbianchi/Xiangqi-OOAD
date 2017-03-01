package xiangqi.student.bgbianchi.common;


import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.MovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class AbsMovementValidator implements MovementValidator {


	public static AbsBoard board;
	
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
		XiangqiPiece source = board.getPieceAt(fromCoord);
		XiangqiPiece attacker = board.getPieceAt(toCoord);
		
		if (source.getColor() != attacker.getColor() || source.getColor() == XiangqiColor.NONE )
			return true;
		
		return false;
	}
	
	public boolean isNewMovementWithinBounds(XiangqiCoordinate toCoord)
	{
		if (toCoord.getRank() > 0 && toCoord.getRank() <= board.getBounds()[0] &&
				toCoord.getFile() > 0 && toCoord.getFile() <= board.getBounds()[1] )
			return true;
		
		return false;
	}
	
	public static void setBounds(AbsBoard board)
	{
		AbsMovementValidator.board = board;
	}

}

package xiangqi.gammaxiangqi.common.MovementValidators;


import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.gammaxiangqi.common.Board;

public class AbsMovementValidator implements MovementValidator {


	public static Board board;
	
	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement inherits default behavior assumed by the game.
	 */
	public MoveResult validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		if (isSpaceNotOccupiedByAlly(fromCoord, toCoord) &&  isNewMovementWithinBounds(toCoord))
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}
	
	public boolean isSpaceNotOccupiedByAlly(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord)
	{
		if (board.getPieceAt(toCoord).getColor() == board.getPieceAt(fromCoord).getColor())
			return false;
		
		return true;
	}
	
	public boolean isNewMovementWithinBounds(XiangqiCoordinate toCoord)
	{
		if (toCoord.getRank() > 0 && toCoord.getRank() <= board.getBounds()[0] &&
				toCoord.getFile() > 0 && toCoord.getRank() <= board.getBounds()[1] )
			return true;
		
		return false;
	}
	
	public static void setBounds(Board board)
	{
		AbsMovementValidator.board = board;
	}

}

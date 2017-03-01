package xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.common.AbsMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class GeneralMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the general only moves between file 2 and file 4 (including).
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		
		//Generally can move horizontally by one between 12 and 14
		
		if (isFlyingGeneral(fromCoord,toCoord) != MoveResult.ILLEGAL)
			return isFlyingGeneral(fromCoord,toCoord);
		
		if (super.validate(fromCoord, toCoord) != MoveResult.OK)
			return MoveResult.ILLEGAL;
		
		if (AbsMovementValidator.board.isWithinPalace(toCoord) && isGeneralMovingOrthognally(fromCoord,toCoord) )
				return MoveResult.OK;
			
		
		
		return MoveResult.ILLEGAL;
	}
	

	boolean isGeneralMovingOrthognally(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		
		if (fromCoord.getRank() == toCoord.getRank() || fromCoord.getFile() == toCoord.getFile())
			return true;
		
		return false;
	}


	public MoveResult isFlyingGeneral(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord)
	{
		//How to check this?
		//Well we have access to the board and can check if blocking....
		//We also need to check if we are moving from one king to another.
		
		//get which king we are
		
		XiangqiPiece threatenedKing = AbsMovementValidator.board.getPieceAt(toCoord);
		
		
		if (threatenedKing.getPieceType() == XiangqiPieceType.GENERAL && !AbsMovementValidator.board.isBlocked(fromCoord, toCoord))
			{
				if (threatenedKing.getColor() == XiangqiColor.BLACK)
					return MoveResult.RED_WINS;
				else
					return MoveResult.BLACK_WINS;
			}		
		return MoveResult.ILLEGAL;
	}

}

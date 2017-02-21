package xiangqi.gammaxiangqi.common.MovementValidatorsImpl;

import common.TestCoordinate;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.gammaxiangqi.common.MovementValidators.AbsMovementValidator;

public class GeneralMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the general only moves between file 2 and file 4 (including).
	 */
	public MoveResult validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		
		//Generally can move horizontally by one between 12 and 14
		
		
		if (super.validate(fromCoord, toCoord) != MoveResult.OK)
			return MoveResult.ILLEGAL;
		
		if (this.board.isWithinPalace(toCoord) && isGeneralMovingOrthognally(fromCoord,toCoord) )
				return isFlyingGeneral(fromCoord,toCoord);
			
		
		
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
		if (fromCoord.getRank() == toCoord.getRank() && Math.abs( toCoord.getFile() - fromCoord.getFile() ) == 1 )
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}

}

package xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.student.bgbianchi.common.AbsMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.MovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class SoldierMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the soldier only moves forwards.
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		
		if ( fromCoord.isForward(toCoord, AbsMovementValidator.board.getPieceAt(fromCoord).getColor()) && fromCoord.distanceTo(toCoord) ==1 && super.validate(fromCoord, toCoord) == MoveResult.OK)
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
		
		
	}

}

package xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.AbsMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.MovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class NoneMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator will always fail. If this is assigned to a class, then it isnt a valid piece.
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		return MoveResult.ILLEGAL;
	}

}

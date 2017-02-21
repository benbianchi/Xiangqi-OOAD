package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class NoneMovementValidator extends DefaultMovementValidator {

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

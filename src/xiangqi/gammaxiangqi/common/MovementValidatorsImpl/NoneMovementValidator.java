package xiangqi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.gammaxiangqi.common.MovementValidators.AbsMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidators.MovementValidator;

public class NoneMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator will always fail. If this is assigned to a class, then it isnt a valid piece.
	 */
	public MoveResult validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		return super.validate(toCoord, toCoord);
	}

}

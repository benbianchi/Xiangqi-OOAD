package xiangqi.betaxiangqi.common.MovementValidators;

import xiangqi.betaxiangqi.common.MovementValidator;
import xiangqi.common.XiangqiCoordinate;

public class NoneMovementValidator implements MovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator will always fail. If this is assigned to a class, then it isnt a valid piece.
	 */
	public boolean validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		return true;
	}

}

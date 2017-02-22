package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;

public class PreRiverSoldierMovementValidator extends DefaultMovementValidator {
	/**
	 * The soldier can only move forwards before the river.
	 */
	@Override
	public MoveResult validate(Coordinate to, Coordinate from)
	{
		if (to.isForward(from) && to.distanceTo(from) == 1)
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}
}

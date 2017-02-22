package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;

public class PostRiverSoldierMovementValidator extends DefaultMovementValidator {
	@Override
	/**
	 * The soldier can move orthogonally, but one by one after the river.
	 */
	public MoveResult validate(Coordinate to, Coordinate from)
	{
		if (to.isOrthogonal(from) && to.distanceTo(from) == 1)
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}
}

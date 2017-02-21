package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class CannonMovementValidator extends DefaultMovementValidator {
	
	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the chariot only moves orthogonally
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		// TODO Auto-generated method stub
			return MoveResult.OK;

	}
}

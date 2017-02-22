package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.MovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class SoldierMovementValidator extends DefaultMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the soldier only moves forwards. Until it crosses the river
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		
		MovementValidator subValidator;
		
		if (isNotCrossingRiver(toCoord))
			subValidator = new PreRiverSoldierMovementValidator();
		else
			subValidator = new PostRiverSoldierMovementValidator();
		
		if (  super.validate(fromCoord, toCoord) == MoveResult.OK && subValidator.validate(fromCoord, toCoord) == MoveResult.OK)
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
		
		
	}

}

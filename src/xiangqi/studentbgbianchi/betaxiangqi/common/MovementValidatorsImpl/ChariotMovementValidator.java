package xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.student.bgbianchi.common.AbsMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.MovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class ChariotMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the chariot only moves orthogonally
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		// TODO Auto-generated method stub
	
		
		if ( fromCoord.isOrthogonal(toCoord) && (super.validate(fromCoord, toCoord) == MoveResult.OK) )
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}

}

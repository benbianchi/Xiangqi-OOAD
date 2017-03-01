package xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.student.bgbianchi.common.AbsMovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.MovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class AdvisorMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the advisor only moves diagonally
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		
		
		if (fromCoord.isDiagonal(toCoord) &&
				(super.validate(fromCoord, toCoord) == MoveResult.OK))
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}

}

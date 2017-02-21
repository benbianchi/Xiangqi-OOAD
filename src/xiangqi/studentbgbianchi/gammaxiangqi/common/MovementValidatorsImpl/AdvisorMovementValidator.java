package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public class AdvisorMovementValidator extends DefaultMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the advisor only moves diagonally
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		
		
		if (fromCoord.isDiagonal(toCoord) && this.board.isWithinPalace(toCoord) &&
				(super.validate(fromCoord, toCoord) == MoveResult.OK))
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}

}

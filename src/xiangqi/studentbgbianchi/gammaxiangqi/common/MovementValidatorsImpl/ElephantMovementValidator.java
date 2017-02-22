package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.MovementValidator;

public class ElephantMovementValidator extends DefaultMovementValidator{

	@Override
	/**
	 * The Elephant can jump 2 diagonally and not cross the river.
	 */
	public MoveResult validate(Coordinate fromCoord, Coordinate toCoord) {
		System.out.println(fromCoord.isDiagonal(toCoord));
		System.out.println(fromCoord.distanceTo(toCoord));
		if (fromCoord.isDiagonal(toCoord) && fromCoord.distanceTo(toCoord) == 4 && isNotCrossingRiver(toCoord))
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}
/**
 * The Elephant can jump 2 diagonally and not cross the river.
 */
	
}

package xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;

public class PreRiverSoldierMovementValidator extends DefaultMovementValidator {
	/**
	 * The soldier can only move forwards before the river.
	 */
	@Override
	public MoveResult validate(Coordinate from, Coordinate to)
	{
		if (to.isForward(from,this.board.getPieceAt(from).getColor()) && to.distanceTo(from) == 1)
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}
}

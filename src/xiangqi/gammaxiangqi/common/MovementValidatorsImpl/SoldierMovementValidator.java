package xiangqi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.gammaxiangqi.common.MovementValidators.AbsMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidators.MovementValidator;

public class SoldierMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the soldier only moves forwards.
	 */
	public MoveResult validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		if (toCoord.getRank() == (fromCoord.getRank()+1) && toCoord.getFile() == fromCoord.getFile() && 
				(super.validate(fromCoord, toCoord)==MoveResult.OK))
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
		
		
	}

}

package xiangqi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.gammaxiangqi.common.MovementValidators.AbsMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidators.MovementValidator;

public class AdvisorMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the advisor only moves diagonally
	 */
	public MoveResult validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		
		
		int slopeX = fromCoord.getFile() - toCoord.getFile();
		int slopeY = fromCoord.getRank() - toCoord.getRank();
		
		if (Math.abs(slopeX) == Math.abs(slopeY) && (super.validate(fromCoord, toCoord) == MoveResult.OK))
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}

}

package xiangqi.gammaxiangqi.common.MovementValidatorsImpl;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.gammaxiangqi.common.MovementValidators.AbsMovementValidator;
import xiangqi.gammaxiangqi.common.MovementValidators.MovementValidator;

public class ChariotMovementValidator extends AbsMovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the chariot only moves orthogonally
	 */
	public MoveResult validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		// TODO Auto-generated method stub
	
		
		if ((fromCoord.getRank() == toCoord.getRank() || fromCoord.getFile() == toCoord.getFile()) && 
				(!toCoord.equals(fromCoord)) && (super.validate(fromCoord, toCoord) == MoveResult.OK) )
			return MoveResult.OK;
		
		return MoveResult.ILLEGAL;
	}

}

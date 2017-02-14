package xiangqi.betaxiangqi.common.MovementValidators;

import xiangqi.betaxiangqi.common.MovementValidator;
import xiangqi.common.XiangqiCoordinate;

public class GeneralMovementValidator implements MovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the general only moves between file 2 and file 4 (including).
	 */
	public boolean validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		
		//Generally can move horizontally by one between 12 and 14
		
		if (fromCoord.getRank() == toCoord.getRank() && Math.abs( toCoord.getFile() - fromCoord.getFile() ) == 1 && (toCoord.getFile() < 5  && toCoord.getFile() > 1))
			return true;
		
		return false;
	}

}

package xiangqi.betaxiangqi.common.MovementValidators;

import xiangqi.betaxiangqi.common.MovementValidator;
import xiangqi.common.XiangqiCoordinate;

public class SoldierMovementValidator implements MovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the soldier only moves forwards.
	 */
	public boolean validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		if (toCoord.getRank() == (fromCoord.getRank()+1) 
				&& toCoord.getFile() == fromCoord.getFile())
			return true;
		
		return false;
		
		
	}

}

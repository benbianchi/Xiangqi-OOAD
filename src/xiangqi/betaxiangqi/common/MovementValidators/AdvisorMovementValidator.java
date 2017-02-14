package xiangqi.betaxiangqi.common.MovementValidators;

import xiangqi.betaxiangqi.common.MovementValidator;
import xiangqi.common.XiangqiCoordinate;

public class AdvisorMovementValidator implements MovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the advisor only moves diagonally
	 */
	public boolean validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		
		int slopeX = fromCoord.getFile() - toCoord.getFile();
		int slopeY = fromCoord.getRank() - toCoord.getRank();
		
		if (slopeX == slopeY)
			return true;
		
		return false;
	}

}

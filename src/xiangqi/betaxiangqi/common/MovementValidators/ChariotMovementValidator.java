package xiangqi.betaxiangqi.common.MovementValidators;

import xiangqi.betaxiangqi.common.MovementValidator;
import xiangqi.common.XiangqiCoordinate;

public class ChariotMovementValidator implements MovementValidator {

	@Override
	/**
	 * see #MovementValidator.validate
	 * 
	 * This movement validator ensures that the chariot only moves orthogonally
	 */
	public boolean validate(XiangqiCoordinate fromCoord, XiangqiCoordinate toCoord) {
		// TODO Auto-generated method stub
		if (fromCoord.getRank() == toCoord.getRank() || fromCoord.getFile() == toCoord.getFile() && (!toCoord.equals(fromCoord)) )
			return true;
		
		return false;
	}

}

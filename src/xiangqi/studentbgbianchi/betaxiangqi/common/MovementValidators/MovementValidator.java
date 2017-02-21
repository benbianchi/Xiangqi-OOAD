package xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators;

import java.util.function.Function;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

public interface MovementValidator {
	/**
	 * A extensible function that is invoked by BetaXiangqiGame. This function allows extensible customizability by implementing
	 * the strategy pattern. 
	 * 
	 * The Function is evaluated and tells the game whether the desired movement is possible.
	 * @param fromCoord the source point
	 * @param toCoord the destination point.
	 * @return boolean representing the legality of the move
	 */ 
	

	MoveResult validate(Coordinate fromCoord, Coordinate toCoord );
	
	
}

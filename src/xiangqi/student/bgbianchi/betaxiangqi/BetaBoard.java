package xiangqi.student.bgbianchi.betaxiangqi;

import java.util.HashMap;
import java.util.Set;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.betaxiangqi.BetaXiangqiGame;
import xiangqi.student.bgbianchi.common.AbsBoard;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.NoneMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

/**
 * The board is a container for the state of the game. It implements a hashmap to keep track of what is where.
 * It also implements bounds.
 * 
 * @author ben
 *
 */
public class BetaBoard  extends AbsBoard {

	protected Integer[] palaceBoundaries = {0,2,1,6};
	/**
	 * Create a Beta Board
	 * @param maxRow the Maximum Row Count
	 * @param maxCol the Maximum Column Count
	 */
	
	
	public BetaBoard() {
		super(5, 5);
	}

	
	
	/**
	 * A function that sees if a coordinate is within the palace. The Game will usually define what is the 
	 * palace.
	 * @param coord the coordinate we are testing whether it is within the palace
	 * @return a boolean denoting whether or not the coordinate is within the palace
	 */
	public boolean isWithinPalace(XiangqiCoordinate coord)
	{
		/**
		 * As defined in Design doc for beta, cannot leave row 1 and bounds.
		 */
		if (coord.getFile() > palaceBoundaries[2] && coord.getFile() < palaceBoundaries[3] && coord.getRank() == 1)
			return true;
		
		return false;
		
	}

}

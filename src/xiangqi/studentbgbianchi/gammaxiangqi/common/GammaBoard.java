package xiangqi.studentbgbianchi.gammaxiangqi.common;

import java.util.concurrent.CompletionException;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.common.AbsBoard;
import xiangqi.student.bgbianchi.common.AbsMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidators.DefaultMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.NoneMovementValidator;

public class GammaBoard extends AbsBoard {

	public GammaBoard() {
		super(10, 10);
	}
	
	/**
	 * River is the row that only a few pieces can cross, and some change behavior.
	 */
	Integer RIVER_ROW = 6;
	
	
	@Override
	/**
	 * A function that sees if a coordinate is within the palace. The Game will usually define what is the 
	 * palace.
	 * @param coord the coordinate we are testing whether it is within the palace
	 * @return a boolean denoting whether or not the coordinate is within the palace
	 */
	public XiangqiPiece getPieceAt(XiangqiCoordinate coord) {

		if (!DefaultMovementValidator.isNewMovementWithinBounds(coord) )
			throw new CompletionException(new RuntimeException());
		
		return super.getPieceAt(coord);

	}
	
	
	@Override
	/* (non-Javadoc)
	 * @see xiangqi.betaxiangqi.BetaXiangqi#isWithinPalace
	 */
	public boolean isWithinPalace(XiangqiCoordinate coord)
	{
		/**
		 * As defined in Design doc for beta, cannot leave row 1 and bounds.
		 */
		if ( coord.getRank() > palaceBoundaries[0] && coord.getRank() < palaceBoundaries[1] && coord.getFile() > palaceBoundaries[2] && coord.getFile() < palaceBoundaries[3])
			return true;
		
		return false;
		
	}
	
	/**
	 * Hook used to denote where the river is in GammaXiangqi
	 * @return the row that is the river row (default is 6 for GammaXiangqi).
	 */
	public Integer getRiverRow()
	{
		return RIVER_ROW;
		
	}

}

/**
 * 
 */
package xiangqi.gammaxiangqi.common;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.gammaxiangqi.common.MovementValidators.MovementValidator;

/**
 * @author ben
 *
 */
public class PieceImpl implements XiangqiPiece {

	private final XiangqiColor color;
	private final XiangqiPieceType pieceType;
	private final MovementValidator validator;
	
	public static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color, MovementValidator v )
	{
		return new PieceImpl(pieceType,color, v);
	}
	
	private PieceImpl(XiangqiPieceType pieceType, XiangqiColor color, MovementValidator validator)
	{
		this.validator = validator;
		this.pieceType = pieceType;
		this.color = color;
	}
	
	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiPiece#getColor()
	 */
	@Override
	public XiangqiColor getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiPiece#getPieceType()
	 */
	@Override
	public XiangqiPieceType getPieceType() {
		// TODO Auto-generated method stub
		return pieceType;
	}

	public MoveResult validate(XiangqiCoordinate source, XiangqiCoordinate destination) {
		return validator.validate(source, destination);
		
	}

}

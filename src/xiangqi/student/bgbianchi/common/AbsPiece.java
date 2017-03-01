/**
 * 
 */
package xiangqi.student.bgbianchi.common;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.MovementValidator;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidatorsImpl.NoneMovementValidator;
import xiangqi.studentbgbianchi.gammaxiangqi.common.Coordinate;

/**
 * @author ben
 *
 */
public class AbsPiece implements XiangqiPiece {

	private final XiangqiColor color;
	private final XiangqiPieceType pieceType;
	private final MovementValidator validator;
	
	
	public AbsPiece(XiangqiPieceType pieceType, XiangqiColor color, MovementValidator validator)
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
		Coordinate cSource = Coordinate.makeCoordinate(source.getRank(), source.getFile());
		Coordinate cDest= Coordinate.makeCoordinate(destination.getRank(), destination.getFile());
		return validator.validate(cSource, cDest);
		
	}

	public static AbsPiece makePiece(XiangqiPieceType t, XiangqiColor c,
			NoneMovementValidator mv) {
		// 
		return new AbsPiece(t,c,mv);
	}

}

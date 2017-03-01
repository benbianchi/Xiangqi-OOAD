package xiangqi.student.bgbianchi.common.impl;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPieceType;
import xiangqi.student.bgbianchi.common.AbsPiece;
import xiangqi.studentbgbianchi.betaxiangqi.common.MovementValidators.MovementValidator;

public class XQPiece extends AbsPiece{

	public XQPiece(XiangqiPieceType pieceType, XiangqiColor color, MovementValidator validator) {
		super(pieceType, color, validator);
	}
	

	public static XQPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color, MovementValidator validator) {
			return new XQPiece(pieceType,color,validator);
		}
	
}

/**
 * 
 */
package xiangqi.versions.alphaxiangqi.common;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

/**
 * @author ben
 *
 */
public class AlphaXiangqiPieceImpl implements XiangqiPiece {

	/**
	 * The color of the pice
	 */
	private final XiangqiColor color;
	
	/**
	 * The type of the peice.
	 */
	private final XiangqiPieceType pieceType;
	
	public static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color )
	{
		return new AlphaXiangqiPieceImpl(pieceType,color);
	}
	
	private AlphaXiangqiPieceImpl(XiangqiPieceType pieceType, XiangqiColor color )
	{
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

}

package xiangqi.studentbgbianchi.gammaxiangqi.common;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentbgbianchi.gammaxiangqi.common.MovementValidatorsImpl.NoneMovementValidator;

public class GammaBoard extends xiangqi.studentbgbianchi.betaxiangqi.common.Board {

	public GammaBoard() {
		super(10, 10);
	}
	
	Integer RIVER_ROW = 6;
	
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate coord) {
		XiangqiPiece p = mapCoordToPiece.get(coord);
		XiangqiCoordinate c = Coordinate.makeCoordinate(coord.getRank(), coord.getFile());

		p = mapCoordToPiece.get(c);
	
		if (p != null)
			return p;

		/*
		 * if (mapCoordToPiece.containsKey(coord)) return
		 * mapCoordToPiece.get(coord); else
		 */
		return (XiangqiPiece) GammaPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE, new NoneMovementValidator());

	}
	
	@Override
	public boolean isWithinPalace(XiangqiCoordinate coord)
	{
		/**
		 * As defined in Design doc for beta, cannot leave row 1 and bounds.
		 */
		if ( coord.getRank() > palaceBoundaries[0] && coord.getRank() < palaceBoundaries[1] && coord.getFile() > palaceBoundaries[2] && coord.getFile() < palaceBoundaries[3])
			return true;
		
		return false;
		
	}
	
	public Integer getRiverRow()
	{
		return RIVER_ROW;
		
	}

}

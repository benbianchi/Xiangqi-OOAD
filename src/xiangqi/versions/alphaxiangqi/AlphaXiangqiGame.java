/**
 * 
 */
package xiangqi.versions.alphaxiangqi;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;

/**
 * @author ben
 *
 */
public class AlphaXiangqiGame implements XiangqiGame {

	private int moveCount;
	private String moveMessage;
	
	public AlphaXiangqiGame() {
		moveCount = 0;
	}
	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		
		if (destination.getRank() > source.getRank())
		{
			this.moveMessage = "Illegal Move.";
			return MoveResult.ILLEGAL;
		}
		
		return moveCount++ == 0? MoveResult.OK : MoveResult.RED_WINS;
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#getMoveMessage()
	 */
	@Override
	public String getMoveMessage() {
		// TODO Auto-generated method stub
		return this.moveMessage;
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#getPieceAt(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiColor)
	 */
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
		
		return null;
	}

}

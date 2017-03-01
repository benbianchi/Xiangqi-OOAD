/**
 * 
 */
package xiangqi.studentbgbianchi.versions.alphaxiangqi;

import java.util.ArrayList;
import java.util.List;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studentbgbianchi.versions.alphaxiangqi.common.AlphaXiangqiPieceImpl;

/**
 * @author ben
 *
 */
public class AlphaXiangqiGame implements XiangqiGame {

	/**
	 * How many moves have been made this game
	 */
	private int moveCount;
	
	/**
	 * The moveMessage returned
	 */
	private String moveMessage;
	
	/**
	 * The state of the game.
	 */
	private List<XiangqiPiece> piecesOnBoard;
	
	/**
	 * Instantiate a new AlphaXiangqiGame
	 */
	public AlphaXiangqiGame() {
		piecesOnBoard =new ArrayList<XiangqiPiece>();
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
		
		return AlphaXiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
	}

}

/**
 * 
 */
package common;

import xiangqi.common.XiangqiCoordinate;

/**
 * @author ben
 *
 */
public class TestCoordinate implements XiangqiCoordinate {

	private int rank;
	private int file;
	
	public static TestCoordinate makeCoordinate(int rank, int file)
	{
		return new TestCoordinate(rank,file);
	}
	
	private TestCoordinate(int rank, int file) {
		this.rank=rank;
		this.file=file;
	}
	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiCoordinate#getRank()
	 */
	@Override
	public int getRank() {
		return rank;
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiCoordinate#getFile()
	 */
	@Override
	public int getFile() {
		return file;
	}

}

/**
 * 
 */
package xiangqi.studentbgbianchi.gammaxiangqi.common;

import xiangqi.common.XiangqiCoordinate;

/**
 * @author ben
 *
 */
public class Coordinate implements XiangqiCoordinate {

	private int rank;
	private int file;
	
	public static Coordinate makeCoordinate(int rank, int file)
	{
		return new Coordinate(rank,file);
	}
	
	private Coordinate(int rank, int file) {
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
	
	public int hashCode()
	{
		int sum = rank + file;
		return sum * (sum +1)/2 + file;
		
	}
	@Override
	public boolean equals(Object t)
	{
		if (!(t instanceof XiangqiCoordinate))
			return false;
		
		if (	((XiangqiCoordinate)t ).getFile() == file && ((XiangqiCoordinate)t ).getRank() == rank)
			return true;
		
		return false;
		
	}
	@Override
	public
	String toString()
	{
		return rank+""+file;
		
	}
	
	public boolean isOrthogonal(XiangqiCoordinate c)
	{
		return c.getRank() == rank || c.getFile() == file;
	}
	
	public boolean isDiagonal(XiangqiCoordinate c)
	{
		if (!isOrthogonal(c))
			return Math.abs( rank- c.getRank()) / Math.abs(file-c.getFile())==1;
		
		return false;
	}
	
	public int distanceTo(XiangqiCoordinate c)
	{
		return Math.abs( rank - c.getRank() ) +  Math.abs(file - c.getFile()) ;
	}
	
	public boolean isForward(XiangqiCoordinate c)
	{
		return c.getRank() > rank;
	}
	
}

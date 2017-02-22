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

	/**
	 * The Row that is used for XiangqiCoordinate
	 */
	private int rank;
	
	/**
	 * The Column that is used for XiangqiCoordinate.
	 */
	private int file;
	
	/**
	 * A factory method for creating coordinates.
	 * @param rank the Row used in the coordinate
	 * @param file the Column used in the coordinate
	 * @return a new instance of a Coordinate
	 */
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
	
	@Override
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
	
	/**
	 * A function to see if a coordinate is orthogonal to another.
	 * @param c the coordinate we are testing is orthogonal
	 * @return a boolean is true if the coordinate is on the same axis (x, or y).
	 */
	public boolean isOrthogonal(XiangqiCoordinate c)
	{
		return c.getRank() == rank || c.getFile() == file;
	}
	
	/**
	 * A function to see if a coordinate is diagonal to another.
	 * @param c the coordinate we are testing is diagonal
	 * @return a boolean is true if the coordinate is on the has a slope of +-1.
	 */
	public boolean isDiagonal(XiangqiCoordinate c)
	{
		if (!isOrthogonal(c))
			return Math.abs( rank- c.getRank()) / Math.abs(file-c.getFile())==1;
		
		return false;
	}
	
	/**
	 * A function that computes the distance from one coordinate to another.
	 * @param c the coordinate we are computing the distance to
	 * @return an integer representing the distance between two points.
	 */
	public int distanceTo(XiangqiCoordinate c)
	{
		return Math.abs( rank - c.getRank() ) +  Math.abs(file - c.getFile()) ;
	}
	
	/**
	 * A function to see if a coordinate is farther down the ranks..
	 * @param c the coordinate we are testing is further forward.
	 * @return a boolean is true if the coordinate has a higher rank.
	 */
	public boolean isForward(XiangqiCoordinate c)
	{
		return c.getRank() > rank;
	}
	
}

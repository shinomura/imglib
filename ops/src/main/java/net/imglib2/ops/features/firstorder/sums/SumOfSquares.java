package net.imglib2.ops.features.firstorder.sums;

import java.util.Iterator;

import net.imglib2.ops.features.annotations.RequiredInput;
import net.imglib2.ops.features.datastructures.AbstractFeature;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;

public class SumOfSquares extends AbstractFeature
{
	@RequiredInput
	private Iterable< ? extends RealType< ? > > ii;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String name()
	{
		return "Sum of Squares";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SumOfSquares copy()
	{
		return new SumOfSquares();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DoubleType recompute()
	{
		final Iterator< ? extends RealType< ? > > it = ii.iterator();
		double result = 0.0;

		while ( it.hasNext() )
		{
			final double val = it.next().getRealDouble();
			result += ( val * val );
		}
		return new DoubleType( result );
	}

}
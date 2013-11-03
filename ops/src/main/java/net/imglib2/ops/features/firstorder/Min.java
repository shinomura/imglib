package net.imglib2.ops.features.firstorder;

import java.util.Iterator;

import net.imglib2.ops.features.annotations.RequiredInput;
import net.imglib2.ops.features.datastructures.AbstractFeature;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;

public class Min extends AbstractFeature
{

	@RequiredInput
	private Iterable< ? extends RealType< ? >> ii;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String name()
	{
		return "Minimum";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Min copy()
	{
		return new Min();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DoubleType recompute()
	{
		System.out.println( "CALCULATED ONCE" );
		double min = Double.MAX_VALUE;

		final Iterator< ? extends RealType< ? > > it = ii.iterator();
		while ( it.hasNext() )
		{
			double val = it.next().getRealDouble();
			min = val < min ? val : min;
		}

		return new DoubleType( min );
	}
}
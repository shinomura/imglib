/**
 * Copyright (c) 2009--2010, Stephan Preibisch & Stephan Saalfeld
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.  Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials
 * provided with the distribution.  Neither the name of the Fiji project nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @author Stephan Preibisch & Stephan Saalfeld
 */
package mpicbg.imglib.interpolation;

import mpicbg.imglib.image.Image;
import mpicbg.imglib.location.Localizable;
import mpicbg.imglib.location.Positionable;
import mpicbg.imglib.location.RasterLocalizable;
import mpicbg.imglib.location.RasterPositionable;
import mpicbg.imglib.location.VoidPositionable;
import mpicbg.imglib.outofbounds.OutOfBoundsStrategyFactory;
import mpicbg.imglib.type.Type;

abstract public class AbstractInterpolator<T extends Type<T>> implements Interpolator<T>
{
	final protected InterpolatorFactory<T> interpolatorFactory;
	final protected OutOfBoundsStrategyFactory<T> outOfBoundsStrategyFactory;
	final protected Image<T> img;

	// the location of the interpolator in the image
	/* TODO Better double[] ? */
	final protected float[] position;

	/**
	 * the number of dimensions 
	 */
	final protected int numDimensions;
	
	protected RasterPositionable linkedRasterPositionable = VoidPositionable.getInstance();
	protected Positionable linkedPositionable = VoidPositionable.getInstance();
	
	protected AbstractInterpolator(
			final Image<T> img,
			final InterpolatorFactory<T> interpolatorFactory,
			final OutOfBoundsStrategyFactory<T> outOfBoundsStrategyFactory )
	{
		this.interpolatorFactory = interpolatorFactory;
		this.outOfBoundsStrategyFactory = outOfBoundsStrategyFactory;
		this.img = img;
		
		numDimensions = img.numDimensions();
	
		position = new float[ numDimensions ];
	}
	
	@Override
	final public int numDimensions(){ return numDimensions; }
	
	@Override
	@Deprecated
	final public T getType(){ return type(); }

	/**
	 * Returns the typed interpolator factory the Interpolator has been instantiated with.
	 * 
	 * @return - the interpolator factory
	 */
	@Override
	public InterpolatorFactory<T> getInterpolatorFactory(){ return interpolatorFactory; }
	
	/**
	 * Returns the {@link OutOfBoundsStrategyFactory} used for interpolation
	 * 
	 * @return - the {@link OutOfBoundsStrategyFactory}
	 */
	@Override
	public OutOfBoundsStrategyFactory<T> getOutOfBoundsStrategyFactory(){ return outOfBoundsStrategyFactory; }
	
	/**
	 * Returns the typed image the interpolator is working on
	 * 
	 * @return - the image
	 */
	@Override
	public Image<T> getImage() { return img; }

	
	/* Localizable */
	
	@Override
	public double getDoublePosition( final int dim ){ return position[ dim ]; }

	@Override
	public float getFloatPosition( final int dim ){ return position[ dim ]; }

	@Override
	public String getLocationAsString()
	{
		final StringBuffer pos = new StringBuffer( "(" );
		pos.append( position[ 0 ] );
		
		for ( int d = 1; d < numDimensions; d++ )
			pos.append( ", " ).append( position[ d ] );
		
		pos.append( ")" );
		
		return pos.toString();
	}

	@Override
	public void localize( final float[] position )
	{
		for ( int d = 0; d < position.length; ++d )
			position[ d ] = this.position[ d ];
	}

	@Override
	public void localize( final double[] position )
	{
		for ( int d = 0; d < position.length; ++d )
			position[ d ] = this.position[ d ];
	}
	
	
	/* Positionable */

	@Override
	public void move( final double distance, final int dim )
	{
		position[ dim ] += distance;
		linkedPositionable.move( distance, dim );
	}
	
	@Override
	public void move( final float distance, final int dim )
	{
		position[ dim ] += distance;
		linkedPositionable.move( distance, dim );
	}

	@Override
	public void moveTo( final double[] position )
	{
		for ( int i = 0; i < numDimensions; ++i )
		{
			final double di = position[ i ] - this.position[ i ];
			if ( di == 0 ) continue;
			move( di, i );
		}
	}

	@Override
	public void moveTo( final float[] position )
	{
		for ( int i = 0; i < numDimensions; ++i )
		{
			final float di = position[ i ] - this.position[ i ];
			if ( di == 0 ) continue;
			move( di, i );
		}
	}

	@Override
	public void moveTo( final Localizable localizable )
	{
		localizable.localize( position );
		linkedPositionable.moveTo( localizable );
	}

	@Override
	public void setPosition( final Localizable localizable )
	{
		localizable.localize( position );
		linkedPositionable.setPosition( localizable );
	}

	@Override
	public void setPosition( final float[] position )
	{
		for ( int i = 0; i < numDimensions; ++i )
			this.position[ i ] = position[ i ];
		linkedPositionable.setPosition( position );
	}

	@Override
	public void setPosition( final double[] position )
	{
		for ( int i = 0; i < numDimensions; ++i )
			this.position[ i ] = ( float )position[ i ];
		linkedPositionable.setPosition( position );
	}

	@Override
	public void setPosition( final float position, final int dim )
	{
		this.position[ dim ] = position;
		linkedPositionable.setPosition( position, dim );
	}

	@Override
	public void setPosition( final double position, final int dim )
	{
		this.position[ dim ] = ( float )position;
		linkedPositionable.setPosition( position, dim );
	}
	
	
	/* RasterPositionable */

	@Override
	public void bck( final int dim )
	{
		position[ dim ] -= 1;
		linkedRasterPositionable.bck( dim );
	}

	@Override
	public void fwd( final int dim )
	{
		position[ dim ] += 1;
		linkedRasterPositionable.fwd( dim );
	}

	@Override
	public void move( final int distance, final int dim )
	{
		position[ dim ] += distance;
		linkedRasterPositionable.move( distance, dim );
	}

	@Override
	public void move( final long distance, final int dim )
	{
		position[ dim ] += distance;
		linkedRasterPositionable.move( distance, dim );
	}

	@Override
	public void moveTo( final RasterLocalizable localizable )
	{
		localizable.localize( position );
		linkedRasterPositionable.moveTo( localizable );
	}

	@Override
	public void moveTo( final int[] position )
	{
		for ( int i = 0; i < numDimensions; ++i )
			this.position[ i ] = position[ i ];
		linkedPositionable.moveTo( position );
	}

	@Override
	public void moveTo( final long[] position )
	{
		for ( int i = 0; i < numDimensions; ++i )
			this.position[ i ] = position[ i ];
		linkedPositionable.setPosition( position );
	}

	@Override
	public void setPosition( final RasterLocalizable localizable )
	{
		localizable.localize( position );
		linkedRasterPositionable.setPosition( localizable );
	}

	@Override
	public void setPosition( final int[] position )
	{
		for ( int i = 0; i < numDimensions; ++i )
			this.position[ i ] = position[ i ];
		linkedPositionable.setPosition( position );
	}

	@Override
	public void setPosition( final long[] position )
	{
		for ( int i = 0; i < numDimensions; ++i )
			this.position[ i ] = position[ i ];
		linkedPositionable.setPosition( position );
	}

	@Override
	public void setPosition( final int position, final int dim )
	{
		this.position[ dim ] = position;
		linkedRasterPositionable.setPosition( position, dim );
	}

	@Override
	public void setPosition( final long position, final int dim )
	{
		this.position[ dim ] = position;
		linkedRasterPositionable.setPosition( position, dim );
	}
	
	
	/* LinkablePositionable */
	
	@Override
	public void linkPositionable( final Positionable positionable )
	{
		linkedRasterPositionable = positionable;
		linkedPositionable = positionable;
	}

	@Override
	public Positionable unlinkPositionable()
	{
		final Positionable positionable = linkedPositionable;
		linkedPositionable = VoidPositionable.getInstance();
		linkedRasterPositionable = VoidPositionable.getInstance();
		return positionable;
	}

	@Override
	public void linkRasterPositionable( final RasterPositionable rasterPositionable )
	{
		linkedRasterPositionable = rasterPositionable;
	}

	@Override
	public RasterPositionable unlinkRasterPositionable()
	{
		final RasterPositionable rasterPositionable = linkedRasterPositionable;
		linkedRasterPositionable = VoidPositionable.getInstance();
		return rasterPositionable;
	}
}

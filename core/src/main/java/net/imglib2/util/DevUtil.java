/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2014 Stephan Preibisch, Tobias Pietzsch, Barry DeZonia,
 * Stephan Saalfeld, Albert Cardona, Curtis Rueden, Christian Dietz, Jean-Yves
 * Tinevez, Johannes Schindelin, Lee Kamentsky, Larry Lindsey, Grant Harris,
 * Mark Hiner, Aivar Grislis, Martin Horn, Nick Perry, Michael Zinsmaier,
 * Steffen Jaensch, Jan Funke, Mark Longair, and Dimiter Prodanov.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imglib2.util;

import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.img.basictypeaccess.array.ByteArray;
import net.imglib2.img.basictypeaccess.array.FloatArray;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.real.FloatType;

/**
 * Utility methods for developers
 *
 * Stephan Preibisch, Curtis Rueden
 *
 *
 */
@Deprecated
final public class DevUtil
{
	private DevUtil() {}
	
	/**
	 * This method is deprecated. Use {@link ArrayImgs#bytes(byte[], long...)} instead.
	 * Creates an {@link ArrayImg} of UnsignedByteType from a java byte array by wrapping it
	 *
	 * @param data - the array
	 * @param dim - the dimensionality
	 *
	 * @return the instance of {@link ArrayImg} using the given byte array
	 */
	@Deprecated
	final public static ArrayImg<UnsignedByteType, ByteArray> createImageFromArray( final byte[] data, final long[] dim )
	{
		final ByteArray byteArray = new ByteArray( data );
		final ArrayImg<UnsignedByteType, ByteArray> array = 
			new ArrayImg<UnsignedByteType, ByteArray>( byteArray, dim, 1 );
			
		// create a Type that is linked to the container
		final UnsignedByteType linkedType = new UnsignedByteType( array );
		
		// pass it to the DirectAccessContainer
		array.setLinkedType( linkedType );
		
		return array;
	}

	/**
	 * This method is deprecated. Use {@link ArrayImgs#floats(float[], long...)} instead.
	 * Creates an {@link ArrayImg} of FloatType from a java float array by wrapping it
	 *
	 * @param data - the array
	 * @param dim - the dimensionality
	 *
	 * @return the instance of {@link ArrayImg} using the given float array
	 */
	@Deprecated
	final public static ArrayImg<FloatType,FloatArray> createImageFromArray( final float[] data, final long[] dim )
	{
		final FloatArray floatArray = new FloatArray( data );
		final ArrayImg<FloatType, FloatArray> array = 
			new ArrayImg<FloatType, FloatArray>( floatArray, dim, 1 );
			
		// create a Type that is linked to the container
		final FloatType linkedType = new FloatType( array );
		
		// pass it to the DirectAccessContainer
		array.setLinkedType( linkedType );
		
		return array;
	}
}

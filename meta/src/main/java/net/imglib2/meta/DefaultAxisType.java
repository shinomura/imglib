/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2013 Stephan Preibisch, Tobias Pietzsch, Barry DeZonia,
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
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of any organization.
 * #L%
 */

package net.imglib2.meta;

import java.io.Serializable;

/**
 * Default {@link AxisType} implementation.
 * 
 * @see Axes
 * @author Mark Hiner
 */
public class DefaultAxisType implements AxisType, Serializable,
	Comparable<AxisType>
{

	// -- Fields --

	private final String label;
	private final boolean spatial;

	// -- Constructors --

	/**
	 * Creates a non-spatial AxisType with the given label
	 */
	public DefaultAxisType(final String label) {
		this(label, false);
	}

	/**
	 * Creates a new AxisType with the given label and spatial status.
	 */
	public DefaultAxisType(final String label, final boolean spatial) {
		this.label = label;
		this.spatial = spatial;
	}

	// -- AxisType methods --

	@Override
	public String getLabel() {
		return label;
	}

	// -- Deprecated AxisType methods --

	@Deprecated
	@Override
	public boolean isXY() {
		return (this == Axes.X || this == Axes.Y);
	}

	@Deprecated
	@Override
	public boolean isSpatial() {
		return spatial;
	}

	// -- Comparable methods --

	@Override
	public int compareTo(AxisType other) {
		return getLabel().compareTo(other.getLabel());
	}

	// -- Object methods --

	@Override
	public String toString() {
		return label;
	}
}

package mpicbg.imglib.container.newcell;

import mpicbg.imglib.container.array.Array;
import mpicbg.imglib.container.basictypecontainer.DataAccess;
import mpicbg.imglib.type.NativeType;
import mpicbg.imglib.type.Type;

public class Cell< T extends NativeType< T >, A extends DataAccess > extends Array< T, A > implements Type< Cell< T, A > > 
{
	final long[] offset;
	
	public Cell( final T type, final A data, final long[] dim, final int entitiesPerPixel )
	{
		super( type, data, dim, entitiesPerPixel );
		offset = new long[ dim.length ];
	}

	@Override
	public Cell<T, A> copy()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set( Cell<T, A> c )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cell<T, A> createVariable()
	{
		// TODO Auto-generated method stub
		return null;
	}
}

package clojure.core.matrix.random;

import java.util.Random;

import clojure.lang.ASeq;
import clojure.lang.IPersistentCollection;
import clojure.lang.IPersistentMap;
import clojure.lang.ISeq;
import clojure.lang.Obj;

public class RandomSeq extends ASeq {
	
	private class Cursor extends ASeq {
		private int pos;
		
		public Cursor(int pos) {
			this.pos=pos;
		}
		
		@Override
		public int count() {
			throw new UnsupportedOperationException("RandomSeq has infinite elements");
		}

		@Override
		public IPersistentCollection empty() {
			return null;
		}

		@Override
		public Object first() {
			return data[pos];
		}

		@Override
		public ISeq next() {
			int npos=pos+1;
			if (npos<data.length) return new Cursor(npos);
			return nextChunk();
		}

		@Override
		public Obj withMeta(IPersistentMap meta) {
			throw new UnsupportedOperationException("RandomSeq does not support metadata");
		}

	}

	private static final int CHUNK_SIZE=16;
	
	final double[] data;
	Object rand;
	
	private RandomSeq(double[] data, Random random) {
		this.data=data;
		this.rand=random;
	}
	
	public RandomSeq(Random random) {
		this(produce(random,CHUNK_SIZE),random);
	}
	
	public static double[] produce(Random random, int count) {
		double[] data=new double[count];
		for (int i=0; i<count; i++) {
			data[i]=random.nextDouble();
		}
		return data;
	}
	
	@Override
	public int count() {
		throw new UnsupportedOperationException("RandomSeq has infinite elements");
	}

	@Override
	public IPersistentCollection empty() {
		return null;
	}

	@Override
	public Object first() {
		return data[0];
	}
	
	private synchronized RandomSeq nextChunk() {
		if (rand instanceof RandomSeq) return (RandomSeq)rand;
		
		Random random=(Random)rand;
		
		RandomSeq result= new RandomSeq(produce(random,CHUNK_SIZE),random);
		rand=result;
		return result;
	}

	@Override
	public ISeq next() {
		return new Cursor(1);
	}
	
	@Override
	public Obj withMeta(IPersistentMap meta) {
		throw new UnsupportedOperationException("RandomSeq does not support metadata");
	}

}

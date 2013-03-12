import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import colibri.lib.Concept;
import colibri.lib.HybridLattice;
import colibri.lib.Lattice;
import colibri.lib.Relation;
import colibri.lib.Traversal;
import colibri.lib.TreeRelation;



public class FormalConceptAnalysis {
	Vector<File> objects;
	Vector<Vector<String>> attributes;
	/**
	 * @param args
	 */
	private static final long MEGABYTE = 1024L * 1024L;
  
	public static long bytesToMegabytes(long bytes) {
	    return bytes / MEGABYTE;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		FormalConceptAnalysis fca=new FormalConceptAnalysis();
		Extract1 e1=new Extract1();
		fca.objects=e1.getFiles();
		fca.attributes=e1.getIdentifiers();
		Relation rel = new TreeRelation();
		//System.out.println(fca.objects.size());
		//System.out.println(fca.attributes.size());
		for(int i=0;i<fca.objects.size();i++){
			for(int j=0;j<fca.attributes.get(i).size();j++){
				rel.add(fca.objects.get(i).getName(),fca.attributes.get(i).get(j));
			}
		}
		Lattice lattice = new HybridLattice(rel);
		//get the iterator
		Iterator<Concept> it = lattice.conceptIterator(Traversal.TOP_OBJSIZE);
		while(it.hasNext()) {
			Concept c = it.next();
			System.out.println(c.toString());
			}
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapesed Time: "+elapsedTime);
	    Runtime runtime = Runtime.getRuntime();
	    //runtime.gc();
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    //System.out.println("Used memory is bytes: " + memory);
	    System.out.println("Used memory is megabytes: "+ bytesToMegabytes(memory));
	}
}
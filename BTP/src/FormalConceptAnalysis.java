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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FormalConceptAnalysis fca=new FormalConceptAnalysis();
		Extract1 e1=new Extract1();
		fca.objects=e1.getFiles();
		fca.attributes=e1.getIdentifiers();
		Relation rel = new TreeRelation();
		System.out.println(fca.objects.size());
		System.out.println(fca.attributes.size());
		for(int i=0;i<fca.objects.size();i++){
			for(int j=0;j<fca.attributes.get(i).size();j++){
				rel.add(fca.objects.get(i),fca.attributes.get(i).get(j));
			}
		}
		Lattice lattice = new HybridLattice(rel);
		//get the iterator
		Iterator<Concept> it = lattice.conceptIterator(Traversal.TOP_OBJSIZE);
		while(it.hasNext()) {
			Concept c = it.next();
			if (c.getObjects().size() >= 10) 
				System.out.println(c);
		}

	}

}

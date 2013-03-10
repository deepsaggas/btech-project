import java.util.Vector;

import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;

import uk.ac.open.crc.intt.IdentifierNameTokeniser;
import uk.ac.open.crc.intt.IdentifierNameTokeniserFactory;

public class IdentifierTokeniser {
	Vector<String> identifiers=new Vector<String>();
	Vector<String> splittedIdentifiers=new Vector<String>();
	
	public IdentifierTokeniser(Vector<String> identifiers){
		this.identifiers=identifiers;
	}
	
	public Vector<String> tokenize(){
		
		for(int j=0;j<identifiers.size();j++){
			String identifier = identifiers.elementAt(j);
		    //System.out.println((j+1) + " : " + identifier); 
		    IdentifierNameTokeniserFactory factory = new IdentifierNameTokeniserFactory();
		    IdentifierNameTokeniser tokeniser = factory.create();
		    String[] tokens = tokeniser.tokenise(identifier);
		    for (int k = 0; k < tokens.length; k++) {
		    	String token = PorterStemmerTokenizerFactory.stem(tokens[k].trim().toLowerCase());
		        splittedIdentifiers.addElement(token.toLowerCase());
		        //System.out.println("Split " + k + " : " + tokens[k].trim());     
		    }
		}
		return splittedIdentifiers;
	}
}

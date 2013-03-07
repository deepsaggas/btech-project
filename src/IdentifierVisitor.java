import org.eclipse.jdt.core.dom.*;
import java.util.*;

public class IdentifierVisitor extends ASTVisitor {
	
	private Vector<String> identifiers = new Vector<String>();
	//org.eclipse.jdt.core.dom.A
	private Vector<String> methods = new Vector<String>();
	private Vector<String> classes = new Vector<String>();
	private Vector<String> variables = new Vector<String>();

	//private Vector<String>  = new Vector<String>();


	public Vector<String> getIdentifiers(){
		return identifiers; 
	}
	public Vector<String> getMethods(){
		return methods; 
	}
	public Vector<String> getClasses(){
		return classes; 
	}
	public Vector<String> getVariables(){
		return variables; 
	}
	
	public boolean visit(SimpleName sn){
		//System.out.println("-----------------------------------------");
		String identifier = sn.getFullyQualifiedName();
		//System.out.println(sn.getParent().getNodeType());
		//sn.getNodeType();
		//sn.getNodeType()==ASTNode.VARIABLE_DECLARATION_FRAGMENT
		//System.out.println("IDENTIFIER : " + identifier);
		identifiers.addElement(identifier);
		//System.out.println("-----------------------------------------");
		return true;
	}
	 public boolean visit(TypeDeclaration sn)
	{	
		//this.visit(node)
		//System.out.println("-----------------------------------------");
		String identifier = sn.getName().toString();
		//sn.get
		//System.out.println("IDENTIFIER : " + identifier);
		classes.addElement(identifier);
		//System.out.println("-----------------------------------------");
		return true;
	}
	 public boolean visit(VariableDeclarationFragment sn)
		{	
			//this.visit(node)
			//System.out.println("-----------------------------------------");
			String identifier = sn.getName().toString();
			//sn.get
			//System.out.println("IDENTIFIER : " + identifier);
			variables.addElement(identifier);
			//System.out.println("-----------------------------------------");
			return true;
		}
	public boolean visit(MethodDeclaration sn)
	{
		//this.visit(node)
		//System.out.println("-----------------------------------------");
		
		String identifier = sn.getName().toString();
		//System.out.println("IDENTIFIER : " + identifier);
		methods.addElement(identifier);
		//System.out.println("-----------------------------------------");
		return true;
	}
}
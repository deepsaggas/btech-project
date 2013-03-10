import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Extract1{

	Vector<File> javaFiles;
	Vector<String> javaFileContent;
	Vector<String> classes;
	Vector<String> methods;
	Vector<String> variables;
	Vector<String> identifiers;
	Vector<String> splittedIdentifiers;
	DirectoryStructure test;
	Vector<Vector<String>> attributes;
	
	public Extract1(){
		String path="C:\\Users\\Yash\\Desktop\\BTP Experimental Dataset\\apache-tomcat\\JavaFiles\\";//in.nextLine();
		javaFileContent = new Vector<String>();
	    javaFiles= new Vector<File>();
	    attributes=new Vector<Vector<String>>();
		test =new DirectoryStructure(path);
		try {
			extract();
		} catch (IOException e) {
		}

	}

	
	public Vector<Vector<String>> getIdentifiers(){
		return attributes;
	}
	
	public Vector<File> getFiles(){
		return javaFiles;
	}
	
	public void extract() throws IOException{
		javaFiles.addAll(test.getFiles());
		for(int i=0;i<javaFiles.size();i++){

			File f = javaFiles.elementAt(i);
			ASTParser parser = ASTParser.newParser(AST.JLS3);
			String content = FileUtils.readFileToString(f);

			//String content = readFile(f);
			javaFileContent.addElement(content);

			//1	System.out.println("------------------------------------------------------");
			//1	System.out.println("Processing File : " + (i+1) + " : " + f.getName());
			//System.out.println(content);
			parser.setSource(content.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			IdentifierVisitor iv = new IdentifierVisitor();
			cu.accept(iv);
			classes = iv.getClasses();
			methods = iv.getMethods();
			variables = iv.getVariables();
			identifiers = iv.getIdentifiers();
			//System.out.println("Identifiers:- "+identifiers.toString());
			//System.out.println("Classes:- " + classes.toString());
			//System.out.println("Methods:- "+methods.toString());
			//System.out.println("Variables:- "+variables.toString());
			//System.out.println(ASTNode.TYPE_DECLARATION);
			//55 40
			IdentifierTokeniser it=new IdentifierTokeniser(identifiers);
			splittedIdentifiers=it.tokenize();
			attributes.addElement(splittedIdentifiers);
			//System.out.println("Split Tokens :- "+splittedIdentifiers.toString());
		}
	}
}
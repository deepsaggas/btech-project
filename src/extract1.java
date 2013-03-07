import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

class extract1
{
	public static void main(String args[]) throws IOException
	{
		String path="F:\\IIIT-D\\BTP_Project\\BTP_JAVA_FileParsing\\JavaFiles\\";//in.nextLine();
		DirectoryStructure test =new DirectoryStructure(path);
		Vector<File> javaFiles= new Vector<File>();
		
		javaFiles.addAll(test.getFiles());
		//javaFiles.add(new File("F:\\IIIT-D\\BTP_Project\\UR\\src\\Min_1.java"));
		Vector<String> javaFileContent = new Vector<String>();
		for(int i=0;i<javaFiles.size();i++)
		{

			File f = javaFiles.elementAt(i);
			ASTParser parser = ASTParser.newParser(AST.JLS3);
			String content = FileUtils.readFileToString(f);

			//String content = readFile(f);
			javaFileContent.addElement(content);

			System.out.println("------------------------------------------------------");
			System.out.println("Processing File : " + (i+1) + " : " + f.getName());
			//System.out.println(content);
			parser.setSource(content.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			IdentifierVisitor iv = new IdentifierVisitor();
			cu.accept(iv);
			Vector<String> classes = iv.getClasses();//
			Vector<String> methods = iv.getMethods();//
			Vector<String> variables = iv.getVariables();//
			Vector<String> identifiers = iv.getIdentifiers();//
			//System.out.println("Identifiers:- "+identifiers.toString());
			System.out.println("Classes:- " + classes.toString());
			System.out.println("Methods:- "+methods.toString());
			System.out.println("Variables:- "+variables.toString());
			//System.out.println(ASTNode.TYPE_DECLARATION);
			//55 40
		}
		
		
	}
}
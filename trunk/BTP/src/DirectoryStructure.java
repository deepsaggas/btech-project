import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class DirectoryStructure {

	/**
	 * @param args
	 */
	static public String path;

	public DirectoryStructure(String path)
	{
		this.path=path;
		directory();
	}

	ArrayList<File> files=new ArrayList<File>();
	public void directory() 
	{
		File k = new File(path);
		File list[]=k.listFiles();
		System.out.println(path);
		UpdateDirectoryStructure(list);

	}

	public String toString()
	{
		String data="";
		for(int i=0;i<files.size();i++)
		{  
			if(files.get(i).isFile()){
				System.out.println(files.get(i).getName());
				System.out.print(files.get(i).getAbsolutePath()+"    ");
				System.out.print(files.get(i).length()+"   ");
				System.out.print(files.get(i).lastModified()+"   ");
				long datetime=files.get(i).lastModified();
				Date d = new Date(datetime);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				String dateString = sdf.format(d);
				//System.out.print(dateString+"\n");
				//name=files.get(i).getName();
				//absolutePath=files.get(i).getAbsolutePath();
				//size=files.get(i).length();
				//last_modified=dateString;
			}
			data=data+"\n"+files.get(i).getAbsolutePath();
		}	
		return data;
	}

	private void UpdateDirectoryStructure(File list[]){

		for(int i=0;i<list.length;i++)
		{
			files.add(list[i]);
			if(list[i].isDirectory())
			{
				UpdateDirectoryStructure(list[i].listFiles());
			}

		}

	}

	public ArrayList<File> getFiles() {
		return files;
	}
	public ArrayList<File> getFiles(String filter)//(eg. java, asp ,net)
	{
		ArrayList<File> temp =new ArrayList<File>();
		for(int i=0;i<files.size();i++)
		{
			File k = files.get(i);
			if(k.getName().endsWith(filter) && k.isFile())
			{
				System.out.println(k.getName());
				temp.add(k);
			}
		}
		return temp;
	}
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Starting Path");//eg."F:\TV-Shows"
		DirectoryStructure test =new DirectoryStructure(path);
		/*String temp="C:\\JavaFiles\\";
		ArrayList<File> files=test.getFiles("java");
		for(int i=0;i<files.size();i++)
		{
			File k =files.get(i);
			temp="C:\\JavaFiles\\";
			temp=temp+k.getName();
			File d = new File(temp);
			try {
				copyFile(k,d);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
				
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if(!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();

			// previous code: destination.transferFrom(source, 0, source.size());
			// to avoid infinite loops, should be:
			long count = 0;
			long size = source.size();              
			while((count += destination.transferFrom(source, count, size-count))<size);
		}
		finally {
			if(source != null) {
				source.close();
			}
			if(destination != null) {
				destination.close();
			}
		}
	}
}
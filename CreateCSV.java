package JDBC;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
public class CreateCSV
{
    private String fileName;
    private boolean append;
    private PrintWriter out;    
    public CreateCSV(String fileName, boolean append)
    {
        this.fileName = fileName;
        this.append = append;
    }   
    public void openFile()
    {
        try{
            out = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName),append)));
         }catch(Exception e){
            e.printStackTrace();
	 }
    }
    public void writeFile(String currentLine)
    {
                out.println(currentLine);  
    }
    public void closeFile()
    {   
        out.flush();
        out.close();
    }   
    /* public void spoolFile(ArrayList<String> lines){
    
    for(String line : lines )
        writeFile(line);
    
}

public void spoolFile(ArrayList<String> lines, String append){

    for(String line : lines ){
        String nLine = append +line;
        writeFile(nLine);
    }
}
*/  
}

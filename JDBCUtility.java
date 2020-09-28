import java.util.*;
public class JDBCUtility{
	private String url,userID, pwd,header,fileName;
	private Connection con;
public JDBCUtility(String url, String userID, String pwd) throws Exception{	
		this.url = url;
		this.userID = userID;
		this.pwd = pwd;
		setConnection();
	}
	public void setHeader(String header){
		
		this.header = header;
	}
	public void setFileName(String fileName){
		
		this.fileName = fileName;
	}
public ArrayList collectData(String sql) throws Exception{
		ArrayList<String> al = new ArrayList();
		Statement smt = con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		ResultSetMetaData rsmd=rs.getMetaData();
		int noOfColumns = rsmd.getColumnCount();
		while (rs.next()){
			
			StringBuffer row = new StringBuffer();
			
			for (int i=1; i< noOfColumns;i++){
				
				row.append(rs.getString(i).trim());row.append(",");					
			}
			row.append (rs.getString(noOfColumns).trim());
			al.add(row.toString());
		}
		rs.close();
		smt.close();
		return al;	
	}
public int collectInt(String sql) throws Exception{
	int var=0;String al;
		Statement smt = con.createStatement();
		
		ResultSet rs = smt.executeQuery(sql);
		ResultSetMetaData rsmd=rs.getMetaData();
		int noOfColumns = rsmd.getColumnCount();
		while (rs.next()){
			
			StringBuffer row = new StringBuffer();
			
			for (int i=1; i< noOfColumns;i++){
				
				row.append(rs.getString(i).trim());row.append(",");					
			}
			row.append (rs.getString(noOfColumns).trim());
			al=(row.toString());
			var=Integer.parseInt(al);
		}
		rs.close();
		smt.close();
		return var;	
	}
public String collectString(String sql) throws Exception
{	
		String al="0",tmp;int i=0;
		Statement smt = con.createStatement();
		ResultSet rs = smt.executeQuery(sql);
		ResultSetMetaData rsmd=rs.getMetaData();
		int noOfColumns = rsmd.getColumnCount();
		while (rs.next())
			{
				
				StringBuffer row = new StringBuffer();
				
				for (i=1; i< noOfColumns;i++){
					
					row.append(rs.getString(i));row.append(",");	
					
				}
				
				row.append (rs.getString(noOfColumns));
				al=row.toString();
				
		}
		rs.close();
		smt.close();
		return al;	
	}
	private void setConnection() throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 con =DriverManager.getConnection(url,userID, pwd);
		
	}
	
}

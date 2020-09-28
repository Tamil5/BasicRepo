
package OIC_Inven;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BossATP_Adjustment 
{
	public static String TimeStmp,path;
	public static void main(String[] args)throws IOException,InterruptedException
	{
		ReadSku();
		System.out.println("Completed..!!!");
	}	
	public static void ReadSku() throws IOException, InterruptedException
	{
		path="F:\\sku1.txt";  //Exception Report
		File inputFile=new File(path);int i=0;String val;
		BufferedReader br=new BufferedReader(new FileReader(inputFile));
		while((val=br.readLine())!=null)
		{	
			if(i%50==0)
			{
				System.out.println("SleepTime");
				Thread.sleep(1000);
			}
			String arr[]=val.split(",");
			String sku=arr[0];
			Boss_func(sku,"20000");
			i++;
		}
		System.out.println("Completed...!");
	}
	public static void timeStamp()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Calendar cal = Calendar.getInstance();
	
		cal.add(Calendar.HOUR, -9);
		cal.add(Calendar.MINUTE, -30);
		Date Ts=cal.getTime();
		TimeStmp=dateFormat.format(Ts);
		//TimeStmp="2019-01-24 20:41:47.047";
	}
	public static void Boss_func(String sku,String BossAtp) throws IOException
	{
		try
		{
		timeStamp();
		String url="http://internal-blue-ocf-oic-dev-015.apps.gcpusc1-b.lle.ocf.kohls.com/oic/atp/enterprise/bossatp";//rel02
		URL u = new URL(url);
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
		String RegInven="{\r\n" + 
				"\"itemID\": \""+sku+"\",\r\n" + 
				"\"bossAtp\":"+BossAtp+",\r\n" + 
				"\"timestamp\": \""+TimeStmp+" CDT\"\r\n" + 
				"}";
		byte[] online=RegInven.getBytes();		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
 	    wr.write(online); 
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while((inputLine = in.readLine()) != null) 
		{
			response.append(inputLine);
		}
		in.close();
		String arr[]=response.toString().split(",");
		if(arr[0].equals("{\"status\":200"))
		{
			System.out.println("Success      : "+sku);
		}
		else
		{
			System.out.println("Not completed: "+sku);
		}	
	}
	catch(Exception E)
	{
		System.out.println("Error..!!"+E);
	}
	}
}

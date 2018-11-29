/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
import java.util.Scanner;
public class LogEntry
{
	private String ipAdress;
	private String identity;
	private String userID;
	private String requestDate;
	private String request;
	private int statusCode;
	private int byteSize;	
	public LogEntry()
	{
		this(null,null,null,null,null,0,0);		
	}
	public LogEntry(String ipAdress,String identity,String userID,String requestDate,String request,int statusCode,int byteSize)
	{
		this.ipAdress=ipAdress;	
		this.identity=identity;
		this.userID=userID;
		this.requestDate=requestDate;
		this.request=request;
		this.statusCode=statusCode;
		this.byteSize=byteSize;
	}
	public String getIPAdress()
	{
		return ipAdress;
	}
	public String getIdentity()
	{
		return identity;
	}
	public String getUserID()
	{
		return userID;
	}
	public String getRequestDate()
	{
		return requestDate;
	}
	public String getRequest()
	{
		return request;
	}
	public int getStatusCode()
	{
		return statusCode;
	}
	public int getByteSize()
	{
		return byteSize;
	}
	@Override
	public String toString()
	{
		return ipAdress + " " + identity + " " + userID + " " + requestDate + " " + request + " " + statusCode + " " + byteSize;
	}
}
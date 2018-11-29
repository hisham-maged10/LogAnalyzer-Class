/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import java.util.Collections;
import java.util.Arrays;
public class LogAnalyzer
{
	private ArrayList<LogEntry> records;
	private HashSet<String> uniqueIPs;
	private HashMap<String,Integer> ipVisits;
	private File file;
	public LogAnalyzer()
	{	
		this(null);
	}
	public LogAnalyzer(File file)
	{
		this.file=(file==null)?getFile():file;
		records=new ArrayList<LogEntry>();
		uniqueIPs=new HashSet<String>();
		readFile();
		fillIPVisits();
	}
	public void readFile()
	{
		try(Scanner input=new Scanner(file);)
		{
			while(input.hasNext())
			{
				records.add(new LogEntry(input.next(),input.next(),input.next(),input.next()+" ".concat(input.next()),input.next()+" ".concat(input.next()).concat(input.next()),input.nextInt(),input.nextInt()));
			}
		}catch(IOException ex){System.out.println("Error with File"); System.exit(0);}			
	}
	public File getFile()
	{
		JFileChooser chooser=new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(new File("."));
		try
		{
			do
			{
				System.out.println("Please select a file!");
				chooser.showOpenDialog(null);
			}while(chooser.getSelectedFile()==null || !chooser.getSelectedFile().isFile());
		}catch(NullPointerException ex){System.out.println("Incorrect Response!");getFile();}
		return chooser.getSelectedFile();
	}
	public void findUniqueIPs()
	{
		makeHashSet();
	}
	public void printAllHigherThanNum(int num)
	{
		System.out.println("Entries that has status code higher than "+num+" :-");
		for(LogEntry e:records)
		{
			if(e.getStatusCode()>num)
				System.out.println(e);
			else
				continue;
		}
	}
	public int getUniqueIPCountInRange(int min,int max)
	{
		makeHashSet(min,max);
		return this.uniqueIPs.size();
	}
	public String getDateFormat(LogEntry log)
	{
		String[] date=log.getRequestDate().substring(log.getRequestDate().indexOf("[")+1,log.getRequestDate().lastIndexOf("/")).split("/");
		return date[1] + " " + date[0];
	}
	public void makeHashSet()
	{
		uniqueIPs=new HashSet<String>();
		for(LogEntry e:records)
		{
		if(!uniqueIPs.contains(e.getIPAdress()))
			uniqueIPs.add(e.getIPAdress());
		else
			continue;
		}
	}
	//overload
	public void makeHashSet(String someday)
	{
		uniqueIPs=new HashSet<String>();	
		for(LogEntry e:records)
		{
		if(!uniqueIPs.contains(e.getIPAdress()) && getDateFormat(e).equalsIgnoreCase(someday))
			uniqueIPs.add(e.getIPAdress());
		else
			continue;
		}	
		
	}
	//overload
	public void makeHashSet(int min, int max)
	{
		uniqueIPs=new HashSet<String>();	
		for(LogEntry e:records)
		{
		if(!uniqueIPs.contains(e.getIPAdress()) && e.getStatusCode()>=min && e.getStatusCode()<=max)
			uniqueIPs.add(e.getIPAdress());
		else
			continue;
		}	
		
	}
	public ArrayList<String> getUniqueIPVisitsOnDay(String someday)
	{
		if(!someday.matches("... ..")) return null;
		makeHashSet(someday);
		return new ArrayList<String>(uniqueIPs);
	}
	public void fillIPVisits()
	{
		ipVisits=new HashMap<String,Integer>();
		for(LogEntry e:records)
		{	
			if(!ipVisits.containsKey(e.getIPAdress()))
				ipVisits.put(e.getIPAdress(),1);
			else
				ipVisits.put(e.getIPAdress(),ipVisits.get(e.getIPAdress())+1);
		}
	}
	//overloaded
	public HashMap<String,Integer> fillIPVisits(ArrayList<String> ips)
	{
		ipVisits=new HashMap<String,Integer>();
		for(String e:ips)
		{	
			if(!ipVisits.containsKey(e))
				ipVisits.put(e,1);
			else
				ipVisits.put(e,ipVisits.get(e)+1);
		}
		return ipVisits;
	}
	public ArrayList<String> getIPsMostVisits(HashMap<String,Integer> map)
	{
		int max=getMostNumberVisitsByIP(map);
		ArrayList<String> result=new ArrayList<>();
		for(String e:map.keySet())
		{
			if(map.get(e)==max)
				result.add(e);		
		}
		return result;
	}
	public HashMap<String,ArrayList<String>> getIPsForDays()
	{
		HashMap<String,ArrayList<String>> map=new HashMap<>();
		for(LogEntry e:records)
		{
			if(!map.containsKey(getDateFormat(e).toLowerCase()))
				map.put(getDateFormat(e).toLowerCase(),new ArrayList<String>(Arrays.asList(e.getIPAdress())));
			else
			{
				ArrayList<String> arr=map.get(getDateFormat(e).toLowerCase());
				arr.add(e.getIPAdress());
				map.put(getDateFormat(e).toLowerCase(),arr);
			}
		}
		return map;
	}
	public String getDayWithMostIPVisits(HashMap<String,ArrayList<String>> map)
	{
		int max=0;
		String result=null;
		for(String e:map.keySet())
			if(map.get(e).size()>max)
			{	
				result=e;
				max=map.get(e).size();
			}
		return result;
	}
	public ArrayList<String> getIPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map,String someday)
	{
		if(!someday.matches("... ..")) return null;
		return getIPsMostVisits(fillIPVisits(getIPsForDays().get(someday.toLowerCase())));
	}
	public int getMostNumberVisitsByIP(HashMap<String,Integer> map)
	{
		return Collections.max(map.values());
	}
	public HashMap<String,Integer> getIPVisits()
	{
		return this.ipVisits;
	}
	public int getUniqueIPsCount()
	{
		findUniqueIPs();
		return uniqueIPs.size();
	}
	public void printAll()
	{
		System.out.println("Entries:-");
		for(LogEntry e:records)
			System.out.println(e);
	}	
}
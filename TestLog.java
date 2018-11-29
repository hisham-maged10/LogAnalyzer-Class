/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
import java.util.ArrayList;
import java.util.HashMap;
public class TestLog
{
	public static void main(String[] args)
	{
		testing();
	}
	public static void testing()
	{
		LogAnalyzer analyzer=new LogAnalyzer();
		analyzer.printAll();
		analyzer.printAllHigherThanNum(400);
		System.out.println("There are "+analyzer.getUniqueIPsCount()+" unique ips");
		ArrayList<String> arr=analyzer.getUniqueIPVisitsOnDay("sep 27");
		System.out.println("Unique IP Adresses for given date is "+ arr.size()+" :-");
		for(String e:arr)System.out.println(e);
		System.out.println("Unique IP Adresses' count for given range is "+ analyzer.getUniqueIPCountInRange(400,499));
		System.out.println("Visits per IP:-");
		HashMap<String,Integer> visits=analyzer.getIPVisits();
		System.out.println(visits);
		System.out.println("Max number of visits is: "+analyzer.getMostNumberVisitsByIP(visits));
		System.out.println("IP Addresses of max visits:- ");
		arr=analyzer.getIPsMostVisits(visits);
		for(String e:arr)System.out.println(e);
		System.out.println("Separated days IPs:-");
		HashMap<String,ArrayList<String>> daysIPs=analyzer.getIPsForDays();
		for(String e:daysIPs.keySet())
			System.out.println(e+" has "+ daysIPs.get(e).size() +" IP Addresses");
		System.out.println("Day with most visits is "+analyzer.getDayWithMostIPVisits(daysIPs));
		System.out.println("Most accessing ips on given day are:- ");
		arr=analyzer.getIPsWithMostVisitsOnDay(daysIPs,"sep 29");
		for(String e:arr)System.out.println(e);
		//LogEntry log=new LogEntry("127.0.0.1","-","frank","[10/Oct/2000:13:55:36 -0700]", "\"GET /apache_pb.gif HTTP/1.0\"",200,2326);
		//System.out.println(log);
	}
}
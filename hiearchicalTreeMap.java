import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
public class hiearchicalTreeMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws UnknownHostException, IOException{
		// TODO Auto-generated method stub
		String clustering_win_file = "C:\\My Projects\\Zheng - Clustering\\Data\\Treecutat1000levels.csv";
		File cluster_f = new File(clustering_win_file);
		FileReader cluster_f_reader = new FileReader(cluster_f);
		BufferedReader buf_cluster_reader = new BufferedReader(cluster_f_reader);
		String cluster_line = buf_cluster_reader.readLine();
		cluster_line = buf_cluster_reader.readLine();
		Hashtable<Integer,Integer> loan_amount = new Hashtable<Integer,Integer>();
		Hashtable<Integer,Integer> mixedscore = new Hashtable<Integer,Integer>();
		Hashtable<String,Integer> cluster_la = new Hashtable<String,Integer>();
		Hashtable<String,Integer> cluster_ms = new Hashtable<String,Integer>();
		Hashtable<String,Integer> cluster_sz = new Hashtable<String,Integer>();
		int index = 1;
		String loanAmount = "";
		String mixedScore = "";
		String key = "";
		int clusteridat2ndlevel = 0;
		int clusteridat1stlevel = 0;
		int appid = 0;
		int la;
		int ms;
		int sz;
        Integer la2;
        Integer ms2;
        Integer sz2;
		while(cluster_line!=null){
//			System.out.println(cluster_line);
			String[] tmp = cluster_line.split(",");
			appid = Integer.parseInt(tmp[0]);
			clusteridat1stlevel = Integer.parseInt(tmp[1]);
			clusteridat2ndlevel = Integer.parseInt(tmp[2]);
			la = Integer.parseInt(tmp[3]);
			ms = Integer.parseInt(tmp[5]);
			
			key ="\"app"+appid+"\",\"cid"+clusteridat2ndlevel+"@2\"";
			cluster_la.put(key, la);
			cluster_sz.put(key, 1);
			cluster_ms.put(key, ms);
			
			key = "\"cid"+clusteridat2ndlevel+"@2\""+",\"cid"+clusteridat1stlevel+"@1\"";
			la2 = cluster_la.get(key);
			la2=(la2!=null?(la2+la):la);
			cluster_la.put(key, la2);
			ms2 = cluster_ms.get(key);
			ms2=(ms2!=null?(ms2+ms):ms);
   			cluster_ms.put(key,ms2);
 			sz2 = cluster_sz.get(key);
			sz2=(sz2!=null?(sz2+1):1);
			cluster_sz.put(key, sz2);
			
			key = "\"cid"+clusteridat1stlevel+"@1\""+",\"toplevel\"";
			la2 = cluster_la.get(key);
			la2=(la2!=null?(la2+la):la);
			cluster_la.put(key, la2);
			ms2 = cluster_ms.get(key);
			ms2=(ms2!=null?(ms2+ms):ms);
   			cluster_ms.put(key,ms2);
 			sz2 = cluster_sz.get(key);
			sz2=(sz2!=null?(sz2+1):1);
			cluster_sz.put(key, sz2);
			
			key = "\"toplevel\",null";
			la2 = cluster_la.get(key);
			la2=(la2!=null?(la2+la):la);
			cluster_la.put(key, la2);
			ms2 = cluster_ms.get(key);
			ms2=(ms2!=null?(ms2+ms):ms);
   			cluster_ms.put(key,ms2);
 			sz2 = cluster_sz.get(key);
			sz2=(sz2!=null?(sz2+1):1);
			cluster_sz.put(key, sz2);
						
			cluster_line = buf_cluster_reader.readLine();
		}
		
//        Enumeration names = cluster_la.keys();
//        String name = "";
//        while(names.hasMoreElements())
//        {
//        	name = (String)names.nextElement();
//
//       		System.out.println("["+name+","+ cluster_la.get(name)+","+cluster_ms.get(name)/cluster_sz.get(name)+"],");
// 
//        }
		
        String[] keys = (String[]) cluster_la.keySet().toArray(new String[0]);  
        Arrays.sort(keys,Collections.reverseOrder());  
        for(String name : keys) {  
        	System.out.println("["+name+","+ cluster_la.get(name)+","+cluster_ms.get(name)*1000/cluster_sz.get(name)+"],");
        }  
		
	}

}

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
//the below code is used to for binary tree map
public class TreeMapUsingHash {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws UnknownHostException, IOException{
		// TODO Auto-generated method stub
		String clustering_win_file = "C:\\My Projects\\Zheng - Clustering\\Data\\ClusteringWindowInputClean.csv";
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
		while(cluster_line!=null)
		{
			String[] tmp = cluster_line.split(",");
			loanAmount = tmp[5];
			mixedScore = tmp[9];
			loanAmount = loanAmount.replaceAll("\"", "");
			mixedScore = mixedScore.replaceAll("\"", "");
			if(!mixedScore.equalsIgnoreCase("NA"))
			{
				loan_amount.put(index, Integer.parseInt(loanAmount));
				mixedscore.put(index,Integer.parseInt(mixedScore));
			}
			cluster_line = buf_cluster_reader.readLine();
			index++;
		}
		buf_cluster_reader.close();
		cluster_f_reader.close();
		
        String tacdata_file = "C:\\My Projects\\Zheng - Clustering\\Data\\merge0619.csv";
        File tac_f = new File(tacdata_file);
        FileReader tac_f_reader = new FileReader(tac_f);
        BufferedReader buf_tac_reader = new BufferedReader(tac_f_reader);
        String tacline = buf_tac_reader.readLine();
        tacline = buf_tac_reader.readLine();
        int id;
        int la;
        int ms;
        Integer la2;
        Integer ms2;
        Integer sz2;
        String key = "";
        String cid = "";
        String pid = "";
        ArrayList<String> data = new ArrayList<String>();
        while(tacline!=null)
        {
        	data.add(tacline);
        	tacline = buf_tac_reader.readLine();
        }
        buf_tac_reader.close();
        tac_f_reader.close();
    
        String tacline2 = "";
        for(int i=0;i<data.size();i++)
    	{
    		tacline = data.get(i);
    		String[] tmp = tacline.split(",");	
    		if(Integer.parseInt(tmp[1])<0)
    		{
    			id = -Integer.parseInt(tmp[1]);
    			key = "\"app"+id+"\", \"cluster"+ tmp[3]+"\"";
    			cid = tmp[3];
    			pid = tmp[4];
    			la = loan_amount.get(id);
    			ms = mixedscore.get(id);
    			cluster_la.put(key, la);
    			cluster_ms.put(key, ms);
    			cluster_sz.put(key, 1);
    			
       			key ="\"cluster"+cid+"\",\"cluster"+pid+"\"";
    			la2 = cluster_la.get(key);
    			la2=(la2!=null?(la2+la):la);
    			cluster_la.put(key, la2);
    			ms2 = cluster_ms.get(key);
    			ms2=(ms2!=null?(ms2+ms):ms);
    			cluster_ms.put(key,ms2);
    			sz2 = cluster_sz.get(key);
    			sz2=(sz2!=null?(sz2+1):1);
    			cluster_sz.put(key, sz2);
    			
    			while(Integer.parseInt(pid)!=0)
    			{
    			   tacline2 = data.get(Integer.parseInt(pid)-1);
    			   String[] tmps = tacline2.split(",");
    			   cid = pid;
    			   pid = tmps[4];
    			   
          		key ="\"cluster"+cid+"\",\"cluster"+pid+"\"";
       			la2 = cluster_la.get(key);
       			la2=(la2!=null?(la2+la):la);
       			cluster_la.put(key, la2);
       			ms2 = cluster_ms.get(key);
       			ms2=(ms2!=null?(ms2+ms):ms);
       			cluster_ms.put(key,ms2);
       			sz2 = cluster_sz.get(key);
       			sz2=(sz2!=null?(sz2+1):1);
       			cluster_sz.put(key, sz2);
    			}
    		}
    		
    		if(Integer.parseInt(tmp[2])<0)
    		{
    			id = -Integer.parseInt(tmp[2]);
    			key = "\"app"+id+"\", \"cluster"+ tmp[3]+"\"";
    			cid = tmp[3];
    			pid = tmp[4];
    			la = loan_amount.get(id);
    			ms = mixedscore.get(id);
    			cluster_la.put(key, la);
    			cluster_ms.put(key, ms);
    			cluster_sz.put(key, 1);
    			
       			key ="\"cluster"+cid+"\",\"cluster"+pid+"\"";
    			la2 = cluster_la.get(key);
    			la2=(la2!=null?(la2+la):la);
    			cluster_la.put(key, la2);
    			ms2 = cluster_ms.get(key);
    			ms2=(ms2!=null?(ms2+ms):ms);
    			cluster_ms.put(key,ms2);
    			sz2 = cluster_sz.get(key);
    			sz2=(sz2!=null?(sz2+1):1);
    			cluster_sz.put(key, sz2);
    			
    			while(Integer.parseInt(pid)!=0)
    			{
    			   tacline2 = data.get(Integer.parseInt(pid)-1);
    			   String[] tmps = tacline2.split(",");
    			   cid = pid;
    			   pid = tmps[4];
    			   
       			key ="\"cluster"+cid+"\",\"cluster"+pid+"\"";
       			la2 = cluster_la.get(key);
       			la2=(la2!=null?(la2+la):la);
       			cluster_la.put(key, la2);
       			ms2 = cluster_ms.get(key);
       			ms2=(ms2!=null?(ms2+ms):ms);
       			cluster_ms.put(key,ms2);
       			sz2 = cluster_sz.get(key);
       			sz2=(sz2!=null?(sz2+1):1);
       			cluster_sz.put(key, sz2);
    			}
    		}
    		
    		
    		
    	}
        
        Enumeration names = cluster_la.keys();
        String name = "";
        String name1 = "";
        while(names.hasMoreElements())
        {
        	name = (String)names.nextElement();
        	if(name.indexOf("cluster0")>0)
        	{
        		name1 = name.replace("cluster0", "null");
        		System.out.println("["+name1+","+ cluster_la.get(name)+","+cluster_ms.get(name)/cluster_sz.get(name)+"],");
        	}
        	else
        		System.out.println("["+name+","+ cluster_la.get(name)+","+cluster_ms.get(name)/cluster_sz.get(name)+"],");
        }
        
	}

}

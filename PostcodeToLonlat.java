import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;







public class PostcodeToLonlat {

	  // URL prefix to the geocoder
	 // private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";
		 private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.googleapis.com/maps/api/geocode/xml";

	  public static final void main (String[] argv) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {

	    // query address
	    String address = "+UK";
	    String coun_address =  "";
	    String county = "";
	    String corcounty = "";
	    String postcode = "SS155YH";
	 //   String ip_topic_file = "C:\\My Projects\\Zheng - Apply sampling algorithm to UK - RAD - 359\\counames.csv";
	  //  String ip_topic_file = "C:\\My Projects\\Zheng - Apply sampling algorithm to UK - RAD - 359\\townname.csv";
	  //  File ip_f = new File(ip_topic_file);
	  //  FileReader ip_f_reader = new FileReader(ip_f);
	  //  BufferedReader buf_ip_reader = new BufferedReader(ip_f_reader);
	 //   String ipline = buf_ip_reader.readLine();
	    boolean found = false;
	    int total = 0;
	    int index = 0;
//	    while(ipline!=null)
//	    {
	    	coun_address =  "";
	    	
//	        String[] tmps = ipline.split(",");


//	        county = tmps[1].toLowerCase();
//	        county = county.replaceAll("\"", "");
//	        county = county.trim();
//	        coun_address = county+address;
	    	coun_address = postcode+address;
	        found = false;
	        
	    // prepare a URL to the geocoder
	    URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + URLEncoder.encode(coun_address, "UTF-8") + "&sensor=false");

	    // prepare an HTTP connection to the geocoder
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	    Document geocoderResultDocument = null;
	    try {
	      // open the connection and get results as InputSource.
	      conn.connect();
	      InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

	      // read result and parse into XML Document
	      geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
	    } finally {
	      conn.disconnect();
	    }

	    // prepare XPath
	    XPath xpath = XPathFactory.newInstance().newXPath();

	    // extract the result
	    NodeList resultNodeListLon = null;
	    NodeList resultNodeListLat = null;
	 /*   // a) obtain the formatted_address field for every result
//	    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", geocoderResultDocument, XPathConstants.NODESET);
//	    for(int i=0; i<resultNodeList.getLength(); ++i) {
//	      System.out.println(resultNodeList.item(i).getTextContent());
//	    }

	    // b) extract the locality for the first result */
	    resultNodeListLat = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/lat", geocoderResultDocument, XPathConstants.NODESET);
	    resultNodeListLon = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/lng", geocoderResultDocument, XPathConstants.NODESET);
	    
	    for(int i=0; i<resultNodeListLat.getLength(); ++i) {
	      System.out.println(postcode+" lat,"+resultNodeListLat.item(i).getTextContent());
	      found = true;
	    }
	    
	    for(int i=0; i<resultNodeListLon.getLength(); ++i) {
		      System.out.println(postcode+" lon,"+resultNodeListLon.item(i).getTextContent());
		      found = true;
		    }
	    
    
	    if(!found)
	    {
	    	System.out.println(postcode+",");
	    }
	    
	    for(int i=0;i<100000000;i++)
	    {
	    	total = (int)Math.random()*i;
	    	for(int j=0;j<total;j++)
	    	{
	    		
	    	}
	    }
//	    ipline = buf_ip_reader.readLine();
	    // c) extract the coordinates of the first result
//	    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
//	    float lat = Float.NaN;
//	    float lng = Float.NaN;
//	    for(int i=0; i<resultNodeList.getLength(); ++i) {
//	      Node node = resultNodeList.item(i);
//	      if("lat".equals(node.getNodeName())) lat = Float.parseFloat(node.getTextContent());
//	      if("lng".equals(node.getNodeName())) lng = Float.parseFloat(node.getTextContent());
//	    }
//	    System.out.println("lat/lng=" + lat + "," + lng);
	    
	    // c) extract the coordinates of the first result
//	    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/address_component[type/text() = 'administrative_area_level_1']/country[short_name/text() = 'US']/*", geocoderResultDocument, XPathConstants.NODESET);
//	    lat = Float.NaN;
//	    lng = Float.NaN;
//	    for(int i=0; i<resultNodeList.getLength(); ++i) {
//	      Node node = resultNodeList.item(i);
//	      if("lat".equals(node.getNodeName())) lat = Float.parseFloat(node.getTextContent());
//	      if("lng".equals(node.getNodeName())) lng = Float.parseFloat(node.getTextContent());
//	    }
//	    System.out.println("lat/lng=" + lat + "," + lng);
//	    }
	  }

	}

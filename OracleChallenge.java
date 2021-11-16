import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OracleChallenge {

	public static void main(String[] args) {
		String str="2343225,2345,us_east,RedTeam,ProjectApple,3445s\n"
				+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n"
				+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n"
				+ "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n"
				+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n"
				+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
		
		String[] items = str.split("\n");
        List<String> itemList = Arrays.asList(items);
		
// Generate the unique set of Contractor IDs and GeoZones from the input String
		Set<String> ctrIds = new HashSet<String>();
		Set<String> geoZones = new HashSet<String>();
		String[] tokens = null;
		for(String tkn :itemList)
	        {
	            tokens = tkn.split(",");
	            if(tokens != null && tokens.length > 0)
	            {
	            	ctrIds.add(tokens[1]);
	            	geoZones.add(tokens[2]);
	            }
	        }
		
//Get the unique customer ID for contractor ID	
        if(ctrIds != null && ctrIds.size() > 0)
		 {
			 for(String ctrid : ctrIds)
			 {
				 Set<String> unqCustId = new HashSet<String>();
					for(String tkn :itemList)
				        {
				            String[] tkns = tkn.split(",");
				            if(tkns != null && tkns.length > 0)
				            {
				            	if(Arrays.asList(tkns).stream().anyMatch(str1 -> str1.substring(0).equals(ctrid)))
				            		unqCustId.add(tkns[0]);
				            }
				        }
					
				System.out.println("The number of unique customerId for contractor ID "+ctrid+" :"+ unqCustId.size());
			 }
		 }
		System.out.println("\n");		

//Get the unique customer ID for geoZones and also average bild duration for each geozone
        int buildDuration = 0;
        Set<String> custIdSet = null;
        if(geoZones != null && geoZones.size() > 0)
		 {
			 for(String geoZone : geoZones)
			 {
				 custIdSet = new HashSet<String>();
				 	buildDuration = 0;
				 	String[] tkns1;
				 	for(String tkn :itemList)
				        {

				            	if(Arrays.asList(tkns1 = tkn.split(",")).stream().anyMatch(str2 -> str2.substring(0).equals(geoZone)))
				            		{
				            			custIdSet.add(tkns1[0]);
				            			buildDuration+=Integer.parseInt(tkns1[5].replace("s", ""));				            			
				            		}
				            		
				        }
				System.out.println("The number of unique customerId for geoZone "+geoZone+" :"+ custIdSet.size()+" and values are :" + custIdSet);
				System.out.println("The average buildduration for each geozone "+geoZone+" : " + buildDuration/custIdSet.size()+"\n");
			 }
		 }
	}

}

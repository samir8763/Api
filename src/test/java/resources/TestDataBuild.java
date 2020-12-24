package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild 
{
	public AddPlace addPlacePayLoad(String name, String language, String address)
	{
		 AddPlace p=new AddPlace();
		 p.setAccuracy(50);
		 p.setName(name);
		 p.setPhone_number("(+91) 983 893 3937");
		 p.setAddress(address);
		 p.setWebsite("http://google.com");
		 p.setLanguage(language);

		 Location l=new Location();
		 l.setLat(-38.383494);
		 l.setLng(33.427362);

		 p.setLocation(l);
		 List<String> MyList=new ArrayList<String>();
		 MyList.add("shoe park");
		 MyList.add("shop");
		 p.setTypes(MyList);
return p;	
	}
public String deletePlacePayload(String placeId) 
{
return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}\r\n";	
}
}

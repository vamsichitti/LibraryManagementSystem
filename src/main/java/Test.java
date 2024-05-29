import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	
	public List<Integer> orderElements(int[] arr){
		return null;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  int[] arr = {2,3,4,5,2,1,5,5,5,1,1,6,1,1};
  HashMap<Integer,Integer> newmap = new HashMap<>();
  
  for(int num:arr) {
	  newmap.put(num,newmap.getOrDefault(num,0)+1);
	  
	  }
  List<Map.Entry<Integer,Integer>> entryList = new ArrayList<>(newmap.entrySet());
  entryList.sort((entry1, entry2)-> entry2.getValue().compareTo(entry1.getValue()));
  if(entryList.size()>=3) {
	  int thirdMostRepeatedValue = entryList.get(2).getKey();
	  System.out.println(thirdMostRepeatedValue);
  }
  
	}

}

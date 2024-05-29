import java.util.Arrays;

public class CheckAnagram {
  public static boolean areAnnagrams(String str1, String str2) {
	  str1 = str1.replaceAll("\\s", "").toLowerCase();
	  str2 = str2.replaceAll("\\s","").toLowerCase();
	  if(str1.length() != str2.length()) {
		  return false;
	  }
	  char[] charArray1 = str1.toCharArray();
	  char[] charArray2 = str2.toCharArray();
	  
	  Arrays.sort(charArray1);
	  Arrays.sort(charArray2);
	   return Arrays.equals(charArray1, charArray2);
  }
	
	public static void main(String args[]) {
		String str1 = "aab";
		String str2 = "bAA";
		System.out.println(areAnnagrams(str1, str2));
		
	}
}

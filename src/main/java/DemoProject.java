
public class DemoProject {
	public static void printRepeatedLetter(String str) {
		int[] charCount = new int[256];
		for(char c:str.toCharArray()) {
			charCount[c]++;
		}
		System.out.println("Repeated letters :");
		for(int i=0;i<charCount.length;i++) {
			if(charCount[i] > 1) {
				System.out.println((char)i + " repated number ->"+ charCount[i]);
			}
		}
	}
	public static void main(String[] args) {
		String str = "awvamsivamsivamsivamsi";
		printRepeatedLetter(str);
	}
}

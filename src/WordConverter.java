import java.util.Arrays;

public class WordConverter {
	char[] charList = {'!', ' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public WordConverter(){
		
	}
	
	public double[] convertFromWord(String word){
		char[] chars = word.toCharArray();
		double[] nums = new double[15];
		for(int i = 0; i < chars.length; i++){
			for(int j = 0; j < charList.length; j++){
				if(chars[i] == charList[j]){
					nums[i] += (double)(j);
				}
			}
		}
		return nums;
	}
	
	public double[] desiredNumbers(String word){
		char[] chars = word.toCharArray();
		double[] nums = new double[15];
		for(int i = 0; i < chars.length; i++){
			for(int j = 0; j < charList.length; j++){
				if(chars[i] == charList[j]){
					nums[i] += (double)(j) / 28 - 1/56;
				}
			}
		}
		return nums;
	}
	
	public char convertToChar(double num){
		for(double i = 0; i < 28; i++){
			if(num < i / 27){
				return charList[(int)(i)];
			}
		}
		return 'z';
	}
}

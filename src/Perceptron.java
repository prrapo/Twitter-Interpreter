public class Perceptron {
	double weights[];
	double learningConstant = .01;
	
	public Perceptron(int numInputs){
		weights = new double[numInputs];
		for(int i = 0; i < numInputs; i++){
			weights[i] = Math.random() * 2 - 1;
		}
	}
	
	public int feedforward(double[] inputs){
		double sum = 0;
		for(int i = 0; i < weights.length; i++){
			sum += inputs[i] * weights[i];
		}
		return activate(sum);
	}
	
	public int activate(double sum){
		if(sum > 0){
			return 1;
		}
		return -1;
	}
	
	public void train(double[] inputs, int desired){
		int guess = feedforward(inputs);
		double error = desired - guess;
		for(int i = 0; i < weights.length; i++){
			weights[i] += learningConstant * error * inputs[i];
		}
		if(guess == 1){
			System.out.println("The point (" + inputs[0] + "," + inputs[1] + ") is above the line. " + desired);
		}else{
			System.out.println("The point (" + inputs[0] + "," + inputs[1] + ") is below the line. " + desired);
		}
	}
}

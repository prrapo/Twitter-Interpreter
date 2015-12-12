public class Perceptron {
	double weights[];
	double learningConstant = .01;
	
	//initialize all of the weights to random values and take in the number of inputs this perceptron will have
	public Perceptron(int numInputs){
		weights = new double[numInputs];
		for(int i = 0; i < numInputs; i++){
			weights[i] = Math.random() * 2 - 1;
		}
	}
	
	//take in the inputs and calculate the answer, then send it to the acivation function
	public int feedforward(double[] inputs){
		double sum = 0;
		for(int i = 0; i < weights.length; i++){
			sum += inputs[i] * weights[i];
		}
		return activate(sum);
	}
	
	//this is a very simple activation function right now, will have to change it to a sigmoid function once I figure out how to do that
	public int activate(double sum){
		if(sum > 0){
			return 1;
		}
		return -1;
	}
	
	//this function changes the weights based on the difference between the desired answer and what it gave, I was testing it with a simple example
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

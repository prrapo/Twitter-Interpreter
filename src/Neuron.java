public class Neuron {
	double[] weights;
	double learningConstant = .01;
	double bias;
	double output;
	double[] inputs;
	
	public Neuron(int numInputs){
		weights = new double[numInputs];
		for(int i = 0; i < numInputs; i++){
			weights[i] = Math.random() * 2 - 1;
		}
	}
	
	public double calculateOutput(double[] inputs){
		this.inputs = inputs;
		output = activate(calculateInput());
		return output;
	}
	
	public double calculateInput(){
		double total = 0;
		for(int i = 0; i < inputs.length; i++){
			total += inputs[i] * weights[i];
		}
		return total;
	}
	
	public double calculateTotalOutputError(double desiredOutput){
		return calculateOutputError(desiredOutput) * calculateTotalInput();
	}
	
	public double calculateOutputError(double desiredOutput){
		return -(desiredOutput - output);
	}
	
	public double calculateTotalInput(){
		return output * (1 - output);
	}
	
	public double activate(double totalInput){
		return 1 / (1 + Math.pow(Math.E, totalInput * -1));
	}
}

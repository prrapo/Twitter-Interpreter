public class NeuralNetwork {
	double learningRate = .5;
	NeuronLayer hiddenLayer;
	NeuronLayer outputLayer;
	WordConverter converter;
	
	public NeuralNetwork(int numInputs, int numHidden, int numOutputs){
		hiddenLayer = new NeuronLayer(numHidden, numInputs);
		outputLayer = new NeuronLayer(numOutputs, numHidden);
	}
	
	public double[] feedForward(double[] inputs){
		double[] hiddenLayerOutputs = hiddenLayer.feedForward(inputs);
		return outputLayer.feedForward(hiddenLayerOutputs);
	}
	
	public String process(String input){
		String output = "";
		double[] inputs = converter.convertFromWord(input);
		feedForward(inputs);
		for(double o: outputLayer.getOutputs()){
			output += converter.convertToChar(o);
		}
		return output;
	}
	
	public void train(double[] inputs, double[] desiredOutputs){
		feedForward(inputs);
		
		//output neuron deltas
		double[] outputNeuronDeltas = new double[outputLayer.size()];
		for(int i = 0; i < outputLayer.size(); i++){
			outputNeuronDeltas[i] = outputLayer.neurons[i].calculateTotalOutputError(desiredOutputs[i]);
		}
		
		//hidden neuron deltas
		double[] hiddenNeuronDeltas = new double[hiddenLayer.size()];
		for(int j = 0; j < hiddenLayer.size(); j++){
			double hiddenOutputError = 0;
			for(int k = 0; k < outputLayer.size(); k++){
				hiddenOutputError += outputNeuronDeltas[k] * outputLayer.neurons[k].weights[j];
			}
			hiddenNeuronDeltas[j] = hiddenOutputError * hiddenLayer.neurons[j].calculateTotalInput();
		}
		
		//update output neuron weights
		for(int a = 0; a < outputLayer.size(); a++){
			for(int b = 0; b < outputLayer.neurons[a].weights.length; b++){
				double error = outputNeuronDeltas[a] * outputLayer.neurons[a].inputs[b];
				outputLayer.neurons[a].weights[b] -= learningRate * error;
			}
		}
		
		//update hidden neuron weights
		for(int c = 0; c < hiddenLayer.size(); c++){
			for(int d = 0; d < hiddenLayer.neurons[c].weights.length; d++){
				double error = hiddenNeuronDeltas[c] * hiddenLayer.neurons[c].inputs[d];
				hiddenLayer.neurons[c].weights[d] -= learningRate * error;
			}
		}
	}
}

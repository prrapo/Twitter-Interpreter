public class NeuronLayer {
	Neuron[] neurons;
	
	public NeuronLayer(int numNeurons, int numInputs){
		double bias = Math.random();
		neurons = new Neuron[numNeurons];
		for(int i = 0; i < numNeurons; i++){
			neurons[i] = new Neuron(numInputs);
		}
		for(Neuron p: neurons){
			p.bias = bias;
		}
	}
	
	public double[] feedForward(double[] inputs){
		double[] outputs = new double[neurons.length];
		for(int i = 0; i < neurons.length; i++){
			outputs[i] = neurons[i].calculateOutput(inputs);
		}
		return outputs;
	}
	
	public double[] getOutputs(){
		double[] outputs = new double[neurons.length];
		for(int i = 0; i < neurons.length; i++){
			outputs[i] = neurons[i].output;
		}
		return outputs;
	}
	
	public int size(){
		return neurons.length;
	}
}

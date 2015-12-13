public class Trainer {
	
	public Trainer(NeuralNetwork network, int iterations, String input, String output){
		WordConverter c = new WordConverter();
		for(int i = 0; i < iterations; i++){
			double[] inputs = c.convertFromWord(input);
			double[] outputs = c.desiredNumbers(output);
			network.train(inputs, outputs);
			for(double o: network.outputLayer.getOutputs()){
				System.out.print(c.convertToChar(o));
			}
			System.out.println();
		}
		
	}
}


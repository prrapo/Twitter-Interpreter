public class Trainer {
	double inputs[];
	int answer;
	
	//this is the trainer I was using to test the perceptron, it takes in two inputs, the answer, and sets the bias to 1
	//its very easy to change the number of inputs to any amount necessary
	public Trainer(double x, double y, int a){
		inputs = new double [3];
		inputs[0] = x;
		inputs[1] = y;
		inputs[2] = 1;
		answer = a;
	}
}

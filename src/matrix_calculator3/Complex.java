package matrix_calculator3;

public class Complex {
	double x, y;
	String num ="";
	Complex(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public String print() {
		num = Double.toString(x) + " + ";
		if(y == 1) {
			num += "j";
			return num;
		}
		if(y == -1) {
			num += "-j";
			return num;
		}
		num += Double.toString(y) + "j";
		return num;
	}
	
}

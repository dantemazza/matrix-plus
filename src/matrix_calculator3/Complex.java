package matrix_calculator3;

public class Complex {
	double x, y;
	String num ="";
	Complex(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public String print() {
		num = String.format("%.2f",x);
		if(y<0) { num += " + ";
		}else { num += " - ";}
		
		if(y == 1) {
			num += "j";
			return num;
		}
		if(y == -1) {
			num += "j";
			return num;
		}
		if(y<0) { num += String.format("%.2f", -y) + "j";
		}else {num += String.format("%.2f", y) + "j";}
		return num;
	}
}

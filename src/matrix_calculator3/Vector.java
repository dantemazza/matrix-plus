package matrix_calculator3;

public class Vector {
	double vector[];
	int dim, i = 0;
	double magnitude;
	Vector(int dim){
		this.dim = dim;
		vector = new double[dim];
	}
	
	Vector() {}
	
	Vector(int dim, double v[]){
		this.dim = dim;
		vector = new double[dim];
		for(int a=0; a<v.length; a++) {
			vector[a] = v[a];
		}
	}
	
	Vector(Vector q){
		this.dim = q.dim;
		vector = new double[dim];
		for(int a = 0; a<this.dim; a++) {
			
			this.vector[a] = q.vector[a];
		}
	}
	
	
	public void make(Vector q){
		this.dim = q.dim;
		for(int a = 0; a<dim; a++) {
			this.vector[a] = q.vector[a];
		}
	}
	
	public double get(int x) {
		return vector[x];
	}
	
	public void add(Vector c) {
		
		if(c.dim != this.dim) {
			System.out.println("Vectors are not the same dim");
			return;
		}
		for(int a=0; a<c.dim; a++) {
			this.vector[a] += c.vector[a];
		}
			
	}
	
	public void subtract(Vector c) {
	
		if(c.dim != this.dim) { 
			System.out.println("Vectors are not the same dim");
			return;
		}
		for(int a=0; a<c.dim; a++) {
			this.vector[a]-= c.vector[a];
		}
		
	}
	
	 public double dot(Vector b) {
		 double result = 0;
		 if(this.dim != b.dim) {
			 System.out.println("Vectors are not the same dim");
			 return 0;
		 }
		 
		 for(int c = 0; c<this.dim; c++) {
			 result += this.get(c) * b.get(c);
		 }	 
		 return result;	 
	 }
	 public void scalarMultiply(double b) {
		 for(int a=0; a<dim; a++) {
			 this.vector[a]*= b;
		 }
		
	 }
	 
	
	 public void print() {
		 for(int i=0; i<this.dim; i++) {
			 System.out.println(this.get(i));
		 }
		 System.out.println();
	 }
	 
	 public void push(double s) {
		 vector[i] = s;
		 i++;
	 }
	 
	 public void normalize() {
		 //this.print();
		 //System.out.println(this.dim);
		 double radicand = 0;
		 for(int i=0; i<dim; i++) {
			 radicand += Math.pow(vector[i], 2);
		 }
		 magnitude = Math.sqrt(radicand);
		 
		 for(int j=0; j<dim; j++) {
			 this.vector[j] /= magnitude;
		 }
		
		 radicand = 0;
		 for(int i=0; i<dim; i++) {
			 radicand += Math.pow(vector[i], 2);
		 }
		 
		 magnitude = Math.sqrt(radicand);
		 
	 }
	
	
}

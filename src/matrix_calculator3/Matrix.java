package matrix_calculator3;

import java.util.*;


public class Matrix {
	private double matrix[][];
	private Matrix inverse, adjugate, transpose, orthogonal, P, Q, R, schur, diag;
	private int order; 
	private double determinant = 0, trace = 0;
	Scanner input = new Scanner(System.in);
	Vector rowSpace[], columnSpace[], eigenVectors[];
	Vector arithmeticVector = new Vector();
	private Object eigenValues[];
	private double eigenspaceBasis;
	//Matrix arithmeticMatrix = new Matrix();
	
	Matrix(){}
	
	
	Matrix(int order){
		matrix = new double[order][order];
		this.order = matrix.length;
	}
	
	Matrix(double[][] n){
		matrix = new double[n.length][n.length];
		order = n.length;
        for(int i = 0; i < order; i++){
            for(int j = 0; j < order; j++){
                matrix[i][j] = n[i][j];}}
        this.order = n.length;
	}
	Matrix(Matrix l){
		this.order = l.order();
		this.matrix = new double[l.order()][l.order()];
		for(int a = 0; a<this.order(); a++) {
			for(int b = 0; b<this.order(); b++) {
				this.matrix[a][b] = l.get(a, b);
			}
		}
		try {
		this.transpose = new Matrix(l.transpose);
		this.adjugate = new Matrix(l.adjugate);
		this.inverse = new Matrix(l.inverse);
		}catch(NullPointerException e) {}
		this.determinant = l.determinant;
		this.setRowAndColSpace();
	}
	
	public int order() {
		return this.order;
	}
	
	public double get(int x, int y) {
		return matrix[x][y];
	}
	
	public void set(int x, int y, double val) {
		this.matrix[x][y] = val;
	}
	
	public void print() {
		for(int c=0; c<order; c++) {
			if(c!=0) System.out.println();
			for(int b=0; b<order; b++){
//				if(matrix[c][b] < 0.000001) {
//					System.out.print(0 + " ");
//					continue;
//				}
			System.out.print(matrix[c][b] + " ");
			}}
		System.out.println();
	}
	
	 public void initialize() {
		for(int c=0; c<order; c++) {
			for(int b=0; b<order; b++){
			System.out.print("Please enter the " + (c+1) + "," + (b+1) + " entry: ");
			matrix[c][b] =  input.nextDouble(); 
			
			}	
		}
		this.setRowAndColSpace();
	 }
	 
	 public void generate(int a, int b) {
		 int p = b-a+1;
		 Random r = new Random();
			for(int c=0; c<order; c++) {
				for(int d=0; d<order; d++){
				matrix[c][d] = r.nextInt(p)+a; }} 
	 }
	 public void setTranspose() {
		 transpose = new Matrix(this.order);
		 for(int c=0; c<order; c++) {
			for(int b=0; b<order; b++){
			transpose.set(c,b, matrix[b][c]); }}

	 }	 
	 public Matrix getTranspose() {
		 return this.transpose;
	 }
	
	 public double[][] coTrix(int d, int e){
		 int k = 0, j = 0;
			double nmOne[][] = new double[order-1][order-1];
			 for(int c=0; c<order; c++) {
			 if(d-1==c) {
				 j++;
				 continue; 
				 
			 }
				for(int b=0; b<order; b++){
				if(e-1==b) {
					k++;
					continue;
				}
				 nmOne[c-j][b-k] = matrix[c][b];		
				} 
				k--;
			}

			return nmOne;
		 }
	 
	 public double cofExp(int f, int g){
		 double coFactor = 0;
		double coefficient = Math.pow(-1, f+g);
			Matrix E = new Matrix(coTrix(f,g));
			
			if(E.order()<3) coFactor = coefficient * E.find2by2();
			else {
				E.setDet();
				coFactor = coefficient * E.getDet();
				
			}

		 return coFactor;
	 }
	 
	 public void setDet(){

			if(order()<3) determinant = find2by2(); 
			int p = 1;		
			while(p<order+1) {
				determinant += cofExp(p,1)*matrix[p-1][0];
				p++;
			}
		 
	 }
	 public double getDet() {
		 return determinant; 
	 }
	 
	 public double find2by2() {	
		 if(order ==1)
			return matrix[0][0];
		return matrix[0][0]*matrix[1][1]-matrix[1][0]*matrix[0][1];
	 }
	 
	 public void setAdjugate(){
		double adj[][] = new double[order][order];
		this.setDet(); 
		 
		 for(int c=1; c<order+1; c++) {
			for(int b=1; b<order+1; b++){

		 adj[c-1][b-1]= cofExp(b,c);}}
		 adjugate = new Matrix(adj);	 
	 }
	
	 public Matrix getAdjugate() {
		 return adjugate;
	 }
	 
	 public double[][] scalarProduct(double a){
		 double scalar_product[][] = new double[order][order];
		 for(int c=0; c<order; c++) {
			for(int b=0; b<order; b++){
		 scalar_product[c][b] = matrix[c][b]*a;}}	 	 	 
		 return scalar_product; 
	 }
	 
	 public void setInverse(){
		 this.setAdjugate();
		 inverse = new Matrix(adjugate);
		 for(int c=0; c<order; c++) {
			for(int b=0; b<order; b++){
			inverse.set(c,b, inverse.get(c, b) / determinant );}}	 
	 }
	 public Matrix getInverse() {
		 return inverse; 
	 }
	 public static void console() {
			Scanner input = new Scanner(System.in);
			while(true){
			
			System.out.println("1: Input\n2: Generate\nWhat would you like to do?: ");
			int num = input.nextInt();
			if(num !=1 && num !=2) System.exit(0);
			System.out.println("What is the order of your matrix: ");
			int size = input.nextInt();
			Matrix A = new Matrix(size);
			if(num==1) 
				A.initialize();
			else if(num==2)	{
				System.out.println("Please enter the range of your entries: ");
				System.out.print("Lower bound: ");
				int lower = input.nextInt();
				System.out.print("\nUpper bound: ");
				int upper = input.nextInt();
				A.generate(lower, upper);
				}
			
			
			System.out.println("Your matrix is: ");
			A.print();
			double start = System.currentTimeMillis();
			A.setDet();
			System.out.println("Determinant: " + A.getDet());
			System.out.println("Adjugate: ");
			A.setAdjugate();
			A.getAdjugate().print();
			System.out.println("Inverse: ");
			if(A.determinant==0) {
				System.out.println("There is no inverse.");
				
			}else {
				A.setInverse();
				A.getInverse().print();
			}
			double end = System.currentTimeMillis();
			System.out.println("This took " + (end - start)/1000 + " seconds\n");
			}
	 }
	 public void times(Matrix B){
		 this.setRowAndColSpace();
		 B.setRowAndColSpace();
		 if(B.order() != this.order()) {
			 System.out.println("not same order"); 
			 return;
			 }
		 
		 for(int a = 0; a<this.order(); a++) {
			 for(int b = 0; b<B.order(); b++) {
				this.set(a, b, this.rowSpace[a].dot(B.columnSpace[b]));
				 
				 
				 
			 }}
	 }
	 
	 public void subtract(Matrix B){
		 if(B.order() != this.order()) {
			 System.out.println("not same order"); 
			 return;
			 }
		 
		 for(int a = 0; a<this.order(); a++) {
			 for(int b = 0; b<B.order(); b++) {
				 this.set(a,b, this.get(a,b) - B.get(a, b));	
			 }}
		
	 }
	 
	 public void add(Matrix B){
		 if(B.order() != this.order()) {
			 System.out.println("not same order"); 
			 return;
			 }
		 
		 for(int a = 0; a<this.order(); a++) {
			 for(int b = 0; b<B.order(); b++) {
				 this.set(a,b, this.get(a,b) + B.get(a, b));	 
			 }}
		
	 }
	 
	 public void setRowAndColSpace(){
		 rowSpace = new Vector[order];
		 columnSpace = new Vector[order];
		 for(int a=0; a<order; a++) {
			 columnSpace[a] = new Vector(order);
			 rowSpace[a] = new Vector(order);
			 for(int b=0; b<order; b++) {
				 rowSpace[a].push(matrix[a][b]);
				 columnSpace[a].push(matrix[b][a]);
			 }
			 
		 }
	 }
	 
	 public void printRC() {
		 System.out.println("Rowspace:");
		 for(int a = 0; a<order; a++) {
			 rowSpace[a].print();
		 }
		 System.out.println("Columnspace");
		 for(int a = 0; a<order; a++) {
			 columnSpace[a].print();
		 }
	 }
	 public void orthogonalize() {
		 orthogonal = new Matrix(this);
		 
		 orthogonal.columnSpace[0].normalize();
//		 System.out.println("magnitude of " + 0 + " " + orthogonal.columnSpace[0].magnitude);
		 for(int u=1; u<this.order; u++) {
			 for(int v = u; v>=0; v--){
				 Vector temp = new Vector(orthogonal.columnSpace[v]);
				 temp.scalarMultiply(orthogonal.columnSpace[u].dot(orthogonal.columnSpace[v]));
				 orthogonal.columnSpace[u].subtract(temp);
			 }
			 orthogonal.columnSpace[u].normalize();
//			 System.out.println("magnitude of " + u + " " + orthogonal.columnSpace[u].magnitude);
		 }
		orthogonal.mergeCols(orthogonal.columnSpace);
//		System.out.println(orthogonal.columnSpace[1].dot(orthogonal.columnSpace[0]));
//		System.out.println(orthogonal.columnSpace[2].dot(orthogonal.columnSpace[0]));
//		System.out.println(orthogonal.columnSpace[2].dot(orthogonal.columnSpace[1]));

		 
	 }
	 
	 public Matrix getOrthogonal() {
		 return orthogonal;
	 }
	
	 public void mergeRows(Vector[] rows) {
		 for(int v=0; v<order; v++) {
			 for(int w=0; w<order; w++) {
				 this.set(v,w, rows[v].get(w));
			 }
		 }
	 }
	 
	 public void mergeCols(Vector[] cols) {
		 //this.print();
		 for(int v=0; v<order; v++) {
			 for(int w=0; w<order; w++) {
				 this.set(w,v, cols[v].get(w));
				 //this.print();
			 }
		 }
	 }
	 
	 public void decomposeQR() {
		 this.orthogonalize();
		 Q = new Matrix(this.orthogonal);
		 Q.setTranspose();
		 R = new Matrix(Q.transpose);
		 R.times(this);
	 }

	 public Matrix getQ() {
		 return Q;
	 }
	 
	 public Matrix getR() {
		 return R;
	 }
	 
	 public void generateSchurForm() {
		 Matrix Ak = new Matrix(this);
		 for(int a=0; a<1000; a++) {
			 Ak.decomposeQR();  

			 Matrix AkPlus = new Matrix(Ak.getR());
			 AkPlus.times(Ak.getQ());
//			 System.out.println("Ak b4: ");
//			 Ak.print();
			 Ak = new Matrix(AkPlus);
//			 System.out.println("Ak after: ");
//			 Ak.print();
		 }
		 schur = new Matrix(Ak);
	 }
	 
	 public Matrix getSchurForm() {
		 return schur;
	 }
	 
	 
	 public void findEigenvalues(){
		 eigenValues = new Object[order];
		 this.generateSchurForm();
		 this.schur.zeroify();
		 this.schur.print();
		 diag = new Matrix(order);
//		 for(int a = 0; a<order; a++) {
//			 for(int b = 0; b<order; b++) {
//				 if(a == b) { 
//					 eigenValues[a] = this.schur.get(a, b);
//				     diag.set(a, b, eigenValues[a]);
//				 }else diag.set(a, b, 0);
//			 }
//		 }
		 
		 int a=0, b=0;
		 while(a<order) { 
			 if(a == order-1){
				 eigenValues[a] = this.schur.get(a, b);
				 a++; b++; continue;
			 }
			 if(this.schur.get(a+1, b) == 0) {
				 eigenValues[a] = this.schur.get(a, b);
				 a++; b++; continue;
			 }else{
				 Object roots[] = solveQuadraticEquation(1, -this.schur.get(a, b) -this.schur.get(a+1, b+1), 
				 this.schur.get(a, b)*this.schur.get(a+1, b+1) - this.schur.get(a+1, b)*this.schur.get(a, b+1));
				 eigenValues[a] = roots[0];
				 eigenValues[a+1] = roots[1];
				 a+=2; b+=2;
			 }
		 }
		 
		 
		 
		 
	 }
	 
	 public Object[] getEigenValues(){
		 return eigenValues;
	 }
	 
	 public void printEigenValues() {
		 System.out.println("All " + order + " eigenvalues:");
		 for(int a=0; a<order; a++) {
			 if(eigenValues[a] instanceof Complex) System.out.println(((Complex)eigenValues[a]).print());
			 else System.out.println(eigenValues[a]);
		 }
	 }
	 
	 public double getTrace() {
		 int a = 0, b = 0;
		 while(a<order) {
			 trace += matrix[a][b];
			 a++; b++;
		 }
		 return trace;
	 }
	 
	 public void findBasisForEigenspace() {
		 for(int a=0; a<order; a++) {
			 Matrix AminusLambdaI = new Matrix(this);
			 Matrix lambdaI = new Matrix(this.identity().scalarProduct((double)eigenValues[a]));
			 AminusLambdaI.subtract(lambdaI);
			 AminusLambdaI.setDet();
			 System.out.println("determinant: " + AminusLambdaI.getDet());		 
		 }
	 }
	 public Matrix identity() {
		 Matrix identity = new Matrix(order);
		 for(int a=0; a<order; a++) {
			 for(int b=0; b<order; b++) {
				 identity.set(a, b, 0);
				 if(a == b) identity.set(a, b, 1);
			 }
		 }
		 
		 return identity;
	 }
	 
	 public void zeroify() {
		 for(int a=0; a<order; a++) {
			 for(int b=0; b<order; b++) {
				 if(matrix[a][b] < 0.0001 && matrix[a][b] > -0.0001) {
					 matrix[a][b] = 0;
				 }}}
	 }
	 
	 public static Object[] solveQuadraticEquation(double a, double b, double c) {
		 Object roots[] = new Object[2];
		 double b2a, discriminant;
		 b2a = -b / (2*a);
		 discriminant = Math.pow(b, 2) - 4*a*c;
		 if(discriminant < 0) {
			 roots[0] = new Complex(b2a, Math.sqrt(Math.abs(discriminant)) / (2*a));
			 roots[1] = new Complex(b2a, Math.sqrt(Math.abs(discriminant)) / (-2*a));
			 return roots;
		 }
		 
		 roots[0] = new Double(b2a - Math.sqrt(discriminant) / (2*a));
		 roots[1] = new Double(b2a + Math.sqrt(discriminant) / (2*a));
		 return roots;
	 }
	 
	 
		public static void main(String[] args) {
			//console();
			
//			double v1[] = new double[]{1,2,3};
//			Vector v3 = new Vector(v1.length,v1);
//			double v2[] = new double[]{-3,0,1};
//			Vector v4 = new Vector(v2.length, v2);
//			
//			Vector.subtract(v3, v4).print();
			Matrix A = new Matrix(3);
     		A.generate(-10,10);
			
//			Matrix B = new Matrix(3);
//			A.initialize();
			A.print();
//			B.print();
//			A.times(B);
//			A.decomposeQR();
			
//			System.out.println("Trace1: " + A.getTrace());
			A.findEigenvalues();
			A.printEigenValues();
			A.getSchurForm().print();
//			A.findBasisForEigenspace();
//			A.diag.print();
//			System.out.println("Trace2: " + A.diag.getTrace());
			
			//A.setDet();
			//System.out.println("Determinant: " + A.determinant);
//			A.generateSchurForm();
//			A.getSchurForm().print();
//			A.decomposeQR();
//			System.out.println("Q");
//			A.getQ().print();
//			System.out.println("R");
//			A.getR().print();
			
			
//			Matrix P = new Matrix(A.getQ());
//			P.times(A.getR());
//			P.print();
			
			
		}
		
		
		
		
		
	
}

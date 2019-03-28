package matrix_calculator2;

import java.util.*;
public class matrix {
	Scanner input = new Scanner(System.in);
   	private int order, j=0, k=0; 
	private double mtrix[][], nmOne[][], transpose[][], adjugate[][];
	matrix adjA, invA, transA;
	private double inverse[][], product[][], scalar_product[][];
	 
	matrix(int order){
	mtrix = new double[order][order];
	this.order = order;
		}
	
	matrix(double[][] n){
		mtrix = new double[n.length][n.length];
		order = n.length;
        for(int i = 0; i < order; i++){
            for(int j = 0; j < order; j++){
                mtrix[i][j] = n[i][j];}}
	}
	
	matrix(matrix l){
		this.order = l.order;
		this.mtrix = new double[l.order()][l.order()];
		for(int a = 0; a<this.order(); a++) {
			for(int b = 0; b<this.order(); b++) {
				this.mtrix[a][b] = l.get(a, b);
			}
		}
		this.adjA = l.adjA;
		this.invA = l.invA;
		this.transA = l.transA;
	}
	
	
	public int order() {
		return this.order();
	}
	
	public double get(int y, int z) {
		return mtrix[y][z];
		
	}
	public void print() {
		for(int c=0; c<order; c++) {
			if(c!=0) System.out.println();
			for(int b=0; b<order; b++){
			System.out.print(mtrix[c][b] + " ");
			}}
		System.out.println();
	}

	
	 
	 public void initialize() {
		for(int c=0; c<order; c++) {
			for(int b=0; b<order; b++){
			System.out.print("Please enter the " + (c+1) + "," + (b+1) + " entry: ");
			mtrix[c][b] =  input.nextDouble(); 
			
			}
			
		}
	 }
	 
	 public void generate(int a, int b) {
		 int p = b-a+1;
		 Random r = new Random();
			for(int c=0; c<order; c++) {
				for(int d=0; d<order; d++){
				mtrix[c][d] = r.nextInt(p)+a; }}
		 
		 
	 }
	 
	 public double[][] transpose() {
		 transpose = new double[order][order];
		 for(int c=0; c<order; c++) {
			for(int b=0; b<order; b++){
			transpose[c][b]=mtrix[b][c];}}
		 transA = new matrix(transA);
		 return transpose;
		 
		 
	 }
	 public double[][] coTrix(int d, int e){
		 
		nmOne = new double[order-1][order-1];
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
			 nmOne[c-j][b-k] = mtrix[c][b];		
			} 
			k--;
		}
		k=0;
		j=0;
	

		return nmOne;
	 }
	 
	 public double cofExp(int f, int g){
		 double coFactor = 0;
		double coefficient = Math.pow(-1, f+g);
			matrix E = new matrix(coTrix(f,g));
			
			if(E.order()<3) coFactor = coefficient * E.find2by2();
			else {
				coFactor = coefficient * E.findDet();
				
			}

		 return coFactor;
	 }
	 public double findDet(){

		 	double determinant = 0;
			if(order()<3) return find2by2(); 
			int p = 1;		
			while(p<order+1) {
				determinant += cofExp(p,1)*mtrix[p-1][0];
				p++;
			}

			return determinant;
		 
	 }
	 public double find2by2() {
			
		 if(order ==1)
				return mtrix[0][0];
				return mtrix[0][0]*mtrix[1][1]-mtrix[1][0]*mtrix[0][1];
		
	 }
	 
	 public double[][] findAdj(){
		 adjugate = new double[order][order];
		 
		 
		 for(int c=1; c<order+1; c++) {
			for(int b=1; b<order+1; b++){

		 adjugate[c-1][b-1]= cofExp(b,c);}}
		 adjA = new matrix(adjugate);
		 return adjugate;
		 
	 }
	 public double[][] sProduct(double a){
		 scalar_product = new double[order][order];
		 for(int c=0; c<order; c++) {
			for(int b=0; b<order; b++){
		 scalar_product[c][b] = mtrix[c][b]*a;}}	 	 	 
		 return scalar_product;  
	 }
	 public double[][] inverse(){
		 double detA = findDet();
		 inverse = new double[order][order];
		 invA = new matrix(adjA);
		 for(int c=0; c<order; c++) {
			for(int b=0; b<order; b++){
			inverse[c][b] /=detA;}}	 
		invA = new matrix(inverse);
		return inverse;
	 }
	 public double[][] times(matrix B){
		 product = new double[this.order()][this.order];
		 
		 for(int a = 0; a<this.order(); a++) {
			 for(int b = 0; b<this.order(); b++) {
				 product[a][b] = this.get(a,b) * B.get(b,a);
		 
		 
			 }}
		 return product;
	 }
	 
	 public static void console() {
			Scanner input = new Scanner(System.in);
			while(true){
			
			System.out.println("1: Input\n2: Generate\nWhat would you like to do?: ");
			int num = input.nextInt();
			if(num !=1 && num !=2) System.exit(0);
			System.out.println("What is the order of your matrix: ");
			int size = input.nextInt();
			matrix A = new matrix(size);
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
			double det = A.findDet();
			System.out.println("Determinant: " + det);
			System.out.println("Adjugate: ");
			matrix B = new matrix(A.findAdj());
			B.print();
			System.out.println("Inverse: ");
			if(det==0) {
				System.out.println("There is no inverse.");
				
			}else {
			matrix C = new matrix(B.sProduct(1/det)); 
			C.print();
			}
			double end = System.currentTimeMillis();
			System.out.println("This took " + (end - start)/1000 + " seconds\n");
			}
	 }
		public static void main(String[] args) {
			new theGUI();

//			console();
	
	} 

}
	 
	 
	 
	 


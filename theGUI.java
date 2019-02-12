package matrix_calculator2;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Scanner;

public class theGUI extends JFrame {
	int order;
	JPanel masterPanel = new JPanel();
	JPanel matrix, matrixLeft, matrixRight;
	JTextField[][] gridArray, myMatrix, myMatrixO;
	JTextField o;
	matrix A, adjugate;
	NumberFormat formatter;
	double[][] graphicMatrix, blank, adjA, invA, randomTrix;
	JButton btnAdj, btnInv, btnDet, btnOrder, btnGen, btnClear;
	JLabel lblDetOut;
	double detA, upper, lower;
	boolean l = true;
	
	public theGUI() {
		
		Font font = new Font("Arial", Font.BOLD, 28);
		Font font2 = new Font("Arial", Font.BOLD, 12);

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		this.setSize(2*dim.width/3, (6*dim.width)/20);
		
		this.setTitle("MATRIX CALCULATOR");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = (dim.width / 2) - (this.getWidth() / 2);
		int y = (dim.height / 2) - (this.getHeight() / 2);
		this.setResizable(false);
		this.setLocation(x,y);

		masterPanel.setLayout(new GridBagLayout());
		while(true) {
		try {
			String p = JOptionPane.showInputDialog("Enter the order of your matrix");
			order = Integer.parseInt(p);
			if(order<2 || order>10) {
				JOptionPane.showMessageDialog(null, "OUT OF RANGE","ERROR", JOptionPane.ERROR_MESSAGE); 
				continue;
			}
			break;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "INVALID","ERROR", JOptionPane.ERROR_MESSAGE);
			
		}}
		
		
		
		myMatrix = new JTextField[order][order];
		this.generateMatrix(true, order, myMatrix, false, blank);	
		this.Grid(masterPanel, matrixLeft, 0, 0, 4, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,50,50);
		
		myMatrixO = new JTextField[order][order];
		this.generateMatrix(false, order, myMatrixO, false, blank);
		this.Grid(masterPanel, matrixRight, 4, 0, 4, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,50,50);

		getButton b = new getButton();
		
		btnAdj = new JButton("adj(A)");
		btnAdj.setFont(font);
		btnAdj.addActionListener(b);
		this.Grid(masterPanel, btnAdj, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,5,5);
		
		
		btnInv = new JButton("A^-1");
		btnInv.setFont(font);
		btnInv.setPreferredSize(btnAdj.getPreferredSize());
		btnInv.addActionListener(b);
		this.Grid(masterPanel, btnInv, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,5,5);
		
		btnDet = new JButton("det(A)");
		btnDet.setFont(font);
		btnDet.setPreferredSize(btnAdj.getPreferredSize());
		btnDet.addActionListener(b);
		this.Grid(masterPanel, btnDet, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,5,5); 
		
		btnOrder = new JButton("nxn");
		btnOrder.setFont(font);
		btnOrder.setPreferredSize(btnAdj.getPreferredSize());
		btnOrder.addActionListener(b);
		this.Grid(masterPanel, btnOrder, 3, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,5,5); 
		
		btnGen = new JButton("RDM");
		btnGen.setFont(font);
		btnGen.setPreferredSize(btnAdj.getPreferredSize());
		btnGen.addActionListener(b);
		this.Grid(masterPanel, btnGen, 4, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,5,5); 
		
		
		btnClear = new JButton("C");
		btnClear.setFont(font);
		btnClear.setPreferredSize(btnAdj.getPreferredSize());
		btnClear.addActionListener(b);
		this.Grid(masterPanel, btnClear, 5, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,5,5); 
		
		
		JButton btnMisc = new JButton("misc");
		btnMisc.setFont(font);
		btnMisc.setPreferredSize(btnAdj.getPreferredSize());
		this.Grid(masterPanel, btnMisc, 6, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,5,5); 
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		lblDetOut = new JLabel("", SwingConstants.CENTER);
		lblDetOut.setFont(font2);
		lblDetOut.setBorder(border);

		lblDetOut.setPreferredSize(btnAdj.getPreferredSize());
		this.Grid(masterPanel, lblDetOut, 7, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,5,5); 
		Dimension label = new Dimension(lblDetOut.getSize());
		lblDetOut.setMinimumSize(label);
		lblDetOut.setMaximumSize(label);	
		
		this.add(masterPanel);
		this.setVisible(true);


	}
	
	public JTextField[][] generateMatrix(boolean editable, int order, JTextField[][] gridArray, boolean display, double[][] inputMatrix) {
		
		matrix = new JPanel();			
		matrix.setLayout(new GridBagLayout());
		GridBagConstraints gridMatrix = new GridBagConstraints();

		gridMatrix.gridwidth = 1;

		gridMatrix.gridheight = 1;

		gridMatrix.weightx = 50;
        
		gridMatrix.weighty = 50;

		gridMatrix.insets = new Insets(1,1,1,1);

		gridMatrix.anchor = GridBagConstraints.CENTER;
        
		gridMatrix.fill = GridBagConstraints.BOTH;

		for(int a = 0; a<order; a++) {
			for(int b = 0; b<order; b++) {
			JTextField entry = new JTextField();
			entry.setHorizontalAlignment(entry.CENTER);
			gridMatrix.gridx = a;
			gridMatrix.gridy = b;
			entry.setEditable(editable);
			if(display==true) entry.setText(String.format("%.2f",inputMatrix[a][b]));
			gridArray [a][b] = entry;
			matrix.add(entry, gridMatrix);	
			
			
			}
		}
		if(editable==true)matrixLeft = matrix;
		if(editable==false)matrixRight = matrix;
		
		return gridArray;
	}
	public double[][] convertToMatrix(JTextField[][] textArray) {
		int order = textArray.length;
		graphicMatrix = new double[order][order];
		for(int a=0; a<order; a++) {
			for(int b=0; b<order; b++) {
				o = new JTextField();
				o = textArray[a][b];
				try{graphicMatrix[b][a] = Double.parseDouble(o.getText());}catch(NumberFormatException | NullPointerException q) {}
			}
			
		}
		return graphicMatrix;
	}
	
	
	
	
	
	public void Grid(JPanel panel, JComponent comp, int xPos, int yPos, int width, int height, int place, int stretch, int x, int y) {
		GridBagConstraints masterGrid = new GridBagConstraints();
		masterGrid.gridx = xPos;
		masterGrid.gridy = yPos;
		masterGrid.gridwidth = width;
		masterGrid.gridheight = height;
		masterGrid.weightx = x;
		masterGrid.weighty = y; 
		masterGrid.insets = new Insets(2,2,2,2);
		masterGrid.anchor = place;
		masterGrid.fill = stretch;
		panel.add(comp, masterGrid);
		
		
	}
public class getButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			A = new matrix(convertToMatrix(myMatrix));
			
			detA = A.findDet();
			adjA = A.findAdj();
			adjugate = new matrix(adjA);

			if(detA!=0) invA = adjugate.sProduct(1/detA); 

			if(e.getSource() == btnAdj){
				masterPanel.revalidate();
				myMatrixO = new JTextField[A.order()][A.order()];
				
			
				generateMatrix(false, A.order(), myMatrixO, true, adjA);
				Grid(masterPanel, matrixRight, 4, 0, 4, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,50,50);
				
			}else if(e.getSource() == btnInv){
				if(detA!=0) {masterPanel.revalidate();
				myMatrixO = new JTextField[A.order()][A.order()];
				generateMatrix(false, A.order(), myMatrixO, true, invA);
				Grid(masterPanel, matrixRight, 4, 0, 4, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,50,50);
				}else {
					JOptionPane.showMessageDialog(null, "This matrix has no inverse.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}else if(e.getSource() == btnDet){
				lblDetOut.setText(String.format("%.4f", detA));
				
			}else if(e.getSource() == btnOrder){
				dispose();
				new theGUI();
				
			}else if(e.getSource() == btnGen){
			
				try{while(true) {
				try {
				String q = JOptionPane.showInputDialog("Please enter the lower bound of your range");
				lower = Double.parseDouble(q);
				
				String r = JOptionPane.showInputDialog("Please enter the upper bound of your range");
				upper = Double.parseDouble(r);
				break;
				}catch(NumberFormatException q) {
					JOptionPane.showMessageDialog(null, "INVALID","ERROR", JOptionPane.ERROR_MESSAGE);
				}}}catch(NullPointerException g){l=false;}
				if(l==true) {
					randomTrix = new double[A.order()][A.order()];
				
				
				for(int c=0; c<A.order(); c++) {
					for(int d=0; d<A.order(); d++){
					randomTrix[c][d] = Math.random() * (upper-lower) + lower; }}
				
				masterPanel.revalidate();
				masterPanel.remove(matrixLeft);
				myMatrix = new JTextField[A.order()][A.order()];
				generateMatrix(true, A.order(), myMatrix, true, randomTrix);
				Grid(masterPanel, matrixLeft, 0, 0, 4, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,50,50);
				
				masterPanel.remove(matrixRight);
				myMatrixO = new JTextField[A.order()][A.order()];
				generateMatrix(false, A.order(), myMatrixO, false, blank);
				Grid(masterPanel, matrixRight, 4, 0, 4, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,50,50);
				}	
				
				
			}else if(e.getSource() == btnClear) {
				masterPanel.revalidate();
				masterPanel.remove(matrixRight);
				
				myMatrixO = new JTextField[A.order()][A.order()];
				generateMatrix(false, A.order(), myMatrixO, false, blank);
				Grid(masterPanel, matrixRight, 4, 0, 4, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,50,50);
				
				masterPanel.remove(matrixLeft);
				myMatrix = new JTextField[A.order()][A.order()];
				generateMatrix(true, A.order(), myMatrix, false, blank);
				Grid(masterPanel, matrixLeft, 0, 0, 4, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH,50,50);
				
				lblDetOut.setText("");
			}
		}
	}
}	


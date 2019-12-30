# matrix-calculator

## matrix_calculator2 (GUI):
Calculates the determinant, adjugate and inverse of n x n real matrices. The program implements the Laplace/cofactor expansion algorithm for finding determinants recursively and completes operations on square matrices of up to order 9 in &lt;6 seconds. Laplace expansion is infeasible for larger matrices as it is of factorial time complexity, so order 9 is the upper limit to which you would use this calculator in any practical sense.

**YOU MIGHT FIND THE CALCULATOR VERY USEFUL FOR LINEAR ALGEBRA HOMEWORK**

## matrix_calculator3 (console):
In addition to the features of version 2: implements QR Decomposition (Q generated using Gram-Schmidt) and its iterative eigenvalue algorithm to converge on the semi-diagonal Schur form of a Matrix A. Iterates through the resulting matrix to return real and complex eigenvalues located from its main diagonal and subdiagonal 2x2 blocks. The eigenvalues of a randomly generated 100x100 matrix are determined quickly in <3 seconds.

Made using previous knowledge of linear algebra (MATH115) and independently learned concepts.

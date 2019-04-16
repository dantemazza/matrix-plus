# matrix-calculator
Version matrix_calculator2 (GUI):
Calculates the determinant, adjugate and inverse of n x n real matrices. Written in Java,
this program implements the Laplace/cofactor expansion algorithm for finding determinants recursively
Completes operations on square matrices of up to order 9 in &lt;6 seconds and 10 in &lt;60 seconds but becomes computationally 
demanding beyond this as the task size compounds factorially.

Version matrix_calculator3 (console):
In addition to the features of version 2: implements QR Decomposition (Q generated using Gram-Schmidt) and its iterative eigenvalue algorithm to converge on the semi-diagonal Schur form of a Matrix A. Iterates through the resulting to return real and complex eigenvalues located from its main diagonal and subdiagonal 2x2 blocks.

Made using previous knowledge of linear algebra (MATH115) and independently learned concepts.

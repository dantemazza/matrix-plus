# matrix-calculator
Version 1.0:
Calculates the determinant, adjugate and inverse of n x n real matrices. Written in Java,
this program implements Laplace/cofactor expansion, an alternative to more common algorithms such as LU/QR decomposition.
Completes operations on square matrices of up to order 9 in &lt;6 seconds and 10 in &lt;60 seconds but becomes computationally 
demanding beyond this as the task size compounds on factorial time complexity.

Version 2.0 (Coming Soon):
Implements QR Decomposition (Q being generated using Gram-Schmidt) and its iterative eigenvalue algorithm to converge on the Schur form of a Matrix A.

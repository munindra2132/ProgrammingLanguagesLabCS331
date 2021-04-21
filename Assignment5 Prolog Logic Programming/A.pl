/*
* CS331 Programming Language Lab
* Assignment 5 - Basic Prolog Programming
*
* Student Details
* Name - Munindra Naik
* Roll - 180101045
*
* Part A - Finding square root of number with given precision 
* Instructions to execute -
*
* On Windows(SWI- Prolog) 
* 1) Open SWI-Prolog on Windows and open the file A.pl by selecting the file to edit under 'File' Menu
* 2) Then select 'Compile buffer' under Compile Menu in the newly opened window or Press Ctrl + C then Ctrl + B 
* 3) Then write the query in the form ' squareroot(X,result,accuracy). ' on the prolog terminal,
*     where X is the number whose square root is to found, result is initialized is with X/2, 
*     and accuracy is the precision we need.
*
** On GNU Prolog
* 1) Change directory on the prolog terminal where the desired file is present using the below command
*        change_directory('file_directory').
* 2) load the file using the below command
*        ['A.pl'].
* 3) use the below format to run
*       squareroot(X,result,accuracy).
*    enter all the values required and give X/2 in result 
*
*  Ex - squareroot(49,25,0.0001).
*/

squareroot(X,Y,Z):-
    number(Y),
    number(Z),
    abs(X - Y*Y) >= Z,
    Y1 is (Y + (X/Y))/2,
    squareroot(X,Y1,Z),
    !.

squareroot(_,Y,_):-
    write(Y).

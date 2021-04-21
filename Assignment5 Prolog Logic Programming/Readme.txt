/*
* CS331 Programming Language Lab
* Assignment 5 - Basic Prolog Programming
*
* Student Details
* Name - Munindra Naik
* Roll - 180101045
*
* Part A - Finding square root of number with given precision 
* Part B - Checking if given list if sublist of another list
*
*
* Instructions to execute -
*
* On Windows(SWI- Prolog) 
* 1) Open SWI-Prolog on Windows and open the file 'A.pl' or 'B.pl' by selecting the file to edit under 'File' Menu
* 2) Then select 'Compile buffer' under Compile Menu in the newly opened window or Press Ctrl + C then Ctrl + B 
* 3) Then write the query in the form 
* for A -       ' squareroot(X,result,accuracy). ' on the prolog terminal,
*     		where X is the number whose square root is to found, result is initialized is with X/2, 
*                and accuracy is the precision we need.
* for B -        'sublist(X,Y)' X and Y are list
*
*
* On GNU Prolog
* 1) Change directory on the prolog terminal where the desired file is present using the below command
*        change_directory('file_directory').
* 2) load the file using the below command
*        ['A.pl']. or ['B'.pl].
* 3) use the below format to run
* for A -   squareroot(X,result,accuracy).
*           enter all the values required and give X/2 in result 
* for B -   sublist(X,Y)
*
*
* Note - on GNU Prolog it may throw error sublist/2 cannot be redifned , 
*        then in that case just replace 'sublist' with 'sublist1' in the code and run.
*
*
*  Ex - for A - squareroot(49,25,0.0001).
*       for B - sublist([a,b],[a,c,d,b,b]).
*/
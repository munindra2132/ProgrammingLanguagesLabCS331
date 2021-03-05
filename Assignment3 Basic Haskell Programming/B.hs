{-
 CS 331 Programming Langugage Lab
 Assignment III : Basic Haskell Programming
 
 Student Details -
 Name - Munindra Naik
 Roll -  180101045
 
 Part B - Find value of nth fibonacci number
 
 Instructions to execute -
 1) On terminal type "ghc -o B B.hs" and hit Enter
 2) After successful compliation type "./B" to execute the program
 3) The program will ask for a number as an input, after the input is given press enter and the result will be printed on terminal
 
  Note - the code contains test cases as specified in the assignment 
        the test values are present inside list named test in line 29 
        when the program is executed after typing ./B then pressing enter the test cases are performed 
        the output of the test values are printed on the screen
        then after that the user specific input is given to the program
 -}

fibo :: (Integer, Integer, Int)-> Integer
fibo (a, b, n) = if n < 2 then do { if n == 0 then a else b } else fibo (b, a+b, n-1)

main :: IO ()
main = do
   putStrLn "Runinng the test cases"
   putStrLn " "
   let test = [2,45,342,534,643,234,643,7,3,4,10]
   let temp = [ (0, 1 , x) | x <- test ]
   let arr = map fibo temp
   let s = zip test arr
   mapM_ print s
   putStrLn " "
   putStrLn "Enter n to find nth fibonacci number : "
   x <- getLine
   let y = (read x :: Int)
   putStrLn "The value is -"
   print (fibo  (0, 1, y) )
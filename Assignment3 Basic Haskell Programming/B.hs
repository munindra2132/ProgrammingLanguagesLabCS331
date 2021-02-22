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
 -}

fibo :: (Integer, Integer) -> Int -> Integer
fibo (a, b) n = if n < 2 then do { if n == 0 then (a) else (b) } else fibo (b, a+b) (n-1)

main = do
   putStrLn "Enter n to find nth fibonacci number : "
   x <- getLine
   let y = (read x :: Int)
   putStrLn "The value is -"
   print (fibo  (0, 1) y )
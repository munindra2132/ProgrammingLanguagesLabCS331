{-
 CS 331 Programming Langugage Lab
 Assignment III : Basic Haskell Programming
 
 Student Details -
 Name - Munindra Naik
 Roll -  180101045
 
 Part A - Square root of a  number
 
 Instructions to execute -
 1) On terminal type "ghc -o A A.hs" and hit Enter
 2) After successful compliation type "./A" to execute the program
 3) The program will ask for a number as an input, after the input is given press enter and the result will be printed on terminal
 -}


-- sqrt(s) in n steps using babylonian method
Sqrt :: Int -> Double -> Double
Sqrt 0 s = 1
Sqrt n s = (x + s/x) / 2 where x = Sqrt (n-1) s

main = do
   putStrLn "Enter n to find to find the square root of n :"
   n <- getLine
   let y = (read n :: Double)
   print $ "sqrt(n) = " ++ show (Sqrt 40 y)
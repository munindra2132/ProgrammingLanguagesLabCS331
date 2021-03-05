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
 
 Note - the code contains test cases as specified in the assignment 
        the test values are present inside list named test in line 28 
        when the program is executed after typing ./A then pressing enter the test cases are performed 
        the output of the test values are printed on the screen
        then after that the user specific input is given to the program
 -}


-- sqrt(s) in n steps using babylonian method
bsqrt :: (Int , Double) -> Double
bsqrt (0 ,s) = 1
bsqrt (n ,s) = (x + s/x) / 2 where x = bsqrt (n-1 ,s)

main :: IO ()
main = do
   putStrLn "Running test cases :"
   let test = [4,56,16,78,98,5253,75453.534,65456,6434.64564]
   let arr = [ (40, x) | x <- test]
   --print test
   --print arr
   let res = map bsqrt arr
   let s = zip test res
   putStrLn "Test Input --- Test Output"
   mapM_ print s
   putStrLn " "
   putStrLn "Enter n to find to find the square root of n :"
   n <- getLine
   let y = (read n :: Double)
   print $ "sqrt(n) = " ++ show ( bsqrt (40 ,y) )
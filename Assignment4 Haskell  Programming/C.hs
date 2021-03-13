{-
 CS 331 Programming Langugage Lab
 Assignment IV :  Haskell Programming
 
 Student Details -
 Name - Munindra Naik
 Roll -  180101045
 
 Part C - Creating BST of the list and printing Pre-order, Post-order and inorder traversal
 
 Instructions to execute -
 1) On terminal type "ghc -o C C.hs" and hit Enter
 2) After successful compliation type "./C" to execute the program
 3) The program will ask for input, after the input is given press enter and the result will be printed on terminal
 
 -}

--BST data type declaration

data Tree a = Nil | Node (Tree a) a (Tree a) deriving Show

empty :: Tree a -> Bool
empty Nil = True
empty  _  = False

-- Inserting in BST
insertInTree :: Ord a => Tree a -> a -> Tree a
insertInTree Nil x = Node Nil x Nil
insertInTree (Node lefttree v righttree) x
   | v == x = Node lefttree v righttree
   | v < x = Node lefttree v (insertInTree righttree x)
   | v > x = Node (insertInTree lefttree x) v righttree

--creating tree from list
crtreefromlist :: Ord a => [a] -> Tree a
crtreefromlist [] = Nil
crtreefromlist (h:t) = ctree2 (Node Nil h Nil) t
    where
       ctree2 tr [] = tr
       ctree2 tr (h:t) = ctree2 (insertInTree tr h) t

--Pre order traversal
pre :: Tree a -> [a]
pre Nil = []
pre (Node a l r) = [l] ++ pre a ++ pre r

--Post order traversal
post :: Tree a -> [a]
post Nil = []
post (Node a l r) = post a ++ post r ++ [l]
 
-- In-order traversal
inorder :: Tree a -> [a]
inorder Nil = []
inorder (Node a l r) = inorder a ++ [l] ++ inorder r

main :: IO ()
main = do
   putStrLn "Enter the input in the form [ a, b, c, d] - "
   input <-getLine 
   let arr = read input :: [Int]
   let x = pre (crtreefromlist arr)
   let y = post (crtreefromlist arr)
   let z = inorder (crtreefromlist arr)
   putStr "Pre-order traversal is - "
   print x
   putStr "Post-order traversal is - "
   print y
   putStr "In-order traversal is - "
   print z
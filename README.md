Lineage Tree program receives pairs child-parent and creates simple tree like string output where the oldest member
of the tree is printed first, and then its children and so on.  

Application uses Maven to compile and get .jar package.  

0) Clone or download the lineage-tree from master branch.  
	git clone https://github.com/stimac95/lineage-tree.git  

1) To compile the application position the pointer to project root ( ./lineage-tree/ ):  
	mvn package  
	
2) To run the application:  
	position pointer to ./lineage-tree/target/  
	java -jar lineage-tree-1.0-SNAPSHOT.jar ../doc/lineage1.txt       # to run lineage1.txt input  
	
3) To run tests only:  
	mvn clean test  

----------------------Example Outputs:------------------------------------------------  
WARNING: Github doesn't store the proper formatting of this file.  
To see the proper output open this file in text editor.  
java -jar lineage-tree-1.0-SNAPSHOT.jar ../doc/lineage1.txt  
----------------------../doc/lineage1.txt---------------------------------------------  
Ivan  
    Adam  
        Stjepan  
            Marko  
            Robert  
    Fran  
Luka  
    Leopold  
	
-----------------------../doc/lineage2.txt---------------------------------------------  
Bad state occured: 
Loop detected in lineage for nodes: Parent: Robert Child: Ivan

	
-----------------------../doc/lineage3_2Parents.txt------------------------------------    
Ivan
    Adam
        Stjepan
            Marko
            Robert
    Fran
Luka
    Leopold
Michelle
    Marko
	
-----------------------../doc/lineage4_pairs.txt--------------------------------------   
B
    A
D
    C
F
    E
H
    G
J
    I
L
    K
N
    M
	
-----------------------../doc/lineage5_loop.txt---------------------------------------	
Bad state occured: 
Loop detected in lineage for nodes: Parent: D Child: A


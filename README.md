Lineage Tree program receives pairs child-parent and creates simple tree like string output where the oldest member
of the tree is printed first, and then its children and so on.  

Application uses Maven to compile and get .jar package.  

1) To compile the application use:  
	mvn package  
	
2) To run the application:  
	position yourself to ./lineage-tree/target/  
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
lineage.exception.LineageLoopException: Loop detected in lineage for nodes:Parent: Robert Child: Ivan
	at lineage.LineageForest.addNode(LineageForest.java:47)
	at main.Main.main(Main.java:29)
	
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
lineage.exception.LineageLoopException: Loop detected in lineage for nodes:Parent: D Child: A
	at lineage.LineageForest.addNode(LineageForest.java:47)
	at main.Main.main(Main.java:29)

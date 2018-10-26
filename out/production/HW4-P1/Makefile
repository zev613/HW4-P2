FLAGS = -cp .:./GridLocation.jar



part1:
	javac $(FLAGS) Maze.java

run-part1:
	java $(FLAGS) Maze mazefile1.txt



midway:
	javac $(FLAGS) Maze.java MazeSolver.java StackAgenda.java Agenda.java

run-midway:
	java $(FLAGS) MazeSolver mazefile1.txt



final:
	javac $(FLAGS) *.java

run-final:
	java $(FLAGS) MazeGUI mazefile1.txt q



clean:
	rm *.class

class SnakesAndLaddersRules{
	public static final int LAST_CELL = 100
	
	public static final boolean IN_PROGRESS = false
	public static final boolean COMPLETE = true

	def static snakes = [16: 6, 64: 60, 87: 24, 49: 11 ]

	def static ladders = [1: 38, 40: 42, 9:31, 51: 67, 71: 81]
	
	static boolean isGameOver(int currentCell){
		currentCell == LAST_CELL
	}
	
	static int nextCell(int currentCell, int steps){
		currentCell  + steps <= 100 ? currentCell  + steps : currentCell
	}
	
	static int applySnakes(int currentCell){
		currentCell in snakes.keySet() ? snakes[currentCell]  :currentCell
	}
	
	static int applyLadders(int currentCell){
		currentCell in ladders.keySet() ? ladders[currentCell]  :currentCell
	}
	
	static nextMove = SnakesAndLaddersRules.&nextCell >> SnakesAndLaddersRules.&applySnakes >> SnakesAndLaddersRules.&applyLadders
}
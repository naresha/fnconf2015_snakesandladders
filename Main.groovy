def positions = [0, 0, 0, 0]
boolean gameOver = false
int player = 0
def random = new Random()
while(!gameOver){
	int rolledValue = random.nextInt(6)  + 1
	positions[player] = SnakesAndLaddersRules.nextMove(positions[player], rolledValue)
	println "Player ${player} rolled ${rolledValue} to move to ${positions[player]}"
	gameOver = SnakesAndLaddersRules.isGameOver(positions[player])
	player = (player == positions.size() - 1) ? 0 : player + 1
}
println "--- Game over --- "
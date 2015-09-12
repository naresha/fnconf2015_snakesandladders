import spock.lang.Specification
import spock.lang.Unroll
import static SnakesAndLaddersRules.IN_PROGRESS
import static SnakesAndLaddersRules.COMPLETE

class SnakesAndLaddersSpec extends Specification{
	def "test it"(){
		expect:
		true == true
	}
	
	@Unroll
	def "a game is over when any player reaches final cell first."(){
		given:
		currentCell
		
		when:
		def gameStatus = SnakesAndLaddersRules.isGameOver(currentCell)
		
		then:
		expectedStatus == gameStatus
		
		where:
		currentCell | expectedStatus
		0 | IN_PROGRESS
		SnakesAndLaddersRules.LAST_CELL | COMPLETE
	}
	
	@Unroll
	def "a player can arrive at a new position by adding the #steps to #currentCell from the throw of dice"(){
		given:
		currentCell 
		steps
		
		when:
		def newCell = SnakesAndLaddersRules.nextCell(currentCell, steps)
		
		then:
		expectedCell == newCell
		
		where:
		currentCell | steps | expectedCell
		0 | 2 | 2
		SnakesAndLaddersRules.LAST_CELL - 2 | 2 | SnakesAndLaddersRules.LAST_CELL
		SnakesAndLaddersRules.LAST_CELL - 1 | 2 | SnakesAndLaddersRules.LAST_CELL - 1
	}
	
	@Unroll
	def "a player is demoted to tail position #expectedCell of snake when they arrive at a cell containing snake’s head #currentCell"(){
		given:
		currentCell
		
		when:
		def newCell = SnakesAndLaddersRules.applySnakes(currentCell)
		
		then:
		expectedCell == newCell
		
		where:
		[currentCell, expectedCell] << SnakesAndLaddersRules.snakes.collect{k, v -> [k, v]}
	}
	
	@Unroll
	def "a player is promoted to head position of ladder when they arrive at a cell containing ladder’s tail"(){
		given:
		currentCell
		
		when:
		def newCell = SnakesAndLaddersRules.applyLadders(currentCell)
		
		then:
		expectedCell == newCell
		
		where:
		[currentCell, expectedCell] << SnakesAndLaddersRules.ladders.collect{k, v -> [k, v]}
	}
	
	@Unroll
	def "move with snakes and ladders"(){
		given:
		currentCell
		steps
		
		when:
		def newCell = SnakesAndLaddersRules.nextMove(currentCell, steps)
		
		then:
		expectedCell == newCell
		
		where:
		currentCell | steps | expectedCell
		0 | 1 | 38
		0 | 2 | 2
		15  | 1 | 6
	}
}
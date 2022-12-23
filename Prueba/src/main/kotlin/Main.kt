/*
Mars Rover Kata.

– Develop an api that moves a rover around a grid.
– You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
– The rover receives a character array of commands.
– The only commands you can give the rover are f,b,l, and r.

Work on:
– Implement commands that move the rover forward/backward (f,b).
– Implement commands that turn the rover left/right (l,r).

Bonus:
– Implement wrapping from one edge of the grid to another. (planets are spheres after all)
– Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.

Here is an example:
– Let’s say that the rover is located at 0,0 facing North on a 100×100 grid.
– Given the command “ffrff” would put the rover at 2,2 facing East.


Rules
- No red phases while refactoring.
- Be careful about edge cases and exceptions. We can not afford to lose a mars rover, just because the developers overlooked a null pointer.

Mars Rover Kata.
Credit http://amirrajan.net/Blog/code-katas-mars-rover
* */
fun main(args: Array<String>) {
    val positionInitial = Pair(0, 0)
    val commands = "ffrff"

    val rover = Rover(x = positionInitial.first, y = positionInitial.second, direction = Direction.North)
    commands.forEach { command ->
        when {
            command.equals(Actions.Forward.code, true) -> {
                rover.moveForward()
            }
            command.equals(Actions.Backward.code, true) -> {
                rover.moveBackward()
            }
            command.equals(Actions.TurnLeft.code, true) -> {
                rover.turnLeft()
            }
            command.equals(Actions.TurnRight.code, true) -> {
                rover.turnRight()
            }
        }
    }

    println(rover)
}

sealed class Actions(val code: Char) {
    object Forward : Actions('f')
    object Backward : Actions('b')
    object TurnLeft : Actions('l')
    object TurnRight : Actions('r')
}

data class Rover(
    var x: Int,
    var y: Int,
    var direction: Direction
) {

    fun moveForward() {
        when(direction) {
            is Direction.North -> {
                y++
            }
            is Direction.South -> {
                y--
            }
            is Direction.East -> {
                x++
            }
            is Direction.West -> {
                x--
            }
        }
    }

    fun moveBackward() {
        when(direction) {
            is Direction.North -> {
                y--
            }
            is Direction.South -> {
                y++
            }
            is Direction.East -> {
                x--
            }
            is Direction.West -> {
                x++
            }
        }
    }

    fun turnLeft() {
        direction = when(direction) {
            is Direction.North -> {
                Direction.West
            }
            is Direction.South -> {
                Direction.East
            }
            is Direction.East -> {
                Direction.North
            }
            is Direction.West -> {
                Direction.South
            }
        }
    }

    fun turnRight() {
        direction = when(direction) {
            is Direction.North -> {
                Direction.East
            }
            is Direction.South -> {
                Direction.West
            }
            is Direction.East -> {
                Direction.South
            }
            is Direction.West -> {
                Direction.North
            }
        }
    }

    override fun toString(): String {
        return "$x,$y facing ${direction.code}"
    }
}

sealed class Direction(val code: String) {
    object North : Direction("N")
    object South : Direction("S")
    object East : Direction("E")
    object West : Direction("W")
}
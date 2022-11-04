package lnu.ooad.dices.game;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;

/**
 * Requirement- Play a Dice-Game
 * The dice(has value from 1 to 6) are rolled and the result is presented.
 * If the sum is 7 then the player wins.
 *  => Two dices are rolled and if the sum is 7 then the player wins
 */
public class DiceTest {
    public static void main(String[] args) throws IOException {
        DiceGame g = new DiceGame();
        System.out.println("Any key to play 'q' to quit");
        while (getInputChar() != 'q') {
            if (g.play())
                System.out.println("Winner...");
            else
                System.out.println("Sorry...");
        }
    }
    private static char getInputChar() throws IOException {
        int c= System.in.read();
        while (c == '\r' || c =='\n'){
            c = System.in.read();
        }
        return (char) c;
    }
}

@Data
final class Dice {
    private int value;

    public void roll() {
        this.value = (int)(Math.random() * 171717.0) % 6 + 1;
    }
}

@Data
final class DiceGame {
    private Dice d1;
    private Dice d2;

    public DiceGame() {
        d1 = new Dice();
        d2 = new Dice();
    }

    public boolean play() {
        //role each dice and get the value and check if sum is 7
        d1.roll();
        d2.roll();

        return d1.getValue() + d2.getValue() == 7;
    }
}

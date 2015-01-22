/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supergracz;

import java.util.List;
import java.util.Random;
import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

public class SuperPlayer extends Player {

    private Random random=new Random(0xdeadbeef);
    
    @Override
    public String getName() {
        return "Kamil Walkowiak 109714";
    }
    
    private Move alphaBeta(int imm, Color col, Board board) {
    	Move best = null;
    	if(imm != 0) {
    		List<Move> moves = board.getMovesFor(col);
    		for(int i = 0; i < moves.size(); i++) {
    			Board act_board = board;
    			act_board.doMove(moves.get(i));
    			if(col == Color.PLAYER1)
    				col = Color.PLAYER2;
    			else
    				col = Color.PLAYER1;
    			alphaBeta(imm - 1, col, act_board);
    		}
    		
    	} else {
    		List<Move> moves = board.getMovesFor(col);
    		for(int i = 0; i < moves.size(); i++) {
    			Board act_board = board;
    			act_board.doMove(moves.get(i));
    			//OCENA HEURYSTYCZNA!!!
    		}
    	}
    	return best;
    }

    @Override
    public Move nextMove(Board b) {
    	getTime();
        List<Move> moves = b.getMovesFor(getColor());
        return moves.get(random.nextInt(moves.size()));
    }
}
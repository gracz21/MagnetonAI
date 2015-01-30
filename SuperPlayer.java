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
        return "Kamil Walkowiak 109714 Jarasz Łojka 114816";
    }
    
    // private Move alphaBeta(int imm, Color col, Board board) {
    // 	Move best = null;
    // 	if(imm != 0) {
    // 		List<Move> moves = board.getMovesFor(col);
    // 		for(int i = 0; i < moves.size(); i++) {
    // 			Board act_board = board;
    // 			act_board.doMove(moves.get(i));
    // 			if(col == Color.PLAYER1)
    // 				col = Color.PLAYER2;
    // 			else
    // 				col = Color.PLAYER1;
    // 			alphaBeta(imm - 1, col, act_board);
    // 		}
    		
    // 	} else {
    // 		List<Move> moves = board.getMovesFor(col);
    // 		for(int i = 0; i < moves.size(); i++) {
    // 			Board act_board = board;
    // 			act_board.doMove(moves.get(i));
    // 			//OCENA HEURYSTYCZNA!!!
    // 		}
    // 	}
    // 	return best;
    // }

    private Move randomMove(Board board) {
        List<Move> moves = board.getMovesFor(getColor());
        int[] notes = new int[moves.size()];
        int loss = 0;
        for (int i = 0; i < moves.size(); i++) {
            notes[i] = 0;
            Board tmp = board.clone();
            tmp.doMove(moves.get(i));
            Color other = tmp.getWinner(getColor());
            if (other == getColor())
                return moves.get(i);
            if (other == NULL) {
                List<Move> otherMoves = tmp.getMovesFor(getOpponent(getColor()));
                for (int j = 0; j < otherMoves.size(); j++) {
                    Board tmp2 = tmp.clone();
                    tmp2.doMove(otherMoves.get(j));
                    if (tmp2.getWinner(getOpponent(getColor())) ==
                        getOpponent(getColor())) {
                        notes[i] = -1;
                        loss++;
                        break;
                    }
                }
            }
        }
        Random r = new Random(System.currentTimeMillis())
        if (loss == moves.size())
            return moves.get(r.nextInt(moves.size()));
        else {
            int zeros = moves.size() - loss;
            int index = r.nextInt(zeros);
            int cnt = 0;
            for (i = 0; i < moves.size(); i++)
                if (notes[i] == 0) {
                    if (cnt == index)
                        return moves.get(i);
                    cnt++;
                }
        }
        return NULL;
    }

    @Override
    public Move nextMove(Board b) {
    	getTime();
        List<Move> moves = b.getMovesFor(getColor());
        return moves.get(random.nextInt(moves.size()));
    }
}

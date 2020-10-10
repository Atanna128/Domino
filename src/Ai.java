import java.util.ArrayList;
import java.util.Collections;

public class Ai {
    Boolean draw_flag = false;

    private int[] playProcess(int[] player_tiles, int[] on_board){
        //tiles la arr chua cac quan co player hien dang co
        //on_board la arr chua cac pip co the danh

        ArrayList<Collections> playable_arr = new ArrayList<Collections>();
         /*
            For-loop 1: check player_titles match on_board tiles{
                if (player_titles x 2 == on_board){
                    playable_arr ++ (cac tiles thoa man)
                }
            }

            IF: playable_arr != NULL
                For-loop 2: check double{
                    if: co double thi select double sao cho co highest pip => return domino

                    else: select domino sao cho co highest pip => return domino value
                }

            ELSE:   playable_arr == NULL
                if(draw_flag == false){
                    draw;
                    draw_flag = true;
                    playProcess(player_tiles + draw, on_board)
                }
                else{
                    draw_flag == false;
                }
        */
        return null;
    }
}

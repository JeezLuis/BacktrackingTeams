import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static ArrayList<ArrayList<Integer>> numeros;
    private static ArrayList<ArrayList<Integer>> teams;
    private static ArrayList<ArrayList<ArrayList<Integer>>> races;

    public static void main(String[] args) {

        numeros = new ArrayList<>();
        teams = new ArrayList<>();
        races = new ArrayList<>();

        int num;
        for(int i = 0; i<3 ; i++){
            numeros.add(new ArrayList<>());
            num = 10*(i+1);
            for(int j = 0; j<3 ; j++) numeros.get(i).add(num+j);
        }

        int max_size = numeros.get(0).size();

        backtrackingEquips(new ArrayList<Integer>(),0);
        backtrackingRaces(new ArrayList<>(),max_size,0,0);

        System.out.println();

    }

    private static void backtrackingEquips(ArrayList<Integer> combination, int level){
        if (level == numeros.size()) {
            teams.add(combination);
        }
        else{
            for (Integer num: numeros.get(level)) {
                ArrayList<Integer> aux = new ArrayList(combination);
                aux.add(num);
                backtrackingEquips(aux,level+1);
            }
        }
    }

    static void backtrackingRaces(ArrayList<ArrayList<Integer>> current_combination, int max_size, int count,int initial) {
        if (max_size == count) {
            races.add(current_combination);
        }
        else {
            for (int i = initial; i < teams.size(); i++) {
                ArrayList<ArrayList<Integer>> aux = new ArrayList(current_combination);
                aux.add(teams.get(i));
                if(checkCombination(aux)) backtrackingRaces(aux, max_size, count + 1, initial + 1);
            }
        }
    }

    private static boolean checkCombination(ArrayList<ArrayList<Integer>> current_combination){
        for (int a = 0; a < numeros.size(); a++) {
            for (int i = 0; i < current_combination.size(); i++) {
                for (int j = 0; j < current_combination.size(); j++) {
                    if (current_combination.get(i).get(a) == current_combination.get(j).get(a) && i != j) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

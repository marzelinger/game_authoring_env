/**
 * @author mpz5...
 */

package Compiler;

import java.util.*;

public class TurtleManager {

    private Map<Integer, Turtle> allTurtles;

    public TurtleManager(){
        allTurtles = new HashMap();

    }

    public List<Turtle> getAllTurtles() {
        List<Turtle> turts = new ArrayList<Turtle>();
        Set<Integer> IDs = allTurtles.keySet();
        for(int i : IDs){
            //get the right ID
            turts.add(allTurtles.get(i));
        }
        return turts;
    }


    public List<Turtle> createTurtle(int ID){
        if(allTurtles.containsKey(ID)){
            throw new Error("Turtle already exists");
        }
        Turtle newTurtle = new Turtle(ID);
        allTurtles.put(ID, newTurtle);
        return getActiveTurtles();
    }

    public Collection<Integer> getExistingIDs(){
        return allTurtles.keySet();
    }

    public boolean checkTurtleExists(int ID){
        return allTurtles.containsKey(ID);
    }

    public void activateTurtle(int ID){
        allTurtles.get(ID).activateTurtle();
    }

    public List<Turtle> getActiveTurtles(){
        List<Turtle> activeTurtles = new ArrayList();
        for(Turtle t : allTurtles.values()){
            if(t.checkActive()){
                activeTurtles.add(t);
            }
        }
        return activeTurtles;
    }


}

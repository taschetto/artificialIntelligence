// Environment code for project tutorial

import jason.asSyntax.*;
import jason.environment.*;
import java.util.logging.*;

public class MyEnvironment extends Environment {

    private Logger logger = Logger.getLogger("tutorial."+MyEnvironment.class.getName());
    
    int agPos[] = new int[] {1,1};

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
//        addPercept(Literal.parseLiteral("percept(demo)"));
        updatePosition(null);
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
    	if(action.getFunctor().equals("move")) {
    		doMove(agName, action);
    		updatePosition(agName);
    	} else {
    		logger.info("executing: "+action+", but not implemented!");
    	}
        return true;
    }
    
    private final void updatePosition(String agName) {
    	String position = "pos("+agPos[0]+","+agPos[1]+")";
    	clearAllPercepts();
    	if(agName != null) {
    		//removePerceptsByUnif(agName, Literal.parseLiteral("pos(X,Y)"));
    		addPercept(agName, Literal.parseLiteral(position));
    	} else {
    		//removePerceptsByUnif(Literal.parseLiteral("pos(X,Y)"));
    		addPercept(Literal.parseLiteral(position));
    	}
    }
    
    private final void doMove(String agName, Structure action) {
    	Term t = action.getTerm(0);
    	String direction = t.toString();
    	if(direction.equals("left")) {
    		agPos[0]--; 
    	} else if(direction.equals("right")) {
    		agPos[0]++;
    	} else if(direction.equals("up")) {
    		agPos[1]++;
    	} else if(direction.equals("down")) {
    		agPos[1]--;
    	} 
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}

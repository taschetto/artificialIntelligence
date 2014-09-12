// Environment code for project bossW.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import java.util.logging.*;
import java.util.*;
import jason.JasonException;

public class MyEnvironment extends Environment {

    private Logger logger = Logger.getLogger("bossW.mas2j."+Environment.class.getName());
	
	private ArrayList<Double> dones;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        addPercept(Literal.parseLiteral("percept(demo)"));
		this.dones = new ArrayList<Double>();
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
		if (((Literal)action).getFunctor().equals("do"))
		{
			Term t = action.getTerm(0);
			if (t.isNumeric())
			{
				double num = 0.0;
				try
				{
					num = ((NumberTerm)t).solve();
				}
				catch (Exception e)
				{
				}
				
				if (!this.dones.contains(num))
				{
					logger.info("doing task: " + t); 
					addPercept(Literal.parseLiteral("done(" + t + ")"));
					this.dones.add(num);
				}
			}
		}
		else
			logger.info("executing: "+action+", but not implemented!");
        return true;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}


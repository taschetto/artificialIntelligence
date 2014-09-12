package edu.pucrs.ai.aop;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import jason.asSyntax.*;
import jason.environment.Environment;

public class EnvironmentD extends Environment {
	private Logger logger = Logger.getLogger("Lab1.mas2j."
			+ EnvironmentD.class.getName());
	private final HashMap<String, String> positions = new HashMap<String, String>();
	private final Random dice = new Random();

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		super.init(args);
		addPercept(ASSyntax.createLiteral("position(init)"));
	}

	@Override
	public boolean executeAction(String agName, Structure action) {
		logger.info(agName + " executing: " + action.getFunctor() + "/"
				+ action.getArity());
		dice.nextBoolean();
		//Deterministic
		if (action.getFunctor().equals("shout")) {
			logger.info(agName + " shouts " + action.getTerms().toString());
			// String heard = action.getTerms().toString();
			// addPercept(ASSyntax.createLiteral("hear",ASSyntax.createString(heard)));
			//Completely un-observable
		} else if (action.getFunctor().equals("move")) {
			if (!positions.containsKey(agName)) {
				positions.put(agName, "init");
			}
			String position = action.getTerms().toString();
			positions.put(agName, position);
			logger.info(agName + " moves to " + position);
//			addPercept(ASSyntax.createLiteral("position",
//					action.getTermsArray()));
		} else {
			logger.info(action.getFunctor() + "/" + action.getArity()
					+ " not implemented!");
		}
		return true;
	}

	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}
}

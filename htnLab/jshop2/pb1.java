import java.util.LinkedList;
import JSHOP2.*;

public class pb1
{
	private static String[] defineConstants()
	{
		String[] problemConstants = new String[6];

		problemConstants[0] = "portoalegre";
		problemConstants[1] = "poa";
		problemConstants[2] = "gru";
		problemConstants[3] = "saopaulo";
		problemConstants[4] = "lhr";
		problemConstants[5] = "london";

		return problemConstants;
	}

	private static void createState0(State s)	{
		s.add(new Predicate(3, 0, new TermList(TermConstant.getConstant(5), TermList.NIL)));
		s.add(new Predicate(1, 0, new TermList(TermConstant.getConstant(6), new TermList(TermConstant.getConstant(5), TermList.NIL))));
		s.add(new Predicate(1, 0, new TermList(TermConstant.getConstant(7), new TermList(TermConstant.getConstant(8), TermList.NIL))));
		s.add(new Predicate(1, 0, new TermList(TermConstant.getConstant(9), new TermList(TermConstant.getConstant(10), TermList.NIL))));
		s.add(new Predicate(0, 0, new TermList(TermConstant.getConstant(5), new TermList(TermConstant.getConstant(8), TermList.NIL))));
		s.add(new Predicate(0, 0, new TermList(TermConstant.getConstant(5), new TermList(TermConstant.getConstant(10), TermList.NIL))));
		s.add(new Predicate(0, 0, new TermList(TermConstant.getConstant(8), new TermList(TermConstant.getConstant(10), TermList.NIL))));
	}

	public static LinkedList<Plan> getPlans()
	{
		LinkedList<Plan> returnedPlans = new LinkedList<Plan>();
		TermConstant.initialize(11);

		Domain d = new travel();

		d.setProblemConstants(defineConstants());

		State s = new State(5, d.getAxioms());

		JSHOP2.initialize(d, s);

		TaskList tl;
		SolverThread thread;

		createState0(s);

		tl = new TaskList(1, true);
		tl.subtasks[0] = new TaskList(new TaskAtom(new Predicate(0, 0, new TermList(TermConstant.getConstant(5), new TermList(TermConstant.getConstant(8), TermList.NIL))), false, false));

		thread = new SolverThread(tl, 1);
		thread.start();

		try {
			while (thread.isAlive())
				Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		returnedPlans.addAll( thread.getPlans() );

		return returnedPlans;
	}

	public static LinkedList<Predicate> getFirstPlanOps() {
		return getPlans().getFirst().getOps();
	}
}
package ai;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
	@Override
	public int compare(Node arg0, Node arg1) {
        Integer cost1 = arg0.F();
        Integer cost2 = arg1.F();
        return cost1.compareTo(cost2);
	}
}

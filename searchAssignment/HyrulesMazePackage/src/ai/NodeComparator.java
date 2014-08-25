package ai;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
        Integer cost1 = arg0.getG() + arg0.getH();
        Integer cost2 = arg1.getG() + arg1.getH();
        return cost1.compareTo(cost2);
	}
}

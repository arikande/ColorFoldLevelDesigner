package game.colorfold.designer.utility;

import org.w3c.dom.Node;

public class XMLUtility {

    public static Node findNode(Node node, String nodeName) {
	int length = node.getChildNodes().getLength();
	for (int i = 0; i < length; i++) {
	    Node subNode = node.getChildNodes().item(i);
	    if (subNode.getNodeType() == Node.ELEMENT_NODE) {
		if (nodeName.equals(subNode.getNodeName())) {
		    return subNode;
		}
	    }
	}
	return null;
    }
}

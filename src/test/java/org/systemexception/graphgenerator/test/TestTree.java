/**
 * @author leo
 * @date 20/04/2015 20:32
 */
package org.systemexception.graphgenerator.test;

import org.junit.Test;
import org.systemexception.graphgenerator.exception.EdgeException;
import org.systemexception.graphgenerator.exception.NodeException;
import org.systemexception.graphgenerator.exception.TreeException;
import org.systemexception.graphgenerator.model.Node;
import org.systemexception.graphgenerator.model.Tree;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTree {

	private Tree sut;
	double totalNodes;

	@Test
	public void createSimpleTree() throws NodeException, EdgeException, TreeException {
		generateTree(1, 3);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createTwoLevelTree() throws NodeException, EdgeException, TreeException {
		generateTree(2, 2);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createFourLevelTree() throws NodeException, EdgeException, TreeException {
		generateTree(2, 4);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void createModerateTree() throws NodeException, EdgeException, TreeException {
		generateTree(5, 6);
		assertTrue(totalNodes == sut.getNodes().size());
	}

	@Test
	public void checkIfRootHasChilds() throws NodeException, EdgeException, TreeException {
		generateTree(1, 3);
		ArrayList<Node> childNodes = sut.getChildNodes(sut.getNodes().get(0));
		assertTrue(!childNodes.isEmpty());
	}

	@Test
	public void dontRemoveNodeWithChilds() throws NodeException, TreeException, EdgeException {
		generateTree(1, 3);
		Node rootNode = sut.getNodes().get(0);
		sut.removeNode(rootNode);
		assertTrue(sut.getNodes().contains(rootNode));
	}

	@Test
	public void emptyTree() throws NodeException, TreeException, EdgeException {
		generateTree(3, 3);
		while (sut.getNodes().size() > 0) {
			for (int i = 0; i < sut.getNodes().size(); i++) {
				Node nodeToRemove = sut.getNodes().get(i);
				sut.removeNode(nodeToRemove);
			}
		}
		assertTrue(sut.getNodes().size() == 0);
		assertTrue(sut.getEdges().size() == 0);
	}

	@Test
	public void emptyTreeInternalMethod() throws NodeException, TreeException, EdgeException {
		generateTree(4, 6);
		sut.emptyTree();
		assertTrue(sut.getNodes().size() == 0);
		assertTrue(sut.getEdges().size() == 0);
	}

	@Test
	public void getParentNode() throws NodeException, TreeException, EdgeException {
		generateTree(1,1);
		Node childNode = sut.getNodes().get(sut.getNodes().size() - 1);
		Node parentNode = sut.getNodes().get(0);
		assertEquals(sut.getParentNode(childNode), parentNode);
		assertEquals(sut.getParentNode(parentNode), null);
	}


	/**
	 * Generates a tree
	 *
	 * @param height       the height of the tree level we're on
	 * @param childPerNode how many childs per node
	 * @throws NodeException
	 * @throws EdgeException
	 * @throws TreeException
	 */
	private void generateTree(int height, int childPerNode) throws NodeException, EdgeException, TreeException {
		totalNodes = (Math.pow(childPerNode, height + 1) - 1) / (childPerNode - 1);
		sut = new Tree(height, childPerNode);
	}
}
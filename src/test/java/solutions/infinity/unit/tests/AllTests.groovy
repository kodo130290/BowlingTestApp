package test.java.solutions.infinity.unit.tests

import junit.framework.Test
import junit.textui.TestRunner

class AllTests {
	static Test suite() {
		def allTests = new GroovyTestSuite()
		allTests.addTestSuite(BowlingGameTests.class)
		return allTests
	 }
	
	static void main(args) {
		TestRunner.run(AllTests.suite())
	}
}

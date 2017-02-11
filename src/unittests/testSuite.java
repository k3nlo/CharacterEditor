package unittests;

import junit.framework.*;

public class testSuite extends TestSuite
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test suite");
        suite.addTestSuite(main.Tests.class);
        suite.addTestSuite(player.PlayerTests.class);
        
        return suite;
    }
}

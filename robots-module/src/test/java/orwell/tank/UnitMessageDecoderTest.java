package orwell.tank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
@RunWith(JUnit4.class)
public class UnitMessageDecoderTest {
    private final static Logger logback = LoggerFactory.getLogger(UnitMessageDecoderTest.class);


    @org.junit.Before
    public void setUp() throws Exception {
        logback.debug(">>>>>>>>> IN");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}


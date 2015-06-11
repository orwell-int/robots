package orwell.tank;

import lejos.mf.common.UnitMessage;
import lejos.mf.common.UnitMessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import orwell.tank.actions.Move;
import orwell.tank.actions.StopTank;

import static org.junit.Assert.*;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
@RunWith(JUnit4.class)
public class UnitMessageDecoderTest {
    private final static Logger logback = LoggerFactory.getLogger(UnitMessageDecoderTest.class);
    private final static String MESSAGE_STOP = "stop";
    private final static String MESSAGE_INPUT_MOVE = "move 50.5 100.0";
    private final static String MESSAGE_INPUT_FIRE = "fire true false";
    private final static String MESSAGE_STOP_PRG = "stopPrg";


    @org.junit.Before
    public void setUp() throws Exception {
        logback.debug(">>>>>>>>> IN");
    }

    @Test
    public void testParseFrom_stop() throws Exception {
        UnitMessage message = new UnitMessage(UnitMessageType.Command, MESSAGE_STOP);
        IActionVisitor actionVisitor = UnitMessageDecoder.parseFrom(message);
        assertTrue(actionVisitor instanceof StopTank);
    }

    @Test
    public void testParseFrom_input() throws Exception {
        UnitMessage message = new UnitMessage(UnitMessageType.Command, MESSAGE_INPUT_MOVE);
        IActionVisitor actionVisitor = UnitMessageDecoder.parseFrom(message);
        assertTrue(actionVisitor instanceof Move);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}


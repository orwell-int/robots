package orwell.tank;

import lejos.mf.common.UnitMessage;
import lejos.mf.common.UnitMessageType;
import orwell.tank.actions.*;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class UnitMessageDecoder {
    private static final char UNIT_MESSAGE_SEPARATOR = ' ';

    public static IActionVisitor parseFrom(UnitMessage msg) {
        if (UnitMessageType.Command != msg.getMsgType())
            return null;
        List<String> payloadArray = Utils.split(UNIT_MESSAGE_SEPARATOR, msg.getPayload());

        if (0 == payloadArray.size())
            return null;
        String payloadHeader = payloadArray.get(0);
        List<String> payloadBody;
        if (1 == payloadArray.size())
            payloadBody = null;
        else
            payloadBody = payloadArray.subList(1, payloadArray.size());
        switch (payloadHeader) {
            case "stop":
                return new StopTank(payloadBody);
            case "stopPrg":
                return new StopProgram(payloadBody);
            case "move":
                return new Move(payloadBody);
            case "fire":
                return new Fire(payloadBody);
            case "game":
                return new GameState(payloadBody);
            default:
                return new NotHandled(payloadBody);
        }
    }
}

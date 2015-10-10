package orwell.tank.exception;

/**
 * Created by Michaël Ludmann on 30/08/15.
 */
public class ParseIniException extends Exception {

    public ParseIniException(String incorrectIniValue) {
        super(incorrectIniValue);
    }
}

package ${PACKAGE_NAME};

import java.security.KeyException;
import java.util.HashMap;

public class ${NAME} extends FuzzyEntry {
    private String precondition;
    private String postcondition;

    public ${NAME}(String precondition, String postcondition) {
        this.precondition = precondition;
        this.postcondition = postcondition;
    }

    @Override
    void putStringChild(String key, String value) throws KeyException {
        switch (key) {
            case "precondition":
                precondition = value;
                break;
            case "postcondition":
                postcondition = value;
                break;
            default:
                throw new KeyException("The key you entered to a ${NAME} (" + key + ") does not exist.");
        }
    }

    @Override
    String getStringChild(String key) throws KeyException {
        switch (key) {
            case "precondition":
                return precondition;
            case "postcondition":
                return postcondition;
            default:
                throw new KeyException("The key you entered to a ${NAME} (" + key + ") does not exist.");
        }
    }

    @Override
    HashMap<String, String> getAttributeMapChild() {
        HashMap<String, String> currentState = new HashMap<String, String>();
        currentState.put("precondition", precondition);
        currentState.put("postcondition", postcondition);
        return currentState;
    }

    @Override
    void clearChild() {
        precondition = "";
        postcondition = "";
    }
}

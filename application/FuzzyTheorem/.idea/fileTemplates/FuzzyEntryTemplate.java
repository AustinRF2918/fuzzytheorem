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
        if (key.equals("precondition")) {
            precondition = value;
        } else if (key.equals("postcondition")) {
            postcondition = value;
        } else {
            throw new KeyException("The key you entered to a ${NAME} (" + key + ") does not exist.");
        }
    }

    @Override
    String getStringChild(String key) throws KeyException {
        if (key.equals("precondition")) {
            return precondition;
        } else if (key.equals("postcondition")) {
            return postcondition;
        } else {
            throw new KeyException("The key you entered to a ${NAME} (" + key + ") does not exist.");
        }
    }

    @Override
    HashMap<String, String> getAttributeMapChild() {
        HashMap currentState = new HashMap();
        currentState.put("precondition", precondition);
        currentState.put("postcondition", postcondition);
    }

    @Override
    void clearChild() {
        precondition = "";
        postcondition = "";
    }
}

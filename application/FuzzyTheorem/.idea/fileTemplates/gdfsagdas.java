#if (${PACKAGE_NAME} != "") package ${PACKAGE_NAME}; #end
#parse("File Header.java")
#* @vtlvariable name="field_list" *#

import java.security.KeyException;
import java.util.HashMap;

public class ${NAME} extends FuzzyEntry {
    #foreach ($field in $field_list)
        private String $field;
    #end

    public ${NAME}(#foreach ($field in ${field_list}) String $field #end) {
        #foreach ($field in ${field_list})
            this.$field = $field;
        #end
    }

    @Override
    void putStringChild(String key, String value) throws KeyException {
        #foreach ($field in $field_list)
            if (key.equals("$field")) {
                $field = value;
                return;
            }
        #end

        throw new KeyException("The key you entered to a Definition (" + key + ") does not exist.");
    }

    @Override
    String getStringChild(String key) throws KeyException {
        #foreach ($field in $field_list)
            if (key.equals("$field")) {
                return $field;
            }
        #end

        throw new KeyException("The key you entered to a Definition (" + key + ") does not exist.");
    }

    // Make sure to implement this in accordance with your
    // classes local methods.
    @Override
    HashMap<String, String> getAttributeMapChild() {
        HashMap currentState = new HashMap();
        #foreach ($field in $field_list)
            currentState.put("$field", $field);
        #end

        return currentState;
    }

    // Make sure to implement this in accordance with your
    // classes local methods.
    @Override
    void clearChild() {
        #foreach ($field in $field_list)
            $field = "";
        #end
    }
}
package zhawmessenger.ui.api.form.validator;

import zhawmessenger.ui.api.validator.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CompoundValidator implements Validator {
    private final List<Validator> validatorList;

    public CompoundValidator() {
        this(new ArrayList<Validator>());
    }

    public CompoundValidator(List<Validator> validatorList) {
        this.validatorList = validatorList;
    }

    public void addValidator(Validator validator) {
        validatorList.add(validator);
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        for (Validator v : validatorList) {
            boolean res = v.validate();
            if ( !res ) {
                valid = false;
            }
        }
        return valid;
    }
}

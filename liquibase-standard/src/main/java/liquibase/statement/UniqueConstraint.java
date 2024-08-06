package liquibase.statement;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueConstraint implements ColumnConstraint {
    @Getter
    private String constraintName;
    /**
     * Default value is true
     */
    private boolean validateUnique = true;
    @Getter
    private final List<String> columns = new ArrayList<>();

    public UniqueConstraint() {

    }

    public UniqueConstraint(String constraintName) {
        this.constraintName = constraintName;
    }

    public UniqueConstraint(String constraintName, boolean validateUnique) {
        this.constraintName = constraintName;
        setValidateUnique(validateUnique);
    }


    public UniqueConstraint addColumns(String... columns) {
        this.columns.addAll(Arrays.asList(columns));

        return this;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public boolean shouldValidateUnique() {
        return validateUnique;
    }

    public void setValidateUnique(boolean validateUnique) {
        this.validateUnique = validateUnique;
    }
}

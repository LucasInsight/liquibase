package liquibase.change.custom;

import liquibase.Scope;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.RollbackImpossibleException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import lombok.Getter;

public class ExampleCustomTaskChange implements CustomTaskChange, CustomTaskRollback {

    @Getter
    private String helloTo;

    @SuppressWarnings({"UnusedDeclaration", "FieldCanBeLocal"})
    private ResourceAccessor resourceAccessor;


    public void setHelloTo(String helloTo) {
        this.helloTo = helloTo;
    }

    @Override
    public void execute(Database database) throws CustomChangeException {
        Scope.getCurrentScope().getLog(getClass()).info("Hello "+getHelloTo());
    }

    @Override
    public void rollback(Database database) throws CustomChangeException, RollbackImpossibleException {
        Scope.getCurrentScope().getLog(getClass()).info("Goodbye "+getHelloTo());
    }

    @Override
    public String getConfirmationMessage() {
        return "Said Hello";
    }

    @Override
    public void setUp() throws SetupException {
        ;
    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        this.resourceAccessor = resourceAccessor;
    }

    @Override
    public ValidationErrors validate(Database database) {
        return new ValidationErrors();
    }
}

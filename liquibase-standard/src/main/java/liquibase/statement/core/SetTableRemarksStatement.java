package liquibase.statement.core;

import liquibase.statement.AbstractSqlStatement;
import lombok.Getter;

public class SetTableRemarksStatement extends AbstractSqlStatement {

    @Getter
    private final String remarks;
    private DatabaseTableIdentifier databaseTableIdentifier = new DatabaseTableIdentifier(null, null, null);

    public SetTableRemarksStatement(String catalogName, String schemaName, String tableName, String remarks) {
        this.databaseTableIdentifier.setCatalogName(catalogName);
        this.databaseTableIdentifier.setSchemaName(schemaName);
        this.databaseTableIdentifier.setTableName(tableName);
        this.remarks = remarks;
    }

    public String getCatalogName() {
        return databaseTableIdentifier.getCatalogName();
    }

    public String getSchemaName() {
        return databaseTableIdentifier.getSchemaName();
    }

    public String getTableName() {
        return databaseTableIdentifier.getTableName();
    }

}

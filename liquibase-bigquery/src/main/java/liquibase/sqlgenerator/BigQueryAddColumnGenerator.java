package liquibase.sqlgenerator;

import liquibase.database.BigqueryDatabase;
import liquibase.database.Database;
import liquibase.sqlgenerator.core.AddColumnGenerator;
import liquibase.statement.core.AddColumnStatement;

public class BigQueryAddColumnGenerator extends AddColumnGenerator {

	@Override
	protected String generateSingleColumnSQL(AddColumnStatement statement, Database database) {
		return super.generateSingleColumnSQL(statement, database).replace(" ADD ", " ADD COLUMN ");
	}

	@Override
	public int getPriority() {
		return BigqueryDatabase.BIGQUERY_PRIORITY_DATABASE;
	}

	@Override
	public boolean supports(AddColumnStatement statement, Database database) {
		return database instanceof BigqueryDatabase;
	}
}
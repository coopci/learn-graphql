package learn.graphql;

import graphql.language.OperationDefinition;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.StaticDataFetcher;

public class JDBCDataFetcher extends StaticDataFetcher {

	public JDBCDataFetcher(Object value) {
		super(value);
	}

    @Override
    public Object get(DataFetchingEnvironment environment) {
    	
    	// TODO generate sql, call db
    	
    	OperationDefinition od = environment.getExecutionContext().getOperationDefinition();
    	
    	
    	
    	
        return super.get(environment);
    }
}

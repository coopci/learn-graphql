package learn.graphql;

import graphql.language.Field;
import graphql.language.OperationDefinition;
import graphql.language.Selection;
import graphql.language.SelectionSet;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.StaticDataFetcher;

public class JDBCDataFetcher extends StaticDataFetcher {

	public JDBCDataFetcher(Object value) {
		super(value);
	}

	
	void printSelections(SelectionSet ss, String ident) {
		if (ss == null)
			return;
		for(Selection<?> s : ss.getSelections()) {
			// s.getChildren()
			if (s instanceof Field) {
				Field f = (Field)s;
				
				System.out.println(ident + f.getName());
				printSelections(f.getSelectionSet(), ident + "\t"); 
				
			}
		}
		
		
	}
    @Override
    public Object get(DataFetchingEnvironment environment) {
    	if (environment.getContext() == null) {
    	
    	}
    	
    	
    	
    	// TODO generate sql, call db
    	
    	OperationDefinition od = environment.getExecutionContext().getOperationDefinition();
    	// od.get
    	printSelections(od.getSelectionSet(), "");
    	
    	
        return super.get(environment);
    }
}

package learn.graphql;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.util.HashMap;

public class HelloWorld {

    public static void main(String[] args) {
        String schema = "type Query{dogs(name:String): Dog hello: String} type Dog{name: String gender:String}";

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        
        HashMap<String, Object> dog1 = new HashMap<String, Object>();
        dog1.put("name", "name of dog1");
        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
                .type("Query", builder -> builder.dataFetcher("dogs", new JDBCDataFetcher(dog1)))
                // .type("Dog", builder -> builder.dataFetcher("name", new StaticDataFetcher("dog1")))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
        // ExecutionResult executionResult = build.execute("{hello}");
        ExecutionResult executionResult = build.execute("query {hello dogs(name:\"sdfs\"){name} }");

        System.out.println(executionResult.getData().toString());
        if (!executionResult.getErrors().isEmpty()) {
        	System.err.println(executionResult.getErrors());	
        }
        
        // Prints: {hello=world}
    }
}

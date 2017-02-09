
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.query.* ;
import org.apache.jena.query.ARQ;
import org.apache.jena.sparql.*;

import java.io.*;

public class Project extends Object {
	
		static final String inputFileName = "./healthcare.rdf";
        
      	public static void main (String args[]) {

		// Create an empty in-memory model 
		Model model = ModelFactory.createDefaultModel();
		
		// use the FileManager to open the bloggers RDF graph from the filesystem
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML file
        model.read( in, "" );
         
		// Create a new query
        
		String queryString = 
			//"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
				"PREFIX ds: <http://data.medicare.gov/resource/tee5-ixt5/>"+
				"SELECT ?state ?quality_of_patient_care_star_rating " +
				"WHERE {" +
				"      ?state ds:quality_of_patient_care_star_rating ?quality_of_patient_care_star_rating . " +
				"      FILTER (?quality_of_patient_care_star_rating >3) . " +
				"	   ?state ds:quality_of_patient_care_star_rating ?quality_of_patient_care_star_rating ." +
				"      }";

		Query query = QueryFactory.create(queryString);
		
		
		// Execute the query and obtain results for first query
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		

		// Output query results	
		ResultSetFormatter.out(System.out, results, query);
		
		// Important - free up resources used running the query
		qe.close();
		
	}
	
}
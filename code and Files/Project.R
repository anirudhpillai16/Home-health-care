library(XML)
library(SPARQL)
result <- xmlTreeParse(file ="healthcare.rdf")
# Exract the root node form the xml file.
rootnode <- xmlRoot(result)
# Find number of nodes in the root.
rootsize <- xmlSize(rootnode)

# Print the result.
print(rootsize)
# Exract the root node form the xml file.
rootnode <- xmlRoot(result)

# Print the result.
print(rootnode[1])
# Converting RDF to DataFrame
xmldataframe <- xmlToDataFrame("healthcare.rdf")
print(xmldataframe)
# Write DataFrame into 
write.csv(xmldataframe,file = "dataset.csv")

 
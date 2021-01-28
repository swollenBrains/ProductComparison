# ProductComparison

Product Comparison can be used to push, pull or bulk import files.

For bulk import, currently the only datasource supported is CSV_FILE.
BulkDataImporter is responsible for reaching out to Providers on the basis of the datasource and uses spring batch to start the import job.

Data -> ItemReader -> ItemProcessor -> ItemWriter

The use of datasource helps make this easy to extend.
##### Examples: 
- Reading from a new datasource say oracle would mean that the DataReaderProvider needs to be injected with a new implementation of ItemReader similar to ProductCsvReader.

- Supporting a new format would mean that we need to inject a new implementation of ItemProcessor and DataProcessorProvider needs to return that on the basis of the format.

- Supporting a new sink to put the data into would mean that we need to inject a new implementation of ProductDbWriter and we need to add a new Provider for writer similar to
 DataReaderProvider or DataProcessorProvider.

### How to run

- mvn clean install

- docker-compose up -d

### Pull products

##### All Products

curl --location --request GET 'localhost:8080/products'

##### Products by name

curl --location --request GET 'localhost:8080/products?name=Dyson%20air%20purifier'

##### Products by category

curl --location --request GET 'localhost:8080/products?category=Home%20Electronics'

##### Products by name and category

curl --location --request GET 'localhost:8080/products?name=Dyson%20air%20purifier&category=Home%20Electronics'

## Push Products

curl --location --request POST 'localhost:8080/products' \
--header 'Content-Type: application/json' \
--data-raw '[
{"name":"iPhone 12 mini", "category": "Mobiles", "price": 150, "merchant": {"type":"WEBSITE", "name":"amazon.com"}}
]'

## Bulk data import

curl --location --request POST 'localhost:8080/products/bulkImport/CSV_FILE'

Reads from productsImport.csv file and puts into mongo.

Make sure you have maven installed
Clone this repository
cd tradeking-api-example; vim src/main/resources/credentials.properties
Enter your api credentials that you need to get from TradeKing. Exit editor (:wq)
mvn clean package exec:java -Dexec.mainClass="Example"
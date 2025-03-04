# tiny-ledger
The Tiny Ledger project is composed of a set of APIs to power a simple ledger with the following features:
* Ability to record money movements (ie: deposits and withdrawals)
* View current balance
* View transaction history

# How to run Tiny Ledger?
## Dependencies
* Java 21
* Maven 3.5+ (built using 3.9.9)

## Start Tiny Ledger Service
Run `./mvnw spring-boot:run` command from the project folder and the application should start on port 8080.

## Testing the API
The API endpoints can be used/tested with the following cURL requests.

### Fetch elements using default query parameters
`curl --location 'http://localhost:8080/ledger/accounts/10/transactions'`
###
`curl --location 'http://localhost:8080/ledger/accounts/10/transactions?startIndex=0&nrOfRecords=40'`

### View Balance
`curl --location 'http://localhost:8080/ledger/accounts/10/balance'`
### Record a transaction
#### Deposit
`curl --location 'http://localhost:8080/ledger/accounts/10/transactions'
--header 'Content-Type: application/json'
--data '{
    "transactionType": "DEPOSIT",
    "amount": "10"
}'`

#### Withdrawals
`curl --location 'http://localhost:8080/ledger/accounts/10/transactions'
--header 'Content-Type: application/json'
--data '{
    "transactionType": "WITHDRAWAL",
    "amount": "10"
}'`

## What's not implemented (yet)
* Unit test coverage
* Integration (controller level) test coverage
* Observability (logging, metrics and tracing)
* Robust request validation/sanitization
* Authz
* Add OAS
* and more!
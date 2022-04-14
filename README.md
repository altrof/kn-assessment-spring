# kn-assessment-spring
KN BE Task for Assessment 

## V1 API Requests

#### *For all POST and PUT requests, Content-Type is `application/json`* 

| Method   | Url                                                | Passed Properties                                                         | Description                                  | Example                                                                               |
|----------|----------------------------------------------------|---------------------------------------------------------------------------|----------------------------------------------|---------------------------------------------------------------------------------------|
| **GET**  | _`/api/v1/customers/`_                             | -                                                                         | Return list of customers                     | -                                                                                     |
| **POST** | _`/api/v1/customers/create`_                       | JSON { "fullName": VARCHAR,  "email": VARCHAR, "phoneNumber": INTEGER }   | Create a new customer with passed properties | { "fullName": "John Smith", "email": "john.smith@mail.com", "phoneNumber": 56123987 } |
| **GET**  | _`/api/v1/products/`_                              | -                                                                         | Return list of products                      | -                                                                                     |
| **POST** | _`/api/v1/products/create/`_                       | JSON {"name": VARCHAR, "price": INTEGER }                                 |                                              |                                                                                       |
| **GET**  | _`/api/v1/orders/`_                                | -                                                                         | Return list of orders                        | -                                                                                     |
| **GET**  | _`/api/v1/orders/search-by-date/{date}`_           | date = VARCHAR                                                            | YYYY-MM-DD                                   | /2022-02-02                                                                           |
| **GET**  | _`/api/v1/orders/search-by-product/{productId}`_   | product = VARCHAR                                                         |                                              |                                                                                       |
| **GET**  | _`/api/v1/orders/search-by-customer/{customerId}`_ | customerId = VARCHAR                                                      |                                              |                                                                                       |
| **GET**  | _`/api/v1/orders/{orderId}`_                       | orderId = INTEGER                                                         |                                              |                                                                                       |
| **POST** | _`/api/v1/orders/create`_                          | JSON { "customerId": VARCHAR, "productId": VARCHAR, "quantity": INTEGER } |                                              |                                                                                       |
| **PUT**  | _`/api/v1/orders/change-item-quantity`_            | JSON {"orderLineId": VARCHAR, "quantity": INTEGER}                        |                                              |                                                                                       |

<br><br>
<div align="center"><img src="./assets/img/postman.png" alt="postman" width="310"/></div>

### Some Example Requests with Postman:

> #### *Get All Customers*
> 
> <img src="./assets/img/screenshot-postman-1.png" alt="screen-postman" width="600" />

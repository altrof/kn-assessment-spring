# kn-assessment-spring
KN BE Task for Assessment 

### V1 API Requests

| Method   | Url                | Passed Properties                                                         | Description          |
|----------|--------------------|---------------------------------------------------------------------------|----------------------|
| **GET**  | _/api/v1/customers/_ | -                                                                         | 
 | **POST** | _/api/v1/customers/create_ | JSON { "fullName": VARCHAR,  "email": VARCHAR, "phoneNumber": INTEGER }   |
| **GET**  | _/api/v1/products/_ | -                                                                         |
| **POST** | _/api/v1/products/create/_ | JSON {"name": VARCHAR, "price": INTEGER }                                 |
 | **GET**  | _/api/v1/orders/_ | -                                                                         |
| **GET**  |_/api/v1/orders/search-by-date/{date}_ | date = VARCHAR                                                            | YYYY-MM-DD (2022-02-02) |
| **GET**  |_/api/v1/orders/search-by-product/{productId}_ | product = VARCHAR                                                         |
| **GET**  |_/api/v1/orders/search-by-customer/{customerId}_ | customerId = VARCHAR                                                      |
| **GET**  |_/api/v1/orders/{orderId}_ | orderId = INTEGER                                                         |                                                             
| **POST** |_/api/v1/orders/create_ | JSON { "customerId": VARCHAR, "productId": VARCHAR, "quantity": INTEGER } |
| **PUT**  | _/api/v1/orders/change-item-quantity_ | JSON {"orderLineId": VARCHAR, "quantity": INTEGER}                        |



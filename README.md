# Patterns for Microservices

### API Composition

OrderInfo apeleaza microserviciile  Book, Inventory, Order.  
Se utilizeaza libraria Open Feign.

- http://localhost:6060/api/v1/books/1
- http://localhost:7070/api/v1/orders/1
- http://localhost:8080/api/v1/inventories/book/1
- http://localhost:9090/api/v1/orderinfo/1

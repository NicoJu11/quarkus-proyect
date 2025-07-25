# api-order-shipping-v1

Este proyecto trata de registar un pedido para despues regitrar la informacion de envio 

Se presentan dos casos 


Caso 1: cuando solo se registra pedido para clientes que lo reciben desde tienda

![Logo del proyecto](tradicional.PNG "caso 1")


curl :


curl --request POST \
  --url http://localhost:9091/v1/purchase-order \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --data '{
  "purchaseOrderId": "8989",
  "clintId": "1",
  "addressShipperId": "1",
  "datePurchaseOrder": "2023-10-01T10:30:00",
  "subtotal": 100.50,
  "iva": 18.09,
  "amountTotal": 118.59,
  "statusOrder": "PENDIENTE",
  "paymentMethod": "TARJETA",
  "shipperId": "S7489",
  "carrier": "DHL",
  "trackingNumber": "TRK123456789",
  "amountShipper": 15.00,
  "statusId": "1",
  "noteShipper": "Handle with care"
}'




Caso 2: cuando se registra pedido y su envio para clientes piden para delivery

![Logo del proyecto](evento.PNG "caso 2")


curl :

curl --request POST \
  --url http://localhost:9098/v1/order-post \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --data '{
  "purchaseOrderId": "GFGG",
  "clintId": "1",
  "addressShipperId": "1",
  "datePurchaseOrder": "2023-10-01T10:30:00",
  "subtotal": 100.50,
  "iva": 18.09,
  "amountTotal": 118.59,
  "statusOrder": "PENDIENTE",
  "paymentMethod": "TARJETA",
  "shipperId": "S7489",
  "carrier": "DHL",
  "trackingNumber": "TRK123456789",
  "amountShipper": 15.00,
  "statusId": "1",
  "noteShipper": "Handle with care"
}'







If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/api-zip-code-v1-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Reactive MySQL client ([guide](https://quarkus.io/guides/reactive-sql-clients)): Connect to the MySQL database using the reactive pattern
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- YAML Configuration ([guide](https://quarkus.io/guides/config-yaml)): Use YAML to configure your Quarkus application


## Errores
- Response message:Non HTTP response message: Can't assign requested address





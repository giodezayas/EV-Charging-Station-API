# EV Charging Station API

La EV Charging Station API es una aplicación para gestionar estaciones de carga de vehículos eléctricos. Proporciona endpoints para crear, leer, actualizar y eliminar estaciones de carga, así como para verificar el estado de disponibilidad de cada estación.

## Características

- Gestión de estaciones de carga de vehículos eléctricos.
- Verificación del estado de disponibilidad de cada estación.
- Documentación de la API con Swagger.

## Requisitos

Antes de ejecutar la API, asegúrate de tener instalado lo siguiente:

- Java 11 o superior
- Maven
- MySQL (u otro sistema de gestión de bases de datos compatible)
- Redis (opcional, para la configuración de caché)

## Configuración
### Configurar la base de datos:

1. Crea una base de datos MySQL llamada evchargingstation.
2. Configura el nombre de usuario y la contraseña en el archivo application.properties.

### Configurar Redis :

Asegúrate de tener un servidor Redis en ejecución.
Configura la conexión al puerto Redis en el archivo application.properties.

### Docker Image Tag :

evcharginstationapiv1.1

### Clonar el repositorio:

```bash
git clone https://github.com/giodezayas/EV-Charging-Station-API.git

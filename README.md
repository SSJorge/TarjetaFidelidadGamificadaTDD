# Sistema de Fidelización de Clientes

Este proyecto es una aplicación de consola en Java que permite gestionar un sistema de fidelización de clientes, registrando compras, sumando puntos, niveles y bonus por frecuencia.

---

## 🧱 Diseño

El sistema está organizado según el estilo de diseño orientado a objetos. Las principales clases son:

- `Cliente`: contiene los datos del cliente y lógica para sumar puntos.
- `Compra`: representa una compra con monto y fecha.
- `ClienteRepository` / `CompraRepository`: gestionan la persistencia en memoria.
- `FidelidadService`: lógica de negocio.
- `App`: clase principal que permite al usuario interactuar desde la consola.



Se usó **JUnit 5** con assertions estándar para cubrir la lógica de `FidelidadService`, incluyendo:

- Suma de puntos según monto.
- Bonus por tercera compra del día.
- Reprocesamiento de puntos.
- Casos de error.

### Ejemplo de salida (VSCode o consola):

## 📊 Cobertura de Código

Se usó **JaCoCo** a través del plugin Maven `jacoco-maven-plugin`. La cobertura medida es:

**Cobertura de instrucciones y ramas (branch + line coverage)**  
✅ Esto permite detectar si se prueban tanto condiciones verdaderas como falsas, además de líneas ejecutadas.

> **¿Por qué?** Porque asegura que todas las rutas posibles en los métodos son ejecutadas al menos una vez.

---

## 🛠️ Instrucciones para compilar y ejecutar

### ✅ Requisitos previos

- Java 21 o superior
- Maven
- VSCode (opcional)

### ⚙️ Compilar

```bash
mvn clean compile

Este proyecto está licenciado bajo la licencia MIT (ver archivo LICENSE).

Consideraciones

La persistencia es en memoria (no se guarda en archivos).

Todos los datos se pierden al cerrar la aplicación.

Se usó programación orientada a objetos limpia, dividiendo entidades, lógica de negocio y persistencia.


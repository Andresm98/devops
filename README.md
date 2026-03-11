# 🚀 DevOps Microservice Project

##  📝 Estado del Proyecto
El despliegue está totalmente automatizado mediante un pipeline de **CI/CD (GitHub Actions)**, garantizando despliegues consistentes y fiables.

* **Calidad de Código:** Integración de **JaCoCo** para métricas de cobertura.
* **Requisito de Calidad:** Se exige una cobertura mínima de pruebas unitarias superior al **80%**.

---

## 🏗️ Arquitectura Técnica
El sistema está diseñado para ser escalable y eficiente en entornos de producción:

* **Framework:** Spring Boot 3.5.11 + Hexagonal Architecture.
* **Orquestación:** K3s corriendo sobre AWS EC2.
* **Escalado:** Implementación de **Horizontal Pod Autoscaler (HPA)** para ajustar la capacidad según la demanda.

---

##  📡 API Documentation

El microservicio expone un endpoint para la gestión de mensajes bajo autenticación estricta.

### Endpoint de Prueba
`POST http://18.118.2.213:31234/DevOps`

#### Cabeceras Requeridas
| Cabecera | Valor |
| :--- | :--- |
| `X-Parse-REST-API-Key` | `2f5ae96c-b558-4c7b-a590-a501ae1c3f6c` |
| `X-JWT-KWY` | `token-unico-123` |
| `Content-Type` | `application/json` |

#### Ejemplo de Petición (cURL)
```bash
curl -v -X POST "http://18.118.2.213:31234/DevOps" \
  -H "X-Parse-REST-API-Key: 2f5ae96c-b558-4c7b-a590-a501ae1c3f6c" \
  -H "X-JWT-KWY: token-unico-3216667" \
  -H "Content-Type: application/json" \
  -d '{ 
        "message" : "Hello World", 
        "to": "Juan Perez", 
        "from": "Rita Asturia", 
        "timeToLifeSec" : 45 
      }'
```
## 🧪 Instrucciones de Prueba

El servicio expone un endpoint `/DevOps` que requiere autenticación mediante headers:

-   `X-Parse-REST-API-Key`
-   `X-JWT-KWY`

# ⚠️ NOTA IMPORTANTE

Para optimizar la entrega del proyecto, se actualizó **solamente** el archivo `README.md` con la información relevante sobre el despliegue, arquitectura y detalles de la API. El código fuente del microservicio no ha sido modificado para mantener la integridad del proyecto original.

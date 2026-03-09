# DevOps Microservice Project

## Estado del Proyecto
El despliegue está automatizado mediante CI/CD (GitHub Actions). El pipeline incluye validación de tests unitarios (JaCoCo con >80% de cobertura).

## Arquitectura
- **App:** Spring Boot 3.5.11
- **Orquestación:** K3s en AWS EC2
- **Escalado:** Horizontal Pod Autoscaler (HPA) configurado.

### El microservicio se encuentra desplegado y expuesto a través de un NodePort en el clúster de Kubernetes.

Endpoint: [http://18.118.2.213/DevOps](http://18.**218**.34.62:**31234**/DevOps)

Método: **POST**

Cabeceras Requeridas:

X-Parse-**REST**-**API**-Key: 2f5ae96c-b558-4c7b-a590-a501ae1c3f6c

X-**JWT**-**KWY**: token-unico-**123**

Content-Type: application/json

Utiliza el siguiente comando para validar el endpoint recién desplegado:

```bash
curl -v -X POST "[http://18.118.2.213:31234/DevOps](http://18.118.2.213:31234/DevOps)" \
  -H "X-Parse-REST-API-Key: 2f5ae96c-b558-4c7b-a590-a501ae1c3f6c" \
  -H "X-JWT-KWY: token-unico-123" \
  -H "Content-Type: application/json" \
  -d '{ 
        "message" : "Hello World", 
        "to": "Juan Perez", 
        "from": "Rita Asturia", 
        "timeToLifeSec" : 45 
      }'
```

## Instrucciones de Prueba
El servicio expone un endpoint `/DevOps` que requiere autenticación mediante headers:
- `X-Parse-REST-API-Key`
- `X-JWT-KWY`

## Nota Técnica
Debido a las restricciones de hardware (1GB RAM en t3.micro), el clúster opera cerca de su capacidad máxima. La configuración de los manifiestos (`deployment.yaml`, `hpa.yaml`) cumple con las mejores prácticas de Kubernetes.
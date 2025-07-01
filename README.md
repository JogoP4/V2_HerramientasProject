[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/JogoP4/V2_HerramientasProject)

#  Release Plan - SistemaGestorDeNotas v2.0

## Contenido

1. [Resumen del Release](#1-resumen-del-release)  
    1.1 [Nombre del release / Versión](#11-nombre-del-release--versión)  
    1.2 [Objetivo general](#12-objetivo-general)  
    1.3 [Fecha de lanzamiento estimada](#13-fecha-de-lanzamiento-estimada)  
    1.4 [Tipo de release](#14-tipo-de-release)  
2. [Contexto del Proyecto](#2-contexto-del-proyecto)  
    2.1 [Sprint(s) involucrados](#21-sprints-involucrados)  
    2.2 [Vinculación con épicas o historias (Jira)](#22-vinculación-con-épicas-o-historias-jira)  
    2.3 [Historias involucradas](#23-historias-involucradas)  
    2.4 [Público destinatario](#24-público-destinatario-usuarios-finales-qa-interno-etc)  
3. [Contenido del Release](#3-contenido-del-release)  
    3.1 [Nuevas funcionalidades](#31-nuevas-funcionalidades)  
    3.2 [Mejoras técnicas](#32-mejoras-técnicas)  
    3.3 [Correcciones de errores](#33-correcciones-de-errores)  
4. [Cronograma](#4-cronograma)  
    4.1 [Fechas clave](#41-fechas-clave)  
    4.2 [Fases del proceso](#42-fases-del-proceso)  
5. [Equipo y Responsabilidades](#5-equipo-y-responsabilidades)  
    5.1 [Roles involucrados](#51-roles-involucrados)  
    5.2 [Aprobaciones requeridas para el release](#52-aprobaciones-requeridas-para-el-release)  
6. [CI/CD y Despliegue](#6-cicd-y-despliegue)  
    6.1 [Repositorio y ramas](#61-repositorio-y-ramas)  
    6.2 [Integración Continua (CI)](#62-integración-continua-ci)  
    6.3 [Docker](#63-docker)  
    6.4 [Despliegue Continuo (CD)](#64-despliegue-continuo-cd)  
7. [Validación y QA](#7-validación-y-qa)  
    7.1 [Estrategia de pruebas](#71-estrategia-de-pruebas)  
    7.2 [Herramientas utilizadas](#72-herramientas-utilizadas)  
    7.3 [Checklist de validación QA](#73-checklist-de-validación-qa)  
    7.4 [Resultado de la validación final](#74-resultado-de-la-validación-final)  

---

## 1. Resumen del Release

### 1.1 Nombre del release / Versión
**SistemaGestorDeNotas v2.0**

### 1.2 Objetivo general
Digitalizar y optimizar la administración de información académica de estudiantes en instituciones educativas con gran trayectoria.

### 1.3 Fecha de lanzamiento estimada
**Julio 01, 2025**

### 1.4 Tipo de release
**Mayor:** Incluye una nueva versión con cambios estructurales y funcionalidades que reemplazan partes importantes del sistema anterior

---

## 2. Contexto del Proyecto

### 2.1 Sprint(s) involucrados
Sprint 2 – del 24 de junio al 1 de julio de 2025

### 2.2 Vinculación con épicas o historias (Jira)
**Épicas involucradas:**
- Gestión de Calificaciones y Notas
- Gestión de Asistencia
- Gestión Académica y Curricular

### 2.3 Historias involucradas:
- Historia de usuario 8: Subir actas de notas.  
- Historia de usuario 9: Registrar asistencia.  
- Historia de usuario 10: Consultar asistencia.  
- Historia de usuario 11: Consultar cursos asignados.  

### 2.4 Público destinatario (usuarios finales, QA interno, etc.)
Equipo de QA interno: Validación de funcionalidades nuevas

---

## 3. Contenido del Release

### 3.1 Nuevas funcionalidades
- Registro de asistencia diaria por parte de docentes.  
- Consulta de historial de asistencia para estudiantes.  
- Subida de actas finales de notas en formato PDF.  
- Visualización de cursos asignados por estudiante.  

### 3.2 Mejoras técnicas
- Refactorización de entidades principales: Curso, Asistencia, Usuario.  
- Mejora en rendimiento de consultas y respuesta de endpoints.  
- Estandarización de la estructura de datos en las respuestas JSON.  

### 3.3 Correcciones de errores
- GES-1 al GES-11: Correcciones operativas, de servicio y de seguridad.

---

## 4. Cronograma

### 4.1 Fechas clave:

| Evento                  | Fecha                  | Descripción                                           |
|-------------------------|------------------------|-------------------------------------------------------|
| Inicio de desarrollo    | 24 de junio de 2025     | Inicio del Sprint 2                                   |
| Cierre de desarrollo    | 28 de junio de 2025     | Finalización de funcionalidades                       |
| Pruebas QA internas     | 28 de junio de 2025     | Validación por el equipo QA                           |
| Corrección de bugs      | 29-30 de junio de 2025  | Análisis y correcciones                               |
| Despliegue a producción | 01 de julio de 2025     | Lanzamiento oficial versión v2.0                      |

### 4.2 Fases del proceso
- **Desarrollo:** Tareas en Jira, PR en GitHub  
- **Integración y Build:** CI con GitHub Actions  
- **Pruebas:** QA interno, pruebas funcionales  
- **Despliegue:** Docker (manual), futuro: Railway  
- **Post-lanzamiento:** Monitoreo, hotfixes si es necesario  

---

## 5. Equipo y Responsabilidades

### 5.1 Roles involucrados:

| Rol                         | Nombre(s)                          | Responsabilidades |
|-----------------------------|------------------------------------|-------------------|
| Scrum Master                | Andrés A. Carrasco Martínez        | Facilita el sprint, organiza reuniones |
| Desarrollador Backend       | Andrés A. Carrasco, Josué G. Palacios | Lógica de negocio y base de datos |
| Desarrollador Fullstack     | Geuseppe E. Lara Garciaurrutia     | Conexión frontend-backend |
| Dev CI/CD                   | Giuseppe Lara, Josué Palacios      | GitHub Actions, Docker, despliegue |
| QA Interno                  | Todo el equipo                     | Validación funcional |
| Responsable del Release     | Andrés A. Carrasco Martínez        | Supervisión y checklist |
| Documentador técnico        | Josué G. Palacios Chero            | Documentación técnica, README |
| Responsable del repositorio | Josué G. Palacios Chero            | Revisión de PRs, políticas, versiones |

### 5.2 Aprobaciones requeridas para el release
-  Revisión de código por al menos 1 compañero  
-  QA aprobado  
-  CI exitoso  

---

## 6. CI/CD y Despliegue

### 6.1 Repositorio y ramas
- **Repositorio GitHub:** https://github.com/JogoP4/V2_HerramientasProject.git  
- **Rama principal:** `main`  
- **Convención de ramas:**
  - `feature/<código-historia>-Iniciales` → Para nuevas funcionalidades  
    _Ej: `feature/S58-AC-RegistrarUsuarios`_  
  - `bugfix/<código-incidente>-Iniciales` → Para correcciones  
    _Ej: `bugfix/GES8-JP-Lista-de-cursos-asignados-incompleta`_  

### 6.2 Integración Continua (CI)
- GitHub Actions con múltiples workflows:
  - Build & Deploy con Maven y Docker  
  - Pruebas automatizadas con contenedor MySQL  
  - Validación de nombres de ramas (`feature/`, `bugfix/`, etc.)  
  - Validación de commits (`feat-`, `fix-`)  
  - Notificaciones automáticas a Discord  

### 6.3 Docker
- Docker para empaquetado de aplicación  
- Imagen generada con `docker/build-push-action@v6`  
- Publicada en DockerHub como:  
  **`holdenandy/lospapus:sistema_gestion_notas`**  
- Login automático a DockerHub y GitHub Container Registry (GHCR)  

### 6.4 Despliegue Continuo (CD)
- **Actual:** Manual con imágenes Docker  
- **Requisitos:** CI exitoso, QA validado, merge aprobado  
- **Futuro:** Integración con Railway (staging primero)  

---

## 7. Validación y QA

### 7.1 Estrategia de pruebas
-  Pruebas unitarias (JUnit + Maven Surefire)  
-  Pruebas de integración (contenedor MySQL)  
-  Pruebas manuales (QA interno)  

### 7.2 Herramientas utilizadas

| Tipo de prueba              | Herramienta / Framework           |
|-----------------------------|-----------------------------------|
| Pruebas unitarias           | JUnit                             |
| Simulación de dependencias | Mockito                           |
| Build y testing             | Maven + Surefire Plugin           |
| CI/CD                       | GitHub Actions                    |
| BD para pruebas             | MySQL 8 (Docker container en CI)  |
| Validación manual           | Revisión cruzada + checklist QA   |

### 7.3 Checklist de validación QA
- Historias marcadas como "Finalizada" en Jira  
- CI verde (tests unitarios y de integración)  
- Validación de endpoints en Postman/manual  
- Funcionalidades probadas con datos reales  
- Validación de errores comunes (PDF inválido, campos vacíos, etc.)  

### 7.4 Resultado de la validación final
 Todos los criterios de aceptación y validación técnica han sido cumplidos.  
 El sistema está listo para ser liberado en la versión **v2.0**.

---

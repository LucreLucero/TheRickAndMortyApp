# Rick and Morty | Lucero Lucrecia

Esta documentación proporciona información sobre las decisiones de diseño y las bibliotecas externas utilizadas en la app 

## Características principales

La aplicación esta basada en la serie Rick and Morty. 
Se espera visualizar todos los personajes de esta serie y la descripción básica de cada uno de ellos.

## Arquitectura

La aplicación sigue la arquitectura MVVM (Model-View-ViewModel) para separar la lógica de negocio de la interfaz de usuario.

## Bibliotecas Externas

Las siguientes bibliotecas externas se utilizaron en la aplicación:

- **Retrofit**: Se utiliza Retrofit para realizar solicitudes HTTP a la API para obtener los datos de los personajes.
  - [Documentación de Retrofit](https://square.github.io/retrofit/)

- **RecyclerView**: Se utiliza RecyclerView para mostrar las listas de personajes.
  - [Documentación de RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)

- **ViewModel**: Se crea un ViewModel para manejar la lógica de negocio y proporcionar los datos a la vista.
  - [Documentación de ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

- **LiveData**: Se utiliza LiveData para notificar a la vista cuando cambian los datos.
  - [Documentación de LiveData](https://developer.android.com/topic/libraries/architecture/livedata)

- **Corrutinas**: Se utilizan corrutinas para realizar operaciones asíncronas, como solicitudes HTTP a traves de consultas al repositorio.
  - [Documentación de Corrutinas](https://developer.android.com/kotlin/coroutines)

- **Inyección de dependencias**: Se utiliza Hilt como librería de inyección de dependencias , para proporcionar las dependencias necesarias a los componentes de la app.
  - [Documentación de Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

- **Glide**: Se utiliza Glide como librería de imágenes para consumir aquellas que vienen del servicio junto con los demás datos.
  - [Documentación de Glide](https://github.com/bumptech/glide)

## SS de la app:
<img width="217" alt="Screenshot 2024-09-15 204705" src="https://github.com/user-attachments/assets/f1a9c172-69f5-442b-a86a-eaf8cb8e422a">

<img width="216" alt="Screenshot 2024-09-15 204726" src="https://github.com/user-attachments/assets/f8080361-c4a2-46c8-98e7-dfedac974ace">
<img width="217" alt="Screenshot 2024-09-15 205239" src="https://github.com/user-attachments/assets/539d766a-e9cb-4370-b879-8e1f0a1eb084">
<img width="217" alt="Screenshot 2024-09-15 205307" src="https://github.com/user-attachments/assets/c944ea42-2653-473f-9edd-864f112093e7">

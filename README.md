# Challenge Mobile Rick and Morty | App Android | Lucero Lucrecia

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

- **RecyclerView**: Se utiliza RecyclerView para mostrar las listas de noticias y usuarios.
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
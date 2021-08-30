<p align="center">MasterJava: DESARROLLO DE APLICACIONES FULL STACK, JAVA EE + ANGULAR JS</p>

## Demo
<p>
Se puede acceder a una demo de la aplicacion a traves del siguiente <a href="http://217.61.97.164:4200/">enlace</a> (Las claves de usuario se proporcionaran junto al email)
</p>

## Sobre la Aplicacion
 <h1>Requerimientos</h1>
 
    JAVA 11
    Mysql 8.0.24
    Maven 4
    Angular 12
    node.js
    
<h1>Enunciado</h1>
<p>
Se desea realizar una aplicación web que se encargue de realizar la gestión de venta de mascotas. El acceso a dicha aplicación podrá ser realizada por un único administrador y por una serie de usuarios.
</p>
<p>
El acceso a la aplicación tanto por el usuario como por el administrador será realizado mediante un username y una contraseña. El username solo admitirá caracteres en minúscula (máximo de 6 caracteres) y la contraseña podrá admitir caracteres mayúsculas y minúsculas así como números, todo entre 6 y 12 caracteres.
</p>
<p>
Una vez logueado correctamente el administrador, le permitirá realizar operaciones como:
</p>
<ul>
  <li>Dar de alta un usuario a la aplicación</li>
  <li>Dar de baja un usuario en la aplicación</li>
  <li>Modificar datos de un usuario</li>
  <li>Listado de los datos de los usuarios a la aplicación.</li>
  <li>Dar de alta una mascota ( id, nombre, tipo, precio)</li>
  <li>Modificar datos de mascota.</li>
</ul>
<p>
De los usuarios de la aplicación utilizaremos como datos:
</p>
<ul>
  <li>Nombre y apellido</li>
  <li>Teléfono de contacto</li>
  <li>Username y contraseña.</li>
</ul>
<p>
La entrada de un usuario en la aplicación consistirá en interaccionar con la aplicación la cual tendrá como finalidad realizar la el proceso de venta de una mascota.
</p>
<p>
Una vez registrado el usuario correctamente, el usuario podrá visualizar el listado de mascotas disponibles visualizando todos los datos de la misma.
</p>
<p>
Una vez elegida la mascota, el usuario podrá realizar las siguientes operaciones:
</p>
<ul>
  <li><strong>Vender la mascota.- </strong>El usuario debe introducir el dni del comprador de la mascota. Si ya se encuentra registrado se procederá a la venta y sino se procederá a registrar al comprador en el sistema. Los datos del registro serán: DNI, nombre (solo caracteres), apellido (solo caracteres), dirección, localidad, correo electrónico ( verificar correo bien escrito) y teléfono de contacto (verificar teléfono bien escrito); todos los campos son obligatorios. La información del comprador será utilizada para posteriormente poder realizar envíos de publicidad.</li>
  <li><strong>Informe por tipo de mascota.- </strong>Para ayudar al usuario en la venta, el usuario dispondrá de esta opción para filtrar el listado de mascotas por su tipo.</li>
  <li><strong>Histórico de ventas. </strong>Además de las ventas realizadas deberá aparecer la suma total de las ventas.</li>
  <li><strong>Cerrar aplicación.</strong></li>
</ul>
<p>
La aplicación será realizada utilizando html y hojas de estilo para su maquetación (podrás valerte de
frameworks como bootstrap) y realizando validaciones síncronas y asíncronas de los datos de los
formularios mediante angular en la parte front-end de todos los formularios de la aplicación.<br>
La parte de back-end será realizada mediante Spring con uso de persistencia de datos para la
manipulación de datos en la base de datos.<br>
Toda la información manipulada será almacenada en la base de datos, siendo realizado previamente el
diseño de la base de datos. Para posibles versiones futuras mantener la aplicación en un repositorio Git.
</p>
<h1>Modelo relacional creado para la aplicación</h1>
<a href="https://lh5.googleusercontent.com/pis3NM7AB0uBDNkSgnrSB-1MQ2k6suRg00j1c90zK7WG11xVU6O00DT4MtfDd30VTczoh-6YRXEhAwkyqGB3=w2560-h1329-rw"><img src="https://lh5.googleusercontent.com/pis3NM7AB0uBDNkSgnrSB-1MQ2k6suRg00j1c90zK7WG11xVU6O00DT4MtfDd30VTczoh-6YRXEhAwkyqGB3=w2560-h1329-rw" style="width: 500px; max-width: 100%; height: auto" title="Click for the larger version." /></a>
<p><strong>UPDATED 28/06/2021</strong> para aumentar el numero de usuarios en un futuro, se ha cambiado el valor de la base de datos de users.username a varchar(255), aunque en la aplicacion esta restringido a 6 caracteres como se pide</p>

<h1>Modelo relacional creado para la aplicación</h1>

<a href="https://drive.google.com/uc?export=view&id=19qvXD0S1zKC7dbGLUzDtSQnZfSnz_7Tv"><img src="https://drive.google.com/uc?export=view&id=19qvXD0S1zKC7dbGLUzDtSQnZfSnz_7Tv" style="width: 500px; max-width: 100%; height: auto" title="Click for the larger version." /></a>

<h1>Instalación y puesta a punto</h1>
<p> Para el correcto funcionamiento de la aplicación es necesario seguir los siguientes pasos:</p>
<h2>Descargar repositorio de GitHub</h2>
<p>Para descargar el repositorio es necesario introducir el siguiente comando en el terminal (teniendo intalado <a href="https://git-scm.com/book/es/v2/Inicio---Sobre-el-Control-de-Versiones-Instalaci%C3%B3n-de-Git">git</a>)</p>

    $ git clone https://github.com/javierfg410/MasterJava.git

<h2>Descargar/instalar las dependencias</h2>
<h3>Back-End</h3>
<p>Crear una base de datos con el nombre de "master" y crear un usuario de la base de datos en "\src\main\resources\application.properties"</p>
<p>Dirigirse al directorio master</p>
<p>Si es la primera instalacion es necesario asegurarse que los archivos \src\main\java\com\java\master\util\CreateAdmin.java y \src\main\java\com\java\master\util\CreateRoles.java estan descomentados</p>
<p>Teniendo descargado Java y maven se puede compilar utilizando dos comandos</p>

    $  ./mvnw spring-boot:run
<p> o tambien </p>

    $  mvn spring-boot:run
    
<p>Si es la primera instalacion es necesario asegurarse que los archivos \src\main\java\com\java\master\util\CreateAdmin.java y \src\main\java\com\java\master\util\CreateRoles.java estan comentados una vez instalado</p>
<h3>Front-End</h3>
<p>Dirigirse al directorio crudMaster</p>
<p>En el caso de no tener instalado nodejs, introducir el siguiente comando</p>

    $  sudo apt install -y nodejs
    
<p>Instalar todas las dependencias</p>

    $  npm install
    
<p>Arrancar un servidor para pruebas</p>

    $  ng serve -o

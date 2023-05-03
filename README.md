# ERIC_LG-M03-Programacio
Avaluació Requeriments Projecte M3-UF3-UF4-UF5

# Introducció
Aquest projecte s'enfoca a implementar una aplicació bancària amb múltiples funcionalitats. L'aplicació permetrà als clients del banc iniciar sessió en un caixer a través de la inserció del vostre identificador d'usuari i contrasenya. Si la informació és correcta, l'aplicació mostrarà el menú d'opcions; si no, l'usuari rebrà un missatge d'error i no podrà operar. En cas que l'usuari no encerti la contrasenya en un nombre determinat d'intents, se'n bloquejarà l'accés.

Els clients del banc poden tenir un o més comptes de dos tipus: corrents i d'estalvi. Els comptes destalvi tindran un tipus dinterès més elevat. Els comptes corrents poden tenir una targeta associada.

El caixer permetrà als clients realitzar diferents operacions com consultar el saldo, retirar o ingressar diners en un compte, fer transferències només entre comptes corrents, canviar la contrasenya del caixer i fer logout. A més, s'han implementat tres funcionalitats diferents diferents que es consideren útils.

Els moviments de cada compte podran ser consultats pel client, incloent-hi informació sobre el tipus de moviment, data, quantitat de diners involucrats, etc. El caixer només opera amb bitllets i té una quantitat determinada de cadascun. Les operacions de retir i ingrés de diners afectaran la quantitat de diners emmagatzemats al caixer. En cas d'operacions de retir, cal comprovar que l'operació sigui possible segons la quantitat de bitllets disponibles. Cal informar el client sobre les quantitats de bitllets lliurades a l'operació. Per a les operacions d'ingrés, podeu utilitzar un teclat amb botons de diferents quantitats per indicar la quantitat dipositada.

L'aplicació s'ha implementat a Java i s'ha utilitzat JavaFX per a la interfície gràfica d'usuari. L'estructura del programa segueix l'arquitectura MVC i el model està estructurat a classe. S'ha fet un Diagrama UML de classes del model. També s'ha implementat la gestió d'errors i les excepcions i el codi està documentat amb notació Javadoc. S'han utilitzat col·leccions d'objectes (ArrayList o equivalents) per gestionar les llistes d'objectes en memòria. Les dades de l'operativa del programa s'emmagatzemen en fitxers de text, i s'han triat quines dades es volen emmagatzemar en almenys dues col·leccions/arxius per carregar-les en memòria i així inicialitzar el model construït. A més, s'ha utilitzat JDBC per treballar amb una base de dades SQL, cosa que permet accedir i manipular les dades emmagatzemades a la base de dades de manera eficient. En general, s'ha implementat una aplicació bancària completa i segura que compleix els requisits tècnics especificats.

# Explicació de les diferents funcionalitats
### AlmacenarUsuario:
Durant l'elaboració del treball he tingut la necessitat de tenir una variable global que guardes la ID de l'usuari que havia iniciat sessió, per fer-ho, he hagut de crear aquesta classe que només em serveix per tenir aquesta ID guardada i poder utilitzar-la a qualsevol lloc de l'aplicació.

### BizumController:
Aquest és el codi del controlador de la vista "Bizum". Aquesta vista permet moure diners d'un compte a un altre. El codi fa servir JavaFX per crear elements gràfics com TextField i ChoiceBox.
- l mètode 'initialize()' s'executa quan es carrega la vista i inicialitza els ComboBox amb les opcions de comptes i usuaris disponibles.
- El mètode 'moureDiners()' és cridat quan es prem el botó per moure diners. El mètode obté el compte origen i la quantitat a moure a partir dels elements gràfics i actualitza les dades de la base de dades per transferir els diners del compte origen al compte destí. Finalment, registra el moviment al registre de moviments cridant el mètode 'registraMoviment()'.
- El mètode 'registraMoviment()' insereix un registre a la taula de moviments amb les dades dels comptes implicats i la quantitat transferida.

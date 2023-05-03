# ERIC_LG-M03-Programacio
Avaluació Requeriments Projecte M3-UF3-UF4-UF5

# Introducció
Aquest projecte s'enfoca a implementar una aplicació bancària amb múltiples funcionalitats. L'aplicació permetrà als clients del banc iniciar sessió en un caixer a través de la inserció del vostre identificador d'usuari i contrasenya. Si la informació és correcta, l'aplicació mostrarà el menú d'opcions; si no, l'usuari rebrà un missatge d'error i no podrà operar. En cas que l'usuari no encerti la contrasenya en un nombre determinat d'intents, se'n bloquejarà l'accés.

Els clients del banc poden tenir un o més comptes de dos tipus: corrents i d'estalvi. Els comptes destalvi tindran un tipus dinterès més elevat. Els comptes corrents poden tenir una targeta associada.

El caixer permetrà als clients realitzar diferents operacions com consultar el saldo, retirar o ingressar diners en un compte, fer transferències només entre comptes corrents, canviar la contrasenya del caixer i fer logout. A més, s'han implementat tres funcionalitats diferents diferents que es consideren útils.

Els moviments de cada compte podran ser consultats pel client, incloent-hi informació sobre el tipus de moviment, data, quantitat de diners involucrats, etc. El caixer només opera amb bitllets i té una quantitat determinada de cadascun. Les operacions de retir i ingrés de diners afectaran la quantitat de diners emmagatzemats al caixer. En cas d'operacions de retir, cal comprovar que l'operació sigui possible segons la quantitat de bitllets disponibles. Cal informar el client sobre les quantitats de bitllets lliurades a l'operació. Per a les operacions d'ingrés, podeu utilitzar un teclat amb botons de diferents quantitats per indicar la quantitat dipositada.

L'aplicació s'ha implementat a Java i s'ha utilitzat JavaFX per a la interfície gràfica d'usuari. L'estructura del programa segueix l'arquitectura MVC i el model està estructurat a classe. S'ha fet un Diagrama UML de classes del model. També s'ha implementat la gestió d'errors i les excepcions i el codi està documentat amb notació Javadoc. S'han utilitzat col·leccions d'objectes (ArrayList o equivalents) per gestionar les llistes d'objectes en memòria. Les dades de l'operativa del programa s'emmagatzemen en fitxers de text, i s'han triat quines dades es volen emmagatzemar en almenys dues col·leccions/arxius per carregar-les en memòria i així inicialitzar el model construït. A més, s'ha utilitzat JDBC per treballar amb una base de dades SQL, cosa que permet accedir i manipular les dades emmagatzemades a la base de dades de manera eficient. En general, s'ha implementat una aplicació bancària completa i segura que compleix els requisits tècnics especificats.

# Diagrama de classes


# Explicació de les diferents funcionalitats
### AlmacenarUsuario:
Durant l'elaboració del treball he tingut la necessitat de tenir una variable global que guardes la ID de l'usuari que havia iniciat sessió, per fer-ho, he hagut de crear aquesta classe que només em serveix per tenir aquesta ID guardada i poder utilitzar-la a qualsevol lloc de l'aplicació.

### BizumController:
Aquest és el codi del controlador de la vista "Bizum". Aquesta vista permet moure diners d'un compte a un altre. El codi fa servir JavaFX per crear elements gràfics com TextField i ChoiceBox.
- El mètode 'initialize()' s'executa quan es carrega la vista i inicialitza els ComboBox amb les opcions de comptes i usuaris disponibles.
- El mètode 'moureDiners()' és cridat quan es prem el botó per moure diners. El mètode obté el compte origen i la quantitat a moure a partir dels elements gràfics i actualitza les dades de la base de dades per transferir els diners del compte origen al compte destí. Finalment, registra el moviment al registre de moviments cridant el mètode 'registraMoviment()'.
- El mètode 'registraMoviment()' insereix un registre a la taula de moviments amb les dades dels comptes implicats i la quantitat transferida.

### ClauController
Aquesta classe gestiona la pàgina de canvi de clau d'accés per a l'usuari del sistema de caixers automàtics.

La classe té diversos atributs de tipus TextField i Label que representen els diferents elements de la pàgina, així com l'objecte Connection que representa la connexió a la base de dades.

La classe té diferents mètodes:
- El mètode switchToMenu() és cridat quan l'usuari prem el botó per tornar al menú principal. Aquest mètode simplement crida al mètode setRoot() de la classe App per canviar la finestra a la pàgina del menú principal.
- El mètode comprovarClaus() és cridat quan l'usuari prem el botó per actualitzar la clau d'accés. Aquest mètode primerament consulta la base de dades per obtenir la contrasenya actual de l'usuari, i després comprova si les claus d'accés introduïdes per l'usuari coincideixen i si la contrasenya actual també coincideix amb la clau d'accés introduïda. Si les claus coincideixen i la contrasenya també, actualitza la contrasenya a la nova clau introduïda per l'usuari. Si no, mostra un missatge d'error a l'etiqueta errorLabel.

### ConnectionClass
Aquest codi és una classe anomenada ConnectionClass que té un atribut Connection anomenat connection. Aquesta classe té un mètode anomenat getConnection que retorna un objecte Connection i que s'encarrega de connectar-se a una base de dades MySQL local.

El mètode getConnection comença creant tres variables locals de tipus String per al nom de la base de dades (dbName), el nom d'usuari (userName) i la contrasenya (password). Després, intenta carregar el controlador JDBC per a MySQL amb la crida a Class.forName('com.mysql.jdbc.Driver'). Aquesta crida és necessària per carregar el controlador JDBC a la memòria del sistema.

Després de carregar el controlador, el mètode getConnection crea un objecte Connection fent servir la funció DriverManager.getConnection() i passant la URL de connexió que inclou el nom de la base de dades, el nom d'usuari i la contrasenya. Aquesta funció retorna un objecte Connection que és assignat a l'atribut connection.

Finalment, si hi ha hagut algun error durant la connexió, el mètode imprimeix el missatge d'error a la consola a través de la crida a e.printStackTrace(). En cas contrari, el mètode retorna l'objecte Connection creat.

Aquesta classe és útil perquè encapsula la lògica de connexió a la base de dades i permet reutilitzar aquesta lògica en altres parts del programa sense haver de copiar-la i enganxar-la. Això també fa que el codi sigui més fàcil de mantenir ja que si cal fer un canvi en la forma de connectar-se a la base de dades, només cal fer el canvi en un lloc.

### ConsultarController
La classe ConsultarController és el controlador per a la finestra de consulta del sistema de caixers automàtics. Aquesta finestra mostra el saldo total dels comptes de l'usuari, permet escollir un compte i mostra les targetes associades a aquest compte si n'hi ha alguna.

La classe conté diversos mètodes que s'utilitzen per a mostrar la informació al usuari i gestionar les interaccions amb el sistema. A continuació es descriuen els mètodes de la classe:
- initialize: Aquest mètode s'executa quan es carrega la finestra i inicialitza la visualització del saldo total dels comptes de l'usuari i carrega els comptes disponibles.
- cargarTargetes: Aquest mètode s'executa quan l'usuari selecciona un compte i carrega les targetes associades a aquest compte. Si no s'ha seleccionat cap compte, es mostra un missatge d'error.
- switchToMenu: Aquest mètode s'executa quan l'usuari prem el botó per a tornar al menú principal i canvia la finestra actual per la finestra del menú principal.

La classe conté també diversos atributs:
- saldoTotal: L'etiqueta on es mostra el saldo total dels comptes de l'usuari.
- compteEscollit: El desplegable on es pot seleccionar un compte.
- targetaAss: El desplegable on es pot seleccionar una targeta.
- connectionClass: La classe de connexió a la base de dades.
- connection: La connexió a la base de dades.

### FacturesController
La classe FacturesController conté diversos mètodes que es relacionen amb el pagament de factures i la gestió de moviments. Els mètodes són:
- switchToMenu: aquest mètode és cridat quan es prem el botó "Menu" i fa que la finestra passi a mostrar la vista "menu".
- initialize: aquest mètode s'executa quan la vista és carregada per primera vegada i inicialitza els valors de les caixes de selecció amb les opcions disponibles de comptes i factures.
- obtenerValoresCompte: aquest mètode extreu els valors d'un compte a partir del text que s'ha seleccionat a la caixa de selecció i els retorna com una instància de la classe Pair.
- extraerValoresDeFactura: aquest mètode extreu els valors d'una factura a partir del text que s'ha seleccionat a la caixa de selecció i els retorna com un vector de tipus double.
- pagarFactura: aquest mètode és cridat quan es prem el botó "Pagar factura". Primer, obté els valors del compte i de la factura seleccionats a les caixes de selecció. A continuació, actualitza la base de dades amb el pagament de la factura i registra el moviment corresponent al compte. Si la resta de saldo és negativa, mostra un missatge d'error.
- registraMoviment: aquest mètode registra un moviment a la base de dades amb les dades del compte i el preu de la factura que s'han passat per paràmetre.

### IngresarController
Aquest és un codi per a una aplicació que permet als usuaris fer un ingrés de diners en un dels seus comptes bancaris. El codi té diversos mètodes que realitzen diferents funcions, que es detallen a continuació:
- initialize(): Aquest mètode s'executa quan la finestra de l'aplicació es carrega per primera vegada. Obre una connexió a la base de dades i executa una consulta per obtenir els comptes bancaris de l'usuari actual, que es mostren en un 'ChoiceBox'. Cada element del 'ChoiceBox' conté l'ID del compte i el seu saldo actual.
- ingresarDiners(): Aquest mètode s'executa quan l'usuari fa clic al botó per fer un ingrés de diners. Obté el compte bancari seleccionat pel 'ChoiceBox', obté la quantitat de diners a ingressar a partir del contingut del 'TextField' corresponent i actualitza el saldo del compte bancari a la base de dades amb aquesta quantitat. Aquest mètode també crida el mètode 'registraMoviment()' per registrar el moviment a la base de dades.
- registraMoviment(): Aquest mètode registra el moviment de l'usuari a la base de dades. Crea una data actual formatada en el format "yyyy-MM-dd" i fa una consulta per afegir una nova fila a la taula de moviments. La nova fila conté el tipus de moviment (Ingrés), la data actual, la quantitat de diners que s'han ingressat i l'ID del compte bancari corresponent.
- calcularDiners(): Aquest mètode s'executa quan l'usuari fa clic al botó per calcular la quantitat de diners a ingressar en funció del nombre de bitllets de diferents denominacions que es volen ingressar. Obté el contingut dels 'TextFields' corresponents per a cada denominació i calcula la quantitat total de diners a partir d'aquestes dades. El resultat es mostra en el 'TextField' corresponent.
- actualitzarInventari(): Aquest mètode actualitza la taula d'inventari de bitllets després que l'usuari hagi ingressat diners en efectiu. Obté la connexió a la base de dades i fa una consulta SQL que actualitza la quantitat de cada denominació de bitllet en la taula d'inventari. Aquesta actualització es realitza sumant les quantitats anteriors amb les quantitats noves.

### LoginController
La classe LoginController és un controlador per a la finestra d'inici de sessió d'una aplicació. Es defineixen tres camps amb anotacions @FXML per fer referència als elements gràfics de la finestra: dos camps TextField per al nom i la contrasenya d'usuari, i un Label per mostrar missatges d'error. També hi ha un comptador d'errors (contErrors) que s'incrementa cada vegada que s'intenta fer login sense èxit.

El mètode button s'executa quan l'usuari prem el botó d'inici de sessió. Primer, es comprova si s'han superat els tres intents d'inici de sessió incorrectes (contErrors). Si no és així, es crea una connexió a la base de dades i s'executa una consulta per obtenir tots els usuaris. A continuació, es comprova si les credencials d'usuari introduïdes són correctes comparant-les amb les dades d'usuari obtingudes de la base de dades. Si les credencials són correctes, es canvia la finestra actual per la finestra principal (Menu) i es guarda l'ID de l'usuari autenticat en una classe auxiliar (AlmacenarUsuario). Si les credencials no són correctes, es mostra un missatge d'error i s'incrementa el comptador d'errors.

Finalment, hi ha un mètode switchToMenu que canvia la finestra actual per la finestra principal (Menu). Això es fa perquè és un procés que es repeteix diverses vegades al llarg del codi.

### LogoutController
Aquest codi implementa la funcionalitat de logout en una aplicació. Quan l'usuari fa clic en el botó "logout", la funció 'switchToMenu()' s'executa i crida a la funció 'setRoot()' de l'objecte 'App' per canviar la pantalla actual a la pantalla del menú principal. A més, també actualitza la variable 'usuari' de la classe 'AlmacenarUsuario' a 0, indicant que no hi ha cap usuari autenticat actualment. Això impedeix que l'usuari pugui accedir a funcionalitats de l'aplicació que requereixen autenticació fins que no torni a iniciar sessió.

### MenuController
Aquest codi defineix un controlador per a la vista del menú principal de l'aplicació bancària. La classe proporciona diversos mètodes per a canviar la vista que es mostra en resposta a la interacció de l'usuari amb els botons del menú. Per exemple, el mètode switchToTransferencia canvia la vista actual per la vista de transferència, el mètode switchToRetirar canvia la vista actual per la vista de retirada de diners, i així successivament per a les altres opcions del menú. Cada mètode utilitza el mètode setRoot de la classe App per a canviar la vista.

### NouCompteController
La classe NouCompteController, que controla la vista de creació de nous comptes. La vista té un ChoiceBox per seleccionar el tipus de compte (estalvi o corrent) i un TextField per especificar la quantitat a ingressar. La classe també conté una connexió a la base de dades.

La funció initialize() s'executa quan la vista és carregada i afegeix les opcions "Estalvi" i "Corrent" al ChoiceBox, amb "Estalvi" com a valor per defecte.

La funció afegirCompte() s'executa quan l'usuari fa clic al botó per afegir el compte. La funció obté la quantitat a ingressar i el tipus de compte seleccionat, i després busca el màxim identificador de compte existent a la base de dades per a poder assignar-li un nou identificador.

Després, la funció insereix un nou registre a la taula de comptes amb l'identificador, el tipus de compte, la quantitat ingressada i l'identificador de l'usuari que està fent la creació. Si s'ha inserit correctament, es tanquen els Statements i ResultSet utilitzats. Si es produeix una excepció, aquesta és capturada i manejada. Finalment, l'usuari és redirigit a la vista del menú principal.

### RetirarController
Aquest codi defineix el comportament de la vista "Retirar" en una aplicació bancària. Inclou mètodes per inicialitzar la vista, retirar diners d'un compte seleccionat, calcular la quantitat de diners a retirar en funció del nombre de bitllets introduïts per l'usuari i actualitzar l'inventari de bitllets al banc.

La vista inclou un quadre d'elecció per seleccionar el compte del qual es retirarà, camps de text per introduir el nombre de bitllets de cada denominació i un botó per iniciar la retirada.

Trobem diferents mètodes com:
- El mètode initialize() omple el quadre d'elecció amb els comptes associats a l'usuari connectat actualment.
- El mètode retirarDiners() recupera el compte seleccionat i l'import a retirar de la vista, actualitza el saldo del compte a la base de dades i registra la transacció a la taula de moviment. També crida al mètode actualitzarInventari() per actualitzar l'inventari de bitllets del banc.
- El mètode calcularDiners() calcula la quantitat total de diners a retirar en funció del nombre de factures introduïdes per l'usuari.
- El mètode actualitzarInventari() actualitza l'inventari de bitllets del banc en funció del nombre de bitllets introduïts per l'usuari.

### TransferenciaController
El controlador TransferenciaController s’utilitza en la finestra d'aplicació que permet transferir diners d'un compte a un altre compte. La finestra té dos camps desplegables ChoiceBox, un per seleccionar el compte d'origen i l'altre per seleccionar el compte de destí. Hi ha també un camp de text per especificar la quantitat de diners que es vol transferir.

La funció initialize() estableix els valors dels dos ChoiceBox amb les opcions de compte disponibles per a l'usuari. Les opcions del ChoiceBox són cadenes de text que mostren el número de compte i el saldo actual.

La funció moureDiners() s'executa quan es prem el botó de "Transferir". Obté els valors dels dos ChoiceBox i la quantitat a transferir des dels camps de text. Utilitza expressions regulars per extreure el número de compte de les cadenes de text seleccionades pels ChoiceBox i realitza la transferència de diners mitjançant la modificació de la base de dades. També crida la funció registraMoviment() per registrar la transferència com un moviment financer.

La funció registraMoviment() registra la transferència com a moviment financer a la base de dades. Utilitza la data actual, el número de compte d'origen, el número de compte de destí i la quantitat transferida com a dades d'entrada per a la inserció del registre en la taula "moviment".

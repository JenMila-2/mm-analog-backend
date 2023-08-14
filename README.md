# mm-analog.

_mm-analog._ is een webapplicatie voor analoge fotografie. Binnen de applicatie kunnen fotografen film stock voorraden, fotoprojecten en film ontwikkel logs bijhouden. De applicatie is ontwikkeld als eindopdracht voor de Bootcamp Full Stack Developer van NOVI Hogeschool en bestaat uit verschillende onderdelen waaronder een frontend en een backend. Voor de eindopdracht zijn ook een functioneel ontwerp, een technisch ontwerp, installatiehandleiding en verantwoordingsdocument opgesteld. Zowel de frontend als de backend broncode zijn geschreven in het Engels. De documenten zijn geschreven in het Nederlands.

De frontend broncode kan bezocht worden via onderstaande link:

[mm-analog-frontend](https://github.com/JenMila-2/mm-analog-frontend)

De repository waar we ons nu bevinden is bedoeld voor de backend broncode.

### Downloaden backend broncode

Om de backend broncode te downloaden kan de ZIP-file onder de blauwe knop **Code** gedownload worden. 

##### De broncode kan ook gekloond worden via onderstaande links:

SHH: `git@github.com:JenMila-2/mm-analog-backend.git `

HTTPS:` https://github.com/JenMila-2/mm-analog-backend.git`

Voor de ontwikkeling van de backend is gebruikgemaakt van het Java Spring Boot framework met Maven als dependency manager.

De backend broncode bestaat uit de volgende entiteiten:
* Authority
* User
* Project Folder
* Photo Log
* Film Stock Inventory
* Film Development Log
* File Upload

Een overzicht van de mappenstructuur is te vinden in de bijlage van de installatiehandleiding. 

### API REST Endpoints

Voor de eindopdracht zijn verschillende endpoints gecreëerd die in de backend getest kunnen worden met Postman. Een overzicht van deze endpoints is te vinden in de bijlage van de installatiehandleiding. In de installatiehandleiding is ook een link te vinden naar de Postman collectie waarmee de endpoints getest kunnen worden. Tot slot is er een JSON-file met alle endpoints toegevoegd aan de documentatie.

**Let op:** tijdens het testen in Postman is het belangrijk dat er een token wordt meegegeven in het Authorization tabje. Hoe dit precies werkt is te lezen in de installatiehandleiding. 

### Installatie

In de installatiehandleiding wordt stap voor stap uitgelegd hoe de backend geïnstalleerd kan worden en welke dependencies er nodig zijn. Om de backend te kunnen runnen is het belangrijk om eerst een aantal instellingen in de application.properties file aan te passen. Hier staan specifieke gegevens in die aangepast dienen te worden naar eigen gegevens.

### Gebruikersinstellingen

Om de werking van de applicatie te tonen zijn dummy gebruikers opgesteld en is in de backend een data.sql file gevuld met voorbeeld data. Om zelf te ontdekken hoe dit werkt kan ingelogd worden met een van de twee dummy accounts.

**Admin2**

**User2**

De juiste inloggegevens zijn te vinden in de installatiehandleiding. 

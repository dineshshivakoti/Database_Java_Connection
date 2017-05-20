# Database_Java_Connection
This project connects to a MySQL database from Java using MySQL Driver.

It has for main classes.

Database Class >  This class displays records and execute queries. For this it uses Connector to execute queries.

Connector Class > This class loads the drivers to connect to the database.

ConnectionDialog class > This class prompts for user informations where it requires authentication to DB. Connector will use the information passed from ConnectionDialog class and connect to DB.

MainConnection class > This class connects everything together.It also launches JFrame

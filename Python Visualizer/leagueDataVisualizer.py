import mysql.connector

cnx = mysql.connector.connect(user='root', password='M7ec27cibqw8kg!', host='127.0.0.1')
cursor = cnx.cursor()
cursor.execute("SELECT * FROM leaguedata.leaguedata_scores;")
result = cursor.fetchall()

for x in result:
    print(x)


cnx.close()

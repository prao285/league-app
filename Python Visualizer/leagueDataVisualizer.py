import mysql.connector
import numpy as np
import matplotlib.pyplot as plt

cnx = mysql.connector.connect(user='root', password='M7ec27cibqw8kg!', host='127.0.0.1')
cursor = cnx.cursor()
cursor.execute("SELECT scoreValue FROM leaguedata.leaguedata_scores;")
result = cursor.fetchall()
scores = [i[0] for i in result]
scores.reverse()
print(scores)
plt.plot(scores)
plt.show()


cnx.close()

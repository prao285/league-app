import mysql.connector
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.patches as mpatches

cnx = mysql.connector.connect(user='root', password='M7ec27cibqw8kg!', host='127.0.0.1')
cursor = cnx.cursor()
cursor.execute("SELECT * FROM leaguedata.leaguedata_scores;")
result = cursor.fetchall()

class match:
    def __init__(self, matchID, score, isWinner):
        self.matchID = matchID
        self.score = score
        self.isWinner = isWinner

matches = [match(i[0], i[1], i[2]) for i in result]
matches.reverse()

scores = np.array([match.score for match in matches])
wins = np.array([match.isWinner for match in matches])
colormap = np.array(['r', 'g'])
data = plt.scatter(range(0, len(matches)), scores, s = 100, c = colormap[wins])
plt.plot(scores)
plt.title("Summoner Data thePraoster69")
win = mpatches.Patch(color='g', label='Win')
loss = mpatches.Patch(color='r', label='Loss')
plt.legend(handles=[win,loss])
plt.xlabel("Games")
plt.ylabel("Score")

plt.show()
cnx.close()

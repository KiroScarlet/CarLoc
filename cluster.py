import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
import random
from numpy import random
from sklearn.datasets import load_iris
from sklearn.datasets import make_blobs
# iris = load_iris()
# print (iris.keys)
data = np.array([0,0])
with open(r'C:\Users\liyan\Desktop\point.txt') as f:
    for line in f:
        x = float(line[1:10])
        y = float(line[11:21])
        point = [x,y]
        data = np.vstack((data,point))
# print (data)
kmeans = KMeans(n_clusters=10, random_state=0).fit(data)
x_data = []
y_data = []
for i in data:
    x_data.append(i[0])
    y_data.append(i[1])
# random_list =[]
# for i in range(500):
#     random_list.append(random.randint(1,1000))
# print (random_list)
# for i in range(len(data)):
#     if i not in random_list:
#         np.delete(data,i,0)
print(kmeans.inertia_)
result = kmeans.fit_predict(data)
for i in range(len(result)):
    if result[i] == 0:
        plt.scatter(data[i,0],data[i,1],color = 'red')
    if result[i] == 1:
        plt.scatter(data[i, 0], data[i, 1], color='blue')
    if result[i] == 2:
        plt.scatter(data[i, 0], data[i, 1], color='green')
    if result[i] == 3:
        plt.scatter(data[i, 0], data[i, 1], color='cyan')
    if result[i] == 4:
        plt.scatter(data[i, 0], data[i, 1], color='black')
    if result[i] == 5:
        plt.scatter(data[i, 0], data[i, 1], color='magenta')
    if result[i] == 6:
        plt.scatter(data[i, 0], data[i, 1], color='orange')
    if result[i] == 7:
        plt.scatter(data[i, 0], data[i, 1], color='yellow')
    if result[i] == 8:
        plt.scatter(data[i, 0], data[i, 1], color='coral')
    if result[i] == 9:
        plt.scatter(data[i, 0], data[i, 1], color='purple')

plt.show()
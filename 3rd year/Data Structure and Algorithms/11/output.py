import matplotlib.pyplot as plt

f = open('result.txt', 'r')

x = []
times = []
heights = []
for i in xrange(10 ** 6):
    i, time, height = map(int, f.readline().split(" "))
    x.append(i)
    times.append(time)
    heights.apeend(height)

# plt.plot(X, C)
# plt.plot(X, S)
plt.plot(x, times)
plt.plot(x, heights)
plt.show()

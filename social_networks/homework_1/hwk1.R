### Homework 1 

## Some visualizations of the Dining data

# ---- C1 ----
# Load packages

library("ggplot2")
# Must load other packages first
library("sand")

# ---- C2 ----
# Load data

setwd("~/Documents/Craft-Repo/social_networks/homework_1")
dining <- read.graph("dining.net", format="pajek")
summary(dining)
is.directed(dining)
is.simple(dining)

# ---- C3 ----
# Student names
V(dining)$id

# ---- C4 ----
# Plot network
# Adjust the edge arrow size for less ugliness
plot(dining, vertex.label=V(dining)$id, vertex.size = 5, edge.arrow.size= .25)


# ---- C5 ----
# In-degree
degree.in = degree(dining, mode = 'in')
summary(degree.in)

# ---- C6 ----
# Degree distribution histogram
hist(degree.in)


# ---- C7 ----
# GGPlot version (optional)
g = ggplot(data.frame(In_Degree = degree.in), aes(x = In_Degree))
g = g + geom_histogram(binwidth = 1, col = "white")
print(g)


# ---- C8 ----
# Calculate wt attribute
E(dining)$weight
wt = E(dining)$weight
wt = ifelse(wt == 1,1,.5)
wt

# ---- C9 ----
# Compute weighted degree
g.weighted = graph.strength(dining, vids = V(dining), mode = "in", weights = wt, loops = FALSE)
summary(g.weighted)

# ---- C10 ----
# Visualization
g = ggplot(data.frame(Weighed_Degree = g.weighted), aes(x = Weighed_Degree))
g = g + geom_histogram(binwidth = .5, col = "white")
print(g)

# ---- Extra ----
# Visualization with weighted degree and weighted edges
# we created label weightings, by using the weighted degree
# above, adding 1 to get ride of 0s and then dividing by two
# this created visually appealing results
V(dining)$label.cex = (g.weighted + 1)/2
plot(dining, vertex.label=V(dining)$id, vertex.size = g.weighted, edge.arrow.size= .25, edge.width = wt)


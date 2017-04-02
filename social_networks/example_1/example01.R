## Example 1
## example01.R
## See also example01.Rmd
## In-class example for CSC 495, Prof. Robin Burke
## 3/28/2017

# ---- C1 ----
# Load packages

library("ggplot2")
# Must load other packages first
library("sand")

# ---- C2 ----
# Load the data.

setwd("~/Documents/Craft-Repo/social_networks/example_1")
dolphin <- read.graph("dolphin2.graphml", format="graphml")

# ---- C3 ----
# Display basic information about the graph
# Summary
summary(dolphin)

# Is it directed? (use functions with _ rather than .)
is.directed(dolphin)

# Is it simple?
is.simple(dolphin)

# Count nodes
vcount(dolphin)

# Count edges
ecount(dolphin)

# ---- C4 ----
# List graph elements
# Nodes
V(dolphin)
V(dolphin)$label

# Edges
E(dolphin)


# ---- C5 ----
# Histogram of sex distribution
hist(V(dolphin)$Sex)

# ---- C6 ----
# GGPlot version
# Note that node attributes cannot be factors
sex = factor(V(dolphin)$Sex, c(0,1,2), labels = c("Female", "Male", "Unknown"))
g = ggplot(data.frame(Sex = sex), aes(x = Sex))
g = g + geom_histogram()
print(g)

# ---- C7 ----
# Create a network visualization. Note that the layout has a random element, so your visualization will not look exactly the same.
# Make the male nodes blue, female nodes pink, and unknown grey
dolphin.col = rep("pink", vcount(dolphin))
dolphin.col[V(dolphin)$Sex == 1] = 'lightblue'
dolphin.col[V(dolphin)$Sex == 2] = 'grey'
plot(dolphin, vertex.color = dolphin.col)  

# ---- C8 ----
# Degree distribution
# Compute degrees of each node (degree sequence)
deg = degree(dolphin)

# ---- C9 ----
# Histogram with ggplot (boundary, binwidth)
g = ggplot(data.frame(Deg = deg), aes(x = Deg))
g = g + geom_histogram(binwidth = 1, col = "white")
print(g)

# ---- C10 ----
# Degree distribution of male and female dolphins.

# Set up the data
dol.df = data.frame(Degree = deg, Sex = V(dolphin)$Sex)

# First make a data frame with the degree and sex. Then remove the "Unknown" dolphins
dol.df = dol.df[dol.df$Sex != 2,]

# Then remake the sex factor so that the "Unknown" option is omitted.
dol.df$Sex = factor(dol.df$Sex, c(1,2), labels = c("Female", "Male"))

# ---- C11 ----
# GGPlot boxplot
g = ggplot(dol.df, aes(x = Sex, y = Degree)) + geom_boxplot()
print(g)
